package be.khleuven.recordaid.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Vincent Ceulemans
 */
@Controller
@RequestMapping("/support")
public class SupportController{
    @RequestMapping(method=RequestMethod.GET)
    public String showSupportForm(){
        return "/support/nieuw"; 
    }
}