package be.khleuven.recordaid.database.jpa;

import be.khleuven.recordaid.database.interfaces.ForumTopicDatabaseInterface;
import be.khleuven.recordaid.domain.forum.ForumTopic;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Een database die ForumTopics bijhoudt op een SQL server.
 *
 * @author Maxime Van den Kerkhof
 */
@Repository("forumTopicDao")
@Transactional
public class JpaForumTopicDao implements ForumTopicDatabaseInterface {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addForumTopic(ForumTopic topic) {
        em.getTransaction().begin();
        em.persist(topic);
        em.getTransaction().commit();
    }

    @Override
    public ForumTopic findForumTopic(long id) {
        return (ForumTopic) em.find(ForumTopic.class, id);
    }

    @Override
    public void updateForumTopic(ForumTopic topic) {
        ForumTopic update = this.findForumTopic(topic.getId());
        update.setMessages(topic.getMessages());
        update.setTitel(topic.getTitel());


    }

    @Override
    public void removeForumTopic(long topicID) {
        em.getTransaction().begin();
        em.remove(this.findForumTopic(topicID));
        em.getTransaction().commit();
    }

    @Override
    public Collection<ForumTopic> getForumTopics() {
        Query query = em.createQuery("SELECT x FROM ForumTopic x");
        Collection<ForumTopic> topics = query.getResultList();
        return topics;
    }
}