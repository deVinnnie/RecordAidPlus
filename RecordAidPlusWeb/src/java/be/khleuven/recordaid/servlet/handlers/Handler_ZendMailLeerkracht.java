package be.khleuven.recordaid.servlet.handlers;

import be.khleuven.recordaid.domain.Aanvraag;
import be.khleuven.recordaid.ui.RecordAidDomainFacade;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Hannes
 */
public class Handler_ZendMailLeerkracht extends Handler
{
    public Handler_ZendMailLeerkracht(RecordAidDomainFacade domainFacade)
    {
        super(domainFacade);
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response)
    {
        String lectorMail = request.getParameter("lectorMail");

        String aanvraagidStr = request.getParameter("aanvraagid");
        long aanvraagid = Long.parseLong(aanvraagidStr);
        Aanvraag aanvraag = super.domainFacade.findAanvraag(aanvraagid);

        super.domainFacade.sendMailVoorGoedkeuring(lectorMail, aanvraag);

        super.destination = "ActionServlet?action=alleAanvragen";
    }
}