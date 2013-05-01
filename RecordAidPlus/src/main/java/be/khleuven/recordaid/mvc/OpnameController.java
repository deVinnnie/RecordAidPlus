package be.khleuven.recordaid.mvc;

import be.khleuven.recordaid.util.propertyeditors.CalendarPropertyEditor;
import be.khleuven.recordaid.util.propertyeditors.OpnameMethodePropertyEditor;
import be.khleuven.recordaid.util.propertyeditors.DepartementPropertyEditor;
import be.khleuven.recordaid.opnames.OpnameMethode;
import be.khleuven.recordaid.opnames.OpnameLijst;
import be.khleuven.recordaid.opnames.OpnameMoment;
import be.khleuven.recordaid.domain.facade.RecordAidDomainFacade;
import be.khleuven.recordaid.domain.*; 
import be.khleuven.recordaid.util.*; 
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
public class OpnameController{
    @Autowired
    private RecordAidDomainFacade domainFacade; 
    
    public OpnameController(){}

    public OpnameController(RecordAidDomainFacade domainFacade) {
        this.domainFacade = domainFacade;
    }
    
    @RequestMapping(value="/editor")
    public String showNieuweOpnameForm(ModelMap model){
        OpnameMoment opnameMoment = new OpnameMoment();
        opnameMoment.setZichtbaar(true);
        model.addAttribute("opname", opnameMoment);
        model.addAttribute("alleLectoren", domainFacade.getLectoren()); 
        model.addAttribute("opnameMethodes", domainFacade.getOpnameMethodes());
        return "/opnames/editor"; 
    }
    
    @RequestMapping(value="/editor", params = "id", method= RequestMethod.GET)
    public String showEditorOpnameForm(@RequestParam("id") long id, ModelMap model){
        OpnameMoment opnameMoment = domainFacade.findOpnameMoment(id); 
        if(opnameMoment==null){
            return "redirect:/opnames/beheer"; 
        }
        model.addAttribute("opname", opnameMoment);
        model.addAttribute("alleLectoren", domainFacade.getLectoren()); 
        model.addAttribute("opnameMethodes", domainFacade.getOpnameMethodes());
        return "/opnames/editor"; 
    }
    
    @RequestMapping(value="/editor", method=RequestMethod.POST)
    public String addNieuweOpname(@Valid OpnameMoment opname, BindingResult bindingResult) throws DomainException{
        domainFacade.edit(opname);
        return "redirect:/opnames/beheer";
    }
    
    @RequestMapping("/beheer")
    public String showBeheer(ModelMap model){
        OpnameLijst lijst = new OpnameLijst(domainFacade.getOpnames()); 
        model.addAttribute("opnames", lijst); 
        return "/opnames/beheer"; 
    }
    
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(Calendar.class,  new CalendarPropertyEditor());
        dataBinder.registerCustomEditor(Departement.class, new DepartementPropertyEditor(domainFacade));
        dataBinder.registerCustomEditor(OpnameMethode.class, new OpnameMethodePropertyEditor(domainFacade));
    }
}