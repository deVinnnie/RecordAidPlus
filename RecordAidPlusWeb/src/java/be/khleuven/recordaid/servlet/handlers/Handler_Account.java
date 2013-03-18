package be.khleuven.recordaid.servlet.handlers;

import be.khleuven.recordaid.database.DatabaseException;
import be.khleuven.recordaid.domain.Gebruiker;
import be.khleuven.recordaid.ui.RecordAidDomainFacade;
import be.khleuven.recordaid.servlet.ActionServlet;
import be.khleuven.recordaid.util.WachtwoordUtility;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hannes Crombez <hannes.crombez@me.com>
 */
public class Handler_Account extends Handler {
    public Handler_Account(RecordAidDomainFacade domainFacade) {
        super(domainFacade);
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String oudWachtwoord = request.getParameter("oud_ww");
        String wachtwoord1 = request.getParameter("nieuw_ww1");
        String wachtwoord2 = request.getParameter("nieuw_ww2");

        if (wachtwoord1 != null && wachtwoord2 != null && oudWachtwoord != null) {
            WachtwoordUtility wachtwoordChecker = new WachtwoordUtility(); 
            
            oudWachtwoord = oudWachtwoord.trim();
            wachtwoord1 = wachtwoord1.trim();
            wachtwoord2 = wachtwoord2.trim();
            
            Gebruiker gebruiker = (Gebruiker) request.getSession().getAttribute("gebruiker");

            if (wachtwoordChecker.controleerWachtwoord(oudWachtwoord, gebruiker.getWachtwoord())){
                if (wachtwoord1.equals(wachtwoord2)){
                    gebruiker.setWachtwoord(wachtwoordChecker.hashWachtwoord(wachtwoord1)); 
                    try {
                        domainFacade.updateGebruiker(gebruiker);
                        request.setAttribute("wachtwoord_verandert_boodschap", "Uw wachtwoord werd met succes aangepast.");
                    } catch (DatabaseException ex) {
                        Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    request.setAttribute("wachtwoord_fout_error", "De wachtwoorden die u hebt ingegeven komen niet overeen.");
                }
            }else{
                request.setAttribute("wachtwoord_fout_error", "Uw huidig wachtwoord is foutief.");
            }
        }
        super.destination = "account.jsp";
    }
}