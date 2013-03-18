package be.khleuven.recordaid.servlet;

import be.khleuven.recordaid.servlet.handlers.HandlerFactory;
import be.khleuven.recordaid.servlet.handlers.Handler;
import be.khleuven.recordaid.database.DatabaseException;
import be.khleuven.recordaid.domain.Gebruiker;
import be.khleuven.recordaid.domain.Rollen;
import be.khleuven.recordaid.ui.RecordAidDomainFacade;
import be.khleuven.recordaid.util.WachtwoordUtility;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet voor de website. Vanuit de website wordt altijd naar deze servlet
 * verwezen, afhankelijk van de parameter "action" wordt via de HandlerFactory
 * een concrete Handler aangemaakt die het request afhandelt.
 *
 * @author Hannes Crombez <hannes.crombez@me.com>
 */
public class ActionServlet extends HttpServlet {

    private HandlerFactory handlerFactory;

    /**
     * Methode handelt het request af voor zowel de GET als de POST methode.
     *
     * @param request Request dat vanuit de servlet gestuurd werd.
     * @param response Response dat vanuit de servlet gestuurd werd.
     *
     * @throws ServletException indien er zich een fout voordoet.
     * @throws IOException indien er zich een I/O fout voordoet.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            Handler handler = handlerFactory.getHandler(action);
            handler.handleRequest(request, response);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher(handler.getDestination());
            requestDispatcher.forward(request, response);
        } catch (ServletException exception) {
            request.setAttribute("error", "De volgende fout trad op: " + exception.getMessage());
        }

    }
   /**
     * Initialiseert de Servlet.
     */
    @Override
    public void init() {
        try {
            RecordAidDomainFacade domainFacade = new RecordAidDomainFacade(this.getServletContext());
            handlerFactory = new HandlerFactory(domainFacade);
            
            //Create the admin user. 
            WachtwoordUtility wachtwoordChecker = new WachtwoordUtility(); 
            String ww = wachtwoordChecker.hashWachtwoord("geheim"); 
            
            Gebruiker g = new Gebruiker(Rollen.ADMIN, "recordaid@khleuven.be", "RecordAid", "Admin", ww);

            g.valideer(g.getValidatieCode());
            
            domainFacade.addGebruiker(g);
            
        } catch (ServletException ex) {
            Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DatabaseException ex) {
            Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Methode handelt het request af voor de GET methode, de algemene methode
     * processRequest wordt opgeroepen en parameters worden doorgegeven.
     *
     * @param request Request dat vanuit de servlet gestuurd werd.
     * @param response Response dat vanuit de servlet gestuurd werd.
     *
     * @throws ServletException indien er zich een fout voordoet.
     * @throws IOException indien er zich een I/O fout voordoet.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Methode handelt het request af voor de POST methode, de algemene methode
     * processRequest wordt opgeroepen en parameters worden doorgegeven.
     *
     * @param request Request dat vanuit de servlet gestuurd werd.
     * @param response Response dat vanuit de servlet gestuurd werd.
     *
     * @throws ServletException indien er zich een fout voordoet.
     * @throws IOException indien er zich een I/O fout voordoet.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Geeft een korte omschrijving van de servlet terug.
     *
     * @return String die de servlet omschrijft.
     */
    @Override
    public String getServletInfo() {
        return "Servlet voor de RecordAid website.";
    }// </editor-fold>
}