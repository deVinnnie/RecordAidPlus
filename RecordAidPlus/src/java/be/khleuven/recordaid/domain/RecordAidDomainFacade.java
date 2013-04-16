package be.khleuven.recordaid.domain;

import be.khleuven.recordaid.domain.forum.ForumTopic;
import be.khleuven.recordaid.domain.forum.Message;
import be.khleuven.recordaid.domain.aanvragen.Status;
import be.khleuven.recordaid.domain.aanvragen.Aanvraag;
import be.khleuven.recordaid.database.DatabaseException;
import be.khleuven.recordaid.database.interfaces.*; 
import be.khleuven.recordaid.mailing.*;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

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
    private LectorDatabaseInterface lectorDB; 
    
    private MailHandler mailing;

    /**
     * Constructor om een nieuwe RecordAidDomainFacade aan te maken.
     *
     * @param servletContext ServletContext die meegegeven moet worden omdat één
     * van de achterliggende klassen het path van de applicatie nodig heeft.
     */
    public RecordAidDomainFacade()
    {
        //mailing = new SendMail(this.servletContext.getRealPath("/WEB-INF"));
        mailing = new MailHandlerDummy(); 
    }

    @Autowired
    public RecordAidDomainFacade(
            FAQDatabaseInterface faqDB, 
            ForumTopicDatabaseInterface forumTopicDB, 
            GebruikerDatabaseInterface gebruikerDB, 
            ItemDatabaseInterface itemDB, 
            MessageDatabaseInterface messageDB, 
            AanvraagDatabaseInterface aanvraagDB, 
            ReservatieDatabaseInterface reservatieDB, 
            SupportDatabaseInterface supportDB, 
            LectorDatabaseInterface lectorDB) {
        this.faqDB = faqDB;
        this.forumTopicDB = forumTopicDB;
        this.gebruikerDB = gebruikerDB;
        this.itemDB = itemDB;
        this.messageDB = messageDB;
        this.aanvraagDB = aanvraagDB;
        this.reservatieDB = reservatieDB;
        this.supportDB = supportDB;
        this.lectorDB = lectorDB; 
        
        //Watch out!! The init method intializes some default but real entities. 
        //You don't want to accidently send a mail to them!
        mailing = new MailHandlerDummy(); 
        
        try{
            this.init();
        }
        catch(Exception e){
            //Move along, nothing to see here. 
        }
    }    
    
    
    /**
     * 
     * Put some test data in the database
     */
    public void init() throws DatabaseException{
        String[] mails = {
            "luc.janssens@khleuven.be", 
            "elke.steegmans@khleuven.be", 
            "mieke.kemme@khleuven.be", 
            "jan.van.hee@khleuven.be"
        }; 
        
        for(String mail : mails){
            Lector lector = new Lector(mail);
            this.lectorDB.addLector(lector);
        }
    }

    //<editor-fold defaultstate="collapsed" desc="FAQ">
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
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Forum">
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
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Gebruikers">
    public Gebruiker getGebruiker(String emailadres)
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
    //</editor-fold >

    //<editor-fold defaultstate="collapsed" desc="Items">
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
    //</editor-fold>

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

    //<editor-fold defaultstate="collapsed" desc="Aanvragen">
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
    //</editor-fold>

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
    
    //<editor-fold defaultState="collapsed descend="Lectoren">
    public Lector getLector(String emailadres){
        return this.lectorDB.getLector(emailadres); 
    }
    
    public void addLector(Lector gebruiker) throws DatabaseException{
        this.lectorDB.addLector(gebruiker); 
    }
    
    public void removeLector(Lector gebruiker) throws DatabaseException{
        this.lectorDB.removeLector(gebruiker);
        
    }
    public void updateLector(Lector gebruiker) throws DatabaseException{
        this.lectorDB.updateLector(gebruiker);
    }
    
    public Collection<Lector> getLectoren(){
        return this.lectorDB.getLectoren(); 
    }
    //</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public FAQDatabaseInterface getFaqDB() {
        return faqDB;
    }

    public void setFaqDB(FAQDatabaseInterface faqDB) {
        this.faqDB = faqDB;
    }

    public ForumTopicDatabaseInterface getForumTopicDB() {
        return forumTopicDB;
    }

    public void setForumTopicDB(ForumTopicDatabaseInterface forumTopicDB) {
        this.forumTopicDB = forumTopicDB;
    }

    public GebruikerDatabaseInterface getGebruikerDB() {
        return gebruikerDB;
    }

    public void setGebruikerDB(GebruikerDatabaseInterface gebruikerDB) {
        this.gebruikerDB = gebruikerDB;
    }

    public ItemDatabaseInterface getItemDB() {
        return itemDB;
    }

    public void setItemDB(ItemDatabaseInterface itemDB) {
        this.itemDB = itemDB;
    }

    public MessageDatabaseInterface getMessageDB() {
        return messageDB;
    }

    public void setMessageDB(MessageDatabaseInterface messageDB) {
        this.messageDB = messageDB;
    }

    public AanvraagDatabaseInterface getAanvraagDB() {
        return aanvraagDB;
    }

    public void setAanvraagDB(AanvraagDatabaseInterface aanvraagDB) {
        this.aanvraagDB = aanvraagDB;
    }

    public ReservatieDatabaseInterface getReservatieDB() {
        return reservatieDB;
    }

    public void setReservatieDB(ReservatieDatabaseInterface reservatieDB) {
        this.reservatieDB = reservatieDB;
    }

    public SupportDatabaseInterface getSupportDB() {
        return supportDB;
    }

    public void setSupportDB(SupportDatabaseInterface supportDB) {
        this.supportDB = supportDB;
    }

    public MailHandler getMailing() {
        return mailing;
    }

    public void setMailing(MailHandler mailing) {
        this.mailing = mailing;
    }
    //</editor-fold>
}