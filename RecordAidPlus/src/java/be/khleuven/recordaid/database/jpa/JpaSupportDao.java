package be.khleuven.recordaid.database.jpa;

import be.khleuven.recordaid.database.DatabaseException;
import be.khleuven.recordaid.database.interfaces.SupportDatabaseInterface;
import be.khleuven.recordaid.domain.Support;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Een database die Supports bijhoudt op een SQL server.
 *
 * @author Maxime
 */
@Repository("supportDao")
@Transactional
public class JpaSupportDao implements SupportDatabaseInterface
{
    @PersistenceContext
    private EntityManager em; 

    @Override
    public Support findSupport(long id) throws DatabaseException
    {
        return em.find(Support.class, id);
    }

    @Override
    public void addSupport(Support support) throws DatabaseException
    {
        em.getTransaction().begin();
        em.persist(support);
        em.getTransaction().commit();
    }

    @Override
    public void removeSupport(long id) throws DatabaseException
    {
        em.getTransaction().begin();
        em.remove(this.findSupport(id));
        em.getTransaction().commit();
    }

    @Override
    public void removeSupport(Support support) throws DatabaseException
    {
        em.getTransaction().begin();
        em.remove(support);
        em.getTransaction().commit();
    }

    @Override
    public void updateSupport(Support support) throws DatabaseException
    {
        Support update = this.findSupport(support.getId());
        update.setLokaal(support.getLokaal());
        update.setReport(support.getReport());

    }

    @Override
    public Collection<Support> getSupports()
    {
        Query query = em.createQuery("SELECT x FROM Support x");
        Collection<Support> supports = query.getResultList();
        return supports;
    }
}