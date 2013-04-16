package be.khleuven.recordaid.database.interfaces;

import be.khleuven.recordaid.domain.forum.Message;
import java.util.List;


/**
 * Een interface voor een database die Messages bijhoudt.
 *
 * @author Maxime Van den Kerkhof
 */
public interface MessageDatabaseInterface
{
    /**
     * Deze methode voegt een bericht toe aan de database.
     *
     * @param message
     */
    public void addMessage(Message message);


    /**
     * Geeft een bericht terug waarvan het ID gelijk is aan het ingegeven ID.
     *
     * @param messageID Het ID van het bericht.
     * @return Een bericht met ID het id ingegeven als messageID
     */
    public Message findMessage(Long id);


    /**
     * Deze methode past het bericht aan naar de recenste versie die wordt
     * meegegeven in de constructor.
     *
     * @param message Het Message object dat je wil aanpassen in de database.
     */
    public void updateMessage(Message message);


    /**
     * Deze methode verwijdert een bericht uit de database.
     *
     * @param messageID Het ID van het bericht.
     */
    public void removeMessage(Long id);


    /**
     * Deze methode geeft alle berichten terug die in de database staan.
     *
     * @return Geeft een lijst van berichten terug
     */
    public List<Message> getMessages();
}


