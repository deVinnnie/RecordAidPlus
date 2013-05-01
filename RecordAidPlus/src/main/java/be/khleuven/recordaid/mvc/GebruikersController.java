package be.khleuven.recordaid.mvc;

import be.khleuven.recordaid.domain.gebruiker.Gebruiker;
import be.khleuven.recordaid.domain.facade.RecordAidDomainFacade;
import be.khleuven.recordaid.domain.gebruiker.Dossier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Vincent Ceulemans
 */
@Controller
@RequestMapping("/gebruikers")
@SessionAttributes("gebruiker")
public class GebruikersController{
    @Autowired
    private RecordAidDomainFacade domainFacade; 
    
    @Autowired
    private MyUserDetailsService userDetailsService; 
    
    @RequestMapping("/beheer")
    public String showBeheer(ModelMap model){
        model.addAttribute("gebruikers", domainFacade.getGebruikers()); 
        return ("/gebruikers/beheer"); 
    }
    
    @RequestMapping("/account")
    public String showAccount(ModelMap model){
        Gebruiker gebruiker = (Gebruiker) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("gebruiker", gebruiker); 
        return "/gebruikers/account"; 
    }
    
    @RequestMapping("/detail")
    public String showDetails(ModelMap model, 
                @RequestParam("emailadres") String emailadres){
        Gebruiker gebruiker = domainFacade.getGebruiker(emailadres); 
        if(gebruiker!=null){
            model.addAttribute("gebruiker", gebruiker); 
            return "/gebruikers/detail";
        }
        else{
            return "/error";        
        }
    }
    
    @RequestMapping(value="/detail",method= RequestMethod.POST)
    public String editGebruiker(@ModelAttribute("gebruiker") Gebruiker gebruiker){
        domainFacade.edit(gebruiker); 
        return "redirect:/gebruikers/detail?emailadres="+gebruiker.getEmailadres(); 
    }
    
    @RequestMapping(value="/valideer",method= RequestMethod.GET)
    public String valideerGebruiker(@RequestParam("emailadres") String emailadres){
        Gebruiker gebruiker = domainFacade.getGebruiker(emailadres); 
        gebruiker.valideer();
        domainFacade.edit(gebruiker); 
        return "redirect:/gebruikers/detail?emailadres="+gebruiker.getEmailadres(); 
    }
    
    @RequestMapping(value="/devalideer",method= RequestMethod.GET)
    public String devalideerGebruiker(@RequestParam("emailadres") String emailadres){
        Gebruiker gebruiker = domainFacade.getGebruiker(emailadres); 
        gebruiker.deValideer();
        domainFacade.edit(gebruiker); 
        return "redirect:/gebruikers/detail?emailadres="+gebruiker.getEmailadres(); 
    }
    
    @RequestMapping(value="/wachtwoord_reset")
    public String resetWachtwoord(@RequestParam("emailadres") String emailadres){
        Gebruiker gebruiker = domainFacade.getGebruiker(emailadres); 
        userDetailsService.changePassword(gebruiker, "temp");
        return "redirect:/gebruikers/detail?emailadres="+gebruiker.getEmailadres(); 
    }
    
    @RequestMapping("/dossier")
    public String showDossier(ModelMap model, @RequestParam("gebruiker") String gebruikerID){
        Gebruiker gebruiker = domainFacade.getGebruiker(gebruikerID); 
        Dossier dossier = domainFacade.getDossier(gebruiker); 
        model.addAttribute("dossier", dossier); 
        return "/gebruikers/dossier"; 
    }
}