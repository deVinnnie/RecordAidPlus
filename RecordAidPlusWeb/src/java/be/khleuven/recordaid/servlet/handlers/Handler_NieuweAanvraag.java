package be.khleuven.recordaid.servlet.handlers;

import be.khleuven.recordaid.database.DatabaseException;
import be.khleuven.recordaid.domain.Aanvraag;
import be.khleuven.recordaid.domain.Gebruiker;
import be.khleuven.recordaid.ui.RecordAidDomainFacade;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Hannes Crombez <hannes.crombez@me.com>
 */
public class Handler_NieuweAanvraag extends Handler
{
    private String error = "";

    public Handler_NieuweAanvraag(RecordAidDomainFacade domainFacade)
    {
        super(domainFacade);
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response)
    { 
        String lector = request.getParameter("lector");
        String vak = request.getParameter("vak");
        String lokaal = request.getParameter("lokaal");
        String reeks = request.getParameter("reeks");
        String reden = request.getParameter("reden");
        String departement = request.getParameter("departement");
        String datumStr = request.getParameter("datum");
        String beginUurH = request.getParameter("beginUurH");
        String beginUurM = request.getParameter("beginUurM");
        String eindUurH = request.getParameter("eindUurH");
        String eindUurM = request.getParameter("eindUurM");

        if(validate(lector, vak, lokaal, reeks, reden, datumStr, beginUurH, beginUurM, eindUurH, eindUurM))
        {
            try {
                String beginUur = beginUurH + ":" + beginUurM;
                String eindUur = eindUurH + ":" + eindUurM;

                SimpleDateFormat fmt = new SimpleDateFormat(); 
                Calendar datum = Calendar.getInstance(); 
                datum.setTime(fmt.parse(datumStr)); 
                
                Gebruiker gebruiker = (Gebruiker) request.getSession().getAttribute("gebruiker");
                String geb = gebruiker.getEmailadres();
                try
                {
                    gebruiker = domainFacade.getGebruiker(geb);
                }
                catch(DatabaseException ex)
                {
                    super.handleException(request, response, ex);
                }


                Aanvraag aanvraag_1 = new Aanvraag(gebruiker, lector, vak, lokaal, reeks, reden, departement, beginUur, eindUur, datum);
                super.domainFacade.addAanvraag(aanvraag_1);
                
                super.domainFacade.sendNieuweAanvraagIngediend(aanvraag_1);

                Collection<Aanvraag> aanvragen = super.domainFacade.getAanvragenAanvrager(gebruiker);
                request.setAttribute("aanvragen", aanvragen);
                super.destination = "mijnaanvragen.jsp";
            } catch (ParseException ex) {
                Logger.getLogger(Handler_NieuweAanvraag.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            request.setAttribute("nieuwe_aanvraag_error", error);
            super.destination = "nieuweAanvraag.jsp";
        }
    }

    private boolean validate(String lector, String vak, String lokaal, String reeks, String reden, String datumStr, String beginUurH, String beginUurM, String eindUurH, String eindUurM)
    {
        boolean ok = true;

        if(vak.trim().length() == 0)
        {
            error += "Een vak moet ingevuld worden<br>";
            ok = false;
        }

        if(reeks.trim().length() == 0)
        {
            error += "Een klas / reeks moet ingevuld worden<br>";
            ok = false;
        }

        if(lokaal.trim().length() == 0)
        {
            error += "Een lokaal moet ingevuld worden<br>";
            ok = false;
        }

        if(datumStr.length() == 0)
        {
            error += "Een datum moet ingevuld worden<br>";
            ok = false;
        }

        int beginH = Integer.parseInt(beginUurH);
        int beginM = Integer.parseInt(beginUurM);
        int eindH = Integer.parseInt(eindUurH);
        int eindM = Integer.parseInt(eindUurM);

        if(beginH > eindH)
        {
            error += "Het beginuur kan niet later zijn dan het einduur<br>";
            ok = false;
        }

        if(beginH == eindH)
        {
            if(beginM >= eindM)
            {
                error += "Het beginuur kan niet later zijn dan het einduur<br>";
                ok = false;
            }
        }

        if(!(lector.matches("^[_a-z0-9-]+(.[_a-z0-9-]+)*@khleuven.be$")))
        {
            error += "Dit is geen goed emailadres voor een leerkracht<br>";
            ok = false;
        }

        if(reden.trim().length() == 0)
        {
            error += "Een reden moet ingevuld worden";
            ok = false;
        }

        return ok;
    }
}