package be.khleuven.recordaid.opnames;

import be.khleuven.recordaid.domain.departement.*; 
import be.khleuven.recordaid.domain.gebruiker.Gebruiker;
import be.khleuven.recordaid.domain.*; 
import be.khleuven.recordaid.util.*; 
import java.io.Serializable;
import java.util.*; 
import javax.persistence.*; 

/**
 *
 * @author Vincent Ceulemans
 */
@Entity
public class OpnameMoment extends Identifiable implements Serializable {    
    private String OOD; 
    
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Lokaal lokaal; 
    
    private String reeks; 
    
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private TimeSpan tijdstip; 
    
    @OneToMany
    private List<OpnameMethode> mogelijkeOpnameMethodes = new ArrayList<OpnameMethode>(); 
    
    @OneToOne
    private Gebruiker verantwoordelijke; 
    
    @OneToOne
    private Lector lector = new Lector(); 
    
    @OneToOne
    private Opname opname; 
    
    private String toegangsCode = ValidatieCodeGenerator.generateValidatieCode(); 
    
    /**
     * Indicates wether this Opname is visible to other users. 
     */
    private boolean zichtbaar = false;

    public OpnameMoment(){}

    public OpnameMoment(String OOD, Lokaal lokaal, String reeks, TimeSpan tijdstip, Lector lector) {
        this.OOD = OOD;
        this.lokaal = lokaal;
        this.reeks = reeks;
        this.tijdstip = tijdstip;
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
    
    public String getToegangsCode() {
        return toegangsCode;
    }

    public void setToegangsCode(String toegangsCode) {
        this.toegangsCode = toegangsCode;
    }
    
    public List<OpnameMethode> getMogelijkeOpnameMethodes() {
        return mogelijkeOpnameMethodes;
    }

    public void setMogelijkeOpnameMethodes(List<OpnameMethode> mogelijkeOpnameMethodes) {
        this.mogelijkeOpnameMethodes = mogelijkeOpnameMethodes;
    }

    //</editor-fold>
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (this.OOD != null ? this.OOD.hashCode() : 0);
        hash = 17 * hash + (this.lokaal != null ? this.lokaal.hashCode() : 0);
        hash = 17 * hash + (this.reeks != null ? this.reeks.hashCode() : 0);
        hash = 17 * hash + (this.tijdstip != null ? this.tijdstip.hashCode() : 0);
        hash = 17 * hash + (this.lector != null ? this.lector.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OpnameMoment other = (OpnameMoment) obj;
        if ((this.OOD == null) ? (other.OOD != null) : !this.OOD.equals(other.OOD)) {
            return false;
        }
        if (this.lokaal != other.lokaal && (this.lokaal == null || !this.lokaal.equals(other.lokaal))) {
            return false;
        }
        if ((this.reeks == null) ? (other.reeks != null) : !this.reeks.equals(other.reeks)) {
            return false;
        }
        if (this.tijdstip != other.tijdstip && (this.tijdstip == null || !this.tijdstip.equals(other.tijdstip))) {
            return false;
        }
        if (this.lector != other.lector && (this.lector == null || !this.lector.equals(other.lector))) {
            return false;
        }
        return true;
    }
}