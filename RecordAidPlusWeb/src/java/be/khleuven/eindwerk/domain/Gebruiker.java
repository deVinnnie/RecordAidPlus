package be.khleuven.eindwerk.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;


/**
 * Gebruiker is een klasse die alle informatie bijhoudt over een bepaalde
 * gebruiker, en aan de hand van het type gebruiker wordt ook bepaald wat deze
 * gebruiker wel en niet kan.
 *
 * @author Hannes
 */
@Entity
public class Gebruiker implements Serializable
{
    private static long nextId;
    private long id;
    private Rollen rol;
    @Id
    private String emailadres;
    private String naam;
    private String voornaam;
    private String wachtwoord;
    private String validatieCode;
    private boolean gevalideerd;


    /**
     * Constructor zonder parameters, nodig voor Java Persistency.
     */
    protected Gebruiker()
    {
    }


    /**
     * Constructor met parameters, dit is de constructor die gebruikt dient te
     * worden om een nieuwe Gebruiker aan te maken.
     *
     * @param rol De rol van de gebruiker, kan één van de mogelijke rollen
     * bepaald in de Enum Rollen zijn.
     * @param emailadres Het emailadres van de gebruiker.
     * @param naam De naam van de gebruiker.
     * @param voornaam De voornaam van de gebruiker.
     * @param wachtwoord Het wachtwoord van de gebruiker.
     */
    public Gebruiker(Rollen rol, String emailadres, String naam, String voornaam, String wachtwoord)
    {
        this.rol = rol;
        this.emailadres = emailadres;
        this.naam = naam;
        this.voornaam = voornaam;
        this.wachtwoord = wachtwoord;

        this.gevalideerd = false;
        this.validatieCode = ValidatieCodeGenerator.generateValidatieCode();

        this.id = Gebruiker.nextId;
        Gebruiker.nextId++;
    }


    /**
     * Methode om een gebruiker te valideren op basis van een validatieCode. De
     * methode geeft een boolean terug om aan te geven of de code juist is.
     * Indien juist zal de validatie boolean instantievariabele aangepast
     * worden.
     *
     * @param validatieCode De validatiecode die vergeleken moet worden met de
     * code aangemaakt bij initialisatie van het object.
     * @return True indien de gebruiker nu gevalideerd is, false indien de
     * validatiecodes niet overeen kwamen.
     */
    public boolean valideer(String validatieCode)
    {
        boolean valid = false;

        if(validatieCode.equals(this.validatieCode))
        {
            valid = true;
            this.gevalideerd = true;
        }

        return valid;
    }


    /**
     * Methode om een gebruiker te valideren zonder de validatieCode. Deze
     * methode is bedoelt zo dat een admin manueel eengebruiker kan valideren.
     */
    public void valideer()
    {
        this.validatieCode = "***";
        this.gevalideerd = true;
    }


    /**
     * Methode om een gebruiker te devalideren. Deze methode is bedoelt zodat
     * een admin een gebruiker manueel kan devalideren, bijvoorbeeld een
     * gebruiker die niet meer toegelaten mag worden op de website.
     */
    public void deValideer()
    {
        this.validatieCode = "***";
        this.gevalideerd = false;
    }


    // <editor-fold defaultstate="collapsed" desc="Setters">
    /**
     * Setter om de rol van de gebruiker te setten.
     *
     * @param rol De rol kan één van de rollen zoals bepaald in Enum Rollen
     * zijn.
     */
    public void setRol(Rollen rol)
    {
        this.rol = rol;
    }


    /**
     * Setter om de naam van de gebruiker te veranderen.
     *
     * @param naam String die de aangepaste naam van de gebruiker voorstelt.
     */
    public void setNaam(String naam)
    {
        this.naam = naam;
    }


    /**
     * Setter om de voornaam van de gebruiker te veranderen.
     *
     * @param voornaam String die de aangepaste voornaam van de gebruiker
     * voorstelt.
     */
    public void setVoornaam(String voornaam)
    {
        this.voornaam = voornaam;
    }


    /**
     * Setter om het wachtwoord van de gebruiker te veranderen.
     *
     * @param wachtwoord String die het aangepaste wachtwoord van de gebruiker
     * voorstelt.
     */
    public void setWachtwoord(String wachtwoord)
    {
        this.wachtwoord = wachtwoord;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getters">

    /**
     * Geeft het emailadres van de gebruiker terug.
     *
     * @return String die het emailadres van de gebruiker teruggeeft.
     */
    public String getEmailadres()
    {
        return emailadres;
    }


    /**
     * Geeft weer of het emailadres van een gebruiker reeds gevalideerd is.
     *
     * @return True indien het emailadres gevalideerd is, false indien het niet
     * gevalideerd is.
     */
    public boolean isGevalideerd()
    {
        return gevalideerd;
    }


    /**
     * Geeft het id van de gebruiker terug.
     *
     * @return long die het id van de gebruiker is.
     */
    public long getId()
    {
        return id;
    }


    /**
     * Geeft de naam van de gebruiker terug.
     *
     * @return String die de naam van de gebruiker is.
     */
    public String getNaam()
    {
        return naam;
    }


    /**
     * Geeft de rol van de gebruiker terug.
     *
     * @return Eén van de mogelijke rollen die de gebruiker is zoals bepaalt in
     * de Enum Rollen.
     */
    public Rollen getRol()
    {
        return rol;
    }


    /**
     * Geeft de validatiecode van de gebruiker terug die gebruikt dient te
     * worden om het emailadres van de gebruiker te valideren.
     *
     * @return String die een validatiecode is.
     */
    public String getValidatieCode()
    {
        return validatieCode;
    }


    /**
     * Geeft de voornaam van de gebruiker terug.
     *
     * @return String die de voornaam van de gebruiker is.
     */
    public String getVoornaam()
    {
        return voornaam;
    }


    /**
     * Geeft het wachtwoord van de gebruiker terug.
     *
     * @return String die het wachtwoord van de gebruiker is.
     */
    public String getWachtwoord()
    {
        return wachtwoord;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Overridden methods">

    /**
     * Methode die gebruikt wordt om een text representatie van het object te
     * verkrijgen, geeft een aantal instantievariabelen terug.
     *
     * @return String die een text representatie van het object is.
     */
    @Override
    public String toString()
    {
        return this.voornaam + " " + this.naam + " (" + this.emailadres + "), " + this.rol;
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
    public boolean equals(Object object)
    {
        boolean equals = false;

        if(object instanceof Gebruiker)
        {
            Gebruiker gebruiker = (Gebruiker) object;

            equals = gebruiker.getEmailadres().equals(this.emailadres);
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
        hash = 41 * hash + (this.emailadres != null ? this.emailadres.hashCode() : 0);
        return hash;
    }
    // </editor-fold>
}


