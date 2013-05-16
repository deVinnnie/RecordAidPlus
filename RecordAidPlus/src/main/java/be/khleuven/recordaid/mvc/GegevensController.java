package be.khleuven.recordaid.mvc;

import be.khleuven.recordaid.domain.departement.Departement;
import be.khleuven.recordaid.domain.departement.Lector;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Vincent Ceulemans
 */
@Controller
@RequestMapping("/gegevens")
public class GegevensController extends AbstractController{
    @RequestMapping("/beheer")
    public String showGegevensBeheer(ModelMap model) {
        model.addAttribute("lectoren", domainFacade.getLectoren());
        model.addAttribute("departementen", domainFacade.getDepartementen()); 
        return "/gegevens/beheer";
    }
    
    //<editor-fold defaultstate="collapsed" desc="Lectoren">
    @RequestMapping(value="/lectoren/verwijder", params="id")
    public String verwijderLector(@RequestParam("id") long id){
        Lector gevondenLector = domainFacade.getLector(id); 
        domainFacade.removeLector(gevondenLector);
        return "redirect:/gegevens/beheer";
    }
    
    @RequestMapping(value="/lectoren/bewerk", params="id", method = RequestMethod.GET)
    public String showBewerkLectorForm(ModelMap model, @RequestParam("id") long id){
        Lector gevondenLector = domainFacade.getLector(id); 
        if(gevondenLector != null){
            model.addAttribute("lector", gevondenLector);
            return "/gegevens/lectoren/editor";
        }
        else{
            return "/gegevens/beheer";
        }
    }
    
    @RequestMapping(value="/lectoren/bewerk")
    public String bewerkLector(@Valid Lector lector){
        domainFacade.edit(lector); 
        return "redirect:/gegevens/beheer";
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Departementen">
    @RequestMapping(value="/departementen/activeer", params="naam", method = RequestMethod.GET)
    public String activeerDepartement(@RequestParam("naam") String naam){
        Departement departement = domainFacade.getDepartement(naam); 
        departement.setActief(true);
        domainFacade.edit(departement);
        return "redirect:/gegevens/beheer"; 
    }
    
    @RequestMapping(value="/departementen/deactiveer", params="naam", method = RequestMethod.GET)
    public String deactiveerDepartement(@RequestParam("naam") String naam){
        Departement departement = domainFacade.getDepartement(naam); 
        departement.setActief(false);
        domainFacade.edit(departement);
        return "redirect:/gegevens/beheer"; 
    }             
    //</editor-fold>
}