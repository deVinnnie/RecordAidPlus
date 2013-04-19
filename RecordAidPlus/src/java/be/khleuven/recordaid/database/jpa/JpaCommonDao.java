package be.khleuven.recordaid.database.jpa;

import be.khleuven.recordaid.database.interfaces.CommonDatabaseInterface;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Vincent Ceulemans
 */
@Repository("commonDao")
@Transactional
public class JpaCommonDao extends JpaAbstractDao implements CommonDatabaseInterface{
    public JpaCommonDao() {
    }

    public JpaCommonDao(EntityManager entityManager) {
        super(entityManager);
    }
    
    @Override
    public <T> T create(T t) {
        return getEntityManager().merge(t); 
    }
    
    @Override
    public <T> T edit(T t) {
        return getEntityManager().merge(t); 
    }
    
    @Override 
    public void remove(Object o){
        getEntityManager().remove(getEntityManager().merge(o));
    }
    
    @Override
    public <T> T find(Class<T> className, Object id) {
        return getEntityManager().find(className, id);
    }

    @Override
    public <T> List<T> findAll(Class<T> className) {
       Query query = getEntityManager().createQuery("SELECT x FROM "+className.getSimpleName()+" x");
       List<T> all = query.getResultList();
       return all;
    }
}