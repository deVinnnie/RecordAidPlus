package be.khleuven.recordaid.servlet.handlers;

import be.khleuven.recordaid.ui.RecordAidDomainFacade;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;

/**
 *
 * @author Hannes Crombez <hannes.crombez@me.com>
 */
public class HandlerFactory
{
    private RecordAidDomainFacade domainFacade;
    private Map<String, Handler> handlers; 
    
    public HandlerFactory(RecordAidDomainFacade domainFacade) throws ServletException
    {
        if(domainFacade == null)
        {
            throw new ServletException("domainFacade null");
        }
        this.domainFacade = domainFacade;
        
        handlers = new HashMap<String, Handler>();
        handlers.put("login", new Handler_Login(this.domainFacade)); 
        handlers.put("registreer", new Handler_Registreer(this.domainFacade)); 
        handlers.put("mijnAanvragen",new Handler_Aanvragen(this.domainFacade)); 
        handlers.put("nieuweAanvraag", new Handler_NieuweAanvraag(this.domainFacade)); 
        handlers.put("update_password",new Handler_Account(this.domainFacade)); 
        handlers.put("logout", new Handler_Logout(this.domainFacade));
        handlers.put("detail", new Handler_DetailAanvraag(this.domainFacade)); 
        handlers.put("faq",new Handler_FAQ(this.domainFacade)); 
        handlers.put("nieuweFAQ",new Handler_FAQ(this.domainFacade)); 
        handlers.put("alleAanvragen",new Handler_AanvragenBeheren(this.domainFacade));
        handlers.put("updateAanvraag", new Handler_AanvragenBeheren(this.domainFacade));
        handlers.put("reserveren",new Handler_Reserveren(this.domainFacade));
        handlers.put("verwijderItem",new Handler_Reserveren(this.domainFacade)); 
        handlers.put("itemBeheer",new Handler_Itembeheer(this.domainFacade)); 
        handlers.put("berichten", new Handler_Forum(this.domainFacade)); 
        handlers.put("mailen",new Handler_Mailen(this.domainFacade)); 
        handlers.put("beantwoordFAQ",new Handler_BeantwoordFAQ(this.domainFacade));
        handlers.put("updateFAQ",new Handler_BeantwoordFAQ(this.domainFacade));
        handlers.put("deleteFAQ",new Handler_BeantwoordFAQ(this.domainFacade));
        handlers.put("faqDetails",new Handler_BeantwoordFAQ(this.domainFacade));
        handlers.put("gebruikerBeheer", new Handler_GebruikerBeheer(this.domainFacade)); 
        handlers.put("gebruikerDetail", new Handler_GebruikerDetail(this.domainFacade));
        handlers.put("valideren", new Handler_ValidatieAccount(this.domainFacade)); 
        handlers.put("support",new Handler_Support(this.domainFacade));
        handlers.put("zendMailOpleidingshoofd", new Handler_ZendMailOpleidingshoofd(this.domainFacade));
        handlers.put("zendMailLeerkracht",new Handler_ZendMailLeerkracht(this.domainFacade));
    }

    public Handler getHandler(String handlerDescription) throws ServletException
    {
        if(!handlers.containsKey(handlerDescription))
        {
            throw new ServletException("handlerDescription kan niet naar een type handler gevormd worden.");
        }
        return this.handlers.get(handlerDescription); 
    }
}