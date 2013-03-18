package be.khleuven.recordaid.servlet.handlers;

import be.khleuven.recordaid.database.DatabaseException;
import be.khleuven.recordaid.domain.Gebruiker;
import be.khleuven.recordaid.ui.RecordAidDomainFacade;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Maxime
 */
public class Handler_ValidatieAccount extends Handler
{
    public Handler_ValidatieAccount(RecordAidDomainFacade domainFacade)
    {
        super(domainFacade);
    }


    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response)
    {
        String validatiecode = request.getParameter("validatiecode");

        if(validatiecode.length() == 26)
        {
            Gebruiker g = domainFacade.getGebruikerByValidatiecode(validatiecode);

            if(g != null)
            {
                g.valideer(validatiecode);
                try
                {
                    domainFacade.updateGebruiker(g);
                }
                catch(DatabaseException ex)
                {
                    handleException(request, response, ex);
                }
                request.setAttribute("gelukt_melding", "Uw emailadres is met succes gevalideert. U kan nu <a href='login.jsp'>inloggen</a> met uw nieuw account.");

            }
            else
            {
                request.setAttribute("fout_melding", "De registratiecode die u heeft ingegeven werd niet geaccepteerd.");
            }

        }
        else
        {
            request.setAttribute("fout_melding", "De registratiecode die u heeft ingegeven werd niet geaccepteerd.");
        }
        super.destination = "valideren.jsp";
    }
}