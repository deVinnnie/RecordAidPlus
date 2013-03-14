package be.khleuven.eindwerk.database;

import be.khleuven.eindwerk.domain.ForumTopic;
import java.util.Collection;


/**
 * Een interface voor een database die ForumTopics bijhoudt.
 *
 * @author Maxime Van den Kerkhof
 */
public interface ForumTopicDatabaseInterface
{
    /**
     * Voegt een nieuw ForumTopic toe aan de database.
     *
     * @param topic Het ForumTopic dat toegevoegd moet worden.
     */
    public void addForumTopic(ForumTopic topic);


    /**
     * Geeft een ForumTopic terug dat in de database is opgeslagen op basis van
     * het id.
     *
     * @param id Het id van het ForumTopic dat teruggegeven moet worden.
     * @return ForumTopic met het bepaalde id.
     */
    public ForumTopic findForumTopic(long id);


    /**
     * Past een ForumTopic dat in de database is opgeslagen aan met nieuwe
     * gegevens die in het ForumTopic zijn veranderd.
     *
     * @param topic ForumTopic dat gewijzigd is en waar de wijzigingen van
     * aangepast moeten worden.
     */
    public void updateForumTopic(ForumTopic topic);


    /**
     * Verwijdert een ForumTopic uit de database op basis van het id.
     *
     * @param id Het id van het ForumTopic dat verwijderd moet worden.
     */
    public void removeForumTopic(long id);


    /**
     * Geeft een collection terug van alle ForumTopics in de database.
     *
     * @return Collection<ForumTopic> van alle ForumTopics in de database.
     */
    public Collection<ForumTopic> getForumTopics();
}


