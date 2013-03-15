package be.khleuven.recordaid.servlet.handlers;

import be.khleuven.eindwerk.domain.Aanvraag;
import be.khleuven.eindwerk.domain.Gebruiker;
import be.khleuven.eindwerk.ui.RecordAidDomainFacade;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Hannes Crombez <hannes.crombez@me.com>
 */
public class Handler_Aanvragen extends Handler
{
    public Handler_Aanvragen(RecordAidDomainFacade domainFacade)
    {
        super(domainFacade);
    }


    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response)
    {
        Gebruiker gebruiker = (Gebruiker) request.getSession().getAttribute("gebruiker");

        Collection<Aanvraag> aanvragen = super.domainFacade.getAanvragenAanvrager(gebruiker);

        if(aanvragen.isEmpty())
        {
            request.setAttribute("geen_aanvragen", "U heeft momenteel nog geen aanvragen.");
            
            super.destination = "mijnaanvragen.jsp";
        }
        else
        {
            request.setAttribute("aanvragen", aanvragen);

            super.destination = "mijnaanvragen.jsp";
        }
    }
}


