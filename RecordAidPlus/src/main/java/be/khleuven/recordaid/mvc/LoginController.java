package be.khleuven.recordaid.mvc;

import be.khleuven.recordaid.domain.DomainException;
import be.khleuven.recordaid.domain.gebruiker.Gebruiker;
import be.khleuven.recordaid.util.Boodschap;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Vincent Ceulemans
 */
@Controller
@RequestMapping("/login")
public class LoginController extends AbstractController {

    @Autowired
    private MyUserDetailsService userDetailsService;

    //<editor-fold defaultstate="collapsed" desc="Login">
    @RequestMapping(value = {"/", "/login"})
    public String showLoginForm(ModelMap model) {
        return "/login/login";
    }

    @RequestMapping(method = RequestMethod.GET, params = "error")
    public String showLoginFormWithError(ModelMap model) {
        model.addAttribute("boodschap", new Boodschap("De combinatie e-mailadres / wachtwoord bestaat niet of het e-mailadres is niet geregistreerd.", "error"));
        return "/login/login";
    }

    @RequestMapping(method = RequestMethod.GET, params = "loginrequired")
    public String showLoginFormWithRequiredMessage(ModelMap model) {
        model.addAttribute("boodschap", new Boodschap("Om deze pagina te bekijken moet u ingelogd zijn.", "error"));
        return "/login/login";
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Registreren">
    @RequestMapping(value = "/registreren", method = RequestMethod.GET)
    public String showRegistratieFormulier(ModelMap model) {
        model.addAttribute("nieuweGebruiker", new Gebruiker());
        return "/login/registreren";
    }

    @RequestMapping(value = "/registreren", method = RequestMethod.POST)
    public String registreerNieuweGebruiker(
            @Valid Gebruiker nieuweGebruiker, BindingResult bindingResult,
            @RequestParam("wachtwoord") String wachtwoord,
            @RequestParam("wachtwoord_confirmation") String wachtwoordConfirmation,
            ModelMap model, RedirectAttributes redirectAttr) throws DomainException {
        //Controleer of deze gebruiker al bestaat. 
        if (domainFacade.getGebruiker(nieuweGebruiker.getEmailadres()) != null) {
            redirectAttr.addFlashAttribute("boodschap", new Boodschap("Er is reeds een gebruiker geregistreerd met dit e-mailadres.", "error"));
            return "redirect:/login/registreren"; 
        }
        
        //Maak nieuwe gebruiker. 
        this.userDetailsService.createUser(nieuweGebruiker, wachtwoord, wachtwoordConfirmation);

        //Voeg boodschap toe voor de volgende pagina. 
        redirectAttr.addFlashAttribute("boodschap", new Boodschap("Registratie gelukt. Binnen enkele ogenblikken ontvang je een mail met een code die je kan gebruiken om je account te valideren.", "succes"));
        return "redirect:/login/valideren";
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Validatie">
    @RequestMapping(value = "/valideren", method = RequestMethod.GET)
    public String showValidatieForm(ModelMap model) {
        return "/login/valideren";
    }

    @RequestMapping(value = "/valideren", method = RequestMethod.POST)
    public String valideerGebruiker(@RequestParam("validatiecode") String validatieCode, ModelMap model, RedirectAttributes redirectAttr) {
        if (validatieCode.length() == 26 && domainFacade.getGebruikerByValidatiecode(validatieCode) != null) {
            Gebruiker gebruiker = domainFacade.getGebruikerByValidatiecode(validatieCode);
            gebruiker.valideer(validatieCode);
            domainFacade.edit(gebruiker);
            redirectAttr.addFlashAttribute("boodschap", new Boodschap("Uw account is met succes gevalideert. U kan nu inloggen met uw nieuw account.", "succes"));
            return "redirect:/login";
        } else {
            redirectAttr.addAttribute("boodschap", new Boodschap("De registratiecode die u heeft ingegeven werd niet geaccepteerd.", "error"));
            return "redirect:/login/valideren";
        }
    }
    //</editor-fold>
}