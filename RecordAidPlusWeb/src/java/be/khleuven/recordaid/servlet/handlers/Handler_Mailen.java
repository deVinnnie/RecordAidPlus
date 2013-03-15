package be.khleuven.recordaid.servlet;

import be.khleuven.eindwerk.domain.Gebruiker;
import be.khleuven.eindwerk.ui.RecordAidDomainFacade;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Maxime
 */
public class Handler_Mailen extends Handler
{
    public Handler_Mailen(RecordAidDomainFacade domainFacade)
    {
        super(domainFacade);
    }


    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response)
    {
        String method = request.getParameter("method");


        if(method.equals("buddyworden"))
        {
            Gebruiker g = (Gebruiker) request.getSession().getAttribute("gebruiker");

            if(domainFacade.sendNewBuddyMail(g))
            {
                request.setAttribute("mail_verstuurd_boodschap", "Er werd een e-mail gestuurd naar de RecordAid verantwoordelijke, we zullen snel contact met je opnemen.");
            }
            super.destination = "account.jsp";
        }
    }
}


