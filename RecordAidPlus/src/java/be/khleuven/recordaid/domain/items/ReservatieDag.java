package be.khleuven.recordaid.domain.items;

import be.khleuven.recordaid.domain.*; 
import java.util.*; 
import javax.persistence.*; 
import static be.khleuven.recordaid.util.CalendarUtils.*;

/**
 *
 * @author Vincent Ceulemans
 */
@Entity
public class ReservatieDag extends Identifiable{
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar dag = Calendar.getInstance(); 
    
    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.MERGE})
    private List<Reservatie> reservaties = new ArrayList<Reservatie>(); 

    public ReservatieDag() {
    }

    public ReservatieDag(Calendar dag) {
        this.dag = trim(dag);
    }
    
    @Override
    public boolean equals(Object o){
        boolean isEqual =false; 
        if(o instanceof ReservatieDag){
            ReservatieDag otherReservatieDag = (ReservatieDag) o; 
            if(this.dag.equals(otherReservatieDag.dag)){
                isEqual = true; 
            }
        }
        return isEqual; 
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + (this.dag != null ? this.dag.hashCode() : 0);
        return hash;
    }
    
    /**
     * Add a new reservation for this day.
     * 
     * @param reservatie
     * @throws DomainException when the given reservation overlaps with an existing one. 
     */
    public void addReservatie(Reservatie reservatie) throws DomainException{
        //Check for overlapping. 
        for(Reservatie r : this.reservaties){
            if(reservatie.getSlot().isOverlapping(r.getSlot())){
                throw new DomainException("Reservatie overlapt met andere reservatie"); 
            }
        }
        this.reservaties.add(reservatie);
    }

    public List<Reservatie> getReservaties() {
        return reservaties;
    }

    public Calendar getDag() {
        return dag;
    }

    public void setDag(Calendar dag) {
        this.dag = trim(dag);
    }

    public void removeReservatie(Reservatie reservatie) throws DomainException {
        if (!this.reservaties.contains(reservatie)) {
            throw new DomainException("The given Reservatie does not exist in this context."); 
        }
        this.reservaties.remove(reservatie);
    }
}