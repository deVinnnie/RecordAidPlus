package be.khleuven.recordaid.mvc;

import be.khleuven.recordaid.util.Boodschap;
import be.khleuven.recordaid.domain.departement.*; 
import be.khleuven.recordaid.util.propertyeditors.*;
import be.khleuven.recordaid.opnames.*;
import be.khleuven.recordaid.domain.gebruiker.*;
import be.khleuven.recordaid.domain.facade.RecordAidDomainFacade;
import be.khleuven.recordaid.domain.*;
import be.khleuven.recordaid.domain.aanvragen.*;
import be.khleuven.recordaid.util.TimeSpan;
import java.util.*;
import java.util.logging.*; 
import javax.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
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
@RequestMapping("/aanvragen")
@SessionAttributes({"aanvraag", "nieuweOpname", "nieuweMultiAanvraag"})
public class AanvragenController extends AbstractController{
    public AanvragenController() {
    }

    public AanvragenController(RecordAidDomainFacade domainFacade) {
        super(domainFacade);
    }

    //<editor-fold defaultstate="collapsed" desc="Creation">
    @RequestMapping("/nieuw")
    public String showNieuwAanvraagForm(ModelMap model) throws Exception {
        Dossier dossier = this.getCurrentDossier();
        List<Departement> departementen = domainFacade.getDepartementen();
        model.addAttribute("aanvraag", new DagAanvraag(dossier, departementen.get(0)));
        model.addAttribute("departementen", departementen);
        return "/aanvragen/nieuw";
    }

    @RequestMapping(value = "/nieuw", method = RequestMethod.POST)
    public String addNieuweAanvraag(@ModelAttribute("aanvraag") AbstractAanvraag aanvraag,
            BindingResult bindingResult,
            ModelMap model) throws DomainException {
        return "redirect:/aanvragen/nieuwe_opname";
    }

    @RequestMapping(value = "/nieuwe_opname")
    public String showNieuweOpnameMomentForm(ModelMap model, @ModelAttribute("aanvraag") AbstractAanvraag aanvraag) throws DomainException {
        Calendar defaultDate = aanvraag.getDefaultOpnameMomentDag();
        OpnameMoment opnameMoment = new OpnameMoment();
        opnameMoment.setTijdstip(new TimeSpan((Calendar) defaultDate.clone(), (Calendar) defaultDate.clone()));

        model.addAttribute("nieuweOpname", opnameMoment);
        model.addAttribute("alleLectoren", domainFacade.getLectoren());
        return "/aanvragen/nieuwe_opname";
    }

    @RequestMapping(value = "/nieuwe_opname", method = RequestMethod.POST)
    public String addNieuwOpnameMoment(
            @ModelAttribute("nieuweOpname") OpnameMoment nieuwOpnameMoment,
            BindingResult bindingResult,
            @ModelAttribute("aanvraag") AbstractAanvraag aanvraag,
            @RequestParam("action") String action,
            ModelMap model) throws DomainException {
        nieuwOpnameMoment.getLokaal().setDepartement(aanvraag.getDepartement());
        aanvraag.addOpnameMoment(nieuwOpnameMoment);

        if (action.equals("Gereed")) {
            return "redirect:/aanvragen/bevestig_aanvraag";
        } else {
            return "redirect:/aanvragen/nieuwe_opname";
        }
    }

    @RequestMapping(value = "/nieuwe_opname", params = "aanvraag", method = RequestMethod.GET)
    public String showNieuweOpnameMomentFormById(
            @RequestParam("aanvraag") long id,
            ModelMap model) throws DomainException {
        AbstractAanvraag aanvraag = domainFacade.findAanvraag(id);
        Calendar defaultDate = aanvraag.getDefaultOpnameMomentDag();
        OpnameMoment opnameMoment = new OpnameMoment();
        opnameMoment.setTijdstip(new TimeSpan((Calendar) defaultDate.clone(), (Calendar) defaultDate.clone()));

        model.addAttribute("nieuweOpname", opnameMoment);
        model.addAttribute("alleLectoren", domainFacade.getLectoren());
        model.addAttribute("opnameMethodes", domainFacade.getOpnameMethodes());
        return "/aanvragen/nieuwe_opname";
    }

    @RequestMapping(value = "/nieuwe_opname", params = "aanvraag", method = RequestMethod.POST)
    public String addNieuwOpnameMomentById(
            @ModelAttribute("nieuweOpname") OpnameMoment nieuwOpnameMoment,
            BindingResult bindingResult,
            @RequestParam("aanvraag") long id,
            @RequestParam("action") String action,
            ModelMap model) throws DomainException {
        AbstractAanvraag aanvraag = domainFacade.findAanvraag(id);
        nieuwOpnameMoment.getLokaal().setDepartement(aanvraag.getDepartement());
        aanvraag.addOpnameMoment(nieuwOpnameMoment);
        domainFacade.edit(aanvraag);

        if (action.equals("Gereed")) {
            return "redirect:/aanvragen/bewerk?id=" + aanvraag.getId();
        } else {
            return "redirect:/aanvragen/nieuwe_opname?id=" + aanvraag.getId();
        }
    }

    @RequestMapping(value = "/bevestig_aanvraag", method = RequestMethod.GET)
    public String showBevestigAanvraagForm(@ModelAttribute("aanvraag") AbstractAanvraag nieuweAanvraag,
            ModelMap model) {
        model.addAttribute("aanvraag", nieuweAanvraag);
        return "/aanvragen/bevestig_aanvraag";
    }

    @RequestMapping(value = "/bevestig_aanvraag", method = RequestMethod.POST)
    public String bevestigAanvraag(@ModelAttribute("aanvraag") AbstractAanvraag aanvraag,
            ModelMap model, @RequestParam("action") String action) {
        try {
            if(action.equals("Ok")){
                aanvraag = domainFacade.addDagAanvraag((DagAanvraag) aanvraag);
                return "redirect:/aanvragen/detail?succes&id=" + aanvraag.getId();
            }
            else{
                return "redirect:/home";  
            }
        } catch (DomainException ex) {
            Logger.getLogger(AanvragenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/aanvragen/detail?succes&id=" + aanvraag.getId();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Koppeling opnamemomenten met opnames">
    @RequestMapping(value = "/koppel_opname", params = {"aanvraag", "opnamemoment"}, method = RequestMethod.GET)
    public String showKoppelOpnameForm(ModelMap model,
            @RequestParam("aanvraag") long aanvraag,
            @RequestParam("opnamemoment") long opnamemoment) {
        model.addAttribute("nieuweOpname", new Opname());
        model.addAttribute("opnameMethodes", domainFacade.getOpnameMethodes()); 
        return "/aanvragen/koppel_opname";
    }

    @RequestMapping(value = "/koppel_opname", params = {"aanvraag", "opnamemoment"}, method = RequestMethod.POST)
    public String koppelOpname(ModelMap model, @Valid Opname opname,
            @RequestParam("aanvraag") long aanvraag,
            @RequestParam("opnamemoment") long opnamemoment) {
        AbstractAanvraag gevondenAanvraag = domainFacade.findAanvraag(aanvraag);
        OpnameMoment opnameMoment = gevondenAanvraag.getOpnameMoment(opnamemoment);

        if (opnameMoment != null) {
            opnameMoment.setOpname(opname);
            domainFacade.edit(opnameMoment);
        }

        return "redirect:/aanvragen/detail?id=" + gevondenAanvraag.getId();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="MultiPeriodeAanvraag">
    @RequestMapping("/nieuw_multi")
    public String showNieuwMultiAanvraagForm(ModelMap model) throws Exception {
        Dossier dossier = this.getCurrentDossier();
        List<Departement> departementen = domainFacade.getDepartementen();
        model.addAttribute("alleLectoren", domainFacade.getLectoren());
        model.addAttribute("nieuweMultiAanvraag", new MultiPeriodeAanvraag(dossier, departementen.get(0)));
        model.addAttribute("departementen", departementen);
        return "/aanvragen/nieuw_multi";
    }

    @RequestMapping(value = "/nieuw_multi", method = RequestMethod.POST)
    public String addNieuwMultiAanvraag(@ModelAttribute("nieuweMultiAanvraag") MultiPeriodeAanvraag aanvraag, 
                                    @RequestParam("student") String student) {
        try {
            Gebruiker begeleider = this.getCurrentDossier().getGebruiker(); 
            Gebruiker gebruiker = domainFacade.getGebruiker(student); 
            if(gebruiker == null){
                domainFacade.addGebruiker(new Gebruiker(student)  ,begeleider);
            }
            aanvraag.setDossier(domainFacade.getDossier(gebruiker));
            
            
            domainFacade.addMultiPeriodeAanvraag(aanvraag, begeleider);
            return "redirect:/aanvragen/detail?id=" + aanvraag.getId();
        } catch (DomainException ex) {
            Logger.getLogger(AanvragenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/home"; 
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Beheer en detail">
    @RequestMapping("/beheer")
    public String showBeheer(ModelMap model) {
        model.addAttribute("aanvragen", domainFacade.getAanvragen());
        return "/aanvragen/beheer";
    }

    @RequestMapping(value = {"/", "", "/mijnaanvragen"}, method = RequestMethod.GET)
    public String showMijnAanvragen(ModelMap model) {
        Gebruiker gebruiker = (Gebruiker) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Dossier dossier = domainFacade.getDossier(gebruiker);
        model.addAttribute("aanvragen", dossier.getAanvragen());
        return "/aanvragen/mijnaanvragen";
    }

    @RequestMapping(value = "/detail", params = "id", method = RequestMethod.GET)
    public String showDetail(
            @RequestParam("id") long id,
            ModelMap model) {
        AbstractAanvraag aanvraag = this.domainFacade.findAanvraag(id);
        model.addAttribute("aanvraag", aanvraag);
        model.addAttribute("type", aanvraag.getClass().getSimpleName());
        return "/aanvragen/detail";
    }

    @RequestMapping(value = "/detail", params = {"id", "succes"}, method = RequestMethod.GET)
    public String showDetailWithSuccesMessage(
            @RequestParam("id") long id,
            ModelMap model) {
        AbstractAanvraag aanvraag = this.domainFacade.findAanvraag(id);
        model.addAttribute("aanvraag", aanvraag);
        model.addAttribute("type", aanvraag.getClass().getSimpleName());
        model.addAttribute("boodschap", new Boodschap("De aanvraag is succesvol toegevoegd.", "succes"));
        return "/aanvragen/detail";
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Bewerken">
    @RequestMapping(value = "/bewerk", params = "id", method = RequestMethod.GET)
    public String showBewerkForm(
            @RequestParam("id") long id,
            ModelMap model) {
        AbstractAanvraag aanvraag = this.domainFacade.findAanvraag(id);
        model.addAttribute("aanvraag", aanvraag);

        //Voeg mogelijke verantwoordelijken toe. 
        List<Gebruiker> alleBuddies = new ArrayList<Gebruiker>();
        for (Gebruiker gebruiker : domainFacade.getGebruikers(Rollen.BUDDY)) {
            alleBuddies.add(gebruiker);
        }

        for (Gebruiker gebruiker : domainFacade.getGebruikers(Rollen.KERNLID)) {
            alleBuddies.add(gebruiker);
        }
        model.addAttribute("alleBuddies", alleBuddies);
        return "/aanvragen/bewerk";
    }

    @RequestMapping(value = "/bewerk", method = RequestMethod.POST)
    public String updateAanvraag(
            @ModelAttribute("aanvraag") DagAanvraag aanvraag,
            BindingResult bindingResult,
            ModelMap model) {
        this.domainFacade.edit(aanvraag);
        return "redirect:/aanvragen/detail?id=" + aanvraag.getId();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Goedkeuring">
    @RequestMapping(value = "goedkeuren", params = "id", method = RequestMethod.GET)
    public String aanvraagGoedkeuren(@RequestParam("id") long id) {
        try {
            AbstractAanvraag aanvraag = domainFacade.findAanvraag(id);
            domainFacade.aanvaardAanvraag(aanvraag, this.getCurrentDossier().getGebruiker());    
        } catch (DomainException ex) {
            Logger.getLogger(AanvragenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/aanvragen/detail?id=" + id;
    }

    @RequestMapping(value = "weigeren", params = "id", method = RequestMethod.GET)
    public String showAanvraagWeigerenForm(@RequestParam("id") long id) {
        return "/aanvragen/weigeren";
    }

    @RequestMapping(value = "weigeren", params = {"id", "reden"}, method = RequestMethod.POST)
    public String aanvraagWeigeren(@RequestParam("id") long id,
            @RequestParam("reden") String reden) {
        try {
            AbstractAanvraag aanvraag = domainFacade.findAanvraag(id);
            domainFacade.weigerAanvraag(aanvraag, this.getCurrentDossier().getGebruiker(), reden);    
        } catch (DomainException ex) {
            Logger.getLogger(AanvragenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/aanvragen/detail?id=" + id;
    }

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Begeleider">
    @RequestMapping(value="/begeleider", method=RequestMethod.GET)
    public String showAanvragenBegeleider(ModelMap model){
        model.addAttribute("aanvragen", domainFacade.getAanvragen(this.getCurrentDossier().getGebruiker())); 
        return "/aanvragen/begeleider/aanvragen";
    }
    
    //@RequestMapping(value="/")
    
    //</editor-fold>
    
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(Calendar.class, new CalendarPropertyEditor());
        dataBinder.registerCustomEditor(Departement.class, new DepartementPropertyEditor(domainFacade));
        dataBinder.registerCustomEditor(OpnameMethode.class, new OpnameMethodePropertyEditor(domainFacade));
        dataBinder.registerCustomEditor(Lector.class, new LectorPropertyEditor(domainFacade));
        dataBinder.registerCustomEditor(Gebruiker.class, new GebruikerPropertyEditor(domainFacade));
    }
}