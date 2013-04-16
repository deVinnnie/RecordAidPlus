package be.khleuven.recordaid.database.jpa;

import be.khleuven.recordaid.database.DatabaseException;
import be.khleuven.recordaid.database.interfaces.LectorDatabaseInterface;
import be.khleuven.recordaid.domain.Lector;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author Vincent Ceulemans
 */
@Repository("lectorDao")
@Transactional
public class JpaLectorDao implements LectorDatabaseInterface
{
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Constructor.
     */
    public JpaLectorDao()
    {
    }

    @Override
    public Lector getLector(String emailadres)
    {
        Lector lector = entityManager.find(Lector.class, emailadres);
        return lector;
    }

    @Override
    public void addLector(Lector lector) throws DatabaseException
    {
        if(lector == null)
        {
            throw new DatabaseException("De lector is null.");
        }

        entityManager.persist(lector);
    }

    @Override
    public void removeLector(Lector lector) throws DatabaseException
    {
        if(entityManager.find(Lector.class, lector.getEmailadres()) == null)
        {
            throw new DatabaseException("Lector bestaat niet");
        }
        entityManager.remove(lector);
    }

    @Override
    public void updateLector(Lector lector) throws DatabaseException
    {
        if(entityManager.find(Lector.class, lector.getEmailadres()) == null)
        {
            throw new DatabaseException("Lector bestaat niet");
        }

        entityManager.remove(lector);
        entityManager.merge(lector);
    }

    @Override
    public Collection<Lector> getLectoren()
    {
        Query query = entityManager.createQuery("SELECT x FROM Lector x");
        Collection<Lector> lectoren = query.getResultList();
        return lectoren;
    }
}