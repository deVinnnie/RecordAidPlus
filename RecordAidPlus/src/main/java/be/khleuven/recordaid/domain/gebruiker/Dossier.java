package be.khleuven.recordaid.domain.gebruiker;

import be.khleuven.recordaid.domain.aanvragen.AbstractAanvraag;
import java.io.Serializable;
import javax.persistence.*; 
import java.util.*; 

/**
 *
 * @author Vincent Ceulemans
 */
@Entity
public class Dossier implements Serializable { 
    @OneToOne
    @Id
    private Gebruiker gebruiker; 
    
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Geschiedenis geschiedenis = new Geschiedenis(); 
    
    @OneToMany(mappedBy="dossier",cascade={CascadeType.MERGE, CascadeType.PERSIST})
    private List<AbstractAanvraag> aanvragen = new ArrayList<AbstractAanvraag>();
    
    @OneToOne
    private Gebruiker verantwoordelijke; 
    
    public Dossier(){}
    
    public Dossier(Gebruiker gebruiker){
        this.gebruiker = gebruiker; 
    }

    public Gebruiker getGebruiker() {
        return gebruiker;
    }

    public void setGebruiker(Gebruiker gebruiker) {
        this.gebruiker = gebruiker;
    }

    public Geschiedenis getGeschiedenis() {
        return geschiedenis;
    }

    public void setGeschiedenis(Geschiedenis geschiedenis) {
        this.geschiedenis = geschiedenis;
    }

    public void addAanvraag(AbstractAanvraag aanvraag) {
        this.addAanvraag(aanvraag, gebruiker); 
    }
    
    public void addAanvraag(AbstractAanvraag aanvraag, Gebruiker aanvrager) {
        this.aanvragen.add(aanvraag);
        this.addGebeurtenis("Nieuwe aanvraag toegevoegd", aanvrager);
    }
    
    public List<AbstractAanvraag> getAanvragen(){
        return this.aanvragen;
    }
    
    public AbstractAanvraag getAanvraag(long id) {
        AbstractAanvraag aanvraag = null;
        boolean found = false;
        int i = 0;
        while (i < this.getAanvragen().size() && !found) {
            if (this.getAanvragen().get(i).getId() == id) {
                aanvraag = this.getAanvragen().get(i);
                found=true; 
            } else {
                i++;
            }
        }
        return aanvraag;
    }   

    public Gebruiker getVerantwoordelijke() {
        return verantwoordelijke;
    }

    public void setVerantwoordelijke(Gebruiker verantwoordelijke) {
        this.verantwoordelijke = verantwoordelijke;
    }
    
    public void addGebeurtenis(String gebeurtenis, Gebruiker  betrokkenGebruiker){
        Gebeurtenis g = new Gebeurtenis(gebeurtenis, betrokkenGebruiker); 
        this.geschiedenis.addGebeurtenis(g);
    }
}