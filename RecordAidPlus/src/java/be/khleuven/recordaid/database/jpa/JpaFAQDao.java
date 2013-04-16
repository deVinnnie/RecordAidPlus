package be.khleuven.recordaid.database.jpa;

import be.khleuven.recordaid.database.interfaces.FAQDatabaseInterface;
import be.khleuven.recordaid.domain.FAQ;
import java.util.Collection;
import javax.persistence.*; 
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("faqDao")
public class JpaFAQDao implements FAQDatabaseInterface {
    @PersistenceContext
    private EntityManager em;
    
    @Override
    @Transactional
    public void addFAQ(FAQ faq) {
        em.persist(faq);
    }

    @Override
    public FAQ findFAQ(Long id) {
        return (FAQ) em.find(FAQ.class, id);
    }

    @Override
    @Transactional
    public void updateFAQ(FAQ faq) {
        em.remove(faq);
        em.merge(faq);
    }

    @Override
    @Transactional
    public void removeFAQ(FAQ faq) {
        em.remove(faq);
    }

    @Override
    public Collection<FAQ> getFAQs() {
        Query query = em.createQuery("SELECT x FROM FAQ x");
        Collection<FAQ> faqs = query.getResultList();
        return faqs;
    }

    @Override
    public Collection<FAQ> getRelevanteFAQs() {
        Query query = em.createQuery("SELECT x FROM FAQ x where x.relevant = 1");
        Collection<FAQ> faqs = query.getResultList();
        return faqs;
    }

    @Override
    public Collection<FAQ> getBeantwoordeFAQs() {
        Query query = em.createQuery("SELECT x FROM FAQ x where x.beantwoord = 1");
        Collection<FAQ> faqs = query.getResultList();
        return faqs;
    }

    @Override
    public Collection<FAQ> getNietBeantwoordeFAQs() {
        Query query = em.createQuery("SELECT x FROM FAQ x where x.beantwoord = 0");
        Collection<FAQ> faqs = query.getResultList();
        return faqs;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}