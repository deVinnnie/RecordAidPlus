package be.khleuven.eindwerk.domain;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;


/**
 * Een Aanvraag beschrijft de aanvraag van een gebruiker om een bepaalde les op
 * te nemen.
 *
 * @author Hannes
 */
@Entity
public class Aanvraag extends Identifiable
{
    private Status status;
    
    @OneToOne
    private Gebruiker aanvrager;
    
    @OneToOne
    private Gebruiker toegewezenLid;
    
    private String lectorMail;
    private String optenemenVak;
    private String lokaal;
    private String klasReeks;
    private String departement;
    private String reden;
    private String linkNaarVideo;
    private String beginUur;
    private String eindUur;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar aanvraagDatum;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar lesDatum;


    /**
     * Constructor zonder parameters, nodig voor Java Persistency.
     */
    public Aanvraag()
    {
    }

    /**
     * Constructor met parameters, dit is de constructor die gebruikt dient te
     * worden om een nieuwe Aanvraag aan te maken.
     *
     * @param aanvrager Gebruiker die de aanvraag indient.
     * @param lectorMail Emailadres van de lector die het vak geeft.
     * @param optenemenVak Vak dat opgenomen moet worden.
     * @param lokaal Lokaal waar de les plaatsvindt.
     * @param klasReeks Standaard reeks waarvoor deze les gegeven wordt.
     * @param reden Reden waarom de gebruiker de aanvraag indient, bv ziekte.
     * @param departement Departement waar de les plaatsvindt.
     * @param beginUur Uur waarop de les start.
     * @param eindUur Uur waarop de les eindigt.
     * @param lesDatum Datum waarop de les plaatvindt.
     */
    public Aanvraag(Gebruiker aanvrager, String lectorMail, String optenemenVak, String lokaal, String klasReeks, String reden, String departement, String beginUur, String eindUur, Calendar lesDatum)
    {
        this.status = Status.NIEUW;
        this.aanvrager = aanvrager;
        this.lectorMail = lectorMail;
        this.optenemenVak = optenemenVak;
        this.lokaal = lokaal;
        this.klasReeks = klasReeks;
        this.departement = departement;
        this.reden = reden;
        this.linkNaarVideo = "";
        this.beginUur = beginUur;
        this.eindUur = eindUur;
        this.aanvraagDatum = GregorianCalendar.getInstance();
        this.lesDatum = lesDatum;
    }

    // <editor-fold defaultstate="collapsed" desc="Setters">
    /**
     * Setter voor link naar de video van de opgenomen les.
     *
     * @param linkNaarVideo String met link naar video, moet volledig adres zijn
     * (http://www....).
     */
    public void setLinkNaarVideo(String linkNaarVideo)
    {
        this.linkNaarVideo = linkNaarVideo;
    }


    /**
     * Setter om status van de aanvraag te veranderen.
     *
     * @param status Kan één van de mogelijke statussen beschreven in Enum
     * Status zijn.
     */
    public void setStatus(Status status)
    {
        this.status = status;
    }


    /**
     * Setter om een gebruiker toe te wijzen als verantwoordelijke voor deze
     * opname.
     *
     * @param toegewezenLid Gebruiker verantwoorderlijk voor deze aanvraag, zal
     * vaak een buddy zijn.
     */
    public void setToegewezenLid(Gebruiker toegewezenLid)
    {
        this.toegewezenLid = toegewezenLid;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getters">
    /**
     *
     * @return Datum waarop de les plaatvindt.
     */
    public Calendar getAanvraagDatum()
    {
        return aanvraagDatum;
    }


    /**
     *
     * @return Gebruiker die de aanvraag indient.
     */
    public Gebruiker getAanvrager()
    {
        return aanvrager;
    }


    /**
     *
     * @return Uur waarop de les start.
     */
    public String getBeginUur()
    {
        return beginUur;
    }


    /**
     *
     * @return Departement waar de les plaatsvindt.
     */
    public String getDepartement()
    {
        return departement;
    }


    /**
     *
     * @return Uus waarop de les eindigt.
     */
    public String getEindUur()
    {
        return eindUur;
    }


    /**
     *
     * @return tandaard reeks waarvoor deze les gegeven wordt.
     */
    public String getKlasReeks()
    {
        return klasReeks;
    }


    /**
     *
     * @return Emailadres van de lector die het vak geeft.
     */
    public String getLectorMail()
    {
        return lectorMail;
    }


    /**
     *
     * @return Datum waarop de les plaatvindt.
     */
    public Calendar getLesDatum()
    {
        return lesDatum;
    }


    /**
     *
     * @return String met link naar video.
     */
    public String getLinkNaarVideo()
    {
        return linkNaarVideo;
    }


    /**
     *
     * @return Lokaal waar de les plaatsvindt.
     */
    public String getLokaal()
    {
        return lokaal;
    }


    /**
     *
     * @return Vak dat opgenomen moet worden.
     */
    public String getOptenemenVak()
    {
        return optenemenVak;
    }


    /**
     *
     * @return Reden waarom de gebruiker de aanvraag indient, bv ziekte.
     */
    public String getReden()
    {
        return reden;
    }


    /**
     *
     * @return Kan één van de mogelijke statussen beschreven in Enum Status
     * zijn.
     */
    public Status getStatus()
    {
        return status;
    }


    /**
     *
     * @return Gebruiker verantwoorderlijk voor deze aanvraag, zal vaak een
     * buddy zijn.
     */
    public Gebruiker getToegewezenLid()
    {
        return toegewezenLid;
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
        final Aanvraag other = (Aanvraag) obj;
        if((this.lectorMail == null) ? (other.lectorMail != null) : !this.lectorMail.equals(other.lectorMail))
        {
            return false;
        }
        if((this.lokaal == null) ? (other.lokaal != null) : !this.lokaal.equals(other.lokaal))
        {
            return false;
        }
        if((this.departement == null) ? (other.departement != null) : !this.departement.equals(other.departement))
        {
            return false;
        }
        if((this.beginUur == null) ? (other.beginUur != null) : !this.beginUur.equals(other.beginUur))
        {
            return false;
        }
        if((this.eindUur == null) ? (other.eindUur != null) : !this.eindUur.equals(other.eindUur))
        {
            return false;
        }
        if(this.lesDatum != other.lesDatum && (this.lesDatum == null || !this.lesDatum.equals(other.lesDatum)))
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
        int hash = 3;
        hash = 29 * hash + (this.lectorMail != null ? this.lectorMail.hashCode() : 0);
        hash = 29 * hash + (this.lokaal != null ? this.lokaal.hashCode() : 0);
        hash = 29 * hash + (this.departement != null ? this.departement.hashCode() : 0);
        hash = 29 * hash + (this.beginUur != null ? this.beginUur.hashCode() : 0);
        hash = 29 * hash + (this.eindUur != null ? this.eindUur.hashCode() : 0);
        hash = 29 * hash + (this.lesDatum != null ? this.lesDatum.hashCode() : 0);
        return hash;
    }
    // </editor-fold>
}