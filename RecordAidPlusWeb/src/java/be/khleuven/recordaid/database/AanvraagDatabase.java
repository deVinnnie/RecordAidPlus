package be.khleuven.recordaid.database;

import be.khleuven.recordaid.domain.Aanvraag;
import be.khleuven.recordaid.domain.Gebruiker;
import be.khleuven.recordaid.domain.Status;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


/**
 * Een database die aanvragen bijhoudt op een SQL server.
 *
 * @author Maxime Van den Kerkhof
 */
public class AanvraagDatabase implements AanvraagDatabaseInterface
{
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("RecordAidPlusModelPU");
    private EntityManager entityManager = entityManagerFactory.createEntityManager();


    @Override
    public void addAanvraag(Aanvraag aanvraag)
    {
        entityManager.getTransaction().begin();
        entityManager.persist(aanvraag);
        entityManager.getTransaction().commit();
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