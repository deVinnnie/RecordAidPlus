package be.khleuven.recordaid.mvc;

import be.khleuven.recordaid.domain.Lector;
import be.khleuven.recordaid.opnames.OpnameMoment;
import be.khleuven.recordaid.domain.facade.RecordAidDomainFacade;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Vincent Ceulemans
 */
@Controller
@RequestMapping("/gegevens")
public class GegevensController {
    @Autowired
    private RecordAidDomainFacade domainFacade;

    @RequestMapping("/beheer")
    public String showGegevensBeheer(ModelMap model) {
        model.addAttribute("lectoren", domainFacade.getLectoren());
        return "/gegevens/beheer";
    }
    
    @RequestMapping(value="/lectoren/verwijder", params="lector")
    public String verwijderLector(@RequestParam("lector") String lector){
        Lector gevondenLector = domainFacade.getLector(lector); 
        if(gevondenLector != null){
            List<OpnameMoment> lessenVanLector = domainFacade.getLessenVanLector(gevondenLector); 
            for(OpnameMoment opnameMoment : lessenVanLector){
                opnameMoment.setLector(null);
                domainFacade.edit(opnameMoment); 
            }
            domainFacade.removeLector(gevondenLector);
        }
        return "redirect:/gegevens/beheer";
    }
    
    @RequestMapping(value="/lectoren/bewerk", params="lector", method = RequestMethod.GET)
    public String showBewerkLectorForm(ModelMap model, @RequestParam("lector") String lector){
        Lector gevondenLector = domainFacade.getLector(lector); 
        if(gevondenLector != null){
            model.addAttribute("lector", gevondenLector);
            return "/gegevens/lectoren/editor";
        }
        else{
            return "/gegevens/beheer";
        }
    }
    
    @RequestMapping(value="/lectoren/bewerk", method = RequestMethod.POST)
    public String bewerkLector(ModelMap model, @RequestParam("lector") Lector lector){
        domainFacade.edit(lector); 
        return "redirect:/gegevens/beheer";
    }
}
