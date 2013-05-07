package be.khleuven.recordaid.mvc;

import be.khleuven.recordaid.domain.DomainException;
import be.khleuven.recordaid.domain.gebruiker.Rollen;
import be.khleuven.recordaid.domain.mailing.MailMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Vincent Ceulemans
 */
@Controller
@RequestMapping("/mailing")
@SessionAttributes("selectedMessage")
public class MailingController extends AbstractController{
    
    //<editor-fold defaultstate="collapsed" desc="Beheer">
    @RequestMapping("/beheer")
    public String showMailingBeheer(ModelMap model){
        model.addAttribute("messages", domainFacade.findMailMessages()); 
        return "/mailing/beheer"; 
    }
    
    @RequestMapping(value="/beheer", method = RequestMethod.GET, params = "selected_message")
    public String showMailingBeheer(ModelMap model, @RequestParam("selected_message") long id){
        MailMessage message = domainFacade.findMailMessage(id);
        model.addAttribute("selectedMessage", message); 
        model.addAttribute("messages", domainFacade.findMailMessages()); 
        return "/mailing/beheer";
    }
    
    @RequestMapping(value="/beheer", method= RequestMethod.POST)
    public String editMailBericht(ModelMap model, @ModelAttribute("selectedMessage") MailMessage selectedMessage, BindingResult bindingResult){
        domainFacade.edit(selectedMessage); 
        return "redirect:/mailing/beheer?selected_message="+selectedMessage.getId();
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Maillijst">
    @RequestMapping(value="/maillijst", method= RequestMethod.GET)
    public String showMaillistForm(ModelMap model){
        model.addAttribute("groepen", Rollen.values()); 
        model.addAttribute("subjectPrefix", domainFacade.getSubjectPrefix()); 
        return "/mailing/maillijst"; 
    }
    
    @RequestMapping(value="/maillijst",params={"onderwerp", "bericht"}, method= RequestMethod.POST)
    public String sendMaillijstMessage(@RequestParam("onderwerp") String onderwerp, 
            @RequestParam("bericht") String bericht, 
            @RequestParam("groepen") List<String> groepen){
        MailMessage mailMessage = new MailMessage(onderwerp, bericht, ""); 
        mailMessage.setSubjectPrefix(domainFacade.getSubjectPrefix());
        List<Rollen> rollen = new ArrayList<Rollen>(); 
        for(String string : groepen){
            rollen.add(Enum.valueOf(Rollen.class, string));
        }
        
        try {
            domainFacade.sendMail(mailMessage, rollen);
        } catch (DomainException ex) {
            Logger.getLogger(MailingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/mailing/beheer"; 
    }
    //</editor-fold>
}