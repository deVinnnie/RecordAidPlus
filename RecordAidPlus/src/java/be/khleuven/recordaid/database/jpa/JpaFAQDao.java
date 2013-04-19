package be.khleuven.recordaid.database.jpa;

import be.khleuven.recordaid.database.interfaces.FAQDatabaseInterface;
import be.khleuven.recordaid.domain.FAQ;
import java.util.Collection;
import javax.persistence.*; 
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("faqDao")
@Transactional
public class JpaFAQDao extends JpaAbstractDao implements FAQDatabaseInterface {
    public JpaFAQDao() {
    }

    public JpaFAQDao(EntityManager entityManager) {
        super(entityManager);
    }
    
    @Override
    public Collection<FAQ> getFAQs() {
        Query query = getEntityManager().createQuery("SELECT x FROM FAQ x");
        Collection<FAQ> faqs = query.getResultList();
        return faqs;
    }

    @Override
    public Collection<FAQ> getRelevanteFAQs() {
        Query query = getEntityManager().createQuery("SELECT x FROM FAQ x where x.relevant = 1");
        Collection<FAQ> faqs = query.getResultList();
        return faqs;
    }

    @Override
    public Collection<FAQ> getBeantwoordeFAQs() {
        Query query = getEntityManager().createQuery("SELECT x FROM FAQ x where x.beantwoord = 1");
        Collection<FAQ> faqs = query.getResultList();
        return faqs;
    }

    @Override
    public Collection<FAQ> getNietBeantwoordeFAQs() {
        Query query = getEntityManager().createQuery("SELECT x FROM FAQ x where x.beantwoord = 0");
        Collection<FAQ> faqs = query.getResultList();
        return faqs;
    }
}