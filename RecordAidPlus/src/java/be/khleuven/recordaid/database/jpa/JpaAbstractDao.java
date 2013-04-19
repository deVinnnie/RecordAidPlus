package be.khleuven.recordaid.database.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Vincent Ceulemans
 */
public abstract class JpaAbstractDao {
    @PersistenceContext
    private EntityManager entityManager;
    
    public JpaAbstractDao(){}
    
    public JpaAbstractDao(EntityManager entityManager){
        this.setEntityManager(entityManager);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}