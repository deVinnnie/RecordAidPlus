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

/**
 * Klasse die fungeert als een facade voor alle database klassen. De
 * RecordAidDomainFacade kan aangesproken worden en deze geeft de betreffende
 * taak door aan het juiste model-object.
 *
 * @author Maxime Van den Kerkhof, Vincent Ceulemans
 */
public class RecordAidDomainFacade {
    //<editor-fold defaultstate="collapsed" desc="Instantievariabelen & Constructors">  
    private static String url = "http://recordaid.khleuven.be/";
    private static String configPath = System.getProperty("user.home") + "/.recordaid/recordaid.properties";
    private String mailHandlerType = "MailHandlerDummy";
    private CommonDatabaseInterface commonDb;
    private GebruikerDatabaseInterface gebruikerDB;
    private AanvraagDatabaseInterface aanvraagDB;
    private ReservatieDatabaseInterface reservatieDB;
    private DepartementDatabaseInterface departementDb;
    private MailDatabaseInterface mailDb;

    /**
     * Constructor om een nieuwe RecordAidDomainFacade aan te maken.
     */
    public RecordAidDomainFacade() {}

    public RecordAidDomainFacade(
            CommonDatabaseInterface commonDb,
            GebruikerDatabaseInterface gebruikerDB,
            AanvraagDatabaseInterface aanvraagDB,
            ReservatieDatabaseInterface reservatieDB,
            DepartementDatabaseInterface departementDb,
            MailDatabaseInterface mailDb) {
        this(commonDb, gebruikerDB, aanvraagDB, reservatieDB, departementDb, mailDb, true);
    }

    @Autowired
    public RecordAidDomainFacade(
            CommonDatabaseInterface commonDb,
            GebruikerDatabaseInterface gebruikerDB,
            AanvraagDatabaseInterface aanvraagDB,
            ReservatieDatabaseInterface reservatieDB,
            DepartementDatabaseInterface departementDb,
            MailDatabaseInterface mailDb,
            boolean initialize) {
        this.commonDb = commonDb;
        this.gebruikerDB = gebruikerDB;
        this.aanvraagDB = aanvraagDB;
        this.reservatieDB = reservatieDB;
        this.departementDb = departementDb;
        this.mailDb = mailDb;

        //Create Admin user
        try {
            //Standaard wachtwoord = "geheim". 
            String hash = "$2a$15$ThkOeJ8D.jfblYskRaxx4uOtvTUPfeY9M2r8v5VmOqZmk7TUyJe5m";
            Gebruiker g = new Gebruiker(Rollen.ADMIN, "recordaid@khleuven.be", "RecordAid", "Admin", hash);
            g.valideer();
            this.create(g);

            Setting setting = new Setting("admin_first_login", "TRUE");
            this.create(setting);
        } catch (Exception e) {
            //Move Along
        }
        
        if (initialize) {
            this.init();
        }
    }
    //</editor-fold>  

    public void init() {
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
        Gebruiker gebruiker = dossier.getGebruiker();
        this.edit(gebruiker);

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
    public Support findSupport(long id) {
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
    
    public Lector getLector(long id){
        Lector lector = commonDb.find(Lector.class, id); 
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

    //<editor-fold defaultstate="collapsed" desc="OpnameMethoden">
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
    //</editor-fold>

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

        if (mailMessage == null) {
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
            MailHandlerFactory mailHandlerFactory = new MailHandlerFactory();
            AbstractMailHandler mailHandler = mailHandlerFactory.createMailHandler(this.mailHandlerType, RecordAidDomainFacade.configPath);

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
            this.sendMail(mailMessage, gebruiker.getEmailadres(), new HashMap<String, String>());
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
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

    public String getMailHandlerType() {
        return mailHandlerType;
    }

    public void setMailHandlerType(String mailHandlerType) {
        this.mailHandlerType = mailHandlerType;
    }
    //</editor-fold>

    public Setting getSetting(String sleutel) {
        return commonDb.find(Setting.class, sleutel);
    }

    //<editor-fold defaultstate="collapsed" desc="Handelingen">
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
        logGebeurtenis(dossier, initiator, "Aanvraag is goedgekeurd door de kern."); 

        //Stuur mail naar de aanvrager met de boodschap dat de aanvraag goedgekeurd is. 
        Map<String, String> context = new HashMap<String, String>();
        context.put("aanvraag_aanvrager_voornaam", dossier.getGebruiker().getVoornaam());
        context.put("aanvraag_datum", aanvraag.getTijdsbepaling());
        this.sendMail("goedkeuring_aanvraag", dossier.getGebruiker().getEmailadres(), context);

        //Stuur mail naar de betrokken lectoren. 
        if (aanvraag instanceof DagAanvraag) {
            for (OpnameMoment opnameMoment : aanvraag.getOpnameMomenten()) {
                Map<String, String> opnameMomentContext = new HashMap<String, String>();
                opnameMomentContext.put("aanvraag_aanvrager_voornaam", aanvraag.getDossier().getGebruiker().getVoornaam());
                opnameMomentContext.put("aanvraag_aanvrager_achternaam", aanvraag.getDossier().getGebruiker().getAchternaam());
                opnameMomentContext.put("aanvraag_ood", opnameMoment.getOOD());
                opnameMomentContext.put("aanvraag_reden", aanvraag.getReden());
                String urlFormulier = url + "opnames/opname_goedkeuren?toegangscode="
                        + opnameMoment.getToegangsCode()
                        + "&opname=" + opnameMoment.getId()
                        + "&aanvraag=" + aanvraag.getId();
                opnameMomentContext.put("url", urlFormulier);
                this.sendMail("aanvraag_goedkeuring_lector", opnameMoment.getLector().getEmailadres(), opnameMomentContext);
            }
        }
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
        logGebeurtenis(dossier, initiator, "Aanvraag is afgekeurd door de kern.");

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

    /**
     * Beantwoord een vraag van een gebruiker. Er wordt een mail gestuurd naar
     * de vrager, tenzij deze vraag al eerder beantwoord werd (in geval van
     * wijziging).
     *
     * @param faq Faq met antwoord
     * @param antwoorder Buddy of Kernlid die de vraag beantwoord.
     */
    public void beantwoordFaq(FAQ faq, Gebruiker antwoorder) throws DomainException {
        if (faq.isBeantwoord() == false) {
            //Maak context voor mail
            Gebruiker gebruiker = faq.getGebruiker();
            Map<String, String> context = new HashMap<String, String>();
            context.put("gebruiker_voornaam", gebruiker.getVoornaam());
            context.put("faq_vraag", faq.getVraag());
            context.put("faq_antwoord", faq.getAntwoord());
            context.put("buddy_voornaam", antwoorder.getVoornaam());
            context.put("buddy_achternaam", antwoorder.getAchternaam());
            context.put("buddy_email", antwoorder.getEmailadres());

            //Verstuur mail
            this.sendMail("antwoord_faq", gebruiker.getEmailadres(), context);
        }

        //Bewerk vraag
        faq.setBeantwoord(true);
        this.edit(faq);
    }

    public void koppelOpname(AbstractAanvraag aanvraag, OpnameMoment opnameMoment, 
            Opname opname, Gebruiker initiator) throws DomainException {
        //Koppel opname
        opnameMoment.setOpname(opname);
        this.edit(opnameMoment);

        //Stuur mail naar aanvrager als alle opnames gebeurt zijn. 
        if (aanvraag instanceof DagAanvraag) {
            boolean alleOpnamesGedaan = true;
            for (OpnameMoment moment : aanvraag.getOpnameMomenten()) {
                if (moment.getGoedgekeurd()!=null && 
                        moment.getGoedgekeurd().equals(Boolean.TRUE)) {
                    if (moment.getOpname() == null
                            || !moment.getOpname().getStatus().equals(OpnameStatus.GEREED)) {
                        alleOpnamesGedaan = false;
                    }
                }
            }

            if (alleOpnamesGedaan) {
                //Construct context. 
                Map<String, String> context = new HashMap<String, String>();
                context.put("aanvraag_aanvrager_voornaam", aanvraag.getDossier().getGebruiker().getVoornaam());
                context.put("aanvraag_datum", aanvraag.getTijdsbepaling());

                this.sendMail("opnames_klaar", aanvraag.getDossier().getGebruiker().getEmailadres(), context);
            }
        }
        
        //Update Geschiedenis
        logGebeurtenis(aanvraag.getDossier(), initiator, "Opname toegevoegd");
    }

    public void sluitAanvraag(AbstractAanvraag aanvraag, Gebruiker initiator) {
        //Verander status. 
        aanvraag.setStatus(Status.AFGEHANDELD);
        this.edit(aanvraag);
        
        //Update Geschiedenis
        logGebeurtenis(aanvraag.getDossier(), initiator, "Aanvraag afgehandeld.");
    }
    
     public void informLectoren(MultiPeriodeAanvraag aanvraag, Gebruiker initiator) throws DomainException {
        //Verstuur mails
        for (Lector lector : aanvraag.getLectoren()) {
            Map<String, String> context = new HashMap<String, String>();
            context.put("aanvraag_aanvrager_voornaam", aanvraag.getDossier().getGebruiker().getVoornaam());
            context.put("aanvraag_aanvrager_achternaam", aanvraag.getDossier().getGebruiker().getAchternaam());
            String link = url + "opnames/multi_goedkeuren?toegangscode="+aanvraag.getToegangsCode()
                    +"&lector="+lector.getEmailadres()
                    +"&aanvraag="+aanvraag.getId(); 
            context.put("url",link);
            this.sendMail("aanvraag_goedkeuring_multi_lector", lector.getEmailadres(), context);
        }

        //Update geschiedenis
        this.logGebeurtenis(aanvraag.getDossier(), initiator, "Mail verstuurd naar betrokken lectoren.");
    }

    public void buddyWorden(Gebruiker gebruiker) throws DomainException {
        Map<String, String> context = new HashMap<String, String>();
        context.put("gebruiker_email", gebruiker.getEmailadres());  
        context.put("gebruiker_voornaam", gebruiker.getVoornaam()); 
        context.put("gebruiker_achternaam", gebruiker.getAchternaam()); 
        this.sendMail("aanmelding_nieuwe_buddy", "recordaid@khleuven.be", context); 
    }
    //</editor-fold>

    private void logGebeurtenis(Gebruiker gebruiker, Gebruiker initiator, String message) {
        Dossier dossier = this.getDossier(gebruiker); 
        dossier.addGebeurtenis(message, initiator);
        this.edit(dossier);
    }
    
    private void logGebeurtenis(Dossier dossier, Gebruiker initiator, String message) {
        this.logGebeurtenis(dossier.getGebruiker(), initiator, message);
    }
    
    public Collection<Dossier> getDossiers() {
        return commonDb.findAll(Dossier.class);
    }
}