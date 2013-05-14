package be.khleuven.recordaid.mvc;

import be.khleuven.recordaid.domain.DomainException;
import be.khleuven.recordaid.domain.items.*; 
import be.khleuven.recordaid.util.propertyeditors.CalendarPropertyEditor;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/items")
public class ItemController extends AbstractController{
    private String[] kleuren = new String[]{
        "#F00", 
        "#0F0", 
        "#00F", 
        "#FF0",
        "#0FF",
        "#F0F"
    };
    
    @RequestMapping(value={"/","/beheer"},method=RequestMethod.GET)
    public String listItems(ModelMap model)
    {
        Collection<Item> items = domainFacade.getItems();
        model.addAttribute("items", items);
        model.addAttribute("newItem", new Item());
        return "/items/beheer";
    }
    
    @RequestMapping(value="/verwijder",method=RequestMethod.GET)
    public String deleteItem(@RequestParam("id") long id, RedirectAttributes redirectAttr){
        Item item = domainFacade.findItem(id); 
        if (item != null) {
            domainFacade.remove(item);
        }
        else{
            redirectAttr.addAttribute("error", "Item bestaat niet."); 
        }
        return "redirect:/items/beheer";
    } 
    
    @RequestMapping(value="/beheer", method=RequestMethod.POST)
    public String newItem(
            @Valid Item newItem, 
            ModelMap model, 
            BindingResult bindingResult
            ){
        if (bindingResult.hasErrors()) {
        } else {
            try {
                domainFacade.create(newItem);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                model.addAttribute("error", ex.getMessage());
            }
        }
        return "redirect:/items/beheer"; 
    }
    
    @RequestMapping(value="/reserveer", method=RequestMethod.GET)
    public String showReservatieForm(ModelMap model){
        model.addAttribute("items", domainFacade.getItems()); 
        return "/items/reserveer";
    }
    
    @RequestMapping(value="/reserveer", method=RequestMethod.GET, params="selected_item")
    public String showReservatieForm(ModelMap model, @RequestParam("selected_item") Long item){
        model.addAttribute("selectedItem", domainFacade.findItem(item)); 
        model.addAttribute("items", domainFacade.getItems()); 
        model.addAttribute("nieuweReservatie", new Reservatie()); 
        return "/items/reserveer";
    }
    
    @RequestMapping(value="/reserveer", method=RequestMethod.GET, params={"selected_item","datum"})
    public String showReservatieForm(ModelMap model, @RequestParam("selected_item") Long item, 
                                         @RequestParam("datum") String datum){
        model.addAttribute("datum", datum); 
        model.addAttribute("selectedItem", domainFacade.findItem(item)); 
        model.addAttribute("items", domainFacade.getItems()); 
        model.addAttribute("nieuweReservatie", new Reservatie()); 
        return "/items/reserveer";
    }
    
    @RequestMapping(value="/reserveer", params="selected_item",method= RequestMethod.POST)
    public String addReservatie(@Valid Reservatie nieuweReservatie, @RequestParam("selected_item") Long itemID){
        try {
            nieuweReservatie.setGebruiker(this.getCurrentDossier().getGebruiker()); 
            Item item = domainFacade.findItem(itemID); 
            item.addReservatie(nieuweReservatie);
            item = domainFacade.edit(item);
            System.out.println(item); 
        } catch (Exception ex) {
            Logger.getLogger(ItemController.class.getName()).log(Level.SEVERE, null, ex);
        }  
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM"); 
        String datum = dateFormat.format(nieuweReservatie.getSlot().getBeginTime().getTime()); 
        return "redirect:/items/reserveer?selected_item="+itemID+"&datum="+datum;
    }
    
    @RequestMapping(value="/reserveer/verwijder", params={"reservatie","selected_item"},method= RequestMethod.GET)
    public String addReservatie(@RequestParam("reservatie") long reservatie, 
                                @RequestParam("selected_item") long selectedItem){
        Reservatie findReservatie = domainFacade.getReservatie(reservatie);
        if(findReservatie.getGebruiker().equals(this.getCurrentDossier().getGebruiker())){
            try {
                Item item = domainFacade.findItem(selectedItem); 
                item.removeReservatie(findReservatie);
                domainFacade.edit(item);
            } catch (DomainException ex) {
                Logger.getLogger(ItemController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM"); 
        String datum = dateFormat.format(findReservatie.getSlot().getBeginTime().getTime()); 
        return "redirect:/items/reserveer?selected_item="+selectedItem+"&datum="+datum;
    }
    
    @RequestMapping(value="/reservaties", params="item")
    public String getReservatiesFeed(ModelMap model, @RequestParam("item") long id, 
                                    @RequestParam("start") long startTimestamp, 
                                    @RequestParam("end") long endTimestamp){
        Calendar start = Calendar.getInstance();
        start.setTimeInMillis(startTimestamp*1000); 
        //Do times 1000 to get the timestamp in miliseconds. 
        //startTimestamp is a UNIX style timestamp. 
        
        Calendar end = Calendar.getInstance();
        end.setTimeInMillis(endTimestamp*1000);
        Item gevondenItem = domainFacade.findItem(id);
        Collection<Reservatie> reservaties = domainFacade.getReservaties(start, end, gevondenItem); 
        model.addAttribute("reservaties", reservaties); 
        
        Map<Item, String> colorMap = new HashMap<Item, String>(); 
        colorMap.put(gevondenItem, "#3a87ad");
        model.addAttribute("kleuren", colorMap); 
        return "/items/reservaties"; 
    }
    
    @RequestMapping(value="/reservaties")
    public String getReservatiesFeed(ModelMap model, 
                                    @RequestParam("start") long startTimestamp, 
                                    @RequestParam("end") long endTimestamp){
        Calendar start = Calendar.getInstance();
        start.setTimeInMillis(startTimestamp*1000); 
        //Do times 1000 to get the timestamp in miliseconds. 
        //startTimestamp is a UNIX style timestamp. 
        
        Calendar end = Calendar.getInstance();
        end.setTimeInMillis(endTimestamp*1000);
        
        Collection<Reservatie> reservaties =  domainFacade.getReservaties(start, end); 
        Map<Item, String> colorMap = new HashMap<Item, String>(); 
        int kleurIndex = 0; 
        for(Reservatie reservatie : reservaties){
            if(!colorMap.containsKey(reservatie.getItem())){
                if(kleurIndex>=this.kleuren.length){
                    kleurIndex=0; 
                }
                
                String kleur = this.kleuren[kleurIndex]; 
                kleurIndex++; 
                
                colorMap.put(reservatie.getItem(), kleur); 
            }
        }
        
        model.addAttribute("kleuren", colorMap); 
        model.addAttribute("reservaties",reservaties); 
        return "/items/reservaties"; 
    }
    
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(Calendar.class,  new CalendarPropertyEditor("yyyy-MM-dd HH:mm"));
    }
}