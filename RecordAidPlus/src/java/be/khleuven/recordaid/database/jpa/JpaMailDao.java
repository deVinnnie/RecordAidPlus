package be.khleuven.recordaid.database.jpa;

import be.khleuven.recordaid.database.interfaces.MailDatabaseInterface;
import be.khleuven.recordaid.domain.mailing.MailMessage;
import javax.persistence.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Vincent Ceulemans
 */
@Repository("mailDao")
@Transactional
public class JpaMailDao extends JpaAbstractDao implements MailDatabaseInterface{
    public JpaMailDao(){}
    
    public JpaMailDao(EntityManager entityManager) {
        super(entityManager); 
    }
    
    @Override
    public MailMessage getMailMessage(String description){
        Query query = getEntityManager().createQuery("SELECT x FROM MailMessage x WHERE x.description = :description");
        query.setParameter("description", description);
        return (MailMessage) query.getSingleResult();
    }
}