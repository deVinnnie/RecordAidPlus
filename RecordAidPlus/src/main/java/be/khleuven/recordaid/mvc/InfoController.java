package be.khleuven.recordaid.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return "/info/" + pagina;
    }
}