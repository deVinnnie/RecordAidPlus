package be.khleuven.recordaid.mvc;

import be.khleuven.recordaid.util.Boodschap;
import be.khleuven.recordaid.domain.departement.Support;
import java.util.Collection;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Vincent Ceulemans
 */
@Controller
@RequestMapping("/support")
public class SupportController  extends AbstractController{
    @RequestMapping(method = RequestMethod.GET)
    public String showSupportForm(ModelMap model) {
        model.addAttribute("support", new Support());
        return "/support/nieuw";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addSupport(@Valid Support support, ModelMap model, RedirectAttributes redirectAttr) {
        domainFacade.create(support);
        redirectAttr.addFlashAttribute("boodschap", new Boodschap("Probleem succesvol toegevoegd.", "succes"));
        return "redirect:/support";
    }
    
    @RequestMapping(value="/beheer", method = RequestMethod.GET)
    public String showBeheer(ModelMap model) {
        Collection<Support> supports = domainFacade.getSupports();
        model.addAttribute("supports", supports);
        return "/support/beheer";
    }
    
    @RequestMapping(value="/deactiveer", params="id",method=RequestMethod.GET)
    public String deactiveerSupport(RedirectAttributes redirectAttr, @RequestParam("id") long id){
        Support support = domainFacade.findSupport(id); 
        support.setActief(false);
        domainFacade.edit(support); 
        return "redirect:/support/beheer"; 
    }
    
    @RequestMapping(value="/activeer", params="id",method=RequestMethod.GET)
    public String activeerSupport(RedirectAttributes redirectAttr, @RequestParam("id") long id){
        Support support = domainFacade.findSupport(id); 
        support.setActief(true);
        domainFacade.edit(support); 
        return "redirect:/support/beheer"; 
    }
}