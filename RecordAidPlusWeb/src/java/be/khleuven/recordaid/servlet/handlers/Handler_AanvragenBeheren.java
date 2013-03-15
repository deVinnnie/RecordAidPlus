/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.khleuven.recordaid.servlet.handlers;

import be.khleuven.eindwerk.database.DatabaseException;
import be.khleuven.eindwerk.domain.Aanvraag;
import be.khleuven.eindwerk.domain.Gebruiker;
import be.khleuven.eindwerk.domain.Rollen;
import be.khleuven.eindwerk.domain.Status;
import be.khleuven.eindwerk.ui.RecordAidDomainFacade;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Maxime
 */
public class Handler_AanvragenBeheren extends Handler
{
    public Handler_AanvragenBeheren(RecordAidDomainFacade domainFacade)
    {
        super(domainFacade);
    }


    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response)
    {
        String method = request.getParameter("methode");

        if(method != null)
        {
            if(method.equals("updaten"))
            {
                Long id = Long.parseLong(request.getParameter("aanvraagid")); 
                Aanvraag a = domainFacade.findAanvraag(id);

                String status = request.getParameter("aanvraagStatus");
                String gebruiker = request.getParameter("toegewezenLid");
                String link = request.getParameter("linkVideo");

                if(status != null)
                {
                    a.setStatus(Status.valueOf(status));
                    try
                    {
                        domainFacade.updateAanvraag(a);
                    }
                    catch(DatabaseException ex)
                    {
                        handleException(request, response, ex);
                    }
                    if(Status.valueOf(status) == Status.BESCHIKBAAR || Status.valueOf(status) == Status.AFGEKEURD)
                    {
                        domainFacade.sendAanvraagBeschikbaar(a);
                    }
                }
                if(gebruiker != null)
                {
                    try
                    {
                        Gebruiker g = domainFacade.getGebruiker(gebruiker);
                        a.setToegewezenLid(g);
                        domainFacade.updateAanvraag(a);
                        domainFacade.sendNieuweAanvraagMailNaarBuddy(a, g);
                    }
                    catch(DatabaseException ex)
                    {
                        handleException(request, response, ex);
                    }
                }

                if(link != null)
                {
                    a.setLinkNaarVideo(link);
                    try
                    {
                        domainFacade.updateAanvraag(a);
                    }
                    catch(DatabaseException ex)
                    {
                        handleException(request, response, ex);
                    }
                }


                Collection<Gebruiker> tmp = domainFacade.getGebruikers(Rollen.BUDDY);
                tmp.addAll(domainFacade.getGebruikers(Rollen.KERNLID));

                request.setAttribute("aanvraag", a);
                request.setAttribute("statussen", Status.values());
                request.setAttribute("buddies", tmp);

                super.destination = "aanvraagDetail_1.jsp";
            }
            else
            {
                Gebruiker gebruiker = (Gebruiker) request.getSession().getAttribute("gebruiker");
                Collection<Aanvraag> aanvragen = null;

                if(gebruiker.getRol() == Rollen.BUDDY)
                {
                    aanvragen = super.domainFacade.getAanvragenToegewezenLid(gebruiker);
                }
                else
                {
                    aanvragen = super.domainFacade.getAanvragen();
                }

                if(aanvragen.isEmpty())
                {
                    request.setAttribute("geen_aanvragen", "Er zijn momenteel nog geen aanvragen.");
                }

                request.setAttribute("aanvragen", aanvragen);
                super.destination = "Aanvragen.jsp";
            }
        }
        else
        {
            Gebruiker gebruiker = (Gebruiker) request.getSession().getAttribute("gebruiker");
            Collection<Aanvraag> aanvragen = null;

            if(gebruiker.getRol() == Rollen.BUDDY)
            {
                aanvragen = super.domainFacade.getAanvragenToegewezenLid(gebruiker);
            }
            else
            {
                aanvragen = super.domainFacade.getAanvragen();
            }

            if(aanvragen.isEmpty())
            {
                request.setAttribute("geen_aanvragen", "Er zijn momenteel nog geen aanvragen.");
            }

            request.setAttribute("aanvragen", aanvragen);
            super.destination = "Aanvragen.jsp";
        }
    }
}