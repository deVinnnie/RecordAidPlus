package be.khleuven.recordaid.servlet;

import be.khleuven.eindwerk.database.DatabaseException;
import be.khleuven.eindwerk.domain.FAQ;
import be.khleuven.eindwerk.domain.Gebruiker;
import be.khleuven.eindwerk.domain.Support;
import be.khleuven.eindwerk.ui.RecordAidDomainFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Hannes Crombez <hannes.crombez@me.com>
 */
public class Handler_Support extends Handler
{
    public Handler_Support(RecordAidDomainFacade domainFacade)
    {
        super(domainFacade);
    }


    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response)
    {
        String lokaal = request.getParameter("lokaal");
        String opmerking = request.getParameter("opmerking");

        if(lokaal != null && opmerking != null)
        {
            if(lokaal.length() == 0 || opmerking.length() == 0)
            {
                request.setAttribute("error", "Gelieve een lokaal en een probleem in te vullen.");
            }
            else
            {
                try
                {
                    Gebruiker user = (Gebruiker) request.getSession().getAttribute("gebruiker");
                    Support support = new Support(opmerking, lokaal, user);

                    domainFacade.addSupport(support);
                    domainFacade.sendSupportMail(support);

                    request.setAttribute("succes", "Uw melding werd goed ontvangen, we proberen het probleem zo snel mogelijk op te lossen. Bedankt.");
                }
                catch(DatabaseException ex)
                {
                    handleException(request, response, ex);
                }
            }
        }
        else
        {
            request.setAttribute("error", "Gelieve een lokaal en een probleem in te vullen.");
        }
        
        destination = "support.jsp";
    }
}


