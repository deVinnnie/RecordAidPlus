package be.khleuven.recordaid.mvc;

import be.khleuven.recordaid.domain.DomainException;
import be.khleuven.recordaid.domain.gebruiker.*;
import be.khleuven.recordaid.util.Boodschap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Vincent Ceulemans
 */
@Controller
@RequestMapping("/gebruikers")
@SessionAttributes("gebruiker")
public class GebruikersController extends AbstractController {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @RequestMapping("/beheer")
    public String showBeheer(ModelMap model) {
        model.addAttribute("gebruikers", domainFacade.getGebruikers());
        model.addAttribute("dossiers", domainFacade.getDossiers());
        return ("/gebruikers/beheer");
    }

    //<editor-fold defaultstate="collapsed" desc="Account & Details">
    @RequestMapping("/account")
    public String showAccount(ModelMap model) {
        Gebruiker gebruiker = (Gebruiker) this.getCurrentDossier().getGebruiker();
        return showDetails(model, gebruiker);
    }

    @RequestMapping("/detail")
    public String showDetails(ModelMap model, @RequestParam("emailadres") String emailadres) {
        Gebruiker gebruiker = domainFacade.getGebruiker(emailadres);
        return showDetails(model, gebruiker);
    }

    @RequestMapping(value = {"/detail", "/account"}, method = RequestMethod.POST)
    public String editGebruiker(@ModelAttribute("gebruiker") Gebruiker gebruiker) {
        domainFacade.getGebruiker(gebruiker.getEmailadres());
        Gebruiker currentGebruiker = this.getCurrentDossier().getGebruiker();
        if (currentGebruiker.getRollen().contains(Rollen.ADMIN)) {
            /*Admin mag alles bewerken*/
            domainFacade.edit(gebruiker);
            return "redirect:/gebruikers/detail?emailadres=" + gebruiker.getEmailadres();
        }
        else if(currentGebruiker.equals(gebruiker)){
            /* Verifieer dat alleen voor- en achternaam aangepast zijn. */
            if(gebruiker.getRollen().equals(currentGebruiker.getRollen()) 
                    && gebruiker.getEmailadres().equals(currentGebruiker.getEmailadres())
                    && gebruiker.isGevalideerd()==currentGebruiker.isGevalideerd()){
                domainFacade.edit(gebruiker); 
                return "redirect:/gebruikers/account"; 
            }
        }
        return "redirect:/home"; 
    }

    private String showDetails(ModelMap model, Gebruiker gebruiker) {
        if (gebruiker != null) {
            model.addAttribute("rollen", Rollen.values());
            model.addAttribute("gebruiker", gebruiker);
            return "/gebruikers/detail";
        }
        return "/error/404";
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Validatie">
    @RequestMapping(value = "/valideer", method = RequestMethod.GET)
    public String valideerGebruiker(@RequestParam("emailadres") String emailadres) {
        Gebruiker gebruiker = domainFacade.getGebruiker(emailadres);
        gebruiker.valideer();
        domainFacade.edit(gebruiker);
        return "redirect:/gebruikers/detail?emailadres=" + gebruiker.getEmailadres();
    }

    @RequestMapping(value = "/devalideer", method = RequestMethod.GET)
    public String devalideerGebruiker(@RequestParam("emailadres") String emailadres) {
        Gebruiker gebruiker = domainFacade.getGebruiker(emailadres);
        gebruiker.deValideer();
        domainFacade.edit(gebruiker);
        return "redirect:/gebruikers/detail?emailadres=" + gebruiker.getEmailadres();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Wachtwoorden">
    @RequestMapping(value="/wachtwoord", method= RequestMethod.GET)
    public String showVeranderWachtwoordForm(){
        return "/gebruikers/wachtwoord";
    }
    
    @RequestMapping(value = "/wachtwoord",method=RequestMethod.POST)
    public String veranderWachtwoord(
            @RequestParam("wachtwoord") String wachtwoord, 
            @RequestParam("wachtwoord_confirmation") String wachtwoord_confirmation, 
            RedirectAttributes redirectAttr) {
        Gebruiker gebruiker = this.getCurrentDossier().getGebruiker(); 
        if(wachtwoord.equals(wachtwoord_confirmation)){
            this.userDetailsService.changePassword(gebruiker, wachtwoord);
            return "redirect:/home"; 
        }
        else{
            redirectAttr.addFlashAttribute("boodschap", new Boodschap("Wachtwoorden komen niet overeen.","error")); 
            return "redirect:/gebruikers/wachtwoord";
        }
    }
    
    @RequestMapping(value = "/wachtwoord_reset")
    public String resetWachtwoord(@RequestParam("emailadres") String emailadres) {
        Gebruiker gebruiker = domainFacade.getGebruiker(emailadres);
        userDetailsService.changePassword(gebruiker, "temp");
        return "redirect:/gebruikers/detail?emailadres=" + gebruiker.getEmailadres();
    }
    //</editor-fold>

    @RequestMapping("/dossier")
    public String showDossier(ModelMap model, @RequestParam("gebruiker") String gebruikerID) {
        Gebruiker gebruiker = domainFacade.getGebruiker(gebruikerID);
        Dossier dossier = domainFacade.getDossier(gebruiker);
        model.addAttribute("dossier", dossier);
        return "/gebruikers/dossier";
    }

    @RequestMapping("/buddyworden")
    public String buddyWorden(RedirectAttributes redirectAttr) throws DomainException {
        Gebruiker gebruiker = this.getCurrentDossier().getGebruiker();
        domainFacade.buddyWorden(gebruiker);
        redirectAttr.addFlashAttribute("boodschap", new Boodschap("Bedankt voor je interesse! We nemen binnenkort contact op met jou.", "succes"));
        return "redirect:/gebruikers/account";
    }
}