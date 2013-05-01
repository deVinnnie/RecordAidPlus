package be.khleuven.recordaid.domain.items;

import be.khleuven.recordaid.domain.Identifiable;
import be.khleuven.recordaid.domain.gebruiker.Gebruiker;
import be.khleuven.recordaid.util.TimeSpan;
import javax.persistence.*; 


/**
 * Represents a reservation of an item for a certain period of a day. 
 * 
 * The date and item of the reservation are stored in the parent <code>ReservatieDag</code>. 
 *
 */
@Entity
public class Reservatie extends Identifiable
{
    @OneToOne
    private TimeSpan slot;
    
    @OneToOne
    private Gebruiker gebruiker;

    /**
     * Constructor zonder parameters, nodig voor Java Persistency.
     */
    public Reservatie(){}

    /**
     * Constructor met parameters, dit is de constructor die gebruikt dient te
     * worden om een nieuwe Reservatie aan te maken.
     *
     * @param slot TimeSpan object representing start and end of reservation. 
     * @param gebruiker Gebruiker die de reservatie aangemaakt heeft.
     */
    public Reservatie(TimeSpan slot, Gebruiker gebruiker)
    {
        this.slot = slot;
        this.gebruiker = gebruiker;
    }

    // <editor-fold defaultstate="collapsed" desc="Getters & Setters">
    /**
     * Setter om het tijdstip van de reservatie te veranderen.
     *
     * @param slot integer die het aangepaste tijdstip van de reservatie is.
     */
    public void setSlot(TimeSpan slot)
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
     * Geeft het tijdstip terug waarop de reservatie is.
     *
     * @return integer die het tijdstip is waarop de reservatie is.
     */
    public TimeSpan getSlot()
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
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Overridden methods">
    /**
     * Methode die bepaalt wanneer 2 objecten hetzelfde zijn op basis van de
     * waarden van de instantievariabelen.
     *
     * @param obj Object dat met dit object vergeleken moet worden.
     * @return True indien ze hetzelfde zijn anders False.
     */
    @Override
    public boolean equals(Object object)
    {
        boolean equals = false;
        if(object instanceof Reservatie)
        {
            Reservatie reservatie = (Reservatie) object;
            if(reservatie.getId() == this.getId()){
                equals = true; 
            }
        }
        return equals;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 53 * hash + (int) (super.getId() ^ (super.getId() >>> 32));
        return hash;
    }
    // </editor-fold>
}