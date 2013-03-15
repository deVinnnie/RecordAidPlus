package be.khleuven.recordaid.servlet;

import be.khleuven.eindwerk.domain.FAQ;
import be.khleuven.eindwerk.domain.Gebruiker;
import be.khleuven.eindwerk.ui.RecordAidDomainFacade;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Hannes Crombez <hannes.crombez@me.com>
 */
public class Handler_FAQ extends Handler
{
    public Handler_FAQ(RecordAidDomainFacade domainFacade)
    {
        super(domainFacade);
    }


    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response)
    {
        String actie = request.getParameter("action");

        if(actie.equals("faq"))
        {
            ArrayList<FAQ> relevanteLijst = new ArrayList<FAQ>(domainFacade.getFAQs().size());
            for(FAQ vraag : domainFacade.getFAQs())
            {
                if(vraag.isRelevant() == true)
                {
                    relevanteLijst.add(vraag);
                }
            }
            request.setAttribute("faqs", relevanteLijst);
            super.destination = "faq.jsp";

        }
        else if(actie.equals("nieuweFAQ"))
        {
            String vraag = request.getParameter("vraag");
            Gebruiker gebruiker = (Gebruiker) request.getSession().getAttribute("gebruiker");
            FAQ faq = new FAQ(gebruiker, vraag);
            domainFacade.addFAQ(faq);
            domainFacade.sentNieuweFAQMail(faq);
            request.setAttribute("action", "faq");
            request.setAttribute("succes", "Bedankt voor uw vraag, ze werd goed ontvangen. U zal spoedig een email ontvangen met een antwoord.");
            super.destination = "ActionServlet?action=faq";
        }


    }
}