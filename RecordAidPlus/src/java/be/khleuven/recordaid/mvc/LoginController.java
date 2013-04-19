package be.khleuven.recordaid.mvc;

import be.khleuven.recordaid.domain.gebruiker.Gebruiker;
import be.khleuven.recordaid.domain.facade.RecordAidDomainFacade;
import be.khleuven.recordaid.database.DatabaseException;
import be.khleuven.recordaid.domain.*; 
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*; 

/**
 *
 * @author Vincent Ceulemans
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private RecordAidDomainFacade domainFacade; 
    
    @Autowired
    private MyUserDetailsService userDetailsService; 
    
    @RequestMapping(method=RequestMethod.GET, params="error")
    public String showLoginFormWithError(ModelMap model){
        model.addAttribute("error", "De combinatie emailadres / paswoord bestaat niet of het emailadres is niet geregistreerd.");
        return "/login/login"; 
    }
    
    @RequestMapping(method=RequestMethod.GET, params="loginrequired")
    public String showLoginFormWithRequiredMessage(ModelMap model){
        model.addAttribute("loginrequired",0); 
        return "/login/login"; 
    }
    
    @RequestMapping(value="/registreren", method = RequestMethod.GET)
    public String showRegistratieFormulier(ModelMap model){
        model.addAttribute("nieuweGebruiker", new Gebruiker()); 
        return "/login/registreren"; 
    }
    
    //TODO use binding result to add error. 
    @RequestMapping(value="/registeren", method = RequestMethod.POST)
    public String registreerNieuweGebruiker(
            @Valid Gebruiker gebruiker,  
            @RequestParam("wachtwoord") String wachtwoord, 
            @RequestParam("wachtwoord_confirmation") String wachtwoordConfirmation
            ){
        try{
            this.userDetailsService.createUser(gebruiker, wachtwoord, wachtwoordConfirmation);
        }
        catch(Exception e){
            
        }
        return "/login/registeren"; 
    }
    
    @RequestMapping
    public String showLoginForm(ModelMap model){
        model.addAttribute("title", "Inloggen"); 
        return "/login/login"; 
    } 
    
    @RequestMapping(value="/valideren", method= RequestMethod.GET)
    public String showValidatieForm(ModelMap model){
        return "/login/valideren"; 
    }
    
    @RequestMapping(value="/valideren", method= RequestMethod.POST)
    public String valideerGebruiker(
            @RequestParam("validatiecode") String validatieCode, 
            ModelMap model
            ){
        if(validatieCode.length() == 26)
        {
            Gebruiker gebruiker = domainFacade.getGebruikerByValidatiecode(validatieCode);

            if(gebruiker != null)
            {
                try
                {
                    gebruiker.valideer(validatieCode);
                    domainFacade.updateGebruiker(gebruiker);
                }
                catch(DatabaseException ex)
                {
                    
                }
                model.addAttribute("gelukt_melding", "Uw emailadres is met succes gevalideert. U kan nu <a href='login.jsp'>inloggen</a> met uw nieuw account.");

            }
            else
            {
                model.addAttribute("fout_melding", "De registratiecode die u heeft ingegeven werd niet geaccepteerd.");
            }
        }
             else
        {
            model.addAttribute("fout_melding", "De registratiecode die u heeft ingegeven werd niet geaccepteerd.");
        }
        return "redirect:/login/valideren"; 
    }
}