package be.khleuven.recordaid.database.embedded;

import be.khleuven.recordaid.database.DatabaseException;
import be.khleuven.recordaid.database.interfaces.SupportDatabaseInterface;
import be.khleuven.recordaid.domain.Support;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


/**
 * Een database die Supports bijhoudt op een SQL server.
 *
 * @author Maxime
 */
public class SupportDatabase implements SupportDatabaseInterface
{
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("RecordAidPlusModelPU");
    private EntityManager em = factory.createEntityManager();


    public Support findSupport(long id) throws DatabaseException
    {
        return em.find(Support.class, id);
    }


    public void addSupport(Support support) throws DatabaseException
    {
        em.getTransaction().begin();
        em.persist(support);
        em.getTransaction().commit();
    }


    public void removeSupport(long id) throws DatabaseException
    {
        em.getTransaction().begin();
        em.remove(this.findSupport(id));
        em.getTransaction().commit();
    }


    public void removeSupport(Support support) throws DatabaseException
    {
        em.getTransaction().begin();
        em.remove(support);
        em.getTransaction().commit();
    }


    public void updateSupport(Support support) throws DatabaseException
    {
        Support update = this.findSupport(support.getId());
        update.setLokaal(support.getLokaal());
        update.setReport(support.getReport());

    }


    public Collection<Support> getSupports()
    {
        Query query = em.createQuery("SELECT x FROM Support x");
        Collection<Support> supports = query.getResultList();
        return supports;
    }
}


