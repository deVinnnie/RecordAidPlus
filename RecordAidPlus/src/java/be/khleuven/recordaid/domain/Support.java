package be.khleuven.recordaid.domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;


/**
 * Klasse die voor een bericht over een defect in een lokaal bijhoudt om welk
 * lokaal het gaat, wat het defect is en wie dit defect gemeld heeft.
 *
 * @author Koen
 */
@Entity
public class Support extends Identifiable
{
    private String report, lokaal;
    
    @OneToOne
    private Gebruiker zender;


    /**
     * Constructor zonder parameters, nodig voor Java Persistency.
     */
    protected Support()
    {
    }


    /**
     * Constructor met parameters, dit is de constructor die gebruikt dient te
     * worden om een nieuwe Support aan te maken.
     *
     * @param report String die het defect omschrijft.
     * @param lokaal String die het lokaal is waarin het defect is.
     * @param zender Gebruiker die het defect gemeld heeft.
     */
    public Support(String report, String lokaal, Gebruiker zender)
    {
        setReport(report);
        setLokaal(lokaal);
        setZender(zender);
    }


    /**
     * Geeft de omschrijving van het defect terug.
     *
     * @return String die het defect omschrijft.
     */
    public String getReport()
    {
        return report;
    }


    /**
     * Setter om de omschrijving van het defect aan te passen.
     *
     * @param report String die het defect omschrijft.
     */
    public void setReport(String report)
    {
        this.report = report;
    }


    /**
     * Geeft het lokaal terug waar het defect zich bevindt.
     *
     * @return String die het lokaal is waar het defect zich bevindt.
     */
    public String getLokaal()
    {
        return lokaal;
    }


    /**
     * Setter om het lokaal waar het defect zich bevindt aan te passen.
     *
     * @param lokaal String die het lokaal is waar het defect zich bevindt.
     */
    public void setLokaal(String lokaal)
    {
        this.lokaal = lokaal;
    }


    /**
     * Geeft de gebruiker terug die het defect gemeld heeft.
     *
     * @return Gebruiker die de gebruiker is die het defect gemeld heeft.
     */
    public Gebruiker getZender()
    {
        return zender;
    }


    /**
     * Setter om de gebruiker die het defect gemeld heeft aan te passen.
     *
     * @param zender Gebruiker die het defect gemeld heeft.
     */
    public void setZender(Gebruiker zender)
    {
        this.zender = zender;
    }


    // <editor-fold defaultstate="collapsed" desc="Overridden methods">
    /**
     * Methode die bepaalt wanneer 2 objecten hetzelfde zijn op basis van de
     * waarden van de instantievariabelen.
     *
     * @param obj Object dat met dit object vergeleken moet worden.
     * @return Yes indien ze hetzelfde zijn, false indien ze niet hetzelfde
     * zijn.
     */
    @Override
    public boolean equals(Object object)
    {
        boolean equals = false;

        if(object instanceof Support)
        {
            Support item = (Support) object;
            equals = (report.equals(item.getReport()));
        }

        return equals;
    }


    /**
     * Methode die gebruikt wordt in bepaalde Collection<> klassen.
     *
     * @return integer gebaseerd op waarden van de instantievariabelen.
     */
    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 79 * hash + (this.report != null ? this.report.hashCode() : 0);
        return hash;
    }


    /**
     * Methode die gebruikt wordt om een text representatie van het object te
     * verkrijgen, geeft een aantal instantievariabelen terug.
     *
     * @return String die een text representatie van het object is.
     */
    @Override
    public String toString()
    {
        return this.report;
    }
    // </editor-fold>
}