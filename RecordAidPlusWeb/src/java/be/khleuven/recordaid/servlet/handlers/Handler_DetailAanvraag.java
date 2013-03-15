package be.khleuven.recordaid.servlet.handlers;

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
public class Handler_DetailAanvraag extends Handler {

    public Handler_DetailAanvraag(RecordAidDomainFacade domainFacade) {
        super(domainFacade);
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Long id = Long.parseLong(request.getParameter("id"));

        Collection<Gebruiker> tmp = domainFacade.getGebruikers(Rollen.BUDDY);
        tmp.addAll(domainFacade.getGebruikers(Rollen.KERNLID));
                
        Collection<Gebruiker> opleidingsh = domainFacade.getGebruikers(Rollen.OPLEIDINGSHOOFD);
        
        Aanvraag aanvraag = domainFacade.findAanvraag(id);
        request.setAttribute("aanvraag", aanvraag);
        request.setAttribute("statussen", Status.values());
        request.setAttribute("buddies", tmp);
        request.setAttribute("opleidingsh", opleidingsh);

        Gebruiker g = (Gebruiker) request.getSession().getAttribute("gebruiker");

        if (g.getRol() == Rollen.STUDENT || g.getRol() == Rollen.LEERKRACHT) {
            super.destination = "aanvraagDetail.jsp";
        } else if (aanvraag.getToegewezenLid() != null && aanvraag.getToegewezenLid().equals(g)) {
            super.destination = "aanvraagDetail_1.jsp";
        } else {
            super.destination = "aanvraagDetail_1.jsp";
        }
    }
}