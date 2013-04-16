package be.khleuven.recordaid.domain.aanvragen;

import be.khleuven.recordaid.util.TimeSpan;
import be.khleuven.recordaid.domain.Gebruiker;
import be.khleuven.recordaid.domain.Identifiable;
import be.khleuven.recordaid.domain.Lector;
import java.util.Calendar;
import javax.persistence.*; 


/**
 * Een Aanvraag beschrijft de aanvraag van een gebruiker om een bepaalde les op
 * te nemen.
 *
 * @author Hannes
 */
@Entity
public class Aanvraag extends Identifiable
{
    @Enumerated
    private Status status;
    
    @OneToOne
    private Gebruiker aanvrager;
    
    @OneToOne
    private Gebruiker toegewezenVerantwoordelijke;
    
    @OneToOne(cascade=CascadeType.PERSIST)
    private Lector lector; 
   
    private String optenemenVak;
    private String lokaal;
    private String reeks;
    private String departement;
    private String reden;
    private String linkNaarVideo ="";
    
    @OneToOne(cascade= CascadeType.PERSIST)
    private TimeSpan timespan = new TimeSpan(); 
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar aanvraagDatum = Calendar.getInstance();

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
     * @param lesDatum Datum waarop de les plaatvindt.
     */
    public Aanvraag(Gebruiker aanvrager, Lector lector, String optenemenVak, String lokaal, String klasReeks, String reden, String departement, String beginUur, String eindUur, Calendar lesDatum)
    {
        this.status = Status.NIEUW;
        this.aanvrager = aanvrager;
        this.lector = lector; 
        this.optenemenVak = optenemenVak;
        this.lokaal = lokaal;
        this.reeks = klasReeks;
        this.departement = departement;
        this.reden = reden;
        this.aanvraagDatum = Calendar.getInstance();
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
        this.toegewezenVerantwoordelijke = toegewezenLid;
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
     * @return Tijdstip waarop de les start.
     */
    public Calendar getBeginTijdstip()
    {
        return timespan.getBeginTime();
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
     * @return Tijdstip waarop de les eindigt.
     */
    public Calendar getEindTijdstip()
    {
        return this.timespan.getEndTime();
    }


    /**
     *
     * @return Standaard reeks waarvoor deze les gegeven wordt.
     */
    public String getReeks()
    {
        return reeks;
    }


    /**
     *
     * @return Emailadres van de lector die het vak geeft.
     */
    public String getLectorMail()
    {
        return this.lector.getEmailadres();
    }

    /**
     *
     * @return Datum waarop de les plaatvindt.
     */
    public Calendar getLesDatum()
    {
        return this.timespan.getBeginTime();
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

    public void setAanvrager(Gebruiker aanvrager) {
        this.aanvrager = aanvrager;
    }

    public void setLokaal(String lokaal) {
        this.lokaal = lokaal;
    }

    public void setReeks(String reeks) {
        this.reeks = reeks;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public void setReden(String reden) {
        this.reden = reden;
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
        return toegewezenVerantwoordelijke;
    }

    public Lector getLector() {
        return lector;
    }

    public void setLector(Lector lector) {
        this.lector = lector;
    }

    public TimeSpan getTimespan() {
        return timespan;
    }

    public void setTimespan(TimeSpan timespan) {
        this.timespan = timespan;
    }
    
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Overridden methods">
    /**
     * Methode die bepaalt wanneer 2 objecten hetzelfde zijn op basis van de
     * waarden van de instantievariabelen.
     *
     * @param obj Object dat met dit object vergeleken moet worden.
     * @return True indien ze hetzelfde zijn, false indien ze niet hetzelfde
     * zijn.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Aanvraag other = (Aanvraag) obj;
        if (this.aanvrager != other.aanvrager && (this.aanvrager == null || !this.aanvrager.equals(other.aanvrager))) {
            return false;
        }
        if (this.toegewezenVerantwoordelijke != other.toegewezenVerantwoordelijke && (this.toegewezenVerantwoordelijke == null || !this.toegewezenVerantwoordelijke.equals(other.toegewezenVerantwoordelijke))) {
            return false;
        }
        if (this.lector != other.lector && (this.lector == null || !this.lector.equals(other.lector))) {
            return false;
        }
        if ((this.optenemenVak == null) ? (other.optenemenVak != null) : !this.optenemenVak.equals(other.optenemenVak)) {
            return false;
        }
        if ((this.lokaal == null) ? (other.lokaal != null) : !this.lokaal.equals(other.lokaal)) {
            return false;
        }
        if ((this.reeks == null) ? (other.reeks != null) : !this.reeks.equals(other.reeks)) {
            return false;
        }
        if ((this.departement == null) ? (other.departement != null) : !this.departement.equals(other.departement)) {
            return false;
        }
        if (this.timespan != other.timespan && (this.timespan == null || !this.timespan.equals(other.timespan))) {
            return false;
        }
        if (this.aanvraagDatum != other.aanvraagDatum && (this.aanvraagDatum == null || !this.aanvraagDatum.equals(other.aanvraagDatum))) {
            return false;
        }
        return true;
    }
    // </editor-fold>
}