package be.khleuven.recordaid.servlet.handlers;

import be.khleuven.recordaid.domain.ForumTopic;
import be.khleuven.recordaid.domain.Gebruiker;
import be.khleuven.recordaid.domain.Message;
import be.khleuven.recordaid.ui.RecordAidDomainFacade;
import java.util.Collection;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Hannes Crombez <hannes.crombez@me.com>
 */
public class Handler_Forum extends Handler
{
    /**
     *
     * @param domainFacade
     */
    public Handler_Forum(RecordAidDomainFacade domainFacade)
    {
        super(domainFacade);
    }


    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response)
    {
        String methode = request.getParameter("method");
        //System.out.println("dit is de methode:" + methode);

        Gebruiker g = (Gebruiker) request.getSession().getAttribute("gebruiker");

        if(methode.equals("krijg"))
        {
            List<ForumTopic> topics = (List<ForumTopic>) domainFacade.getForumTopics();
            Collections.reverse(topics);
            request.setAttribute("topics", topics);
            super.destination = "berichten.jsp";
        }
        else if(methode.equals("nieuw"))
        {
            super.destination = "nieuwBericht.jsp";
        }
        else if(methode.equals("antwoord"))
        {
            Long id = Long.parseLong(request.getParameter("topicid"));
            request.setAttribute("topic", domainFacade.findForumTopic(id));

            super.destination = "berichtDetail.jsp";
        }
        else if(methode.equals("beantwoord"))
        {
            String text = request.getParameter("berichtInhoud");
            Long id = Long.parseLong(request.getParameter("topicid"));

            ForumTopic topic = domainFacade.findForumTopic(id);

            if(!text.equals("") && !text.equals(" ") && text != null)
            {
                Message m = new Message(g, GregorianCalendar.getInstance(), text);
                topic.addMessage(m);
                domainFacade.updateForumTopic(topic);
            }

            super.destination = "ActionServlet?action=berichten&method=detail&topicid=" + topic.getId();
        }
        else if(methode.equals("voegtoe"))
        {
            String titel = request.getParameter("titel");
            String text = request.getParameter("berichtInhoud");
            
            ForumTopic nieuw = new ForumTopic(titel);
            Message nieuwm = new Message(g, GregorianCalendar.getInstance(), text);
            nieuw.addMessage(nieuwm);

            domainFacade.addForumTopic(nieuw);
            super.destination = "ActionServlet?action=berichten&method=krijg";
        }
        else if(methode.equals("verwijder"))
        {
            Long id = Long.parseLong(request.getParameter("topicid"));
            ForumTopic topic = domainFacade.findForumTopic(id);

            Collection<Message> messages = domainFacade.getMessages();

            topic.setMessages(null);
            domainFacade.updateForumTopic(topic);
            domainFacade.removeForumTopic(id);

            super.destination = "ActionServlet?action=berichten&method=krijg";
        }
        else if(methode.equals("detail"))
        {
            Long id = Long.parseLong(request.getParameter("topicid"));
            ForumTopic topic = domainFacade.findForumTopic(id);

            request.setAttribute("topic", topic);
            super.destination = "berichtDetail.jsp";
        }
    }
}