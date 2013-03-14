package be.khleuven.recordaid.servlet;

import be.khleuven.eindwerk.database.DatabaseException;
import be.khleuven.eindwerk.domain.Gebruiker;
import be.khleuven.eindwerk.domain.Rollen;
import be.khleuven.eindwerk.ui.RecordAidDomainFacade;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Hannes
 */
public class Handler_GebruikerDetail extends Handler
{
    public Handler_GebruikerDetail(RecordAidDomainFacade domainFacade)
    {
        super(domainFacade);
    }


    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response)
    {
        String gebruikerStr = request.getParameter("gebruiker");
        String action2 = request.getParameter("action2");

        try
        {
            Gebruiker gebruiker = domainFacade.getGebruiker(gebruikerStr);
            request.setAttribute("gebruiker", gebruiker);
            super.destination = "gebruikerDetail.jsp";


            if(action2 != null)
            {
                if(action2.equals("Gebruiker bijwerken"))
                {
                    String naam = request.getParameter("naam");
                    String voornaam = request.getParameter("voornaam");
                    String rolStr = request.getParameter("rol");

                    Rollen rol = getRol(rolStr);

                    gebruiker.setNaam(naam);
                    gebruiker.setVoornaam(voornaam);
                    gebruiker.setRol(rol);

                    domainFacade.updateGebruiker(gebruiker);
                }
                else if(action2.equals("Wachtwoord wijzigen naar temp"))
                {
                    String wachtwoord = super.saltWachtwoord("temp", request, response);
                    gebruiker.setWachtwoord(wachtwoord);

                    domainFacade.updateGebruiker(gebruiker);
                }
                else if(action2.equals("Gebruiker valideren"))
                {
                    gebruiker.valideer();
                    
                    domainFacade.updateGebruiker(gebruiker);
                }
                else if(action2.equals("Gebruiker devalideren"))
                {
                    gebruiker.deValideer();
                    
                    domainFacade.updateGebruiker(gebruiker);
                }
                
                super.destination = "ActionServlet?action=gebruikerBeheer";
            }
        }
        catch(DatabaseException ex)
        {
            handleException(request, response, ex);
        }
    }


    private Rollen getRol(String rolStr)
    {
        Rollen rol = Rollen.STUDENT;

        if(rolStr.equals("LEERKRACHT"))
        {
            rol = Rollen.LEERKRACHT;
        }
        if(rolStr.equals("BUDDY"))
        {
            rol = Rollen.BUDDY;
        }
        if(rolStr.equals("OPLEIDINGSHOOFD"))
        {
            rol = Rollen.OPLEIDINGSHOOFD;
        }
        if(rolStr.equals("KERNLID"))
        {
            rol = Rollen.KERNLID;
        }
        if(rolStr.equals("ADMIN"))
        {
            rol = Rollen.ADMIN;
        }

        return rol;
    }
}


