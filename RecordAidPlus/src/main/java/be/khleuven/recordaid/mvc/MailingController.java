package be.khleuven.recordaid.mvc;

import be.khleuven.recordaid.domain.facade.RecordAidDomainFacade;
import be.khleuven.recordaid.domain.mailing.MailMessage;
import org.springframework.beans.factory.annotation.Autowired;
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
public class MailingController {
    @Autowired
    RecordAidDomainFacade domainFacade;
    
    public MailingController(){
    }
    
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
}