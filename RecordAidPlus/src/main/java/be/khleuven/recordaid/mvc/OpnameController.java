package be.khleuven.recordaid.mvc;

import be.khleuven.recordaid.domain.departement.*;
import be.khleuven.recordaid.util.propertyeditors.*;
import be.khleuven.recordaid.opnames.*;
import be.khleuven.recordaid.domain.facade.RecordAidDomainFacade;
import be.khleuven.recordaid.domain.*;
import be.khleuven.recordaid.domain.aanvragen.AbstractAanvraag;
import java.util.*;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Vincent Ceulemans
 */
@Controller
@RequestMapping("/opnames")
public class OpnameController extends AbstractController {

    //<editor-fold defaultstate="collapsed" desc="Bewerken">
    @RequestMapping(value = "/editor")
    public String showNieuweOpnameForm(ModelMap model) {
        OpnameMoment opnameMoment = new OpnameMoment();
        opnameMoment.setZichtbaar(true);
        model.addAttribute("opname", opnameMoment);
        model.addAttribute("alleLectoren", domainFacade.getLectoren());
        model.addAttribute("opnameMethodes", domainFacade.getOpnameMethodes());
        return "/opnames/editor";
    }

    @RequestMapping(value = "/editor", params = "id", method = RequestMethod.GET)
    public String showEditorOpnameForm(@RequestParam("id") long id, ModelMap model) {
        OpnameMoment opnameMoment = domainFacade.findOpnameMoment(id);
        if (opnameMoment == null) {
            return "redirect:/opnames/beheer";
        }
        model.addAttribute("opname", opnameMoment);
        model.addAttribute("alleLectoren", domainFacade.getLectoren());
        model.addAttribute("opnameMethodes", domainFacade.getOpnameMethodes());
        return "/opnames/editor";
    }

    @RequestMapping(value = "/editor", method = RequestMethod.POST)
    public String addNieuweOpname(@Valid OpnameMoment opname, BindingResult bindingResult) throws DomainException {
        domainFacade.edit(opname);
        return "redirect:/opnames/beheer";
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="OpnameMethodes">
    @RequestMapping(value = "/verwijdermethode", params = "id", method = RequestMethod.POST)
    public String removeOpnameMethode(@RequestParam("id") long id) throws DomainException {
        domainFacade.removeOpnameMethode(domainFacade.findOpnameMethode(id));
        return "redirect:/opnames/beheer";
    }
    
    @RequestMapping(value = "/nieuw_opnamemethode", method = RequestMethod.GET)
    public String showNieuwOpnameMethode(ModelMap model){
        model.addAttribute("opnameMethode",new OpnameMethode()); 
        return "/opnames/nieuw_opnamemethode";
    }
    
    @RequestMapping(value = "/nieuw_opnamemethode", method = RequestMethod.POST)
    public String addNieuwOpnameMethode(@Valid OpnameMethode opnameMethode){
        domainFacade.create(opnameMethode); 
        return "redirect:/opnames/beheer";
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Beheer">
    @RequestMapping("/beheer")
    public String showBeheer(ModelMap model) {
        List<OpnameMoment> lijst = domainFacade.getOpnames();
        model.addAttribute("opnames", lijst);
        model.addAttribute("opnameMethodes", domainFacade.getOpnameMethodes());
        return "/opnames/beheer";
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Opnames goedkeuren">
    @RequestMapping(value = "/opname_goedkeuren", params = {"toegangscode", "opname", "aanvraag"})
    public String showOpnameGoedkeurenForm(ModelMap model,
            @RequestParam("toegangscode") String toegangscode,
            @RequestParam("opname") long id,
            @RequestParam("aanvraag") long aanvraagID) {
        OpnameMoment opnameMoment = domainFacade.findOpnameMoment(id);
        AbstractAanvraag gevondenAanvraag = domainFacade.findAanvraag(aanvraagID);

        //Controleer of de toegangscode klopt!
        if (gevondenAanvraag.getOpnameMomenten().contains(opnameMoment)
                && opnameMoment.getToegangsCode().equals(toegangscode)) {
            model.addAttribute("opnameMoment", opnameMoment);
            model.addAttribute("aanvraag", gevondenAanvraag);
            model.addAttribute("opnameMethodes", domainFacade.getOpnameMethodes());
            return "/opnames/opname_goedkeuren";
        } else {
            return "redirect:/home";
        }
    }

    @RequestMapping(value = "/opname_goedkeuren", params = {"toegangscode", "opname", "aanvraag", "action"}, method = RequestMethod.POST)
    public String goedkeurenOpname(ModelMap model, @RequestParam("toegangscode") String toegangscode,
            @RequestParam("opname") long id,
            @RequestParam("aanvraag") long aanvraagID,
            @RequestParam("methodes") List<String> methodes, 
            @RequestParam("action") String action) {
        OpnameMoment opnameMoment = domainFacade.findOpnameMoment(id);
        AbstractAanvraag gevondenAanvraag = domainFacade.findAanvraag(aanvraagID);

        //Controleer of de toegangscode klopt!
        if (gevondenAanvraag.getOpnameMomenten().contains(opnameMoment)
                && opnameMoment.getToegangsCode().equals(toegangscode)) {
            if(action.equals("Goedkeuren")){
                List<OpnameMethode> mogelijkeOpnameMethodes = new ArrayList<OpnameMethode>(); 
                
                for(String methodeID : methodes){
                    OpnameMethode opnameMethode = domainFacade.findOpnameMethode(Long.parseLong(methodeID)); 
                    mogelijkeOpnameMethodes.add(opnameMethode);
                }
                
                opnameMoment.setMogelijkeOpnameMethodes(mogelijkeOpnameMethodes);
                domainFacade.edit(opnameMoment); 
            }
        }
        return "redirect:/home";
    }
    //</editor-fold>

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(Calendar.class, new CalendarPropertyEditor());
        dataBinder.registerCustomEditor(Departement.class, new DepartementPropertyEditor(domainFacade));
        dataBinder.registerCustomEditor(OpnameMethode.class, new OpnameMethodePropertyEditor(domainFacade));
        dataBinder.registerCustomEditor(Lector.class, new LectorPropertyEditor(domainFacade));
    }
}