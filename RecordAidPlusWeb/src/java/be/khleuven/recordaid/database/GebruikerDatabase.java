package be.khleuven.recordaid.database;

import be.khleuven.recordaid.domain.Gebruiker;
import be.khleuven.recordaid.domain.Rollen;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


/**
 * Een database die Gebruikers bijhoudt op een SQL server.
 *
 * @author Hannes
 */
public class GebruikerDatabase implements GebruikerDatabaseInterface
{
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;


    /**
     * Constructor.
     */
    public GebruikerDatabase()
    {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("RecordAidPlusModelPU");
        this.entityManager = entityManagerFactory.createEntityManager();
    }


    @Override
    public Gebruiker getGebruiker(String emailadres) throws DatabaseException
    {
        Gebruiker gebruiker = entityManager.find(Gebruiker.class, emailadres);

        if(gebruiker == null)
        {
            throw new DatabaseException("gebruiker bestaat niet");
        }

        return gebruiker;
    }


    public Gebruiker getGebruikerByValidatiecode(String validatiecode)
    {
        Query query = entityManager.createQuery("SELECT x FROM Gebruiker x WHERE x.validatieCode = :validatiecode");
        query.setParameter("validatiecode", validatiecode);
        return (Gebruiker) query.getSingleResult();
    }


    @Override
    public void addGebruiker(Gebruiker gebruiker) throws DatabaseException
    {
        if(gebruiker == null)
        {
            throw new DatabaseException("gebruiker null");
        }

        if(entityManager.find(Gebruiker.class, gebruiker.getEmailadres()) != null)
        {
            throw new DatabaseException("gebruiker bestaat reeds");
        }

        System.out.println("*************************************" + gebruiker.isGevalideerd());
        entityManager.getTransaction().begin();
        entityManager.persist(gebruiker);
        entityManager.getTransaction().commit();
    }


    @Override
    public void removeGebruiker(String emailadres) throws DatabaseException
    {
        if(entityManager.find(Gebruiker.class, emailadres) == null)
        {
            throw new DatabaseException("gebruiker bestaat niet");
        }

        entityManager.getTransaction().begin();
        entityManager.remove(getGebruiker(emailadres));
        entityManager.getTransaction().commit();
    }


    @Override
    public void removeGebruiker(Gebruiker gebruiker) throws DatabaseException
    {
        if(entityManager.find(Gebruiker.class, gebruiker.getEmailadres()) == null)
        {
            throw new DatabaseException("gebruiker bestaat niet");
        }

        entityManager.getTransaction().begin();
        entityManager.remove(gebruiker);
        entityManager.getTransaction().commit();
    }


    @Override
    public void updateGebruiker(Gebruiker gebruiker) throws DatabaseException
    {
        if(entityManager.find(Gebruiker.class, gebruiker.getEmailadres()) == null)
        {
            throw new DatabaseException("gebruiker bestaat niet");
        }

        entityManager.getTransaction().begin();
        entityManager.remove(gebruiker);
        entityManager.persist(gebruiker);
        entityManager.getTransaction().commit();
    }


    @Override
    public Collection<Gebruiker> getGebruikers()
    {
        Query query = entityManager.createQuery("SELECT x FROM Gebruiker x");
        Collection<Gebruiker> gebruikers = query.getResultList();
        return gebruikers;
    }


    @Override
    public Collection<Gebruiker> getGebruikers(Rollen rol)
    {
        Query query = entityManager.createQuery("SELECT x FROM Gebruiker x WHERE x.rol = :rol");
        query.setParameter("rol", rol);
        Collection<Gebruiker> gebruikers = query.getResultList();
        return gebruikers;
    }
}


