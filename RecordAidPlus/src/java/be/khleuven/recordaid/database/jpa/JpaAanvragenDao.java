package be.khleuven.recordaid.database.jpa;

import be.khleuven.recordaid.domain.aanvragen.Status;
import be.khleuven.recordaid.domain.aanvragen.Aanvraag;
import be.khleuven.recordaid.database.interfaces.AanvraagDatabaseInterface;
import be.khleuven.recordaid.database.DatabaseException;
import be.khleuven.recordaid.database.DatabaseException;
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
public class JpaAanvragenDao implements AanvraagDatabaseInterface{
    @PersistenceContext
    private EntityManager entityManager;
   
    @Override
    public void addAanvraag(Aanvraag aanvraag)
    {
        entityManager.persist(aanvraag);
    }

    @Override
    public Aanvraag findAanvraag(long id)
    {
        return entityManager.find(Aanvraag.class, id);
    }

    @Override
    public void updateAanvraag(Aanvraag aanvraag) throws DatabaseException
    {
        entityManager.getTransaction().begin();

        Aanvraag update = this.findAanvraag(aanvraag.getId());
        update.setLinkNaarVideo(aanvraag.getLinkNaarVideo());
        update.setStatus(aanvraag.getStatus());
        update.setToegewezenLid(aanvraag.getToegewezenLid());

        entityManager.merge(aanvraag);
        entityManager.getTransaction().commit();
    }

    @Override
    public void removeAanvraag(long id)
    {
        entityManager.remove(findAanvraag(id));
    }

    @Override
    public void removeAanvraag(Aanvraag aanvraag)
    {
        entityManager.remove(aanvraag);
    }

    @Override
    public List<Aanvraag> getAanvragen()
    {
        Query query = entityManager.createQuery("SELECT x FROM Aanvraag x");
        Collection<Aanvraag> aanvragen = query.getResultList();
        return (List<Aanvraag>) aanvragen;
    }

    @Override
    public List<Aanvraag> getAanvragen(Status status)
    {
        Query query = entityManager.createQuery("SELECT x FROM Aanvraag x WHERE x.status=:status");
        query.setParameter("status", status);
        Collection<Aanvraag> aanvragen = query.getResultList();
        return (List<Aanvraag>) aanvragen;
    }

    @Override
    public List<Aanvraag> getAanvragenAanvrager(Gebruiker aanvrager)
    {
        Query query = entityManager.createQuery("SELECT x FROM Aanvraag x WHERE x.aanvrager=:aanvrager");
        query.setParameter("aanvrager", aanvrager);
        Collection<Aanvraag> aanvragen = query.getResultList();
        return (List<Aanvraag>) aanvragen;
    }

    @Override
    public List<Aanvraag> getAanvragenToegewezenLid(Gebruiker toegewezenLid)
    {
        Query query = entityManager.createQuery("SELECT x FROM Aanvraag x WHERE x.toegewezenLid=:toegewezenLid");
        query.setParameter("toegewezenLid", toegewezenLid);
        Collection<Aanvraag> aanvragen = query.getResultList();
        return (List<Aanvraag>) aanvragen;
    }  
}