package be.khleuven.recordaid.database.jpa;

import be.khleuven.recordaid.database.DatabaseException;
import be.khleuven.recordaid.database.interfaces.ItemDatabaseInterface;
import be.khleuven.recordaid.domain.Item;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Een database die Items bijhoudt op een SQL server.
 *
 * @author Maxime
 */
@Repository("itemDao")
@Transactional
public class JpaItemDao implements ItemDatabaseInterface {

    @PersistenceContext
    private EntityManager entityManager;

    public JpaItemDao() {
    }

    @Override
    public void addItem(Item item) throws DatabaseException {
        if (findItem(item.getNaam()) != null) {
            throw new DatabaseException("Dit item bestaat reeds.");
        }
        entityManager.persist(item);
    }

    @Override
    public void removeItem(Item item) {
        entityManager.remove(item);
    }

    @Override
    public void removeItem(String naam) {
        Item item = findItem(naam);
        if (item != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(item);
            entityManager.getTransaction().commit();
        }


    }

    @Override
    public Item findItem(String naam) {
        return entityManager.find(Item.class, naam);
    }

    @Override
    public void updateItem(String oudeNaam, Item item) {
        entityManager.getTransaction().begin();
        entityManager.remove(findItem(oudeNaam));
        entityManager.persist(item);
        entityManager.getTransaction().commit();

    }

    @Override
    public Collection<Item> getItems() {
        Query query = entityManager.createQuery("SELECT x FROM Item x");
        Collection<Item> reservaties = query.getResultList();
        return reservaties;
    }
}