package be.khleuven.recordaid.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;


/**
 * Klasse die een item omschrijft dat gebruikt wordt door RecordAid om opnames
 * te doen. De klasse item bestaat zodat op de website items gereserveerd kunnen
 * worden op bepaalde tijdstippen.
 *
 * @author Hannes
 */
@Entity
public class Item implements Serializable
{
    @Id
    @NotNull
    @NotEmpty
    private String naam;

    /**
     * Constructor zonder parameters, nodig voor Java Persistency.
     */
    public Item(){}

    /**
     * /**
     * Constructor met parameters, dit is de constructor die gebruikt dient te
     * worden om een nieuw Item aan te maken.
     *
     * @param naam String die de naam van het item is.
     * @throws DomainException indien geen String naam wordt meegegeven of
     * indien de String leeg is of ongewenste charakters bevat.
     */
    public Item(String naam) throws DomainException
    {
        setNaam(naam);
    }

    /**
     * Setter voor de naam van het item.
     *
     * @param naam String die de naam van het item is.
     * @throws DomainException indien geen String naam wordt meegegeven of
     * indien de String leeg is of ongewenste charakters bevat.
     */
    public void setNaam(String naam) throws DomainException
    {
        if(naam == null)
        {
            throw new DomainException("De naam mag niet null zijn");
        }
        if(!naam.matches("([a-zA-Z0-9]+( |_)?)+"))
        {
            throw new DomainException("Geen goede naam");
        }

        this.naam = naam;
    }

    /**
     * Geeft de naam van het item terug.
     *
     * @return String die de naam van het item is.
     */
    public String getNaam()
    {
        return naam;
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
        if(object != null && object instanceof Item)
        {
            Item item = (Item) object;
            if(item.getNaam()!=null && this.getNaam()!=null && 
                    this.getNaam().equals(item.getNaam())){
                equals = true; 
            }
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
        hash = 79 * hash + (this.naam != null ? this.naam.hashCode() : 0);
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
        return this.naam;
    }
    // </editor-fold>
}