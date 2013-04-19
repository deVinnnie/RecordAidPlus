package be.khleuven.recordaid.mvc;

import be.khleuven.recordaid.domain.gebruiker.*; 
import be.khleuven.recordaid.domain.facade.RecordAidDomainFacade;
import be.khleuven.recordaid.database.DatabaseException;
import be.khleuven.recordaid.domain.*; 
import be.khleuven.recordaid.domain.aanvragen.*; 
import be.khleuven.recordaid.util.*; 
import java.util.*; 
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AanvragenController{
    @Autowired
    private RecordAidDomainFacade domainFacade; 
    
    public AanvragenController(){}

    public AanvragenController(RecordAidDomainFacade domainFacade) {
        this.domainFacade = domainFacade;
    }
    
    /**
     * Fetches the dossier of the current user in this session. 
     * 
     * @return The dossier of the current user. 
     */
    private Dossier getCurrentDossier(){
        Gebruiker gebruiker = (Gebruiker) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Dossier dossier = domainFacade.getDossier(gebruiker); 
        return dossier; 
    }
    
    @RequestMapping("/nieuw")
    public String showNieuwAanvraagForm(ModelMap model)throws Exception{
        Dossier dossier = this.getCurrentDossier(); 
        List<Departement> departementen = domainFacade.getDepartementen(); 
        model.addAttribute("nieuweAanvraag", new DagAanvraag(dossier,departementen.get(0))); 
        model.addAttribute("departementen", departementen); 
        return "/aanvragen/nieuw"; 
    }
    
    @RequestMapping(value="/nieuw",method= RequestMethod.POST)
    public String nieuweAanvraag(@Valid DagAanvraag aanvraag, 
                    BindingResult bindingResult, 
                    ModelMap model) throws DomainException{
            aanvraag = domainFacade.create(aanvraag);
            Dossier dossier = this.getCurrentDossier(); 
            dossier.addAanvraag(aanvraag);
            domainFacade.edit(dossier); 
            return "redirect:/aanvragen/nieuwe_opname?aanvraag="+aanvraag.getId();
    }
    
    @RequestMapping(value="/nieuwe_opname", params="aanvraag")
    public String showNieuweOpnameForm(ModelMap model, @RequestParam("aanvraag") long aanvraag){
        model.addAttribute("nieuweOpname", new OpnameMoment());
        model.addAttribute("alleLectoren", domainFacade.getLectoren()); 
        model.addAttribute("opnameMethodes", domainFacade.getOpnameMethodes());
        return "/aanvragen/nieuwe_opname"; 
    }
    
    @RequestMapping(value="/nieuwe_opname", params="aanvraag", method=RequestMethod.POST)
    public String addNieuweOpname(
            @Valid OpnameMoment opname, BindingResult bindingResult, 
            @RequestParam("aanvraag") long aanvraagId, @RequestParam("action") String action
            ) throws DomainException{
        domainFacade.create(opname);
        
        Dossier dossier = this.getCurrentDossier();
        AbstractAanvraag aanvraag = dossier.getAanvraag(aanvraagId);
        opname.getLokaal().setDepartement(aanvraag.getDepartement());
        domainFacade.edit(opname); 
        
        aanvraag.addOpnameMoment(opname);
        aanvraag = domainFacade.edit(aanvraag);

        if (action.equals("Gereed")) {
            return "redirect:/aanvragen/detail?id=" + aanvraag.getId();
        } else {
            return "redirect:/aanvragen/nieuwe_opname?aanvraag=" + aanvraag.getId();
        }
    }
   
    @RequestMapping("/beheer")
    public String showBeheer(ModelMap model){
        model.addAttribute("aanvragen", domainFacade.getAanvragen()); 
        return "/aanvragen/beheer"; 
    }
    
    @RequestMapping(value={"/", "", "/mijnaanvragen"},method=RequestMethod.GET)
    public String showMijnAanvragen(ModelMap model){
        Gebruiker gebruiker = (Gebruiker) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Dossier dossier = domainFacade.getDossier(gebruiker); 
        model.addAttribute("aanvragen", dossier.getAanvragen()); 
        return "/aanvragen/mijnaanvragen"; 
    }
    
    @RequestMapping(value="/detail", params="id", method= RequestMethod.GET)
    public String showDetail(
            @RequestParam("id") long id, 
            ModelMap model
            ){
        DagAanvraag aanvraag = this.domainFacade.findAanvraag(id); 
        model.addAttribute("aanvraag", aanvraag); 
        return "/aanvragen/detail"; 
    }
    
    @RequestMapping(value="/bewerk", params="id", method= RequestMethod.GET)
    public String showBewerkForm(
            @RequestParam("id") long id, 
            ModelMap model
            ){
        DagAanvraag aanvraag = this.domainFacade.findAanvraag(id); 
        model.addAttribute("aanvraag", aanvraag); 
        return "/aanvragen/bewerk"; 
    }
    
    @RequestMapping(value="/bewerk", method= RequestMethod.POST)
    public String updateAanvraag(
            @Valid DagAanvraag aanvraag, 
            BindingResult bindingResult, 
            ModelMap model
            ){
        try {
            this.domainFacade.updateAanvraag(aanvraag);
            return "redirect:aanvragen/detail?id="+aanvraag.getId();
        } catch (DatabaseException ex) {
           return "/aanvragen/beheer";
        }      
    }
    
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(Calendar.class,  new CalendarPropertyEditor());
        dataBinder.registerCustomEditor(Departement.class, new DepartementPropertyEditor(domainFacade));
        dataBinder.registerCustomEditor(OpnameMethode.class, new OpnameMethodePropertyEditor(domainFacade));
    }
}