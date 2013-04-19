package be.khleuven.recordaid.mvc;

import be.khleuven.recordaid.database.DatabaseException;
import be.khleuven.recordaid.domain.Item;
import be.khleuven.recordaid.domain.Reservatie;
import be.khleuven.recordaid.domain.facade.RecordAidDomainFacade;
import java.util.Collection;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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
    public String deleteItem(@RequestParam("naam") String naam, 
                                ModelMap model){
        if (domainFacade.findItem(naam) != null) {
            Collection<Reservatie> reservaties = domainFacade.getReservaties();

            for (Reservatie r : reservaties) {
                if (r.getItem().getNaam().equals(naam)) {
                    r.setItem(null);
                    domainFacade.remove(r);
                }
            }
            domainFacade.removeItem(naam);
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
}