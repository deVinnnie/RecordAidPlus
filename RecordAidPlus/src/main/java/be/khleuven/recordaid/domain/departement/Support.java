package be.khleuven.recordaid.domain.departement;

import be.khleuven.recordaid.domain.Identifiable;
import be.khleuven.recordaid.domain.gebruiker.Gebruiker;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.*; 

/**
 * Klasse die voor een bericht over een defect in een lokaal bijhoudt om welk
 * lokaal het gaat, wat het defect is en wie dit defect gemeld heeft.
 *
 * @author Koen
 */
@Entity
public class Support extends Identifiable implements Serializable
{
    @Column(columnDefinition = "CLOB")
    private String probleem; 
    
    @OneToOne
    private Lokaal lokaal;
    
    @OneToOne
    private Gebruiker zender;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar datum = Calendar.getInstance();
    
    /**
     * Constructor zonder parameters, nodig voor Java Persistency.
     */
    public Support(){}

    /**
     * Constructor met parameters, dit is de constructor die gebruikt dient te
     * worden om een nieuwe Support aan te maken.
     *
     * @param probleem String die het defect omschrijft.
     * @param lokaal String die het lokaal is waarin het defect is.
     * @param zender Gebruiker die het defect gemeld heeft.
     */
    public Support(String probleem, Lokaal lokaal, Gebruiker zender)
    {
        this.setProbleem(probleem);
        this.setLokaal(lokaal);
        this.setZender(zender);
    }

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    /**
     * Geeft de omschrijving van het defect terug.
     *
     * @return String die het defect omschrijft.
     */
    public String getProbleem()
    {
        return probleem;
    }


    /**
     * Setter om de omschrijving van het defect aan te passen.
     *
     * @param report String die het defect omschrijft.
     */
    public void setProbleem(String report)
    {
        this.probleem = report;
    }


    /**
     * Geeft het lokaal terug waar het defect zich bevindt.
     *
     * @return String die het lokaal is waar het defect zich bevindt.
     */
    public Lokaal getLokaal()
    {
        return lokaal;
    }


    /**
     * Setter om het lokaal waar het defect zich bevindt aan te passen.
     *
     * @param lokaal String die het lokaal is waar het defect zich bevindt.
     */
    public void setLokaal(Lokaal lokaal)
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

    public Calendar getDatum() {
        return datum;
    }

    public void setDatum(Calendar datum) {
        this.datum = datum;
    }
    //</editor-fold>
    
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
            equals = (probleem.equals(item.getProbleem()));
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
        hash = 79 * hash + (this.probleem != null ? this.probleem.hashCode() : 0);
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
        return this.probleem;
    }
    // </editor-fold>
}