package be.khleuven.recordaid.opnames;

import be.khleuven.recordaid.domain.Identifiable;
import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author Vincent Ceulemans
 */
@Entity
public class Locatie extends Identifiable implements Serializable{
    private String medium;
    private String wegwijzer; 

    public Locatie() {
    }

    public Locatie(String medium, String pad) {
        this.medium = medium;
        this.wegwijzer = pad;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getWegwijzer() {
        return wegwijzer;
    }

    public void setWegwijzer(String wegwijzer) {
        this.wegwijzer = wegwijzer;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + (this.medium != null ? this.medium.hashCode() : 0);
        hash = 19 * hash + (this.wegwijzer != null ? this.wegwijzer.hashCode() : 0);
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
        final Locatie other = (Locatie) obj;
        if ((this.medium == null) ? (other.medium != null) : !this.medium.equals(other.medium)) {
            return false;
        }
        if ((this.wegwijzer == null) ? (other.wegwijzer != null) : !this.wegwijzer.equals(other.wegwijzer)) {
            return false;
        }
        return true;
    }
}