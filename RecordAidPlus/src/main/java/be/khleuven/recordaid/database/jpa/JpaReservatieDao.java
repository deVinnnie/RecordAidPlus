package be.khleuven.recordaid.database.jpa;

import be.khleuven.recordaid.database.interfaces.ReservatieDatabaseInterface;
import be.khleuven.recordaid.domain.items.*; 
import java.util.Calendar;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import static be.khleuven.recordaid.util.CalendarUtils.*; 
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TemporalType;

/**
 * Een database die Reservaties bijhoudt op een SQL server.
 *
 * @author Hannes
 */
@Repository("reservatiesDao")
@Transactional
public class JpaReservatieDao extends JpaAbstractDao implements ReservatieDatabaseInterface {
    public JpaReservatieDao() {
    }

    public JpaReservatieDao(EntityManager entityManager) {
        super(entityManager);
    }
    
    @Override
    public Collection<Reservatie> getReservaties() {
        Query query = getEntityManager().createQuery("SELECT x FROM Reservatie x ORDER BY x.slot asc");
        Collection<Reservatie> reservaties = query.getResultList();
        return reservaties;
    }
    
    @Override
    public Collection<ReservatieDag> getReservaties(Item item) {
        Query query = getEntityManager().createQuery("SELECT item.reservatieDagen "
                + "FROM Item item "
                + "WHERE item = :item");
        query.setParameter("item", item); 
        return query.getResultList();
    }

    @Override
    public Collection<Reservatie> getReservaties(Calendar datum, Item item) {
        datum = trim(datum); 
        Query query = getEntityManager().createQuery("SELECT reservatieDag.reservaties "
                + "FROM Item item JOIN item.reservatieDagen reservatieDag "
                + "WHERE item = :item AND reservatieDag.dag = :datum");
        query.setParameter("datum", datum);
        query.setParameter("item", item); 
        return query.getResultList();
    }
    
    @Override
    public Collection<Reservatie> getReservaties(Calendar start, Calendar end, Item item) { 
        Query query = getEntityManager().createQuery(
                "SELECT reservatie "
                + "FROM Item item JOIN item.reservatieDagen reservatieDag JOIN reservatieDag.reservaties reservatie "
                + "WHERE item = :item AND reservatieDag.dag BETWEEN :start AND :end");
        query.setParameter("start", start, TemporalType.DATE);
        query.setParameter("end", end, TemporalType.DATE);
        query.setParameter("item", item); 
        return query.getResultList();
    }
    
    @Override
    public Collection<Reservatie> getReservaties(Calendar start, Calendar end) { 
        Query query = getEntityManager().createQuery(
                "SELECT reservatie "
                + "FROM Item item JOIN item.reservatieDagen reservatieDag JOIN reservatieDag.reservaties reservatie "
                + "WHERE reservatieDag.dag BETWEEN :start AND :end");
        query.setParameter("start", start, TemporalType.DATE);
        query.setParameter("end", end, TemporalType.DATE);
        List<Reservatie> resultList = query.getResultList();
        return resultList; 
    }
}