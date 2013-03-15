package be.khleuven.eindwerk.database;

import be.khleuven.eindwerk.domain.FAQ;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


/**
 * Een database die FAQs bijhoudt op een SQL server.
 *
 * @author Maxime Van den Kerkhof
 */
public class FAQDatabase implements FAQDatabaseInterface
{
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("RecordAidPlusModelPU");
    private EntityManager em = factory.createEntityManager();

    @Override
    public void addFAQ(FAQ faq)
    {
        em.getTransaction().begin();
        em.persist(faq);
        em.getTransaction().commit();

    }


    @Override
    public FAQ findFAQ(Long id)
    {
        return (FAQ) em.find(FAQ.class, id);
    }


    @Override
    public void updateFAQ(FAQ faq)
    {
        em.getTransaction().begin();
        FAQ update = findFAQ(faq.getId());
        update.setRelevant(faq.isRelevant());
        update.setAntwoord(faq.getAntwoord());
        update.setVraag(faq.getVraag());
        em.getTransaction().commit();
    }


    @Override
    public void removeFAQ(FAQ faq)
    {
        em.getTransaction().begin();
        em.remove(faq);
        em.getTransaction().commit();
    }


    @Override
    public Collection<FAQ> getFAQs()
    {
        Query query = em.createQuery("SELECT x FROM FAQ x");
        Collection<FAQ> faqs = query.getResultList();
        return faqs;
    }


    @Override
    public Collection<FAQ> getRelevanteFAQs()
    {
        Query query = em.createQuery("SELECT x FROM FAQ x where x.relevant = 1");
        Collection<FAQ> faqs = query.getResultList();
        return faqs;
    }


    @Override
    public Collection<FAQ> getBeantwoordeFAQs()
    {
        Query query = em.createQuery("SELECT x FROM FAQ x where x.beantwoord = 1");
        Collection<FAQ> faqs = query.getResultList();
        return faqs;
    }


    @Override
    public Collection<FAQ> getNietBeantwoordeFAQs()
    {
        Query query = em.createQuery("SELECT x FROM FAQ x where x.beantwoord = 0");
        Collection<FAQ> faqs = query.getResultList();
        return faqs;
    }
}


