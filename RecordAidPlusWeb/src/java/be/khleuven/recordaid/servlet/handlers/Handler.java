package be.khleuven.recordaid.servlet.handlers;

import be.khleuven.eindwerk.ui.RecordAidDomainFacade;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Abstracte klasse die een interface biedt voor alle concrete handlers die één
 * bepaald request moeten afhandelen. De klasse houdt de referentie naar de
 * facade bij en ook een variabele waarin de url van de volgende web-pagina
 * opgeslagen wordt tijdens het afhandelen van een request.
 *
 * @author Hannes Crombez <hannes.crombez@me.com>
 */
public abstract class Handler
{
    protected RecordAidDomainFacade domainFacade;
    protected String destination;

    /**
     * Constructor.
     *
     * @param domainFacade De RecordAidDomainFacade waar de handler een
     * referentie naar zal bijhouden, zodat een subklasse deze kan gebruiken.
     */
    public Handler(RecordAidDomainFacade domainFacade)
    {
        this.domainFacade = domainFacade;
    }


    /**
     * Abstracte methode die geïmplementeerd dient te worden in de subklassen.
     *
     * @param request
     * @param response
     */
    public abstract void handleRequest(HttpServletRequest request, HttpServletResponse response);


    /**
     * Geeft de url naar de volgende pagina terug.
     *
     * @return String met de url naar de volgende pagina.
     */
    public String getDestination()
    {
        return this.destination;
    }


    /**
     * Methode die opgeroepen kan worden wanneer in de subklasse een exception
     * wordt opgegooid. Deze methode navigeert naar een error pagina en toont
     * het bericht van de exception.
     *
     * @param request
     * @param response
     * @param exception De exception die opgegooid werd.
     */
    protected void handleException(HttpServletRequest request, HttpServletResponse response, Exception exception)
    {
        request.setAttribute("error", "De volgende fout trad op: " + exception.getMessage());
        this.destination = "mijnFouten.jsp";
    }
}