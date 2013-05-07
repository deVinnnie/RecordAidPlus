package be.khleuven.recordaid.mvc;

import be.khleuven.recordaid.domain.gebruiker.Gebruiker;
import be.khleuven.recordaid.util.Boodschap;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*; 

/**
 *
 * @author Vincent Ceulemans
 */
@Controller
@RequestMapping("/login")
public class LoginController  extends AbstractController{
    @Autowired
    private MyUserDetailsService userDetailsService; 
    
    @RequestMapping(method=RequestMethod.GET, params="error")
    public String showLoginFormWithError(ModelMap model){
        model.addAttribute("boodschap", new Boodschap("De combinatie e-mailadres / wachtwoord bestaat niet of het e-mailadres is niet geregistreerd.", "error"));
        return "/login/login"; 
    }
    
    @RequestMapping(method=RequestMethod.GET, params="loginrequired")
    public String showLoginFormWithRequiredMessage(ModelMap model){
        model.addAttribute("boodschap", new Boodschap("Om deze pagina te bekijken moet u ingelogd zijn.", "error"));
        return "/login/login"; 
    }
    
    @RequestMapping(value="/registreren", method = RequestMethod.GET)
    public String showRegistratieFormulier(ModelMap model){
        model.addAttribute("nieuweGebruiker", new Gebruiker()); 
        return "/login/registreren"; 
    }
     
    @RequestMapping(value="/registreren", method = RequestMethod.POST)
    public String registreerNieuweGebruiker(
            @Valid Gebruiker nieuweGebruiker, BindingResult bindingResult, 
            @RequestParam("wachtwoord") String wachtwoord, 
            @RequestParam("wachtwoord_confirmation") String wachtwoordConfirmation, 
            ModelMap model
            ){
        try{
            this.userDetailsService.createUser(nieuweGebruiker, wachtwoord, wachtwoordConfirmation);
        }
        catch(Exception e){
            bindingResult.addError(new ObjectError("gebruik_bestaat_al", 
                    "Er is reeds een gebruiker geregistreerd met dit e-mailadres.")); 
            model.addAttribute("nieuweGebruiker", nieuweGebruiker); 
        }
        return "/login/registreren"; 
    }
    
    @RequestMapping
    public String showLoginForm(ModelMap model){
        return "/login/login"; 
    } 
    
    @RequestMapping(value="/valideren", method= RequestMethod.GET)
    public String showValidatieForm(ModelMap model){
        return "/login/valideren"; 
    }
    
    @RequestMapping(value="/valideren", method= RequestMethod.POST)
    public String valideerGebruiker(@RequestParam("validatiecode") String validatieCode, ModelMap model){
        if(validatieCode.length() == 26 && domainFacade.getGebruikerByValidatiecode(validatieCode)!=null)
        {
            Gebruiker gebruiker = domainFacade.getGebruikerByValidatiecode(validatieCode);
            gebruiker.valideer(validatieCode);
            domainFacade.edit(gebruiker);
            model.addAttribute("gelukt_melding", "Uw account is met succes gevalideert. U kan nu inloggen met uw nieuw account.");
            return "redirect:/login/login"; 
        }
        else{
            model.addAttribute("fout_melding", "De registratiecode die u heeft ingegeven werd niet geaccepteerd.");
            return "redirect:/login/valideren"; 
        }
    }
}