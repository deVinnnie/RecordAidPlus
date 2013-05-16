package be.khleuven.recordaid.domain.departement;

import be.khleuven.recordaid.domain.Identifiable;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author Vincent Ceulemans
 */
@Entity
public class Lector extends Identifiable implements Serializable {
    private String naam; 
    
    @Column(unique=true)
    private String emailadres; 
    
    private boolean autonoomOpnemen = false; 
    
    /*
     * Hoe lager dit getal hoe meer opnames de lector geweigerd heeft, 
     * en waarschijnlijk zal weigeren. 
     * Hoe hoger hoe meer geneigd de lector is om de opname toe te staan. 
     */
    private int aanvaardingsFactor = 0; 
    
    public Lector(){}
    
    private Lector(String naam, String emailadres){
        this.naam = naam; 
        this.emailadres = emailadres;
    }
    
    public Lector(String emailadres){ 
        this.emailadres = emailadres; 
       
        /*Given the emailadress we can determine the name, 
        because the emailadresses are in the form of: name.surname@khleuven.be. 
        We will first split the emailadress on the @ sign. Leaving the part with the actual name. 
        The second split removes the dots. */
        String[] tussenResultaat = this.emailadres.split("@");
        String[] naamSplitted = tussenResultaat[0].split("\\.");
        String n = "";
        for (int i = 0; i < naamSplitted.length; i++) {
            n += Character.toUpperCase(naamSplitted[i].charAt(0)) + naamSplitted[i].substring(1) + " ";
        }
        this.naam = n.trim();
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getEmailadres() {
        return emailadres;
    }

    public void setEmailadres(String emailadres) {
        this.emailadres = emailadres;
    }
    
    public void setAutonoomOpnemen(boolean autonoomOpnemen){
        this.autonoomOpnemen = autonoomOpnemen; 
    }
    
    public boolean getAutonoomOpnemen(){
        return this.autonoomOpnemen; 
    }
}