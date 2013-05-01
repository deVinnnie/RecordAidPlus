package be.khleuven.recordaid.domain.forum;

import be.khleuven.recordaid.domain.gebruiker.Gebruiker;
import be.khleuven.recordaid.domain.Identifiable;
import java.util.Calendar;
import javax.persistence.*; 


/**
 * Klasse die alle informatie over een bericht dat in een ForumTopic kan worden
 * geplaatst bijhoudt.
 *
 * @author Maxime Van den Kerkhof
 */
@Entity
public class Message extends Identifiable
{
    @OneToOne
    private Gebruiker zender;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar datum;
    
    private String text;


    /**
     * Constructor met parameters, dit is de constructor die gebruikt dient te
     * worden om een nieuwe FAQ aan te maken.
     *
     * @param zender Gebruiker die het bericht toevoegt aan het ForumTopic.
     * @param datum Datum waarop het bericht aangemaakt werd.
     * @param text String die de gebruiker als boodschap van het bericht heeft
     * ingegeven.
     */
    public Message(Gebruiker zender, Calendar datum, String text)
    {
        this.zender = zender;
        this.datum = datum;
        this.text = text;
    }


    /**
     * Constructor zonder parameters, nodig voor Java Persistency.
     */
    public Message()
    {
    }


    /**
     * Geeft de gebruiker terug die het bericht aangemaakt heeft.
     *
     * @return Gebruiker die het bericht aangemaakt heeft.
     */
    public Gebruiker getZender()
    {
        return zender;
    }


    /**
     * Geeft de datum terug waarop het bericht aangemaakt werd.
     *
     * @return Datum van het type Calender die de datum is waarop het bericht
     * aangemaakt werd.
     */
    public Calendar getDatum()
    {
        return datum;
    }


    /**
     * Setter om de datum waarop het bericht werd aangemaakt te veranderen.
     *
     * @param datum Calendar die de aangepaste datum van het bericht is.
     */
    public void setDatum(Calendar datum)
    {
        this.datum = datum;
    }


    /**
     * Geeft de boodschap terug die de gebruiker aan het bericht heeft
     * toegevoegd.
     *
     * @return String met de boodschap van het bericht.
     */
    public String getText()
    {
        return text;
    }


    /**
     * Setter om de boodschap van het bericht te veranderen.
     *
     * @param text String die de aangepaste boodschap van het bericht is.
     */
    public void setText(String text)
    {
        this.text = text;
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
        hash = 29 * hash + (this.text != null ? this.text.hashCode() : 0);
        return hash;
    }


    /**
     * Methode die bepaalt wanneer 2 objecten hetzelfde zijn op basis van de
     * waarden van de instantievariabelen.
     *
     * @param obj Object dat met dit object vergeleken moet worden.
     * @return Yes indien ze hetzelfde zijn, false indien ze niet hetzelfde
     * zijn.
     */
    @Override
    public boolean equals(Object obj)
    {
        if(obj == null && obj instanceof Message)
        {
            return false;
        }

        final Message other = (Message) obj;
        if(this.zender != other.zender && (this.zender == null || !this.zender.equals(other.zender)))
        {
            return false;
        }
        if((this.text == null) ? (other.text != null) : !this.text.equals(other.text))
        {
            return false;
        }
        return true;
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
        return this.zender.getVoornaam() + " " + this.zender.getAchternaam() + "\n" + datum + "\n" + text;
    }
}