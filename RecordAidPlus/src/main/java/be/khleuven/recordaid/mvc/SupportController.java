package be.khleuven.recordaid.mvc;

import be.khleuven.recordaid.domain.departement.Support;
import be.khleuven.recordaid.domain.facade.RecordAidDomainFacade;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Vincent Ceulemans
 */
@Controller
@RequestMapping("/support")
public class SupportController{
    @Autowired
    private RecordAidDomainFacade domainFacade; 
    
    @RequestMapping(method=RequestMethod.GET)
    public String showSupportForm(ModelMap model){
        model.addAttribute("nieuwSupport",new Support()); 
        return "/support/nieuw"; 
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public String addSupport(@Valid Support support, BindingResult bindingResult){
        domainFacade.create(support); 
        return "/support/nieuw"; 
    }
}