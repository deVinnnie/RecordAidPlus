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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.*; 
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Vincent Ceulemans
 */
@Controller
@RequestMapping("/aanvragen")
@SessionAttributes({"aanvraag", "nieuweOpname", "nieuweMultiAanvraag"})
public class AanvragenController extends AbstractController{
    @Autowired
    private MyUserDetailsService userDetailsService; 
    
    public AanvragenController() {}

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
        return "redirect:/aanvragen/nieuwe_les";
    }
    
    //<editor-fold defaultstate="collapsed" desc="OpnameMomenten">
    @RequestMapping(value = "/nieuwe_les")
    public String showNieuweOpnameMomentForm(ModelMap model, @ModelAttribute("aanvraag") AbstractAanvraag aanvraag) throws DomainException {
        Calendar defaultDate = aanvraag.getDefaultOpnameMomentDag();
        OpnameMoment opnameMoment = new OpnameMoment();
        opnameMoment.setTijdstip(new TimeSpan((Calendar) defaultDate.clone(), (Calendar) defaultDate.clone()));

        model.addAttribute("nieuweOpname", opnameMoment);
        model.addAttribute("alleLectoren", domainFacade.getLectoren());
        model.addAttribute("type", aanvraag.getClass().getSimpleName()); 
        model.addAttribute("chained", true);
        return "/aanvragen/nieuwe_les";
    }

    @RequestMapping(value = "/nieuwe_les", params = "aanvraag", method = RequestMethod.GET)
    public String showNieuweOpnameMomentForm(@RequestParam("aanvraag") long id, ModelMap model) throws DomainException {
        AbstractAanvraag aanvraag = domainFacade.findAanvraag(id);
        String redirect =  this.showNieuweOpnameMomentForm(model, aanvraag);
        model.addAttribute("chained", false);
        return redirect; 
    }
   
    @RequestMapping(value = "/nieuwe_les", method = RequestMethod.POST)
    public String addNieuwOpnameMoment(@ModelAttribute("nieuweOpname") OpnameMoment nieuwOpnameMoment,
            @ModelAttribute("aanvraag") AbstractAanvraag aanvraag,
            @RequestParam("action") String action) throws DomainException {
        nieuwOpnameMoment.getLokaal().setDepartement(aanvraag.getDepartement());
        aanvraag.addOpnameMoment(nieuwOpnameMoment);

        if (action.equals("Gereed")) {
            return "redirect:/aanvragen/bevestigen";
        } else {
            return "redirect:/aanvragen/nieuwe_les";
        }
    }
    
    @RequestMapping(value = "/nieuwe_les", params = "aanvraag", method = RequestMethod.POST)
    public String addNieuwOpnameMoment(
            @ModelAttribute("nieuweOpname") OpnameMoment nieuwOpnameMoment,
            @RequestParam("aanvraag") long id, @RequestParam("action") String action, ModelMap model) throws DomainException {
        AbstractAanvraag aanvraag = domainFacade.findAanvraag(id);
        nieuwOpnameMoment.getLokaal().setDepartement(aanvraag.getDepartement());
        aanvraag.addOpnameMoment(nieuwOpnameMoment);
        domainFacade.edit(aanvraag);

        if (action.equals("Gereed")) {
            return "redirect:/aanvragen/detail?id=" + aanvraag.getId();
        } else {
            return "redirect:/aanvragen/nieuwe_opname?aanvraag=" + aanvraag.getId();
        }
    }
    
    //TODO: Abstract this and previous method. 
    @RequestMapping(value = "/nieuwe_les", params = {"aanvraag","datum"}, method = RequestMethod.POST)
    public String addNieuwOpnameMoment(
            @ModelAttribute("nieuweOpname") OpnameMoment nieuwOpnameMoment,
            @RequestParam("aanvraag") long id, @RequestParam("action") String action, 
            ModelMap model, @RequestParam("datum") String dat) throws DomainException, ParseException {
        AbstractAanvraag aanvraag = domainFacade.findAanvraag(id);
        nieuwOpnameMoment.getLokaal().setDepartement(aanvraag.getDepartement());
        
        SimpleDateFormat smdf = new SimpleDateFormat("yyyy-MM-dd"); 
        Calendar datum = Calendar.getInstance(); 
        datum.setTime(smdf.parse(dat));
        
        nieuwOpnameMoment.getTijdstip().getBeginTime()
                .set(datum.get(Calendar.YEAR), datum.get(Calendar.MONTH), datum.get(Calendar.DATE));
        nieuwOpnameMoment.getTijdstip().getEndTime()
                .set(datum.get(Calendar.YEAR), datum.get(Calendar.MONTH), datum.get(Calendar.DATE));
        
        aanvraag.addOpnameMoment(nieuwOpnameMoment);
        domainFacade.edit(aanvraag);
        
        if (action.equals("Gereed")) {
            return "redirect:/aanvragen/detail?id=" + aanvraag.getId();
        } else {
            return "redirect:/aanvragen/nieuwe_opname?aanvraag=" + aanvraag.getId();
        }
    }
    //</editor-fold>
    
    @RequestMapping(value = "/inform_lectoren", params="aanvraag",method = RequestMethod.GET)
    public String informLectoren(@RequestParam("aanvraag") long id, RedirectAttributes redirectAttr) throws DomainException{
        AbstractAanvraag findAanvraag = domainFacade.findAanvraag(id);
        if(findAanvraag instanceof MultiPeriodeAanvraag){
            domainFacade.informLectoren((MultiPeriodeAanvraag) findAanvraag, this.getCurrentDossier().getGebruiker()); 
            redirectAttr.addFlashAttribute("boodschap", new Boodschap("Mails verzonden", "succes")); 
        }
        else{
            redirectAttr.addFlashAttribute("boodschap", new Boodschap("Can't do that, sorry!", "error")); 
        }
        return "redirect:/aanvragen/bewerk?id="+id; 
    }

    @RequestMapping(value = "/bevestigen", method = RequestMethod.GET)
    public String showBevestigAanvraagForm(@ModelAttribute("aanvraag") AbstractAanvraag nieuweAanvraag, ModelMap model) {
        model.addAttribute("aanvraag", nieuweAanvraag);
        return "/aanvragen/bevestigen";
    }

    @RequestMapping(value = "/bevestigen", method = RequestMethod.POST)
    public String bevestigAanvraag(@ModelAttribute("aanvraag") AbstractAanvraag aanvraag,
            ModelMap model, @RequestParam("action") String action, RedirectAttributes redirectAttr) {
        try {
            if(action.equals("Ok")){
                aanvraag = domainFacade.addDagAanvraag((DagAanvraag) aanvraag);
                redirectAttr.addFlashAttribute("boodschap", new Boodschap("De aanvraag is succesvol toegevoegd.", "succes"));
                return "redirect:/aanvragen/detail?id=" + aanvraag.getId();
            }
        } catch (DomainException ex) {
            Logger.getLogger(AanvragenController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return "redirect:/home";  
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
            @RequestParam("opnamemoment") long opnamemoment) throws DomainException {
        AbstractAanvraag gevondenAanvraag = domainFacade.findAanvraag(aanvraag);
        OpnameMoment opnameMoment = gevondenAanvraag.getOpnameMoment(opnamemoment);

        if (opnameMoment != null) {
            domainFacade.koppelOpname(gevondenAanvraag, opnameMoment,opname, this.getCurrentDossier().getGebruiker()); 
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
    public String addNieuwMultiAanvraag(@ModelAttribute("nieuweMultiAanvraag") MultiPeriodeAanvraag aanvraag) throws DomainException {
        return this.addNieuwMultiAanvraag(aanvraag, this.getCurrentDossier().getGebruiker().getEmailadres()); 
    }
    
    @RequestMapping(value = "/nieuw_multi", params="student", method = RequestMethod.POST)
    public String addNieuwMultiAanvraag(@ModelAttribute("nieuweMultiAanvraag") MultiPeriodeAanvraag aanvraag,
            @RequestParam("student") String student) throws DomainException {
        Gebruiker gebruiker = domainFacade.getGebruiker(student);
        Gebruiker begeleider = this.getCurrentDossier().getGebruiker();
      
        if (gebruiker == null) {
            //Create the user if the user doesn't exist yet. 
            gebruiker = new Gebruiker(student);
            userDetailsService.createUser(gebruiker, gebruiker.getValidatieCode(), begeleider);
            gebruiker = domainFacade.getGebruiker(student);
        } 
       
        aanvraag.setDossier(domainFacade.getDossier(gebruiker));
        aanvraag.setBegeleider(begeleider);

        aanvraag = domainFacade.addMultiPeriodeAanvraag(aanvraag, begeleider);
        return "redirect:/aanvragen/detail?id=" + aanvraag.getId();
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
        Dossier dossier = this.getCurrentDossier();
        model.addAttribute("aanvragen", dossier.getAanvragen());
        model.addAttribute("dossier", dossier); 
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
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Bewerken">
    @RequestMapping(value = "/bewerk", params = "id", method = RequestMethod.GET)
    public String showBewerkForm(
            @RequestParam("id") long id,
            ModelMap model) {
        AbstractAanvraag aanvraag = this.domainFacade.findAanvraag(id);
        model.addAttribute("aanvraag", aanvraag);
        model.addAttribute("type", aanvraag.getClass().getSimpleName());
        
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
    public String updateAanvraag(@ModelAttribute("aanvraag") AbstractAanvraag aanvraag, RedirectAttributes redirectAttr) {
        this.domainFacade.edit(aanvraag);
        redirectAttr.addFlashAttribute("boodschap", new Boodschap("Aanvraag gewijzigd", "succes")); 
        return "redirect:/aanvragen/detail?id=" + aanvraag.getId();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Status veranderen">
    @RequestMapping(value = "goedkeuren", params = "id", method = RequestMethod.GET)
    public String aanvraagGoedkeuren(@RequestParam("id") long id, RedirectAttributes redirectAttr) {
        try {
            AbstractAanvraag aanvraag = domainFacade.findAanvraag(id);
            domainFacade.aanvaardAanvraag(aanvraag, this.getCurrentDossier().getGebruiker());   
            String mailVerstuurd = "";
            if(aanvraag instanceof DagAanvraag){
                mailVerstuurd = " Mails naar lectoren zijn verstuurd."; 
            }
            redirectAttr.addFlashAttribute("boodschap", new Boodschap("Aanvraag goedgekeurd." + mailVerstuurd, "succes"));     
        } catch (DomainException ex) {
            Logger.getLogger(AanvragenController.class.getName()).log(Level.SEVERE, null, ex);
            redirectAttr.addFlashAttribute("boodschap", new Boodschap("Er ging iets mis!", "error")); 
        }
        return "redirect:/aanvragen/detail?id=" + id;
    }

    @RequestMapping(value = "weigeren", params = "id", method = RequestMethod.GET)
    public String showAanvraagWeigerenForm(@RequestParam("id") long id) {
        return "/aanvragen/weigeren";
    }

    @RequestMapping(value = "weigeren", params = {"id", "reden"}, method = RequestMethod.POST)
    public String aanvraagWeigeren(@RequestParam("id") long id,
            @RequestParam("reden") String reden,
            RedirectAttributes redirectAttr) {
        try {
            AbstractAanvraag aanvraag = domainFacade.findAanvraag(id);
            domainFacade.weigerAanvraag(aanvraag, this.getCurrentDossier().getGebruiker(), reden);  
            redirectAttr.addFlashAttribute("boodschap", new Boodschap("Aanvraag geweigerd", "succes")); 
        } catch (DomainException ex) {
            Logger.getLogger(AanvragenController.class.getName()).log(Level.SEVERE, null, ex);
            redirectAttr.addFlashAttribute("boodschap", new Boodschap("Er ging iets mis!", "error")); 
        }
        return "redirect:/aanvragen/detail?id=" + id;
    }
    
    @RequestMapping(value = "sluiten", params = "id", method = RequestMethod.GET)
    public String aanvraagSluiten(@RequestParam("id") long id, RedirectAttributes redirectAttr) {
        AbstractAanvraag aanvraag = domainFacade.findAanvraag(id);
        domainFacade.sluitAanvraag(aanvraag, this.getCurrentDossier().getGebruiker());
        redirectAttr.addFlashAttribute("boodschap", new Boodschap("Aanvraag voltooid.", "succes"));
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