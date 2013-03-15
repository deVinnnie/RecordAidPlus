package be.khleuven.recordaid.servlet;

import be.khleuven.eindwerk.ui.RecordAidDomainFacade;
import javax.servlet.ServletException;


/**
 *
 * @author Hannes Crombez <hannes.crombez@me.com>
 */
public class HandlerFactory
{
    private RecordAidDomainFacade domainFacade;


    public HandlerFactory(RecordAidDomainFacade domainFacade) throws ServletException
    {
        if(domainFacade == null)
        {
            throw new ServletException("domainFacade null");
        }

        this.domainFacade = domainFacade;
    }


    public Handler getHandler(String handlerDescription) throws ServletException
    {
        Handler handler = null;

        if(handlerDescription.equals("login"))
        {
            handler = new Handler_Login(this.domainFacade);
        }
        else if(handlerDescription.equals("registreer"))
        {
            handler = new Handler_Registreer(this.domainFacade);
        }
        else if(handlerDescription.equals("mijnAanvragen"))
        {
            handler = new Handler_Aanvragen(this.domainFacade);
        }
        else if(handlerDescription.equals("nieuweAanvraag"))
        {
            handler = new Handler_NieuweAanvraag(this.domainFacade);
        }
        else if(handlerDescription.equals("update_password"))
        {
            handler = new Handler_Account(domainFacade);
        }
        else if(handlerDescription.equals("logout"))
        {
            handler = new Handler_Logout(domainFacade);
        }
        else if(handlerDescription.equals("detail"))
        {
            handler = new Handler_DetailAanvraag(domainFacade);
        }
        else if(handlerDescription.equals("faq") || handlerDescription.equals("nieuweFAQ"))
        {
            handler = new Handler_FAQ(this.domainFacade);
        }
        else if(handlerDescription.equals("alleAanvragen"))
        {
            handler = new Handler_AanvragenBeheren(domainFacade);
        }
        else if(handlerDescription.equals("updateAanvraag"))
        {
            handler = new Handler_AanvragenBeheren(domainFacade);
        }
        else if(handlerDescription.equals("reserveren") || handlerDescription.equals("verwijderItem"))
        {
            handler = new Handler_Reserveren(domainFacade);
        }
        else if(handlerDescription.equals("itemBeheer"))
        {
            handler = new Handler_Itembeheer(domainFacade);
        }
        else if(handlerDescription.equals("berichten"))
        {
            handler = new Handler_Forum(domainFacade);
        }
        else if(handlerDescription.equals("mailen"))
        {
            handler = new Handler_Mailen(domainFacade);
        }
        else if(handlerDescription.equals("beantwoordFAQ") || handlerDescription.equals("updateFAQ") || handlerDescription.equals("deleteFAQ") || handlerDescription.equals("faqDetails"))
        {
            handler = new Handler_BeantwoordFAQ(domainFacade);
        }
        else if(handlerDescription.equals("gebruikerBeheer"))
        {
            handler = new Handler_GebruikerBeheer(domainFacade);
        }
        else if(handlerDescription.equals("gebruikerDetail"))
        {
            handler = new Handler_GebruikerDetail(domainFacade);
        }
        else if(handlerDescription.equals("valideren"))
        {
            handler = new Handler_ValidatieAccount(domainFacade);
        }
        else if(handlerDescription.equals("support"))
        {
            handler = new Handler_Support(domainFacade);
        }
        else if(handlerDescription.equals("zendMailOpleidingshoofd"))
        {
            handler = new Handler_ZendMailOpleidingshoofd(domainFacade);
        }
        else if(handlerDescription.equals("zendMailLeerkracht"))
        {
            handler = new Handler_ZendMailLeerkracht(domainFacade);
        }
        else
        {
            throw new ServletException("handlerDescription kan niet naar een type handler gevormd worden.");
        }

        return handler;
    }
}


