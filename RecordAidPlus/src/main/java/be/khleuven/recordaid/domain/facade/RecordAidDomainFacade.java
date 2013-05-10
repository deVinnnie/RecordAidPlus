package be.khleuven.recordaid.domain.facade;

import be.khleuven.recordaid.domain.departement.*;
import be.khleuven.recordaid.opnames.*;
import be.khleuven.recordaid.domain.items.*;
import be.khleuven.recordaid.domain.gebruiker.*;
import be.khleuven.recordaid.domain.mailing.*;
import be.khleuven.recordaid.domain.aanvragen.*;
import be.khleuven.recordaid.database.DatabaseException;
import be.khleuven.recordaid.database.interfaces.*;
import be.khleuven.recordaid.domain.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

/**
 * Klasse die fungeert als een facade voor alle database klassen. De
 * RecordAidDomainFacade kan aangesproken worden en deze geeft de betreffende
 * taak door aan het juiste model-object.
 *
 * @author Maxime Van den Kerkhof
 */
public class RecordAidDomainFacade {
    //<editor-fold defaultstate="collapsed" desc="Instantievariabelen & Constructors">  
    private String url = "http://recordaid.khleuven.be/";
    private CommonDatabaseInterface commonDb;
    private FAQDatabaseInterface faqDB;
    private GebruikerDatabaseInterface gebruikerDB;
    private AanvraagDatabaseInterface aanvraagDB;
    private ReservatieDatabaseInterface reservatieDB;
    private DepartementDatabaseInterface departementDb;
    private MailDatabaseInterface mailDb;
    private AbstractMailHandler mailHandler;

    /**
     * Constructor om een nieuwe RecordAidDomainFacade aan te maken.
     */
    public RecordAidDomainFacade() {
        //mailing = new SendMail(this.servletContext.getRealPath("/WEB-INF"));
        mailHandler = new MailHandlerDummy();
    }

    public RecordAidDomainFacade(
            CommonDatabaseInterface commonDb,
            FAQDatabaseInterface faqDB,
            GebruikerDatabaseInterface gebruikerDB,
            AanvraagDatabaseInterface aanvraagDB,
            ReservatieDatabaseInterface reservatieDB,
            DepartementDatabaseInterface departementDb,
            MailDatabaseInterface mailDb) {
        this(commonDb, faqDB, gebruikerDB, aanvraagDB, reservatieDB, departementDb, mailDb, true); 
    }
    
    @Autowired
    public RecordAidDomainFacade(
            CommonDatabaseInterface commonDb,
            FAQDatabaseInterface faqDB,
            GebruikerDatabaseInterface gebruikerDB,
            AanvraagDatabaseInterface aanvraagDB,
            ReservatieDatabaseInterface reservatieDB,
            DepartementDatabaseInterface departementDb,
            MailDatabaseInterface mailDb, 
            boolean initialize) {
        this.commonDb = commonDb;
        this.faqDB = faqDB;
        this.gebruikerDB = gebruikerDB;
        this.aanvraagDB = aanvraagDB;
        this.reservatieDB = reservatieDB;
        this.departementDb = departementDb;
        this.mailDb = mailDb;

        //Watch out!! Dbunit inserts some default but real entities into the DB. 
        //You don't want to accidently send a mail to them!
        mailHandler = new MailHandlerDummy();
        
        if(initialize){
            this.init(); 
        }
    }
    //</editor-fold>  

    public void init (){
        try {
            StartUpDataFiller filler = new StartUpDataFiller(this);
            filler.init();
        } catch (DatabaseException ex) {
            Logger.getLogger(RecordAidDomainFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //<editor-fold defaultstate="collapsed" desc="FAQ">
    public FAQ findFAQ(Long id) {
        return commonDb.find(FAQ.class, id);
    }

    public Collection<FAQ> getFAQs() {
        return commonDb.findAll(FAQ.class);
    }

    public Collection<FAQ> getRelevanteFAQs() {
        return faqDB.getRelevanteFAQs();
    }

    public Collection<FAQ> getBeantwoordeFAQs() {
        return faqDB.getBeantwoordeFAQs();
    }

    public Collection<FAQ> getNietBeantwoordeFAQs() {
        return faqDB.getNietBeantwoordeFAQs();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Gebruikers">
    public Gebruiker getGebruiker(String emailadres) {
        Gebruiker gebruiker = commonDb.find(Gebruiker.class, emailadres);
        return gebruiker;
    }

    public Gebruiker getGebruikerByValidatiecode(String validatiecode) {
        return gebruikerDB.getGebruikerByValidatiecode(validatiecode);
    }

    public void addGebruiker(Gebruiker gebruiker) throws DomainException {
        //Add user to database. 
        commonDb.create(gebruiker);

        //Send mail with Validation-code to the new user. 
        Map<String, String> context = new HashMap<String, String>();
        context.put("gebruiker_voornaam", gebruiker.getVoornaam());
        context.put("validatie_code", gebruiker.getValidatieCode());
        this.sendMail("validatie_gebruiker", gebruiker.getEmailadres(), context);
    }

    /**
     * Een nieuwe gebruiker wordt aangemaakt in opdracht van een andere
     * gebruiker. Dit is het geval bij begeleiders die een aanvraag willen
     * plaatsen voor studenten die nog niet geregistreerd zijn. De mail die
     * verstuurd wordt naar de student bevat een tijdelijk wachtwoord.
     */
    public Gebruiker addGebruiker(Gebruiker gebruiker, Gebruiker begeleider) throws DomainException {
        if (!begeleider.getRollen().contains(Rollen.BEGELEIDER)) {
            throw new DomainException("Enkel begeleiders kunnen nieuwe gebruikers aanmaken.");
        }

        //Add user to database. 
        Gebruiker res = commonDb.create(gebruiker);

        //Send mail with Validation-code to the new user. 
        Map<String, String> context = new HashMap<String, String>();
        context.put("gebruiker_voornaam", gebruiker.getVoornaam());
        context.put("validatie_code", gebruiker.getValidatieCode());
        context.put("tijdelijk_wachtwoord", gebruiker.getValidatieCode());
        this.sendMail("validatie_gebruiker_indirect", gebruiker.getEmailadres(), context);
        
        return res; 
    }

    public void removeGebruiker(Gebruiker gebruiker) throws DatabaseException {
        commonDb.remove(gebruiker);
    }

    public Collection<Gebruiker> getGebruikers() {
        return commonDb.findAll(Gebruiker.class);
    }

    public Collection<Gebruiker> getGebruikers(Rollen rol) {
        return gebruikerDB.getGebruikers(rol);
    }

    public Dossier getDossier(Gebruiker gebruiker) {
        Dossier dossier = commonDb.find(Dossier.class, gebruiker.getEmailadres());
        if (dossier == null) {
            dossier = new Dossier(gebruiker);
            dossier = commonDb.create(dossier);
        }
        return dossier;
    }
    //</editor-fold >

    //<editor-fold defaultstate="collapsed" desc="Items"> 
    public Item findItem(Long id) {
        return commonDb.find(Item.class, id);
    }

    public Collection<Item> getItems() {
        return commonDb.findAll(Item.class);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Aanvragen">
    public DagAanvraag addDagAanvraag(DagAanvraag aanvraag) throws DomainException {
        aanvraag = commonDb.create(aanvraag);
        Dossier dossier = aanvraag.getDossier();
        dossier.addAanvraag(aanvraag);
        commonDb.edit(dossier);

        //Verzend mail naar recordaid met de boodschap dat een nieuwe aanvraag is toegevoegd. 
        Map<String, String> context = new HashMap<String, String>();
        context.put("aanvraag_aanvrager_voornaam", dossier.getGebruiker().getVoornaam());
        context.put("aanvraag_aanvrager_achternaam", dossier.getGebruiker().getAchternaam());
        context.put("url", this.url + "RecordAidPlus/aanvraag/detail?id=" + aanvraag.getId());
        this.sendMail("nieuwe_aanvraag_melding", "recordaid@khleuven.be", context);
        return aanvraag;
    }

    public MultiPeriodeAanvraag addMultiPeriodeAanvraag(MultiPeriodeAanvraag aanvraag, Gebruiker begeleider) throws DomainException {
        aanvraag = commonDb.create(aanvraag);
        Dossier dossier = aanvraag.getDossier();
        dossier.addAanvraag(aanvraag, begeleider);
        commonDb.edit(dossier);

        //Verzend mail naar recordaid met de boodschap dat een nieuwe aanvraag is toegevoegd. 
        Map<String, String> context = new HashMap<String, String>();
        context.put("aanvraag_aanvrager_voornaam", dossier.getGebruiker().getVoornaam());
        context.put("aanvraag_aanvrager_achternaam", dossier.getGebruiker().getAchternaam());
        context.put("url", this.url + "RecordAidPlus/aanvraag/detail?id=" + aanvraag.getId());
        this.sendMail("nieuwe_aanvraag_melding", "recordaid@khleuven.be", context);
        return aanvraag;
    }

    public AbstractAanvraag findAanvraag(long id) {
        return commonDb.find(AbstractAanvraag.class, id);
    }

    public Collection<AbstractAanvraag> getAanvragen() {
        return commonDb.findAll(AbstractAanvraag.class);
    }

    public Collection<AbstractAanvraag> getAanvragen(Status status) {
        return aanvraagDB.getAanvragen(status);
    }

    public Collection<AbstractAanvraag> getAanvragenToegewezenLid(Gebruiker toegewezenLid) {
        return aanvraagDB.getAanvragenToegewezenLid(toegewezenLid);
    }

    public Collection<MultiPeriodeAanvraag> getAanvragen(Gebruiker begeleider) {
        return aanvraagDB.getAanvragen(begeleider);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Reservaties">
    public Reservatie getReservatie(long id) {
        return commonDb.find(Reservatie.class, id);
    }

    public Collection<Reservatie> getReservaties() {
        return reservatieDB.getReservaties();
    }

    public Collection<ReservatieDag> getReservaties(long itemId) {
        return reservatieDB.getReservaties(commonDb.find(Item.class, itemId));
    }

    public Collection<Reservatie> getReservaties(Calendar datum, Item item) {
        return reservatieDB.getReservaties(datum, item);
    }

    public Collection<Reservatie> getReservaties(Calendar start, Calendar end, Item item) {
        return reservatieDB.getReservaties(start, end, item);
    }

    public Collection<Reservatie> getReservaties(Calendar start, Calendar end) {
        Collection<Reservatie> reservaties = reservatieDB.getReservaties(start, end);
        return reservaties;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Support">
    public Support findSupport(long id) throws DatabaseException {
        return commonDb.find(Support.class, id);
    }

    public Collection<Support> getSupports() {
        return commonDb.findAll(Support.class);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Lectoren">
    public Lector getLector(String emailadres) {
        Lector lector;
        try {
            lector = departementDb.getLector(emailadres);
        } catch (Exception ex) {
            lector = new Lector(emailadres);
            lector = commonDb.create(lector);
        }
        return lector;
    }

    public void removeLector(Lector lector) {
        List<OpnameMoment> lessenVanLector = this.getLessenVanLector(lector);
        for (OpnameMoment opnameMoment : lessenVanLector) {
            opnameMoment.setLector(null);
            this.edit(opnameMoment);
        }
        this.remove(lector);
    }

    public Collection<Lector> getLectoren() {
        return commonDb.findAll(Lector.class);
    }

    public List<OpnameMoment> getLessenVanLector(Lector lector) {
        return departementDb.getLessenVanLector(lector);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Departementen">
    public void addDepartement(Departement departement) {
        commonDb.create(departement);
    }

    public List<Departement> getDepartementen() {
        return this.departementDb.getAlleDepartementen();
    }

    public Departement getDepartement(String naam) {
        return commonDb.find(Departement.class, naam);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Common"> 
    public <T> T create(T t) {
        return commonDb.create(t);
    }

    public <T> T edit(T t) {
        return commonDb.edit(t);
    }

    public void remove(Object object) {
        commonDb.remove(object);
    }
    //</editor-fold>

    public OpnameMethode findOpnameMethode(Long id) {
        return commonDb.find(OpnameMethode.class, id);
    }

    public List<OpnameMethode> getOpnameMethodes() {
        return commonDb.findAll(OpnameMethode.class);
    }

    public List<OpnameMoment> getZichtbareOpnames() {
        List<OpnameMoment> zichtbareOpnames = new ArrayList<OpnameMoment>();
        List<OpnameMoment> alle = commonDb.findAll(OpnameMoment.class);
        for (OpnameMoment opnameMoment : alle) {
            if (opnameMoment.isZichtbaar()) {
                zichtbareOpnames.add(opnameMoment);
            }
        }
        return zichtbareOpnames;
    }

    public List<OpnameMoment> getOpnames() {
        return commonDb.findAll(OpnameMoment.class);
    }

    public OpnameMoment findOpnameMoment(long id) {
        return this.commonDb.find(OpnameMoment.class, id);
    }

    //<editor-fold defaultstate="collapsed" desc="Mailing">
    public List<MailMessage> findMailMessages() {
        return commonDb.findAll(MailMessage.class);
    }

    public MailMessage findMailMessage(long id) {
        return commonDb.find(MailMessage.class, id);
    }

    public SubjectPrefix getSubjectPrefix() {
        long id = 1;
        return commonDb.find(SubjectPrefix.class, id);
    }

    private void sendMail(String message_key, String recipient, Map<String, String> context) throws DomainException {
        MailMessage mailMessage = mailDb.getMailMessage(message_key);
        
        if(mailMessage==null){
            //Indicates that no mailmessages are present  in database.
            //MailMessages in database are only called from code, and not dependant on user-input. 
            this.resetMessages();
            mailMessage = mailDb.getMailMessage(message_key);
        }
        
        this.sendMail(mailMessage, recipient, context);
    }

    private void resetMessages() {
        MailMessageFactory factory = new MailMessageFactory();
        List<MailMessage> messages = factory.createMailMessages();
        
        SubjectPrefix prefix = new SubjectPrefix();
        prefix.setId(1);
        prefix.setSubject_prefix("[RecordAid]");
        
        this.create(prefix); 

        for (MailMessage message : messages) {
            message.setSubjectPrefix(this.getSubjectPrefix());
            this.create(message);
        }
    }

    private void sendMail(MailMessage mailMessage, String recipient, Map<String, String> context) throws DomainException {
        try {
            mailMessage.setRecipient(recipient);
            mailMessage.setContext(context);
            mailHandler.sendMessage(mailMessage);
        } catch (Exception e) {
            throw new DomainException(e.getMessage(), e);
        }
    }

    public void sendMail(MailMessage mailMessage, List<Rollen> rollen) throws DomainException {
        ArrayList<Gebruiker> maillijst = new ArrayList<Gebruiker>();
        for (Rollen rol : rollen) {
            Collection<Gebruiker> gebruikers = gebruikerDB.getGebruikers(rol);
            for (Gebruiker gebruiker : gebruikers) {
                //Make sure we don't end up with duplicates.
                if (!maillijst.contains(gebruiker)) {
                    maillijst.add(gebruiker);
                }
            }
        }

        for (Gebruiker gebruiker : maillijst) {
            mailMessage.setRecipient(gebruiker.getEmailadres());
            this.sendMail(mailMessage, gebruiker.getEmailadres(), null);
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public FAQDatabaseInterface getFaqDB() {
        return faqDB;
    }

    public void setFaqDB(FAQDatabaseInterface faqDB) {
        this.faqDB = faqDB;
    }

    public GebruikerDatabaseInterface getGebruikerDB() {
        return gebruikerDB;
    }

    public void setGebruikerDB(GebruikerDatabaseInterface gebruikerDB) {
        this.gebruikerDB = gebruikerDB;
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

    public CommonDatabaseInterface getCommonDb() {
        return commonDb;
    }

    public void setCommonDb(CommonDatabaseInterface commonDb) {
        this.commonDb = commonDb;
    }

    public DepartementDatabaseInterface getDepartementDb() {
        return departementDb;
    }

    public void setDepartementDb(DepartementDatabaseInterface departementDb) {
        this.departementDb = departementDb;
    }

    public AbstractMailHandler getMailHandler() {
        return mailHandler;
    }

    public void setMailHandler(AbstractMailHandler mailing) {
        this.mailHandler = mailing;
    }
    //</editor-fold>

    /**
     * Keurt de gegeven aanvraag goed door de RecordAid-kern. Het RecordAid-team
     * aanvaard de moeilijke taak om de aanvrager bij te staan. Hierna moeten de
     * lectoren (misschien) nog hun goedkeuring geven. Deze methode vervult nog
     * enkele randactiviteiten: Het aanvullen van de geschiedenis en het sturen
     * van een mail naar de aanvrager.
     *
     * @param aanvraag De aanvraag die dient aanvaard te worden.
     * @param initiator De gebruiker die de goedkeuring initialiseerd. (Hij/zij
     * die op de knop drukt)
     */
    public void aanvaardAanvraag(AbstractAanvraag aanvraag, Gebruiker initiator) throws DomainException {
        //Verander de status 
        aanvraag.setStatus(Status.GOEDGEKEURD);
        this.edit(aanvraag);

        //Voeg deze gebeurtenis toe aan de geschiedenis
        Dossier dossier = aanvraag.getDossier();
        dossier.addGebeurtenis("Aanvraag is goedgekeurd door de kern.", initiator);
        this.edit(dossier);

        //Stuur mail naar de aanvrager met de boodschap dat de aanvraag goedgekeurd is. 
        Map<String, String> context = new HashMap<String, String>();
        context.put("aanvraag_aanvrager_voornaam", dossier.getGebruiker().getVoornaam());
        context.put("aanvraag_datum", aanvraag.getTijdsbepaling());
        this.sendMail("goedkeuring_aanvraag", dossier.getGebruiker().getEmailadres(), context);
    }

    /**
     * Markeer de gegeven aanvraag als geweigerd. RecordAid zal deze aanvraag
     * niet behandelen. Deze methode vervult bovendien de volgende
     * randactiviteiten: Het aanvullen van de geschiedenis en het versturen van
     * een mail naar de aanvrager met de boodschap dat zijn aanvraag geweigerd
     * is en eventueel de reden waarom zijn aanvraag geweigerd is.
     *
     * @param aanvraag De betrokken aanvraag.
     * @param initiator De gebruiker die de aanvraag weigerde.
     * @param reden De reden waarom de aanvraag genegeerd werd. Deze wordt
     * gebruikt die de mail die naar de aanvrager wordt verstuurd.
     */
    public void weigerAanvraag(AbstractAanvraag aanvraag, Gebruiker initiator, String reden) throws DomainException {
        //Weiger aanvraag. 
        aanvraag.setStatus(Status.AFGEKEURD);
        this.edit(aanvraag);

        //Voeg deze gebeurtenis toe aan de geschiedenis
        Dossier dossier = aanvraag.getDossier();
        dossier.addGebeurtenis("Aanvraag is afgekeurd door de kern.", initiator);
        this.edit(dossier);

        //Stuur mail naar de aanvrager met de boodschap dat de aanvraag afgekeurd is. 
        Map<String, String> context = new HashMap<String, String>();
        context.put("aanvraag_aanvrager_voornaam", dossier.getGebruiker().getVoornaam());
        context.put("aanvraag_datum", aanvraag.getTijdsbepaling());
        context.put("weigering_reden", reden);
        this.sendMail("weigering_aanvraag", dossier.getGebruiker().getEmailadres(), context);
    }

    public void removeOpnameMethode(OpnameMethode opnameMethode) {
        Collection<Opname> opnames = this.aanvraagDB.getOpnames(opnameMethode);

        for (Opname opname : opnames) {
            opname.setMethode(null);
            this.edit(opname);
        }

        this.remove(opnameMethode);
    }
}