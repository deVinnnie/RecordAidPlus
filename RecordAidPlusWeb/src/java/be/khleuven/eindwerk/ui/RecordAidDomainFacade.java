package be.khleuven.eindwerk.ui;

import be.khleuven.eindwerk.database.*;
import be.khleuven.eindwerk.domain.*;
import be.khleuven.recordaid.mailing.SendMail;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import javax.servlet.ServletContext;


/**
 * Klasse die fungeert als een facade voor alle database klassen. De
 * RecordAidDomainFacade kan aangesproken worden en deze geeft de betreffende
 * taak door aan de juiste database.
 *
 * @author Maxime Van den Kerkhof
 */
public class RecordAidDomainFacade
{
    private FAQDatabaseInterface faqDB;
    private ForumTopicDatabaseInterface forumTopicDB;
    private GebruikerDatabaseInterface gebruikerDB;
    private ItemDatabaseInterface itemDB;
    private MessageDatabaseInterface messageDB;
    private AanvraagDatabaseInterface aanvraagDB;
    private ReservatieDatabaseInterface reservatieDB;
    private SupportDatabaseInterface supportDB;
    private ServletContext servletContext;
    private SendMail mailing;


    /**
     * Constructor om een nieuwe RecordAidDomainFacade aan te maken.
     *
     * @param servletContext ServletContext die meegegeven moet worden omdat één
     * van de achterliggende klassen het path van de applicatie nodig heeft.
     */
    public RecordAidDomainFacade(ServletContext servletContext)
    {
        faqDB = new FAQDatabase();
        forumTopicDB = new ForumTopicDatabase();
        gebruikerDB = new GebruikerDatabase();
        itemDB = new ItemDatabase();
        messageDB = new MessageDatabase();
        aanvraagDB = new AanvraagDatabase();
        reservatieDB = new ReservatieDatabase();
        supportDB = new SupportDatabase();
        this.servletContext = servletContext;

        mailing = new SendMail(this.servletContext.getRealPath("/WEB-INF"));
    }


    public void addFAQ(FAQ faq)
    {
        faqDB.addFAQ(faq);
    }


    public FAQ findFAQ(Long id)
    {
        return faqDB.findFAQ(id);
    }


    public void updateFAQ(FAQ faq)
    {
        faqDB.updateFAQ(faq);
    }


    public void removeFAQ(FAQ faq)
    {
        faqDB.removeFAQ(faq);
    }


    public Collection<FAQ> getFAQs()
    {
        return faqDB.getFAQs();
    }


    public Collection<FAQ> getRelevanteFAQs()
    {
        return faqDB.getRelevanteFAQs();
    }


    public Collection<FAQ> getBeantwoordeFAQs()
    {
        return faqDB.getBeantwoordeFAQs();
    }


    public Collection<FAQ> getNietBeantwoordeFAQs()
    {
        return faqDB.getNietBeantwoordeFAQs();
    }


    public void addForumTopic(ForumTopic topic)
    {
        forumTopicDB.addForumTopic(topic);
    }


    public ForumTopic findForumTopic(long id)
    {
        return forumTopicDB.findForumTopic(id);
    }


    public void updateForumTopic(ForumTopic topic)
    {
        forumTopicDB.updateForumTopic(topic);
    }


    public void removeForumTopic(long topicID)
    {
        forumTopicDB.removeForumTopic(topicID);
    }


    public Collection<ForumTopic> getForumTopics()
    {
        return forumTopicDB.getForumTopics();
    }


    public Gebruiker getGebruiker(String emailadres) throws DatabaseException
    {
        return gebruikerDB.getGebruiker(emailadres);
    }


    public Gebruiker getGebruikerByValidatiecode(String validatiecode)
    {
        return gebruikerDB.getGebruikerByValidatiecode(validatiecode);
    }


    public void addGebruiker(Gebruiker gebruiker) throws DatabaseException
    {
        gebruikerDB.addGebruiker(gebruiker);
    }


    public void removeGebruiker(String emailadres) throws DatabaseException
    {
        gebruikerDB.removeGebruiker(emailadres);
    }


    public void removeGebruiker(Gebruiker gebruiker) throws DatabaseException
    {
        gebruikerDB.removeGebruiker(gebruiker);
    }


    public void updateGebruiker(Gebruiker gebruiker) throws DatabaseException
    {
        gebruikerDB.updateGebruiker(gebruiker);
    }


    public Collection<Gebruiker> getGebruikers()
    {
        return gebruikerDB.getGebruikers();
    }


    public Collection<Gebruiker> getGebruikers(Rollen rol)
    {
        return gebruikerDB.getGebruikers(rol);
    }


    public void addItem(Item i) throws DatabaseException
    {
        itemDB.addItem(i);
    }


    public void removeItem(Item item)
    {
        itemDB.removeItem(item);
    }


    public void removeItem(String naam)
    {
        itemDB.removeItem(naam);
    }


    public Item findItem(String naam)
    {
        return itemDB.findItem(naam);
    }


    public void updateItem(String oudeNaam, Item i)
    {
        itemDB.updateItem(oudeNaam, i);
    }


    public Collection<Item> getItems()
    {
        return itemDB.getItems();
    }


    public void addMessage(Message message)
    {
        messageDB.addMessage(message);
    }


    public Message findMessage(Long messageID)
    {
        return messageDB.findMessage(messageID);
    }


    public void updateMessage(Message message)
    {
        messageDB.updateMessage(message);
    }


    public void removeMessage(Long messageID)
    {
        messageDB.removeMessage(messageID);
    }


    public List<Message> getMessages()
    {
        return messageDB.getMessages();
    }


    public void addAanvraag(Aanvraag aanvraag)
    {
        aanvraagDB.addAanvraag(aanvraag);
    }


    public Aanvraag findAanvraag(long id)
    {
        return aanvraagDB.findAanvraag(id);
    }


    public void updateAanvraag(Aanvraag aanvraag) throws DatabaseException
    {
        aanvraagDB.updateAanvraag(aanvraag);
    }


    public void removeAanvraag(long id)
    {
        aanvraagDB.removeAanvraag(id);
    }


    public void removeAanvraag(Aanvraag aanvraag)
    {
        aanvraagDB.removeAanvraag(aanvraag);
    }


    public Collection<Aanvraag> getAanvragen()
    {
        return aanvraagDB.getAanvragen();
    }


    public Collection<Aanvraag> getAanvragen(Status status)
    {
        return aanvraagDB.getAanvragen(status);
    }


    public Collection<Aanvraag> getAanvragenAanvrager(Gebruiker gebruiker)
    {
        return aanvraagDB.getAanvragenAanvrager(gebruiker);
    }


    public Collection<Aanvraag> getAanvragenToegewezenLid(Gebruiker toegewezenLid)
    {
        return aanvraagDB.getAanvragenToegewezenLid(toegewezenLid);
    }


    public Reservatie getReservatie(long id)
    {
        return reservatieDB.getReservatie(id);
    }


    public void addReservatie(Reservatie reservatie) throws DatabaseException
    {
        reservatieDB.addReservatie(reservatie);
    }


    public void updateReservatie(Reservatie reservatie)
    {
        reservatieDB.updateReservatie(reservatie);
    }


    public void removeReservatie(Reservatie reservatie)
    {
        reservatieDB.removeReservatie(reservatie);
    }


    public void removeReservatie(long id)
    {
        reservatieDB.removeReservatie(id);
    }


    public Collection<Reservatie> getReservaties()
    {
        return reservatieDB.getReservaties();
    }


    public Collection<Reservatie> getReservaties(Calendar datum)
    {
        return reservatieDB.getReservaties(datum);
    }


    public Collection<Reservatie> getReservaties(Calendar datum, Item item)
    {
        return reservatieDB.getReservaties(datum, item);
    }


    public ServletContext getServletContext()
    {
        return servletContext;
    }


    public Support findSupport(long id) throws DatabaseException
    {
        return supportDB.findSupport(id);
    }


    public void addSupport(Support support) throws DatabaseException
    {
        supportDB.addSupport(support);
    }


    public void removeSupport(long id) throws DatabaseException
    {
        supportDB.removeSupport(id);
    }


    public void removeSupport(Support support) throws DatabaseException
    {
        supportDB.removeSupport(support);
    }


    public void updateSupport(Support support) throws DatabaseException
    {
        supportDB.updateSupport(support);
    }


    public Collection<Support> getSupports()
    {
        return supportDB.getSupports();
    }


    public boolean sendValidatieEmail(Gebruiker gebruiker)
    {
        return mailing.sendValidatieEmail(gebruiker);
    }


    public boolean sendNewBuddyMail(Gebruiker gebruiker)
    {
        return mailing.sendNewBuddyMail(gebruiker);
    }


    public boolean sendAntwoordFAQMail(FAQ faq, Gebruiker buddy)
    {
        return mailing.sendAntwoordFAQMail(faq, buddy);
    }


    public boolean sendNieuweAanvraagMailNaarBuddy(Aanvraag aanvraag, Gebruiker buddy)
    {
        return mailing.sendNieuweAanvraagMailNaarBuddy(aanvraag, buddy);
    }


    public boolean sendSupportMail(Support support)
    {
        return mailing.sendSupportMail(support);
    }


    public boolean sendMailVoorGoedkeuring(String email, Aanvraag aanvraag)
    {
        return mailing.sendMailVoorGoedkeuring(email, aanvraag);
    }


    public boolean sendAanvraagBeschikbaar(Aanvraag aanvraag)
    {
        return mailing.sendAanvraagBeschikbaar(aanvraag);
    }


    public boolean sendNieuweAanvraagIngediend(Aanvraag aanvraag)
    {
        return mailing.sendNieuweAanvraagIngediend(aanvraag);
    }
    
    
    public boolean sentNieuweFAQMail(FAQ faq)
    {
        return mailing.sentNieuweFAQMail(faq);
    }
}


