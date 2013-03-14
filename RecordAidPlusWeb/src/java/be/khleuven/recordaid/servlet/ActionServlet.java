package be.khleuven.recordaid.servlet;

import be.khleuven.eindwerk.database.DatabaseException;
import be.khleuven.eindwerk.domain.Gebruiker;
import be.khleuven.eindwerk.domain.Rollen;
import be.khleuven.eindwerk.ui.RecordAidDomainFacade;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Initialiseert de Servlet.
     */
    @Override
    public void init() {
        try {
            RecordAidDomainFacade domainFacade = new RecordAidDomainFacade(this.getServletContext());
            handlerFactory = new HandlerFactory(domainFacade);

            String ww = this.saltWachtwoord("geheim", null, null);

            Gebruiker g = new Gebruiker(Rollen.ADMIN, "recordaid@khleuven.be", "RecordAid", "Admin", ww);

            g.valideer(g.getValidatieCode());
            
            domainFacade.addGebruiker(g);
            
        } catch (ServletException ex) {
            Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DatabaseException ex) {
            Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

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

    private String saltWachtwoord(String wachtwoord, HttpServletRequest request, HttpServletResponse response) {
        wachtwoord = "@#&!$*£%" + wachtwoord + "*%$€£@###&";

        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(wachtwoord.getBytes(), 0, wachtwoord.length());
            wachtwoord = new BigInteger(1, digest.digest()).toString(16);

            digest = MessageDigest.getInstance("SHA-1");
            digest.update(wachtwoord.getBytes(), 0, wachtwoord.length());
            wachtwoord = new BigInteger(1, digest.digest()).toString(16);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        return wachtwoord;
    }
}
