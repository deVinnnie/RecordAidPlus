package be.khleuven.recordaid.domain.aanvragen;

import be.khleuven.recordaid.opnames.OpnameMoment;
import be.khleuven.recordaid.domain.gebruiker.Dossier;
import be.khleuven.recordaid.domain.Departement;
import be.khleuven.recordaid.domain.DomainException;
import be.khleuven.recordaid.domain.Identifiable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.*; 

/**
 *
 * @author Vincent Ceulemans
 */
@Entity
public abstract class AbstractAanvraag extends Identifiable implements Serializable {
    @OneToOne
    private Departement departement;  

    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar aanvraagDatum = Calendar.getInstance();
    
    @ManyToOne
    private Dossier dossier; 
    
    @Enumerated
    private Status status = Status.NIEUW;
    
    /**
    * Lijst van opnames die dienen gemaakt te worden. 
    */
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<OpnameMoment> opnameMomenten = new ArrayList<OpnameMoment>(); 
    
    @Column(columnDefinition = "CLOB")
    private String reden;
    
    @Column(columnDefinition = "CLOB")
    private String opmerking; 
    
    public AbstractAanvraag(){
    }
    
    public AbstractAanvraag(Dossier dossier, Departement departement){
        this.dossier = dossier; 
        this.departement = departement; 
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
    
    public String getOpmerking() {
        return opmerking;
    }

    public void setOpmerking(String opmerking) {
        this.opmerking = opmerking;
    }
    //</editor-fold>
    
    public void addOpnameMoment(OpnameMoment opnameMoment) throws DomainException{
        this.opnameMomenten.add(opnameMoment);
    }
    
    public List<OpnameMoment> getOpnameMomenten(){
        return this.opnameMomenten; 
    }
    
    /**
     * Searches and retrieves the OpnameMoment with the given id. 
     * 
     * @param id The id of the OpnameMoment. 
     * @return The OpnameMoment instance with the given id. Or null. 
     */
    public OpnameMoment getOpnameMoment(long id){
        OpnameMoment opnameMoment=null; 
        int i = 0; 
        boolean gevonden = false;
        while(i < this.getOpnameMomenten().size() && !gevonden){
            if(this.getOpnameMomenten().get(i).getId() == id){
                opnameMoment = this.getOpnameMomenten().get(i); 
                gevonden = true; 
            }
            else{
                i++; 
            }
        } 
        return opnameMoment; 
    }
    
    public void setOpnameMomenten(List<OpnameMoment> opnameMomenten){
        this.opnameMomenten = opnameMomenten; 
    }
    
    public abstract Calendar getDefaultOpnameMomentDag();
}