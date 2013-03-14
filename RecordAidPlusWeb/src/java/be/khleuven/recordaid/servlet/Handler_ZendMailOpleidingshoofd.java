package be.khleuven.recordaid.servlet;

import be.khleuven.eindwerk.database.DatabaseException;
import be.khleuven.eindwerk.domain.Aanvraag;
import be.khleuven.eindwerk.domain.Gebruiker;
import be.khleuven.eindwerk.ui.RecordAidDomainFacade;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Hannes
 */
public class Handler_ZendMailOpleidingshoofd extends Handler
{
    public Handler_ZendMailOpleidingshoofd(RecordAidDomainFacade domainFacade)
    {
        super(domainFacade);
    }
    
    
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response)
    {
        String opleidingshoofdStr = request.getParameter("OPHDEN");
        
        if(!opleidingshoofdStr.equals("Selecteer een opleidingshoofd..."))
        {
            try
            {
                Gebruiker opleidingshoofd = super.domainFacade.getGebruiker(opleidingshoofdStr);
                
                String aanvraagidStr = request.getParameter("aanvraagid");
                long aanvraagid = Long.parseLong(aanvraagidStr);
                Aanvraag aanvraag = super.domainFacade.findAanvraag(aanvraagid);
                
                super.domainFacade.sendMailVoorGoedkeuring(opleidingshoofd.getEmailadres(), aanvraag);
            }
            catch(DatabaseException ex)
            {
                handleException(request, response, ex);
            }
        }
        
        super.destination = "ActionServlet?action=alleAanvragen";
    }
}


