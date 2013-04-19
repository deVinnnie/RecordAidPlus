package be.khleuven.recordaid.database.jpa;

import be.khleuven.recordaid.database.interfaces.DepartementDatabaseInterface;
import be.khleuven.recordaid.domain.Departement;
import be.khleuven.recordaid.domain.Lector;
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
}