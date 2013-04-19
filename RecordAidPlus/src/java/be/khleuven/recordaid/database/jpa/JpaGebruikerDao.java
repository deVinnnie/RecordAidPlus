package be.khleuven.recordaid.database.jpa;

import be.khleuven.recordaid.domain.gebruiker.Gebruiker;
import be.khleuven.recordaid.domain.gebruiker.Rollen;
import be.khleuven.recordaid.database.interfaces.GebruikerDatabaseInterface;
import java.util.Collection;
import javax.persistence.*; 
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Een database die Gebruikers bijhoudt op een SQL server.
 *
 * @author Hannes
 */
@Repository("gebruikersDao")
@Transactional()
public class JpaGebruikerDao extends JpaAbstractDao implements GebruikerDatabaseInterface
{
    public JpaGebruikerDao(){}

    public JpaGebruikerDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Gebruiker getGebruiker(String emailadres)
    {
        Gebruiker gebruiker = getEntityManager().find(Gebruiker.class, emailadres);
        return gebruiker;
    }

    @Override
    public Gebruiker getGebruikerByValidatiecode(String validatiecode)
    {
        Query query = getEntityManager().createQuery("SELECT x FROM Gebruiker x WHERE x.validatieCode = :validatiecode");
        query.setParameter("validatiecode", validatiecode);
        return (Gebruiker) query.getSingleResult();
    }

    @Override
    public Collection<Gebruiker> getGebruikers()
    {
        Query query = getEntityManager().createQuery("SELECT x FROM Gebruiker x");
        Collection<Gebruiker> gebruikers = query.getResultList();
        return gebruikers;
    }

    @Override
    public Collection<Gebruiker> getGebruikers(Rollen rol)
    {
        Query query = getEntityManager().createQuery("SELECT x FROM Gebruiker x WHERE x.rol = :rol");
        query.setParameter("rol", rol);
        Collection<Gebruiker> gebruikers = query.getResultList();
        return gebruikers;
    }
}