package be.khleuven.recordaid.mvc;

import be.khleuven.recordaid.domain.DomainException;
import org.springframework.stereotype.Controller;
import be.khleuven.recordaid.domain.FAQ;
import be.khleuven.recordaid.domain.gebruiker.Gebruiker;
import be.khleuven.recordaid.util.Boodschap;
import java.util.*; 
import javax.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*; 
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 *
 * @author Vincent Ceulemans
 */
@Controller
@RequestMapping("/faq")
@SessionAttributes("faq")
public class FAQController extends AbstractController{
    @RequestMapping(method=RequestMethod.GET)
    public String showFaq(ModelMap model)
    {
        ArrayList<FAQ> relevanteLijst = new ArrayList<FAQ>(domainFacade.getFAQs().size());
        for(FAQ vraag : domainFacade.getFAQs())
        {
            if(vraag.isRelevant())
            {
                relevanteLijst.add(vraag);
            }
        }
        model.addAttribute("faqList", relevanteLijst);
        return "/faq/faq";
    }
    
    @RequestMapping(value="/nieuw",method=RequestMethod.GET)
    public String showNieuwFaqForm(ModelMap model){
        model.addAttribute("faq",new FAQ());
        return "/faq/nieuw"; 
    }
    
    @RequestMapping(value="/nieuw", method=RequestMethod.POST)
    public String nieuweFAQ(@Valid FAQ faq, RedirectAttributes redirectAttr){
        Gebruiker gebruiker = this.getCurrentDossier().getGebruiker(); 
        faq.setGebruiker(gebruiker);
        domainFacade.create(faq);
        
        redirectAttr.addFlashAttribute("boodschap",new Boodschap("Je vraag werd succesvol ontvangen, we proberen ze zo snel mogelijk te beantwoorden.", "succes")); 
        return "redirect:/faq/nieuw";
    }
    
    @RequestMapping("/beheer")
    public String showBeheer(ModelMap model){
        ArrayList<FAQ> beantwoordeVragen = new ArrayList<FAQ>(domainFacade.getFAQs().size());
        ArrayList<FAQ> nietBeantwoordeVragen = new ArrayList<FAQ>(domainFacade.getFAQs().size());
       
        for(FAQ vraag : domainFacade.getFAQs())
        {
            if(vraag.isBeantwoord())
            {
                beantwoordeVragen.add(vraag);
            }
            else
            {
                nietBeantwoordeVragen.add(vraag);
            }
        }
        model.addAttribute("beantwoordeVragen", beantwoordeVragen);
        model.addAttribute("nietBeantwoordeVragen", nietBeantwoordeVragen);
        return "/faq/beheer";
    }
    
     @RequestMapping(value="/beheer", params="delete")
     public String deleteFAQ(@RequestParam("id") long id, RedirectAttributes redirectAttr){
         FAQ faq = this.domainFacade.findFAQ(id); 
         this.domainFacade.remove(faq);
         redirectAttr.addFlashAttribute("boodschap", new Boodschap("FAQ verwijderd", "succes")); 
         return "redirect:/faq/beheer";
     }
    
    @RequestMapping(method=RequestMethod.GET, params="id")
    public String showDetail(@RequestParam("id") long id, ModelMap model){
        FAQ faq = domainFacade.findFAQ(id); 
        model.addAttribute("faq",faq); 
        return "/faq/detail";
    } 
    
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public String updateFaq(@ModelAttribute("faq") FAQ faq, RedirectAttributes redirectAttr) throws DomainException{
        domainFacade.beantwoordFaq(faq, this.getCurrentDossier().getGebruiker()); 
        redirectAttr.addFlashAttribute("boodschap", new Boodschap("FAQ beantwoord", "succes")); 
        return "redirect:/faq/beheer";
    }
}