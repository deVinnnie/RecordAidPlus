package be.khleuven.recordaid.database;

import be.khleuven.recordaid.domain.ForumTopic;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


/**
 * Een database die ForumTopics bijhoudt op een SQL server.
 *
 * @author Maxime Van den Kerkhof
 */
public class ForumTopicDatabase implements ForumTopicDatabaseInterface
{
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("RecordAidPlusModelPU");
    private EntityManager em = factory.createEntityManager();


    @Override
    public void addForumTopic(ForumTopic topic)
    {
        em.getTransaction().begin();
        em.persist(topic);
        em.getTransaction().commit();

    }


    @Override
    public ForumTopic findForumTopic(long id)
    {
        return (ForumTopic) em.find(ForumTopic.class, id);
    }


    @Override
    public void updateForumTopic(ForumTopic topic)
    {
        ForumTopic update = this.findForumTopic(topic.getId());
        update.setMessages(topic.getMessages());
        update.setTitel(topic.getTitel());


    }


    @Override
    public void removeForumTopic(long topicID)
    {
        em.getTransaction().begin();
        em.remove(this.findForumTopic(topicID));
        em.getTransaction().commit();
    }


    @Override
    public Collection<ForumTopic> getForumTopics()
    {
        Query query = em.createQuery("SELECT x FROM ForumTopic x");
        Collection<ForumTopic> topics = query.getResultList();
        return topics;
    }
}


