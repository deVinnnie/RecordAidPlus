package be.khleuven.recordaid.database;

import be.khleuven.recordaid.domain.Item;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


/**
 * Een database die Items bijhoudt op een SQL server.
 *
 * @author Maxime
 */
public class ItemDatabase implements ItemDatabaseInterface
{
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;


    public ItemDatabase()
    {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("RecordAidPlusModelPU");
        this.entityManager = entityManagerFactory.createEntityManager();
    }


    @Override
    public void addItem(Item item) throws DatabaseException
    {
        if(findItem(item.getNaam()) != null)
        {
            throw new DatabaseException("item bestaat reeds");
        }

        entityManager.getTransaction().begin();
        entityManager.persist(item);
        entityManager.getTransaction().commit();
    }


    @Override
    public void removeItem(Item item)
    {
        entityManager.getTransaction().begin();
        entityManager.remove(item);
        entityManager.getTransaction().commit();
    }


    @Override
    public void removeItem(String naam)
    {


        Item item = findItem(naam);
        if(item != null)
        {
            entityManager.getTransaction().begin();
            entityManager.remove(item);
            entityManager.getTransaction().commit();
        }


    }


    @Override
    public Item findItem(String naam)
    {
        return entityManager.find(Item.class, naam);
    }


    @Override
    public void updateItem(String oudeNaam, Item item)
    {
        entityManager.getTransaction().begin();
        entityManager.remove(findItem(oudeNaam));
        entityManager.persist(item);
        entityManager.getTransaction().commit();

    }


    @Override
    public Collection<Item> getItems()
    {
        Query query = entityManager.createQuery("SELECT x FROM Item x");
        Collection<Item> reservaties = query.getResultList();
        return reservaties;
    }
}


