package be.khleuven.recordaid.database.embedded;

import be.khleuven.recordaid.database.interfaces.MessageDatabaseInterface;
import be.khleuven.recordaid.domain.forum.Message;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


/**
 * Een database die Messages bijhoudt op een SQL server.
 *
 * @author Maxime Van den Kerkhof
 */
public class MessageDatabase implements MessageDatabaseInterface
{
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("RecordAidPlusModelPU");
    private EntityManager em = factory.createEntityManager();


    @Override
    public void addMessage(Message message)
    {
        em.getTransaction().begin();
        em.persist(message);
        em.getTransaction().commit();
    }


    @Override
    public Message findMessage(Long messageID)
    {
        return (Message) em.find(Message.class, messageID);
    }


    @Override
    public void updateMessage(Message message)
    {
        Message update = this.findMessage(message.getId());

        update.setText(message.getText());

    }


    @Override
    public void removeMessage(Long messageID)
    {
        em.getTransaction().begin();
        em.remove(this.findMessage(messageID));
        em.getTransaction().commit();
    }


    @Override
    public List<Message> getMessages()
    {
        Query query = em.createQuery("SELECT x FROM Message x");
        Collection<Message> messages = query.getResultList();
        return (List<Message>) messages;
    }
}


