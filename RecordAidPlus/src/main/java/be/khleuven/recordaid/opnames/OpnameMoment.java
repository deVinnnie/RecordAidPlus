package be.khleuven.recordaid.opnames;

import be.khleuven.recordaid.domain.gebruiker.Gebruiker;
import be.khleuven.recordaid.domain.*; 
import be.khleuven.recordaid.util.TimeSpan;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.*; 

/**
 *
 * @author Vincent Ceulemans
 */
@Entity
public class OpnameMoment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String OOD; 
    
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Lokaal lokaal; 
    
    private String reeks; 
    
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private TimeSpan tijdstip; 
    
    @OneToOne
    private OpnameMethode methode; 
    
    @OneToOne
    private Gebruiker verantwoordelijke; 
    
    @OneToOne
    private Lector lector = new Lector(); 
    
    @OneToOne
    private Opname opname; 
    
    /**
     * Indicates wether this Opname is visible to other users. 
     */
    private boolean zichtbaar = false;

    public OpnameMoment(){}

    public OpnameMoment(String OOD, Lokaal lokaal, String reeks, TimeSpan tijdstip, Lector lector, OpnameMethode methode) {
        this.OOD = OOD;
        this.lokaal = lokaal;
        this.reeks = reeks;
        this.tijdstip = tijdstip;
        this.methode = methode;
        this.lector = lector; 
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public String getOOD() {
        return OOD;
    }

    public void setOOD(String OOD) {
        this.OOD = OOD;
    }

    public Lokaal getLokaal() {
        return lokaal;
    }

    public void setLokaal(Lokaal lokaal) {
        this.lokaal = lokaal;
    }

    public String getReeks() {
        return reeks;
    }

    public void setReeks(String reeks) {
        this.reeks = reeks;
    }

    public TimeSpan getTijdstip() {
        return tijdstip;
    }

    public void setTijdstip(TimeSpan tijdstip) {
        this.tijdstip = tijdstip;
    }

    public OpnameMethode getMethode() {
        return methode;
    }

    public void setMethode(OpnameMethode methode) {
        this.methode = methode;
    }

    public Gebruiker getVerantwoordelijke() {
        return verantwoordelijke;
    }

    public void setVerantwoordelijke(Gebruiker verantwoordelijke) {
        this.verantwoordelijke = verantwoordelijke;
    }

    public Lector getLector() {
        return lector;
    }

    public void setLector(Lector lector) {
        this.lector = lector;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     *
     * @return Tijdstip waarop de les start.
     */
    public Calendar getBeginTijdstip()
    {
        return tijdstip.getBeginTime();
    }

    /**
     *
     * @return Tijdstip waarop de les eindigt.
     */
    public Calendar getEindTijdstip()
    {
        return this.tijdstip.getEndTime();
    }
    
    public boolean isZichtbaar() {
        return zichtbaar;
    }

    public void setZichtbaar(boolean zichtbaar) {
        this.zichtbaar = zichtbaar;
    }

    public Opname getOpname() {
        return opname;
    }

    public void setOpname(Opname opname) {
        this.opname = opname;
    }
    //</editor-fold>
}