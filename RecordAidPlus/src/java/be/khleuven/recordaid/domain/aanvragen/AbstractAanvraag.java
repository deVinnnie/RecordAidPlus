package be.khleuven.recordaid.domain.aanvragen;

import be.khleuven.recordaid.domain.gebruiker.Dossier;
import be.khleuven.recordaid.domain.Departement;
import be.khleuven.recordaid.domain.DomainException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.*; 

/**
 *
 * @author Vincent Ceulemans
 */
@Entity
public abstract class AbstractAanvraag implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne
    private Departement departement;  

    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar aanvraagDatum = Calendar.getInstance();
    
    @ManyToOne
    private Dossier dossier; 
    
    @Enumerated
    private Status status = Status.NIEUW;
    
    /*
    * Lijst van opnames die dienen gemaakt te worden. 
    */
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<OpnameMoment> opnameMomenten; 
    
    private String reden;
    
    public AbstractAanvraag(){
    }
    
    public AbstractAanvraag(Dossier dossier, Departement departement){
        this.dossier = dossier; 
        this.departement = departement; 
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public Departement getDepartement(){
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public Calendar getAanvraagDatum() {
        return aanvraagDatum;
    }

    public void setAanvraagDatum(Calendar aanvraagDatum) {
        this.aanvraagDatum = aanvraagDatum;
    }

    public Dossier getDossier() {
        return dossier;
    }

    public void setDossier(Dossier dossier) {
        this.dossier = dossier;
    }

    public String getReden() {
        return reden;
    }

    public void setReden(String reden) {
        this.reden = reden;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    //</editor-fold>
    
    public void addOpnameMoment(OpnameMoment opnameMoment) throws DomainException{
        this.opnameMomenten.add(opnameMoment);
    }
    
    public List<OpnameMoment> getOpnameMomenten(){
        return this.opnameMomenten; 
    }
}