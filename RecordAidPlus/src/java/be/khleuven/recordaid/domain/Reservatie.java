package be.khleuven.recordaid.domain;

import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;


/**
 * Een reservatie houdt voor een bepaald item de datum, het uur en de gebruiker
 * bij die het item gereserveerd heeft. Per reservatie van een item is er dus
 * een object van Reservatie die die datum en dat uur bijhoudt.
 *
 * @author Hannes
 */
@Entity
public class Reservatie extends Identifiable
{
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar datum;
    
    private int slot;
    
    @OneToOne
    private Gebruiker gebruiker;
    
    @OneToOne
    private Item item;


    /**
     * Constructor zonder parameters, nodig voor Java Persistency.
     */
    protected Reservatie()
    {
    }


    /**
     * /**
     * Constructor met parameters, dit is de constructor die gebruikt dient te
     * worden om een nieuwe Reservatie aan te maken.
     *
     * @param datum Calender die de datum van de reservatie is.
     * @param slot integer die het startuur van de reservatie is, het einduur is
     * één uur later.
     * @param gebruiker Gebruiker die de reservatie aangemaakt heeft.
     * @param item Het item dat op dat ogenblik gereserveerd is.
     */
    public Reservatie(Calendar datum, int slot, Gebruiker gebruiker, Item item)
    {
        this.datum = datum;
        this.slot = slot;
        this.gebruiker = gebruiker;
        this.item = item;
    }

    // <editor-fold defaultstate="collapsed" desc="Setters">

    /**
     * Setter om de datum van de reservatie te veranderen.
     *
     * @param datum Calender die de aangepaste datum voor de reservatie is.
     */
    public void setDatum(Calendar datum)
    {
        this.datum = datum;
    }


    /**
     * Setter om het tijdstip van de reservatie te veranderen.
     *
     * @param slot integer die het aangepaste tijdstip van de reservatie is.
     */
    public void setSlot(int slot)
    {
        this.slot = slot;
    }


    /**
     * Setter om de gebruiker die de reservatie gedaan heeft te veranderen.
     *
     * @param gebruiker Gebruiker die de aangepaste gebruiker voor de reservatie
     * is.
     */
    public void setGebruiker(Gebruiker gebruiker)
    {
        this.gebruiker = gebruiker;
    }


    /**
     * Setter om de item waarvoor de reservatie geldt te veranderen.
     *
     * @param item Item die het aangepaste item is waarvoor de reservatie geldt.
     */
    public void setItem(Item item)
    {
        this.item = item;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getters">

    /**
     * Geeft de datum terug waarom de reservatie is.
     *
     * @return Calendar die de datum is waarop de reservatie is.
     */
    public Calendar getDatum()
    {
        return datum;
    }


    /**
     * Geeft het tijdstip terug waarop de reservatie is.
     *
     * @return integer die het tijdstip is waarop de reservatie is.
     */
    public int getSlot()
    {
        return slot;
    }


    /**
     * Geeft de gebruiker terug die de reservatie gemaakt heeft.
     *
     * @return Gebruiker die de gebruiker is die de reservatie gemaakt heeft.
     */
    public Gebruiker getGebruiker()
    {
        return gebruiker;
    }


    /**
     * Geeft het item terug waarvoor de reservatie geldt.
     *
     * @return Item dat het item is waarvoor de reservatie geldt.
     */
    public Item getItem()
    {
        return item;
    }
    // </editor-fold>

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

        if(object instanceof Reservatie)
        {
            Reservatie reservatie = (Reservatie) object;
            equals = reservatie.getId() == super.getId();
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
        int hash = 7;
        hash = 53 * hash + (int) (super.getId() ^ (super.getId() >>> 32));
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
        return this.datum.get(Calendar.DATE) + "/" + this.datum.get(Calendar.MONTH) + "/" + this.datum.get(Calendar.YEAR) + " | " + this.item.getNaam() + " | " + this.slot + "u - " + (this.slot + 1) + "u | Gereserveerd door: " + this.gebruiker.toString();
    }
    // </editor-fold>
}