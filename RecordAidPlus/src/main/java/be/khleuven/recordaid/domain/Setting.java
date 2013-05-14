package be.khleuven.recordaid.domain;

import java.io.Serializable;
import javax.persistence.*; 

/**
 *
 * @author Vincent Ceulemans
 */
@Entity
public class Setting implements Serializable{
    @Id
    private String sleutel; 
    private String waarde; 

    public Setting() {
    }

    public Setting(String sleutel, String waarde) {
        this.sleutel = sleutel;
        this.waarde = waarde;
    }

    public String getSleutel() {
        return sleutel;
    }

    public void setSleutel(String sleutel) {
        this.sleutel = sleutel;
    }

    public String getWaarde() {
        return waarde;
    }

    public void setWaarde(String waarde) {
        this.waarde = waarde;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + (this.sleutel != null ? this.sleutel.hashCode() : 0);
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
        final Setting other = (Setting) obj;
        if ((this.sleutel == null) ? (other.sleutel != null) : !this.sleutel.equals(other.sleutel)) {
            return false;
        }
        return true;
    }
}