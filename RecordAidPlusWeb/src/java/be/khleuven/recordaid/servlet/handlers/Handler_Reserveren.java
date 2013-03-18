package be.khleuven.recordaid.servlet.handlers;

import be.khleuven.recordaid.domain.Reservatie;
import be.khleuven.recordaid.domain.DatumMaker;
import be.khleuven.recordaid.domain.Rollen;
import be.khleuven.recordaid.domain.Item;
import be.khleuven.recordaid.domain.Gebruiker;
import be.khleuven.recordaid.database.DatabaseException;
import be.khleuven.recordaid.ui.RecordAidDomainFacade;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Hannes Crombez <hannes.crombez@me.com>
 */
public class Handler_Reserveren extends Handler
{
    public Handler_Reserveren(RecordAidDomainFacade domainFacade)
    {
        super(domainFacade);
    }


    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response)
    {
        String datumStr = request.getParameter("datum");
        String itemStr = request.getParameter("item");
        String action = request.getParameter("action");

        getItems(request, response);

        if(action.equals("reserveren"))
        {
            String slotStr = request.getParameter("slot");

            if(datumStr != null)
            {
                if(slotStr != null)
                {
                    int slot = Integer.parseInt(slotStr);

                    Calendar datum = DatumMaker.maakDatum(datumStr);

                    Item item = domainFacade.findItem(itemStr);

                    Gebruiker gebruiker = (Gebruiker) request.getSession().getAttribute("gebruiker");

                    try
                    {
                        Reservatie reservatie = new Reservatie(datum, slot, gebruiker, item);
                        domainFacade.addReservatie(reservatie);
                    }
                    catch(DatabaseException ex)
                    {
                        super.handleException(request, response, ex);
                    }
                }
            }
        }
        else if(action.equals("verwijderItem"))
        {
            String idStr = request.getParameter("id");
            long id = Long.parseLong(idStr);

            domainFacade.removeReservatie(id);
        }

        if(datumStr != null)
        {
            if(!datumStr.equals(""))
            {
                getReservaties(itemStr, datumStr, request, response);
            }
        }

        super.destination = "reserveer.jsp";
    }


    private void getItems(HttpServletRequest request, HttpServletResponse response)
    {
        Collection<Item> items = domainFacade.getItems();
        request.setAttribute("items", items);
    }


    private void getReservaties(String itemStr, String datumStr, HttpServletRequest request, HttpServletResponse response)
    {
        Calendar datum = DatumMaker.maakDatum(datumStr);

        Item item = domainFacade.findItem(itemStr);
        Collection<Reservatie> reservaties = domainFacade.getReservaties(datum, item);

        Gebruiker gebruiker = (Gebruiker) request.getSession().getAttribute("gebruiker");

        List<String> reservatieLijst = new ArrayList<String>();
        for(int i = 0; i < 15; i++)
        {
            int slotStart = i + 8;
            int slotEind = i + 9;

            String reservatieStr = "";
            reservatieStr += "<tr>";
            reservatieStr += "<td>";
            reservatieStr += slotStart + "u - " + slotEind + "u";
            reservatieStr += "</td>";
            reservatieStr += "<td>";
            reservatieStr += "<a href='ActionServlet?action=reserveren&slot=" + slotStart + "&datum=" + datumStr + "&item=" + itemStr + "'>Reserveer Item</a>";
            reservatieStr += "</td>";
            reservatieStr += "<td>&nbsp;</td>";
            reservatieStr += "</tr>";

            reservatieLijst.add(i, reservatieStr);
        }
        for(Reservatie reservatie : reservaties)
        {
            int slotStart = reservatie.getSlot();
            int slotEind = reservatie.getSlot() + 1;

            String reservatieStr = "";
            reservatieStr += "<tr>";
            reservatieStr += "<td style='background-color: red;'>";
            reservatieStr += slotStart + "u - " + slotEind + "u";
            reservatieStr += "</td>";
            reservatieStr += "<td style='background-color: red;'>";
            reservatieStr += reservatie.getGebruiker().toString();
            reservatieStr += "</td>";
            reservatieStr += "<td>";
            if(reservatie.getGebruiker().equals(gebruiker) || gebruiker.getRol() == Rollen.ADMIN || gebruiker.getRol() == Rollen.KERNLID)
            {
                reservatieStr += "<a href='ActionServlet?action=verwijderItem&id=" + reservatie.getId() + "&datum=" + datumStr + "&item=" + itemStr + "'>Verwijder Item</a>";
            }
            else
            {
                reservatieStr += "&nbsp;";
            }
            reservatieStr += "</td>";
            reservatieStr += "</tr>";

            reservatieLijst.set(reservatie.getSlot() - 8, reservatieStr);
        }

        request.setAttribute("reservaties", reservatieLijst);
    }
}