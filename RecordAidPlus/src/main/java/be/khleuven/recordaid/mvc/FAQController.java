package be.khleuven.recordaid.mvc;

import be.khleuven.recordaid.domain.gebruiker.Gebruiker;
import be.khleuven.recordaid.domain.*;
import be.khleuven.recordaid.domain.facade.RecordAidDomainFacade;
import java.util.ArrayList;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*; 

@Controller
@RequestMapping("/faq")
public class FAQController
{
    @Inject
    private RecordAidDomainFacade domainFacade;
    
    public FAQController(){
    }
    
    @RequestMapping(method=RequestMethod.GET)
    public String listFAQ(ModelMap model)
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
    
    @RequestMapping(method=RequestMethod.GET, params="nieuw")
    public String showNieuwFaqForm(ModelMap model){
        model.addAttribute("faq",new FAQ());
        return "/faq/nieuw"; 
    }
    
    @RequestMapping(method=RequestMethod.POST, params="nieuw")
    public String nieuweFAQ(@Valid FAQ faq, ModelMap model, BindingResult bindingResult){
        Gebruiker gebruiker = (Gebruiker) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
        faq.setGebruiker(gebruiker);
        domainFacade.create(faq);
        //domainFacade.sentNieuweFAQMail(faq);
        model.addAttribute("faq_toegevoegd",1); 
        return "redirect:/faq";
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
        model.addAttribute("BeantwoordeVragen", beantwoordeVragen);
        model.addAttribute("nietBeantwoordeVragen", nietBeantwoordeVragen);
        return "/faq/beheer";
    }
    
     @RequestMapping(value="/beheer", params="delete")
     public String deleteFAQ(@RequestParam("id") long id){
         FAQ faq = this.domainFacade.findFAQ(id); 
         this.domainFacade.remove(faq);
         return "redirect:/faq/beheer";
     }
    
    @RequestMapping(method=RequestMethod.GET, params="id")
    public String showDetail(
            @RequestParam("id") long id, 
            ModelMap model){
        FAQ faq = domainFacade.findFAQ(id); 
        model.addAttribute("faq",faq); 
        return "/faq/detail";
    } 
    
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public String updateFaq(
            @Valid FAQ faq, 
            BindingResult bindingResult, 
            ModelMap model){
        domainFacade.edit(faq);
        /*Gebruiker buddy = (Gebruiker) request.getSession().getAttribute("gebruiker");
        domainFacade.sendAntwoordFAQMail(deFAQ, buddy);*/
        return "redirect:/faq/beheer";
    }
}