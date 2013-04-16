package be.khleuven.recordaid.mvc;

import be.khleuven.recordaid.domain.Gebruiker;
import be.khleuven.recordaid.domain.RecordAidDomainFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Vincent Ceulemans
 */
@Controller
@RequestMapping("/gebruikers")
public class GebruikersController{
    @Autowired
    private RecordAidDomainFacade domainFacade; 
    
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
            return "/FoutPagina";        
        }
    }
}