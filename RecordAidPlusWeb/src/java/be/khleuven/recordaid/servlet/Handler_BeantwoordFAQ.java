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
public class Handler_BeantwoordFAQ extends Handler
{
    public Handler_BeantwoordFAQ(RecordAidDomainFacade domainFacade)
    {
        super(domainFacade);
    }


    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response)
    {
        String actie = request.getParameter("action");
        
        if(actie.equals("beantwoordFAQ"))
        {
            ArrayList<FAQ> onbLijst = new ArrayList<FAQ>(domainFacade.getFAQs().size());
            ArrayList<FAQ> bLijst = new ArrayList<FAQ>(domainFacade.getFAQs().size());
            for(FAQ vraag : domainFacade.getFAQs())
            {
                if(vraag.isBeantwoord() == false)
                {
                    onbLijst.add(vraag);
                }
                else
                {
                    bLijst.add(vraag);
                }
            }
            request.setAttribute("onbLijst", onbLijst);
            request.setAttribute("bLijst", bLijst);
            super.destination = "beantwoordFAQ.jsp";
        }
        else if(actie.equals("updateFAQ"))
        {
            long id;
            try
            {
                id = Long.parseLong(request.getParameter("FAQID"));
                String vraagu = request.getParameter("FAQVRAAG");
                String antwoord = request.getParameter("FAQANTWOORD");
                String rel = request.getParameter("ISRELEVANT");
                boolean relevant = false;
                if(rel != null)
                {
                    relevant = true;
                }
                FAQ deFAQ = domainFacade.findFAQ(id);
                deFAQ.setVraag(vraagu);
                deFAQ.beantwoord(antwoord);
                deFAQ.setRelevant(relevant);
                domainFacade.updateFAQ(deFAQ);
                Gebruiker buddy = (Gebruiker) request.getSession().getAttribute("gebruiker");
                domainFacade.sendAntwoordFAQMail(deFAQ, buddy);
                super.destination = "ActionServlet?action=beantwoordFAQ";
            }
            catch(Exception ex)
            {
                super.handleException(request, response, ex);
            }
            super.destination = "ActionServlet?action=beantwoordFAQ";
        }
        else if(actie.equals("deleteFAQ"))
        {
            long id = Long.parseLong(request.getParameter("faqID"));
            domainFacade.removeFAQ(domainFacade.findFAQ(id));
            super.destination = "ActionServlet?action=beantwoordFAQ";
        }
        else if(actie.equals("faqDetails"))
        {
            long id = Long.parseLong(request.getParameter("faqID"));
            FAQ faq = domainFacade.findFAQ(id);
            request.setAttribute("faq", faq);
            super.destination = "faqDetails.jsp";
        }
    }
}