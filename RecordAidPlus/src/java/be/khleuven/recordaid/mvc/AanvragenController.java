package be.khleuven.recordaid.mvc;

import be.khleuven.recordaid.util.CalendarPropertyEditor;
import be.khleuven.recordaid.database.DatabaseException;
import be.khleuven.recordaid.domain.Gebruiker;
import be.khleuven.recordaid.domain.RecordAidDomainFacade;
import be.khleuven.recordaid.domain.aanvragen.Aanvraag;
import java.util.Calendar;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*; 

/**
 *
 * @author Vincent Ceulemans
 */
@Controller
@RequestMapping("/aanvragen")
public class AanvragenController{
    @Autowired
    private RecordAidDomainFacade domainFacade; 
    
    @RequestMapping("/nieuw")
    public String showNieuwAanvraagForm(ModelMap model){
        model.addAttribute("nieuweAanvraag", new Aanvraag()); 
        model.addAttribute("alleLectoren", domainFacade.getLectoren()); 
        return "/aanvragen/nieuw"; 
    }
    
    @RequestMapping(value="/nieuw", method= RequestMethod.POST)
    public String nieuweAanvraag(@Valid Aanvraag aanvraag, 
                    BindingResult bindingResult){
        Gebruiker gebruiker = (Gebruiker) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
        aanvraag.setAanvrager(gebruiker);
        domainFacade.addAanvraag(aanvraag);
        return "redirect:/aanvragen/detail?id="+aanvraag.getId(); 
    }
    
    @RequestMapping("/beheer")
    public String showBeheer(ModelMap model){
        model.addAttribute("aanvragen", domainFacade.getAanvragen()); 
        return "/aanvragen/beheer"; 
    }
    
    @RequestMapping(value={"/", "", "/mijnaanvragen"},method=RequestMethod.GET)
    public String showMijnAanvragen(ModelMap model){
        Gebruiker gebruiker = (Gebruiker) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("aanvragen", domainFacade.getAanvragenAanvrager(gebruiker)); 
        return "/aanvragen/mijnaanvragen"; 
    }
    
    @RequestMapping(value="/detail", params="id", method= RequestMethod.GET)
    public String showDetail(
            @RequestParam("id") long id, 
            ModelMap model
            ){
        Aanvraag aanvraag = this.domainFacade.findAanvraag(id); 
        model.addAttribute("aanvraag", aanvraag); 
        return "/aanvragen/detail"; 
    }
    
    @RequestMapping(value="/bewerk", params="id", method= RequestMethod.GET)
    public String showBewerkForm(
            @RequestParam("id") long id, 
            ModelMap model
            ){
        Aanvraag aanvraag = this.domainFacade.findAanvraag(id); 
        model.addAttribute("aanvraag", aanvraag); 
        return "/aanvragen/bewerk"; 
    }
    
    @RequestMapping(value="/bewerk", method= RequestMethod.POST)
    public String updateAanvraag(
            @Valid Aanvraag aanvraag, 
            BindingResult bindingResult, 
            ModelMap model
            ){
        try {
            this.domainFacade.updateAanvraag(aanvraag);
            return "redirect:aanvragen/detail?id="+aanvraag.getId();
        } catch (DatabaseException ex) {
           return "/aanvragen/beheer";
        }      
    }
    
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(Calendar.class,  new CalendarPropertyEditor());
    }
}