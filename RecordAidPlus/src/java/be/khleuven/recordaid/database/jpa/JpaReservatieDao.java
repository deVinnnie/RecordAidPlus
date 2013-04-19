package be.khleuven.recordaid.database.jpa;

import be.khleuven.recordaid.database.interfaces.ReservatieDatabaseInterface;
import be.khleuven.recordaid.domain.Item;
import be.khleuven.recordaid.domain.Reservatie;
import java.util.Calendar;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    public Collection<Reservatie> getReservaties(Calendar datum) {
        Query query = getEntityManager().createQuery("SELECT x FROM Reservatie x WHERE x.datum=:datum ORDER BY x.slot asc");
        query.setParameter("datum", datum);
        Collection<Reservatie> reservaties = query.getResultList();
        return reservaties;
    }

    @Override
    public Collection<Reservatie> getReservaties(Calendar datum, Item item) {
        Query query = getEntityManager().createQuery("SELECT x FROM Reservatie x WHERE x.datum=:datum AND x.item=:item ORDER BY x.slot asc");
        query.setParameter("datum", datum);
        query.setParameter("item", item);
        Collection<Reservatie> reservaties = query.getResultList();
        return reservaties;
    }
}