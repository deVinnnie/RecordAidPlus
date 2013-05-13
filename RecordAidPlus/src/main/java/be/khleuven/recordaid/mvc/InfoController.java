package be.khleuven.recordaid.mvc;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*; 

/**
 * Dit is belachelijk: een controller met één mapping die de mapping zelf
 * teruggeeft.
 *
 * @author Vincent Ceulemans
 */
@Controller
public class InfoController {
    @RequestMapping(value = "/info/{pagina}")
    public String handleInfoRequest(@PathVariable("pagina") String pagina, ModelMap model) {
        /*title, url*/
        Map<String, String> links = new LinkedHashMap<String, String>();
        links.put("Voorstelling", "/info/voorstelling"); 
        links.put("Studenten", "/info/studenten"); 
        links.put("Lectoren","/info/lectoren");
        links.put("Problemen", "/info/problemen"); 
        links.put("Buddies", "/info/buddies"); 
        links.put("Studentenbegeleiding", "/info/studentenbegeleiding"); 
        links.put("Media", "/info/media"); 
        links.put("Contact", "/info/contact"); 
        model.addAttribute("links",links); 
        return "/info/" + pagina;
    }
}