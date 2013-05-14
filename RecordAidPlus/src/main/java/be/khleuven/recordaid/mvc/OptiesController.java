package be.khleuven.recordaid.mvc;

import be.khleuven.recordaid.domain.Setting;
import be.khleuven.recordaid.domain.gebruiker.Gebruiker;
import be.khleuven.recordaid.util.Boodschap;
import java.io.*;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Vincent Ceulemans
 */
@Controller
@RequestMapping("/opties")
public class OptiesController extends AbstractController {
    @Autowired
    private MyUserDetailsService userDetailsService;

    @RequestMapping(method = RequestMethod.GET)
    public String showSetup(ModelMap model) {
        return "/opties/opties";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveSetup(
            @RequestParam("wachtwoord") String wachtwoord,
            @RequestParam("wachtwoord_confirmation") String wachtwoordConfirmation,
            @RequestParam("email") String emailadres,
            @RequestParam("email_wachtwoord") String emailWachtwoord,
            RedirectAttributes redirectAttr) throws FileNotFoundException, IOException {
        if (wachtwoord.equals(wachtwoordConfirmation)) {
            //Verander wachtwoord
            Gebruiker gebruiker = this.getCurrentDossier().getGebruiker();
            userDetailsService.changePassword(gebruiker, wachtwoord);
            
            Setting setting = domainFacade.getSetting("admin_first_login"); 
            setting.setWaarde("FALSE");
            domainFacade.edit(setting); 
            
            //Schrijf wachtwoord en emailadres weg op schijf. 
            //Maak RecordAid directory aan in de home folder van de huidige gebruiker (van OS).  
            String homeDir = System.getProperty("user.home");
            File directory = new File(homeDir + "/.recordaid");

            if (!directory.exists()) {
                directory.mkdir();
            }

            File propertiesFile = new File(homeDir + "/.recordaid/recordaid.properties");
            FileOutputStream bestand = new FileOutputStream(propertiesFile);
            Properties properties = new Properties();
            properties.setProperty("email", emailadres); 
            properties.setProperty("email_wachtwoord", emailWachtwoord);
            properties.store(bestand, "---RecordAid Configuratie---");
            bestand.close();

            //Voeg boodschap toe aan pagina. 
            redirectAttr.addFlashAttribute("boodschap", new Boodschap("Setup geslaagd.", "succes"));
            return "redirect:/home";
        } else {
            redirectAttr.addFlashAttribute("boodschap", new Boodschap("Wachtwoord niet hetzelfde.", "error"));
            return "redirect:/opties";
        }
    }
}