package be.khleuven.recordaid.servlet;

import be.khleuven.eindwerk.database.DatabaseException;
import be.khleuven.eindwerk.domain.Item;
import be.khleuven.eindwerk.domain.Reservatie;
import be.khleuven.eindwerk.domain.exception.DomainException;
import be.khleuven.eindwerk.ui.RecordAidDomainFacade;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Hannes Crombez <hannes.crombez@me.com>
 */
public class Handler_Itembeheer extends Handler
{
    public Handler_Itembeheer(RecordAidDomainFacade domainFacade)
    {
        super(domainFacade);
    }


    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response)
    {
        String itemNaam = request.getParameter("itemNaam");
        String naam = request.getParameter("naam");
        
        if(itemNaam != null)
        {
            
            Collection<Reservatie> reservaties = domainFacade.getReservaties();
            
            for(Reservatie r : reservaties){
                if(r.getItem().getNaam().equals(itemNaam)){
                    r.setItem(null);
                    domainFacade.removeReservatie(r);
                }
            }

            domainFacade.removeItem(itemNaam);
        }
        if(naam != null)
        {
            try
            {
                Item item = new Item(naam);
                domainFacade.addItem(item);
            }
            catch(DatabaseException ex)
            {
                System.out.println(ex.getMessage());
                request.setAttribute("error", "Dit item bestaat reeds.");
            }
            catch(DomainException ex)
            {
                System.out.println(ex.getMessage());
                request.setAttribute("error", "Dit is geen goede naam.");
            }
        }

        Collection<Item> items = domainFacade.getItems();

        request.setAttribute("items", items);

        super.destination = "items.jsp";
    }
}


