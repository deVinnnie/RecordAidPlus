package be.khleuven.recordaid.database.jpa;

import be.khleuven.recordaid.domain.gebruiker.Gebruiker;
import be.khleuven.recordaid.domain.aanvragen.Status;
import be.khleuven.recordaid.domain.aanvragen.DagAanvraag;
import be.khleuven.recordaid.database.interfaces.AanvraagDatabaseInterface;
import be.khleuven.recordaid.domain.*; 
import java.util.*; 
import javax.persistence.*; 
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Vincent Ceulemans
 */
@Repository("aanvragenDao")
@Transactional
public class JpaAanvragenDao extends JpaAbstractDao implements AanvraagDatabaseInterface{
    public JpaAanvragenDao() {
    }

    public JpaAanvragenDao(EntityManager entityManager) {
        super(entityManager);
    }
    
    @Override
    public List<DagAanvraag> getAanvragen(Status status)
    {
        Query query = getEntityManager().createQuery("SELECT x FROM Aanvraag x WHERE x.status=:status");
        query.setParameter("status", status);
        Collection<DagAanvraag> aanvragen = query.getResultList();
        return (List<DagAanvraag>) aanvragen;
    }

    @Override
    public List<DagAanvraag> getAanvragenToegewezenLid(Gebruiker toegewezenLid)
    {
        Query query = getEntityManager().createQuery("SELECT x FROM Aanvraag x WHERE x.toegewezenLid=:toegewezenLid");
        query.setParameter("toegewezenLid", toegewezenLid);
        Collection<DagAanvraag> aanvragen = query.getResultList();
        return (List<DagAanvraag>) aanvragen;
    }  
}