package be.khleuven.eindwerk.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;


/**
 * Object dat één topic binnen het forum bijhoudt. Het topic heeft een titel en
 * een lijst van berichten die in het topic toegevoegd werden door verschillende
 * gebruikers.
 *
 * @author Maxime Van den Kerkhof
 */
@Entity
public class ForumTopic extends Identifiable
{
    @OneToMany(cascade =
    {
        CascadeType.PERSIST, CascadeType.MERGE
    })
    private List<Message> messages;
    private String titel;


    /**
     * Constructor zonder parameters, nodig voor Java Persistency.
     */
    public ForumTopic()
    {
    }


    /**
     * Constructor met parameters, dit is de constructor die gebruikt dient te
     * worden om een nieuw ForumTopic aan te maken.
     *
     * @param titel String die de titel van het topic voorstelt.
     */
    public ForumTopic(String titel)
    {
        messages = new ArrayList<Message>();
        setTitel(titel);
    }


    /**
     * Geeft een lijst terug met alle berichten die in het topic werden
     * geplaatst.
     *
     * @return List van Messages die alle berichten die in het topic werden
     * geplaatst bevat.
     */
    public List<Message> getMessages()
    {
        return messages;
    }


    /**
     * Setter om een volledige lijst berichten in het topic te setten, dit
     * overschrijft de huidige berichten in het topic.
     *
     * @param messages List van Messages die alle berichten die in het topic
     * moeten worden geset bevat.
     */
    public void setMessages(List<Message> messages)
    {
        this.messages = messages;
    }


    /**
     * Voegt een nieuw bericht toe aan het topic.
     *
     * @param message Message die moet worden toegevoegd aan het topic.
     */
    public void addMessage(Message message)
    {
        this.messages.add(message);
    }


    /**
     * Verwijdert een bepaald bericht uit de lijst van berichten.
     *
     * @param message Message dat moet worden verwijderd.
     */
    public void removeMessage(Message message)
    {
        messages.remove(message);
    }


    /**
     * Verwijdert een bepaald bericht uit de lijst van berichten gebaseerd op
     * het id van dat bericht.
     *
     * @param id long dat het is is van het bericht dat moet worden verwijderd.
     */
    public void removeMessage(long id)
    {
        for(Message m : messages)
        {
            if(m.getId() == id)
            {
                messages.remove(m);
                break;
            }
        }
    }


    /**
     * Verandert een bericht naar een aangepaste versie van dat bericht.
     *
     * @param message Message die de aangepaste versie van het bericht is (id
     * moet hetzelfde blijven).
     */
    public void updateMessage(Message message)
    {
        for(Message m : messages)
        {
            if(m.getId() == message.getId())
            {
                m.setText(message.getText());
                break;
            }
        }
    }


    /**
     * Getter voor de titel van het topic.
     *
     * @return String die de titel van het topic voorstelt.
     */
    public String getTitel()
    {
        return titel;
    }


    /**
     * Setter om de titel van het topic te setten.
     *
     * @param titel String met de aangepaste titel van het bericht.
     */
    public void setTitel(String titel)
    {
        if(titel.equals(""))
        {
            this.titel = "Geen titel";
        }
        else
        {
            this.titel = titel;
        }
    }


    /**
     * Methode die gebruikt wordt in bepaalde Collection<> klassen.
     *
     * @return integer gebaseerd op waarden van de instantievariabelen.
     */
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 79 * hash + (this.titel != null ? this.titel.hashCode() : 0);
        return hash;
    }


    /**
     * Methode die bepaalt wanneer 2 objecten hetzelfde zijn op basis van de
     * waarden van de instantievariabelen.
     *
     * @param obj Object dat met dit object vergeleken moet worden.
     * @return Yes indien ze hetzelfde zijn, false indien ze niet hetzelfde
     * zijn.
     */
    @Override
    public boolean equals(Object obj)
    {
        if(obj == null)
        {
            return false;
        }
        if(getClass() != obj.getClass())
        {
            return false;
        }
        final ForumTopic other = (ForumTopic) obj;
        if((this.titel == null) ? (other.titel != null) : !this.titel.equals(other.titel))
        {
            return false;
        }
        return true;
    }


    /**
     * Methode die gebruikt wordt om een text representatie van het object te
     * verkrijgen, geeft een aantal instantievariabelen terug.
     *
     * @return String die een text representatie van het object is.
     */
    @Override
    public String toString()
    {
        String output = "";

        for(Message m : messages)
        {
            output += m.toString() + "\n************************************\n";
        }

        return this.titel + "\n************************************\n" + output;
    }
}


