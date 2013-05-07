package be.khleuven.recordaid.database.jpa;

import be.khleuven.recordaid.domain.gebruiker.Gebruiker;
import be.khleuven.recordaid.domain.aanvragen.*; 
import be.khleuven.recordaid.database.interfaces.AanvraagDatabaseInterface;
import be.khleuven.recordaid.opnames.*; 
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
    public JpaAanvragenDao() {}

    public JpaAanvragenDao(EntityManager entityManager) {
        super(entityManager);
    }
    
    @Override
    public Collection<AbstractAanvraag> getAanvragen(Status status)
    {
        Query query = getEntityManager().createQuery("SELECT x FROM AbstractAanvraag x WHERE x.status=:status");
        query.setParameter("status", status);
        Collection<AbstractAanvraag> aanvragen = query.getResultList();
        return aanvragen;
    }

    @Override
    public Collection<AbstractAanvraag> getAanvragenToegewezenLid(Gebruiker toegewezenLid)
    {
        Query query = getEntityManager().createQuery("SELECT x FROM AbstractAanvraag x WHERE x.toegewezenLid=:toegewezenLid");
        query.setParameter("toegewezenLid", toegewezenLid);
        Collection<AbstractAanvraag> aanvragen = query.getResultList();
        return aanvragen;
    }  
    
    @Override 
    public Collection<MultiPeriodeAanvraag> getAanvragen(Gebruiker begeleider){
        Query query = getEntityManager().createQuery("SELECT x FROM MultiPeriodeAanvraag x "
                + "WHERE x.begeleider=:begeleider");
        query.setParameter("begeleider", begeleider);
        Collection<MultiPeriodeAanvraag> aanvragen = query.getResultList();
        return aanvragen;
    }
    
    @Override
    public Collection<Opname> getOpnames(OpnameMethode opnameMethode){
        Query query = getEntityManager().createQuery("SELECT x FROM Opname x WHERE x.methode = :methode");
        query.setParameter("methode", opnameMethode);
        Collection<Opname> opnames = query.getResultList();
        return opnames;    
    }
}