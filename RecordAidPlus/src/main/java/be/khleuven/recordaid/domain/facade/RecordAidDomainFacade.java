package be.khleuven.recordaid.domain.facade;

import be.khleuven.recordaid.opnames.*; 
import be.khleuven.recordaid.domain.items.*;
import be.khleuven.recordaid.domain.gebruiker.*;
import be.khleuven.recordaid.domain.mailing.*;
import be.khleuven.recordaid.domain.aanvragen.*;
import be.khleuven.recordaid.database.DatabaseException;
import be.khleuven.recordaid.database.interfaces.*; 
import be.khleuven.recordaid.domain.*; 
import java.util.*; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

/**
 * Klasse die fungeert als een facade voor alle database klassen. 
 * De RecordAidDomainFacade kan aangesproken worden en deze geeft de betreffende taak door aan het juiste model-object.
 *
 * @author Maxime Van den Kerkhof
 */
public class RecordAidDomainFacade
{
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
    public RecordAidDomainFacade()
    {
        //mailing = new SendMail(this.servletContext.getRealPath("/WEB-INF"));
        mailHandler = new MailHandlerDummy(); 
    }

    @Autowired
    public RecordAidDomainFacade(
            CommonDatabaseInterface commonDb,
            FAQDatabaseInterface faqDB,
            GebruikerDatabaseInterface gebruikerDB,
            AanvraagDatabaseInterface aanvraagDB, 
            ReservatieDatabaseInterface reservatieDB, 
            DepartementDatabaseInterface departementDb, 
            MailDatabaseInterface mailDb) {
        this.commonDb = commonDb; 
        this.faqDB = faqDB;
        this.gebruikerDB = gebruikerDB;
        this.aanvraagDB = aanvraagDB;
        this.reservatieDB = reservatieDB;
        this.departementDb = departementDb; 
        this.mailDb = mailDb; 
        
        //Watch out!! The init method intializes some default but real entities. 
        //You don't want to accidently send a mail to them!
        mailHandler = new MailHandlerDummy(); 
        
        try{
            this.init();
        }
        catch(Exception e){
            //Move along, nothing to see here. 
        }
    }    
    
    /**
     * Put some test data in the database.
     */
    public void init() throws DatabaseException{
        StartUpDataFiller filler = new StartUpDataFiller(this); 
        filler.init();
    }

    //<editor-fold defaultstate="collapsed" desc="FAQ">
    public FAQ findFAQ(Long id)
    {
        return commonDb.find(FAQ.class, id);
    }

    public Collection<FAQ> getFAQs()
    {
        return commonDb.findAll(FAQ.class); 
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

    //<editor-fold defaultstate="collapsed" desc="Gebruikers">
    public Gebruiker getGebruiker(String emailadres)
    {
        return commonDb.find(Gebruiker.class,emailadres);
    }

    public Gebruiker getGebruikerByValidatiecode(String validatiecode)
    {
        return gebruikerDB.getGebruikerByValidatiecode(validatiecode);
    }

    public void addGebruiker(Gebruiker gebruiker) throws DatabaseException
    {
        //Add user to database. 
        commonDb.create(gebruiker); 
        
        //Send mail with Validation-code to the new user. 
        MailMessage mailMessage = mailDb.getMailMessage("validatie_gebruiker"); 
        Map<String, String> context = new HashMap<String, String>(); 
        context.put("gebruiker_voornaam", gebruiker.getVoornaam());
        context.put("validatie_code", gebruiker.getValidatieCode()); 
        mailMessage.setContext(context); 
        mailHandler.sendMessage(mailMessage);
    }

    public void removeGebruiker(Gebruiker gebruiker) throws DatabaseException
    {
        commonDb.remove(gebruiker);
    }

    public Collection<Gebruiker> getGebruikers()
    {
        return commonDb.findAll(Gebruiker.class); 
    }

    public Collection<Gebruiker> getGebruikers(Rollen rol)
    {
        return gebruikerDB.getGebruikers(rol);
    }
    
    public Dossier getDossier(Gebruiker gebruiker) {
        Dossier dossier = commonDb.find(Dossier.class, gebruiker.getEmailadres()); 
        if(dossier ==null){
            dossier = new Dossier(gebruiker); 
            dossier = commonDb.create(dossier); 
        }
        return dossier; 
    }
    //</editor-fold >

    //<editor-fold defaultstate="collapsed" desc="Items"> 
    public Item findItem(Long id)
    {
        return commonDb.find(Item.class,id);
    }

    public Collection<Item> getItems()
    {
        return commonDb.findAll(Item.class);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Aanvragen">
    public AbstractAanvraag addAanvraag(AbstractAanvraag aanvraag)
    {
        aanvraag = commonDb.create(aanvraag); 
        Dossier dossier = aanvraag.getDossier(); 
        dossier.addAanvraag(aanvraag);
        commonDb.edit(dossier); 
        
        //Verzend mail naar recordaid met de boodschap dat een nieuwe aanvraag is toegevoegd.  
        MailMessage mailMessage = mailDb.getMailMessage("nieuwe_aanvraag_melding"); 
        Map<String, String> context = new HashMap<String, String>(); 
        context.put("aanvraag_aanvrager_voornaam", dossier.getGebruiker().getVoornaam());
        context.put("aanvraag_aanvrager_achternaam", dossier.getGebruiker().getAchternaam()); 
        context.put("url", this.url+"RecordAidPlus/aanvraag/detail?id="+aanvraag.getId()); 
        mailMessage.setContext(context); 
        mailHandler.sendMessage(mailMessage);
        
        return aanvraag;  
    }
    
    public AbstractAanvraag findAanvraag(long id)
    {
        return commonDb.find(AbstractAanvraag.class, id);
    }

    public void updateAanvraag(DagAanvraag aanvraag) throws DatabaseException
    {
        commonDb.edit(aanvraag);
    }

    public void removeAanvraag(long id)
    {
        commonDb.remove(commonDb.find(AbstractAanvraag.class,id));
    }

    public void removeAanvraag(DagAanvraag aanvraag)
    {
        commonDb.remove(aanvraag);
    }

    public Collection<DagAanvraag> getAanvragen()
    {
        return commonDb.findAll(DagAanvraag.class); 
    }

    public Collection<DagAanvraag> getAanvragen(Status status)
    {
        return aanvraagDB.getAanvragen(status);
    }

    public Collection<DagAanvraag> getAanvragenToegewezenLid(Gebruiker toegewezenLid)
    {
        return aanvraagDB.getAanvragenToegewezenLid(toegewezenLid);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Reservaties">
    public Reservatie getReservatie(long id)
    {
        return commonDb.find(Reservatie.class, id);
    }

    public Collection<Reservatie> getReservaties()
    {
        return reservatieDB.getReservaties();
    }
    
    public Collection<ReservatieDag> getReservaties(long itemId)
    {
        return reservatieDB.getReservaties(commonDb.find(Item.class, itemId)); 
    }

    public Collection<Reservatie> getReservaties(Calendar datum, Item item)
    {
        return reservatieDB.getReservaties(datum, item);
    }
    
    public Collection<Reservatie> getReservaties(Calendar start, Calendar end, Item item)
    {
        return reservatieDB.getReservaties(start, end, item);
    }
    
    public Collection<Reservatie> getReservaties(Calendar start, Calendar end)
    {
        Collection<Reservatie> reservaties = reservatieDB.getReservaties(start, end);
        return reservaties; 
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Support">
    public Support findSupport(long id) throws DatabaseException
    {
        return commonDb.find(Support.class, id); 
    }

    public Collection<Support> getSupports()
    {
        return commonDb.findAll(Support.class);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Lectoren">
    public Lector getLector(String emailadres) {
        Lector lector;
        try {
            lector = departementDb.getLector(emailadres);
        } catch (EmptyResultDataAccessException ex) {
            lector = new Lector(emailadres);
            lector = commonDb.create(lector);
        }
        return lector;
    }
    
    public void removeLector(Lector lector){
        commonDb.remove(lector); 
    }
    
    public Collection<Lector> getLectoren(){
        return commonDb.findAll(Lector.class); 
    }
    
    public List<OpnameMoment> getLessenVanLector(Lector lector){
        return departementDb.getLessenVanLector(lector);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Departementen">
    public void addDepartement(Departement departement){
        commonDb.create(departement);
    }
    
    public List<Departement> getDepartementen(){
        return this.departementDb.getAlleDepartementen(); 
    }
    
    public Departement getDepartement(String naam) {
        return commonDb.find(Departement.class, naam); 
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Common"> 
    public <T> T create(T t){
        return commonDb.create(t); 
    }
    
    public <T> T edit(T t) {
        return commonDb.edit(t); 
    }
    
    public void remove(Object object){
        commonDb.remove(object);
    }
    //</editor-fold>

    public OpnameMethode findOpnameMethode(Long id){
        return commonDb.find(OpnameMethode.class, id); 
    }
    
    public List<OpnameMethode> getOpnameMethodes(){
        return commonDb.findAll(OpnameMethode.class); 
    }
    
    public List<OpnameMoment> getZichtbareOpnames(){
        List<OpnameMoment> zichtbareOpnames = new ArrayList<OpnameMoment>(); 
        List<OpnameMoment> alle = commonDb.findAll(OpnameMoment.class); 
        for(OpnameMoment opnameMoment : alle){
            if(opnameMoment.isZichtbaar()){
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
    public List<MailMessage> findMailMessages(){
        return commonDb.findAll(MailMessage.class); 
    }

    public MailMessage findMailMessage(long id) {
        return commonDb.find(MailMessage.class, id);
    }
    
    public SubjectPrefix getSubjectPrefix(){
        long id =1; 
        return commonDb.find(SubjectPrefix.class, id); 
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
}