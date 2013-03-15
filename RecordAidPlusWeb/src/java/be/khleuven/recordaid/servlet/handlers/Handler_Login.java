package be.khleuven.recordaid.servlet.handlers;

import be.khleuven.eindwerk.database.DatabaseException;
import be.khleuven.eindwerk.domain.Gebruiker;
import be.khleuven.eindwerk.ui.RecordAidDomainFacade;
import be.khleuven.recordaid.util.WachtwoordUtility;
import javax.servlet.http.*;

/**
 *
 * @author Hannes Crombez <hannes.crombez@me.com>
 */
public class Handler_Login extends Handler {

    /**
     *
     * @param domainFacade
     */
    public Handler_Login(RecordAidDomainFacade domainFacade) {
        super(domainFacade);
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String wachtwoord = request.getParameter("wachtwoord");
        wachtwoord = wachtwoord.trim();
        WachtwoordUtility wachtwoordChecker = new WachtwoordUtility(); 
        wachtwoord = wachtwoordChecker.hashWachtwoord(wachtwoord); 

        try {
            Gebruiker dbGebruiker = super.domainFacade.getGebruiker(email);

            if (!dbGebruiker.isGevalideerd()) {
                gebruikerNietGevalideerd(request, response);
            } else if (dbGebruiker.getWachtwoord().equals(wachtwoord)) {
                login(request, response, dbGebruiker);
            } else {
                loginNietMogelijk(request, response);
            }
        } catch (DatabaseException ex) {
            loginNietMogelijk(request, response);
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response, Gebruiker gebruiker) {
        HttpSession session = request.getSession(true);
        session.setAttribute("gebruiker", gebruiker);
        request.setAttribute("result", "Nu ingelogd.");
        super.destination = "home.jsp";
    }

    private void loginNietMogelijk(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("gegevens_onjuist", "De combinatie emailadres / paswoord bestaat niet of het emailadres is niet geregistreerd.");
        super.destination = "login.jsp";
    }

    private void gebruikerNietGevalideerd(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("gegevens_onjuist", "Het emailadres is nog niet gevalideerd. Klik op de link in de email die u bij registratie heeft ontvangen om het emailadres te valideren.");
        super.destination = "login.jsp";
    }
}