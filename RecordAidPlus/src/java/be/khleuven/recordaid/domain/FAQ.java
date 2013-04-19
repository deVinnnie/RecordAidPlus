package be.khleuven.recordaid.domain;

import be.khleuven.recordaid.domain.gebruiker.Gebruiker;
import javax.persistence.Entity;
import javax.persistence.OneToOne;


/**
 * Een FAQ is een klasse die een vraag bijhoudt van een gebruiker omtrent
 * RecordAid. De FAQ bevat ook het antwoord (eens de FAQ beantwoord is) en een
 * boolean om te bepalen of de FAQ relevant is voor andere gebruikers of niet.
 *
 * @author Maxime Van den Kerkhof
 */
@Entity
public class FAQ extends Identifiable
{
    private String vraag;
    private String antwoord;
    private boolean relevant = false;
    private boolean beantwoord = false;
    
    @OneToOne
    private Gebruiker gebruiker;

    /**
     * Constructor zonder parameters, nodig voor Java Persistency.
     */
    public FAQ()
    {
    }


    /**
     * Constructor met parameters, dit is de constructor die gebruikt dient te
     * worden om een nieuwe FAQ aan te maken.
     *
     * @param gebruiker De gebruiker die de vraag gesteld heeft.
     * @param vraag De vraag die de gebruiker heeft.
     */
    public FAQ(Gebruiker gebruiker, String vraag)
    {
        this.gebruiker = gebruiker;
        this.vraag = vraag;
    }

    /**
     * Vult een antwoord in op de vraag en zet de vraag op beantwoord.
     *
     * @param antwoord String met het antwoord op de vraag.
     */
    public void beantwoord(String antwoord)
    {
        if(antwoord != null)
        {
            this.antwoord = antwoord;
            beantwoord = true;
        }
    }


    /**
     * Geeft weer of de vraag relevant is voor andere gebruikers.
     *
     * @return True indien de vraag relevant is, false indien niet.
     */
    public boolean isRelevant()
    {
        return relevant;
    }


    /**
     * Setter om te bepalen of de vraag relevant is of niet.
     *
     * @param relevant Boolean die weergeeft of de vraag relevant is of niet.
     */
    public void setRelevant(boolean relevant)
    {
        this.relevant = relevant;
    }


    /**
     * Geeft de vraag die gesteld werd terug.
     *
     * @return String met de vraag.
     */
    public String getVraag()
    {
        return vraag;
    }


    /**
     * Geeft het opgegeven antwoord voor de vraag terug.
     *
     * @return String met het antwoord.
     */
    public String getAntwoord()
    {
        return antwoord;
    }


    /**
     * Geeft weer of de vraag reeds beantwoord is of niet.
     *
     * @return True indien beantwoord, false indien nog niet beantwoord.
     */
    public boolean isBeantwoord()
    {
        return beantwoord;
    }


    /**
     * Setter om de vraag te veranderen.
     *
     * @param vraag String met de aangepaste vraag.
     */
    public void setVraag(String vraag)
    {
        this.vraag = vraag;
    }


    /**
     * Setter om het antwoord te veranderen.
     *
     * @param antwoord String met het aangepaste antwoord.
     */
    public void setAntwoord(String antwoord)
    {
        this.antwoord = antwoord;
    }


    /**
     * Setter om de gebruiker die de vraag gesteld heeft te veranderen.
     *
     * @param g Gebruiker die de nieuwe aanvrager voorstelt.
     */
    public void setGebruiker(Gebruiker g)
    {
        gebruiker = g;
    }


    /**
     * Getter die de gebruiker die de vraag gesteld heeft teruggeeft.
     *
     * @return Gebruiker die de vraag gesteld heeft.
     */
    public Gebruiker getGebruiker()
    {
        return gebruiker;
    }


    /**
     * Methode die bepaalt wanneer 2 objecten hetzelfde zijn op basis van de
     * waarden van de instantievariabelen.
     *
     * @param obj Object dat met dit object vergeleken moet worden.
     * @return Yes indien ze hetzelfde zijn, false indien ze niet hetzelfde zijn.
     */
    @Override
    public boolean equals(Object obj)
    {
        if(obj == null)
        {
            return false;
        }
        if(getClass() != obj.getClass())
        {
            return false;
        }
        final FAQ other = (FAQ) obj;
        if((this.vraag == null) ? (other.vraag != null) : !this.vraag.equals(other.vraag))
        {
            return false;
        }
        if(this.gebruiker != other.gebruiker && (this.gebruiker == null || !this.gebruiker.equals(other.gebruiker)))
        {
            return false;
        }
        return true;
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
        hash = 47 * hash + (this.vraag != null ? this.vraag.hashCode() : 0);
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
        return "vraag=" + vraag + "\nantwoord=" + antwoord;
    }
}


