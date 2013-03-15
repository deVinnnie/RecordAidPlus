/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.khleuven.recordaid.servlet;

import be.khleuven.eindwerk.domain.Gebruiker;
import be.khleuven.eindwerk.ui.RecordAidDomainFacade;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Hannes
 */
public class Handler_GebruikerBeheer extends Handler
{
    public Handler_GebruikerBeheer(RecordAidDomainFacade domainFacade)
    {
        super(domainFacade);
    }


    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response)
    {
        Collection<Gebruiker> gebruikers = domainFacade.getGebruikers();
        request.setAttribute("gebruikers", gebruikers);
        super.destination = "gebruikerBeheer.jsp";
    }
}


