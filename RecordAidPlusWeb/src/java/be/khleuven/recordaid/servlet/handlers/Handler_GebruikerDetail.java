package be.khleuven.recordaid.servlet.handlers;

import be.khleuven.recordaid.database.DatabaseException;
import be.khleuven.recordaid.domain.Gebruiker;
import be.khleuven.recordaid.domain.Rollen;
import be.khleuven.recordaid.ui.RecordAidDomainFacade;
import be.khleuven.recordaid.util.WachtwoordUtility;
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
                    WachtwoordUtility wachtwoordChecker = new WachtwoordUtility(); 
                    String wachtwoord = wachtwoordChecker.hashWachtwoord("temp");
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
        Rollen rol; 
        
        try{
            rol = Rollen.valueOf(rolStr.toUpperCase()); 
        }
        catch(IllegalArgumentException ex){
            //De rol is niet gevonden. 
            rol = Rollen.STUDENT;
        }
        
        return rol; 
    }
}