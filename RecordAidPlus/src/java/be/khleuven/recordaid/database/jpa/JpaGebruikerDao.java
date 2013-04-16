package be.khleuven.recordaid.database.jpa;

import be.khleuven.recordaid.database.interfaces.GebruikerDatabaseInterface;
import be.khleuven.recordaid.database.DatabaseException;
import be.khleuven.recordaid.database.DatabaseException;
import be.khleuven.recordaid.domain.Gebruiker;
import be.khleuven.recordaid.domain.Rollen;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Een database die Gebruikers bijhoudt op een SQL server.
 *
 * @author Hannes
 */
@Repository("gebruikersDao")
@Transactional()
public class JpaGebruikerDao implements GebruikerDatabaseInterface
{
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Constructor.
     */
    public JpaGebruikerDao()
    {
    }

    @Override
    public Gebruiker getGebruiker(String emailadres)
    {
        Gebruiker gebruiker = entityManager.find(Gebruiker.class, emailadres);
        return gebruiker;
    }

    @Override
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
            throw new DatabaseException("De gebruiker is null.");
        }

        entityManager.persist(gebruiker);
    }

    @Override
    public void removeGebruiker(String emailadres) throws DatabaseException
    {
        if(entityManager.find(Gebruiker.class, emailadres) == null)
        {
            throw new DatabaseException("gebruiker bestaat niet");
        }
        entityManager.remove(getGebruiker(emailadres));
    }

    @Override
    public void removeGebruiker(Gebruiker gebruiker) throws DatabaseException
    {
        if(entityManager.find(Gebruiker.class, gebruiker.getEmailadres()) == null)
        {
            throw new DatabaseException("gebruiker bestaat niet");
        }
        entityManager.remove(gebruiker);
    }

    @Override
    public void updateGebruiker(Gebruiker gebruiker) throws DatabaseException
    {
        if(entityManager.find(Gebruiker.class, gebruiker.getEmailadres()) == null)
        {
            throw new DatabaseException("gebruiker bestaat niet");
        }

        entityManager.remove(gebruiker);
        entityManager.merge(gebruiker);
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