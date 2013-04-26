package be.khleuven.recordaid.mvc;

import be.khleuven.recordaid.database.DatabaseException;
import be.khleuven.recordaid.domain.DomainException;
import be.khleuven.recordaid.domain.items.*; 
import be.khleuven.recordaid.domain.facade.RecordAidDomainFacade;
import be.khleuven.recordaid.domain.gebruiker.Dossier;
import be.khleuven.recordaid.domain.gebruiker.Gebruiker;
import be.khleuven.recordaid.util.CalendarPropertyEditor;
import java.util.Calendar;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/items")
public class ItemController 
{
    @Autowired
    RecordAidDomainFacade domainFacade;
    
    public ItemController(){
    }
    
    @RequestMapping(value={"/","/beheer"},method=RequestMethod.GET)
    public String listItems(ModelMap model)
    {
        Collection<Item> items = domainFacade.getItems();
        model.addAttribute("items", items);
        model.addAttribute("newItem", new Item());
        return "/items/beheer";
    }
    
    @RequestMapping(params="verwijder",method=RequestMethod.POST)
    public String deleteItem(@RequestParam("id") Long id, 
                                ModelMap model){
        Item item = domainFacade.findItem(id); 
        if (item != null) {
            domainFacade.remove(item);
        }
        else{
            model.addAttribute("error", "Item bestaat niet."); 
        }
        return "/items/beheer";
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
                domainFacade.addItem(newItem);
            } catch (DatabaseException ex) {
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
        return "redirect:/items/reserveer?selected_item="+itemID; 
    }
    
    @RequestMapping(value="/reserveer/verwijder", params={"reservatie","selected_item"},method= RequestMethod.GET)
    public String addReservatie(@RequestParam("reservatie") long reservatie, @RequestParam("selected_item") long selectedItem){
        Reservatie findReservatie = domainFacade.findReservatie(reservatie);
        if(findReservatie.getGebruiker().equals(this.getCurrentDossier().getGebruiker())){
            try {
                Item item = domainFacade.findItem(selectedItem); 
                item.removeReservatie(findReservatie);
                domainFacade.edit(item);
            } catch (DomainException ex) {
                Logger.getLogger(ItemController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        return "redirect:/items/reserveer?selected_item="+selectedItem; 
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
        
        Collection<Reservatie> reservaties = domainFacade.getReservaties(start, end, domainFacade.findItem(id)); 
        model.addAttribute("reservaties", reservaties); 
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
        model.addAttribute("reservaties",reservaties); 
        return "/items/reservaties"; 
    }
    
    /**
     * Fetches the dossier of the current user in this session. 
     * 
     * @return The dossier of the current user. 
     */
    private Dossier getCurrentDossier(){
        Gebruiker gebruiker = (Gebruiker) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Dossier dossier = domainFacade.getDossier(gebruiker); 
        return dossier; 
    }
    
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(Calendar.class,  new CalendarPropertyEditor("yyyy-MM-dd HH:mm"));
    }
}