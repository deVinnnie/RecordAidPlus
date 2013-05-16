package be.khleuven.recordaid.util;

import be.khleuven.recordaid.domain.facade.RecordAidDomainFacade;
import be.khleuven.recordaid.domain.gebruiker.Gebruiker;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 *
 * @author Vincent Ceulemans
 */
public class RecordAidAuthenticationHandler implements AuthenticationSuccessHandler {

    private AuthenticationSuccessHandler target = new SavedRequestAwareAuthenticationSuccessHandler();
    @Autowired
    private RecordAidDomainFacade domainFacade;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response, Authentication auth) throws IOException, ServletException {
        Gebruiker gebruiker = (Gebruiker) auth.getPrincipal();
        if (gebruiker.getEmailadres().equals("recordaid@khleuven.be")
                && domainFacade.getSetting("admin_first_login").getWaarde().equals("TRUE")) {
            response.sendRedirect("/opties");
        } else if (gebruiker.isForcePasswordChange()) {
            response.sendRedirect("/gebruikers/wachtwoord"); 
        } else {
            target.onAuthenticationSuccess(request, response, auth);
        }
    }
}