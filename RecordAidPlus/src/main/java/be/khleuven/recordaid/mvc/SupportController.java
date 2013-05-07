package be.khleuven.recordaid.mvc;

import be.khleuven.recordaid.util.Boodschap;
import be.khleuven.recordaid.domain.departement.Support;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    public String addSupport(@Valid Support support, BindingResult bindingResult, ModelMap model) {
        domainFacade.create(support);
        model.addAttribute("boodschap", new Boodschap("Probleem succesvol toegevoegd.", "succes"));
        model.addAttribute("support", new Support());
        return "/support/nieuw";
    }
}