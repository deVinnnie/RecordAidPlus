package be.khleuven.recordaid.mvc;

import be.khleuven.recordaid.opnames.OpnameMoment;
import be.khleuven.recordaid.domain.facade.RecordAidDomainFacade;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Vincent Ceulemans
 */
@Controller
public class HomeController {
    @Autowired
    private RecordAidDomainFacade domainFacade; 
    
    @RequestMapping(value={"/home","/"},method=RequestMethod.GET)
    public String showHome(ModelMap model){
        List<OpnameMoment> opnames = this.domainFacade.getZichtbareOpnames(); 
        model.addAttribute("opnames", opnames); 
        return "/home";
    }
    
    @RequestMapping(value="/beheer",method=RequestMethod.GET)
    public String showBeheer(){
        return "/beheer"; 
    }
    
    @RequestMapping("/error")
    public String customError(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        // retrieve some useful information from the request
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        try{
            Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
            model.addAttribute("exception",exception);
        }
        catch(Exception e){
 
        }
        return "/error";
    }
}