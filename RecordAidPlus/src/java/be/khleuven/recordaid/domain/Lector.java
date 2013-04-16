package be.khleuven.recordaid.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Vincent Ceulemans
 */
@Entity
public class Lector implements Serializable {
    private String naam; 
    
    @Id
    private String emailadres; 
    
    public Lector(){}
    
    private Lector(String naam, String emailadres){
        this.naam = naam; 
        this.emailadres = emailadres;
    }
    
    public Lector(String emailadres){ 
        this.setEmailadres(emailadres); 
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
        
        /*Given the emailadress we can determine the name, 
        because the emailadresses are in the form of: name.surname@khleuven.be. 
        We will first split the emailadress on the @ sign. Leaving the part with the actual name. 
        The second split removes the dots. */
        String[] tussenResultaat = this.emailadres.split("@"); 
        String[] naamSplitted = tussenResultaat[0].split("\\."); 
        String n=""; 
        for(int i =0; i<naamSplitted.length;i++){
            n+=Character.toUpperCase(naamSplitted[i].charAt(0)) + naamSplitted[i].substring(1)+" ";
        }
        this.naam=n.trim(); 
    }
    
    @Override
    public boolean equals(Object o){
        boolean isEqual = false; 
        if(o != null && o instanceof Lector){
            Lector other = (Lector) o; 
            if(this.emailadres.equals(other.emailadres)){
                isEqual = true; 
            }
        }
        return isEqual; 
    }
}