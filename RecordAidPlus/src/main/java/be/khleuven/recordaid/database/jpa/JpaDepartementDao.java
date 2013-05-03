package be.khleuven.recordaid.database.jpa;

import be.khleuven.recordaid.domain.departement.Lector;
import be.khleuven.recordaid.domain.departement.Departement;
import be.khleuven.recordaid.database.interfaces.DepartementDatabaseInterface;
import be.khleuven.recordaid.domain.*;
import be.khleuven.recordaid.opnames.OpnameMoment;
import java.util.List;
import javax.persistence.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("departementDao")
@Transactional
public class JpaDepartementDao extends JpaAbstractDao implements DepartementDatabaseInterface
{
    public JpaDepartementDao() {
    }

    public JpaDepartementDao(EntityManager entityManager) {
        super(entityManager);
    }
    
    @Override
    public List<Departement> getAlleDepartementen() {
        Query query = getEntityManager().createQuery("SELECT x FROM Departement x");
        return query.getResultList(); 
    }
    
    @Override
    public List<Lector> getLectoren()
    {
        Query query = getEntityManager().createQuery("SELECT x FROM Lector x");
        List<Lector> lectoren = query.getResultList();
        return lectoren;
    }

    @Override
    public List<OpnameMoment> getLessenVanLector(Lector lector) {
        Query query = getEntityManager().createQuery("SELECT o FROM OpnameMoment o WHERE o.lector = :lector");
        query.setParameter("lector", lector);
        List<OpnameMoment> opnameMomenten = query.getResultList();
        return opnameMomenten;
    }

    @Override
    public Lector getLector(String emailadres) {
        Query query = getEntityManager().createQuery("SELECT l FROM Lector l WHERE l.emailadres = :emailadres");
        query.setParameter("emailadres", emailadres);
        return (Lector) query.getSingleResult(); 
    }
}