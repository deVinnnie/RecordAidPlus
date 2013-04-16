package be.khleuven.recordaid.database.jpa;

import be.khleuven.recordaid.database.interfaces.MessageDatabaseInterface;
import be.khleuven.recordaid.domain.forum.Message;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Een database die Messages bijhoudt op een SQL server.
 *
 * @author Maxime Van den Kerkhof
 */
@Repository("messageTopicDao")
@Transactional
public class JpaMessageDao implements MessageDatabaseInterface {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addMessage(Message message) {
        em.persist(message);
    }

    @Override
    public Message findMessage(Long messageID) {
        return (Message) em.find(Message.class, messageID);
    }

    @Override
    public void updateMessage(Message message) {
        Message update = this.findMessage(message.getId());
        update.setText(message.getText());
    }

    @Override
    public void removeMessage(Long messageID) {
        em.remove(this.findMessage(messageID));
    }

    @Override
    public List<Message> getMessages() {
        Query query = em.createQuery("SELECT x FROM Message x");
        Collection<Message> messages = query.getResultList();
        return (List<Message>) messages;
    }
}