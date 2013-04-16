package be.khleuven.recordaid.database.jpa;

import be.khleuven.recordaid.database.interfaces.ReservatieDatabaseInterface;
import be.khleuven.recordaid.database.DatabaseException;
import be.khleuven.recordaid.database.DatabaseException;
import be.khleuven.recordaid.domain.Item;
import be.khleuven.recordaid.domain.Reservatie;
import java.util.Calendar;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
public class JpaReservatieDao implements ReservatieDatabaseInterface {
    @PersistenceContext
    private EntityManager entityManager;

    public JpaReservatieDao() {
    }

    @Override
    public Reservatie getReservatie(long id) {
        return entityManager.find(Reservatie.class, id);
    }

    @Override
    public void addReservatie(Reservatie reservatie) throws DatabaseException {
        if (entityManager.find(Reservatie.class, reservatie.getId()) != null) {
            throw new DatabaseException("reservatie bestaat reeds");
        }

        entityManager.getTransaction().begin();
        entityManager.persist(reservatie);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateReservatie(Reservatie reservatie) {
        entityManager.getTransaction().begin();
        entityManager.remove(getReservatie(reservatie.getId()));
        entityManager.persist(reservatie);
        entityManager.getTransaction().commit();
    }

    @Override
    public void removeReservatie(Reservatie reservatie) {
        entityManager.getTransaction().begin();
        entityManager.remove(reservatie);
        entityManager.getTransaction().commit();
    }

    @Override
    public void removeReservatie(long id) {
        entityManager.getTransaction().begin();

        Reservatie reservatie = getReservatie(id);
        reservatie.setGebruiker(null);
        reservatie.setItem(null);

        entityManager.remove(reservatie);
        entityManager.getTransaction().commit();
    }

    @Override
    public Collection<Reservatie> getReservaties() {
        Query query = entityManager.createQuery("SELECT x FROM Reservatie x ORDER BY x.slot asc");
        Collection<Reservatie> reservaties = query.getResultList();
        return reservaties;
    }

    @Override
    public Collection<Reservatie> getReservaties(Calendar datum) {
        Query query = entityManager.createQuery("SELECT x FROM Reservatie x WHERE x.datum=:datum ORDER BY x.slot asc");
        query.setParameter("datum", datum);
        Collection<Reservatie> reservaties = query.getResultList();
        return reservaties;
    }

    @Override
    public Collection<Reservatie> getReservaties(Calendar datum, Item item) {
        Query query = entityManager.createQuery("SELECT x FROM Reservatie x WHERE x.datum=:datum AND x.item=:item ORDER BY x.slot asc");
        query.setParameter("datum", datum);
        query.setParameter("item", item);
        Collection<Reservatie> reservaties = query.getResultList();
        return reservaties;
    }
}