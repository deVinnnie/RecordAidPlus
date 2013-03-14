package be.khleuven.recordaid.servlet;

import be.khleuven.eindwerk.database.DatabaseException;
import be.khleuven.eindwerk.domain.Gebruiker;
import be.khleuven.eindwerk.domain.Rollen;
import be.khleuven.eindwerk.ui.RecordAidDomainFacade;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Hannes Crombez <hannes.crombez@me.com>
 */
public class Handler_Registreer extends Handler
{
    private String error = "";


    public Handler_Registreer(RecordAidDomainFacade domainFacade)
    {
        super(domainFacade);
    }


    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response)
    {
        String email = request.getParameter("email");
        String naam = request.getParameter("naam");
        String voornaam = request.getParameter("voornaam");
        String wachtwoord = request.getParameter("wachtwoord1");
        String wachtwoord2 = request.getParameter("wachtwoord2");


        email = email.trim();
        naam = naam.trim();
        voornaam = voornaam.trim();

        if(valideer(email, naam, voornaam, wachtwoord, wachtwoord2))
        {
            Rollen rol = Rollen.STUDENT;
            if(email.matches("^.+@khleuven.be$"))
            {
                rol = Rollen.LEERKRACHT;
            }

            wachtwoord = super.saltWachtwoord(wachtwoord, request, response);

            try
            {
                Gebruiker gebruiker = new Gebruiker(rol, email, naam, voornaam, wachtwoord);

                super.domainFacade.addGebruiker(gebruiker);

                super.domainFacade.sendValidatieEmail(gebruiker);
                super.destination = "valideren.jsp";
            }
            catch(DatabaseException exception)
            {
                if(exception.getMessage().equals("gebruiker bestaat reeds"))
                {
                    request.setAttribute("gebruiker_bestaat_error", "Er is reeds een gebruiker geregistreerd met dit emailadres. Gelieve aan te melden met uw emailadres en wachtwoord.");
                    super.destination = "registreren.jsp";
                }
                else
                {
                    handleException(request, response, exception);
                }
            }
        }
        else
        {
            request.setAttribute("gebruiker_bestaat_error", error);
            super.destination = "registreren.jsp";
        }
    }


    private boolean valideer(String email, String naam, String voornaam, String wachtwoord, String wachtwoord2)
    {
        boolean ok = true;


        if(!(email.matches("^[_a-z0-9-]+(.[_a-z0-9-]+)*@khleuven.be$") || email.matches("^[_a-z0-9-]+(.[_a-z0-9-]+)*@student.khleuven.be$")))
        {
            error += "Geen goed emailadres<br>";
            ok = false;
        }

        if(!naam.matches("([a-zA-ZáíóúñÑö]+( |'|-)?)+"))
        {
            error += "Geen goede naam<br>";
            ok = false;
        }

        if(!voornaam.matches("([a-zA-ZáíóúñÑö]+( |'|-)?)+"))
        {
            error += "Geen goede voornaam<br>";
            ok = false;
        }

        if(!(wachtwoord.length() > 2))
        {
            error += "Uw wachtwoord is te kort<br>";
            ok = false;
        }

        if(!(wachtwoord.equals(wachtwoord2)))
        {
            error += "Uw wachtwoorden komen niet overeen";
            ok = false;
        }

        return ok;
    }
}

