package be.khleuven.recordaid.mailing;

import be.khleuven.recordaid.domain.aanvragen.Aanvraag;
import be.khleuven.recordaid.domain.FAQ;
import be.khleuven.recordaid.domain.Gebruiker;
import be.khleuven.recordaid.domain.Support;
import java.io.*;
import java.util.Properties;

/**
 *
 * @author Vincent Ceulemans
 */
public abstract class MailHandler {
    private String username, password, path;
    private String emailadresRecordAid; 
    
    protected MailHandler(){}
    
    /**
     * Constructor.
     *
     * @param path String die het path naar het project weergeeft.
     */
    public MailHandler(String path)
    {
        this.path = path;
        this.readProperties(); 
    }

    private void readProperties()
    { 
        try
        {
            Properties userProperties = new Properties(); 
            userProperties.load(new FileInputStream(path + "/config.properties"));
            this.username = userProperties.getProperty("username");
            this.password = userProperties.getProperty("password");
            this.emailadresRecordAid = userProperties.getProperty("emailadresRecordAid");
        }
        catch(FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Zendt een mail naar een nieuw geregistreerde gebruiker met zijn unieke
     * validatiecode in het bericht.
     *
     * @param gebruiker Gebruiker waarnaar de mail verzonden moet worden.
     * @return True indien de mail verzonden is, false indien die niet verzonden
     *         werd.
     */
    public abstract boolean sendValidatieEmail(Gebruiker gebruiker); 

    /**
     * Zendt een mail naar RecordAid dat één van de gebruikers graag buddy zou
     * willen worden.
     *
     * @param gebruiker Gebruiker waarnaar de mail verzonden moet worden.
     * @return True indien de mail verzonden is, false indien die niet verzonden
     *         werd.
     */
    public abstract boolean sendNewBuddyMail(Gebruiker gebruiker);
    
    /**
     * Zendt een mail naar een gebruiker wanneer een FAQ vraag die deze
     * gebruiker stelde beantwoord is.
     *
     * @param faq   Het FAQ bericht dat beantwoord is.
     * @param buddy Gebruiker die de mail verstuurd.
     * @return True indien de mail verzonden is, false indien die niet verzonden
     *         werd.
     */
    public abstract boolean sendAntwoordFAQMail(FAQ faq, Gebruiker buddy); 
    
    /**
     * Zendt een mail naar Recordaid wanneer een FAQ vraag gesteld werd.
     *
     * @param faq Het nieuw FAQ bericht.
     * @return True indien de mail verzonden is, false indien die niet verzonden
     *         werd.
     */
    public abstract boolean sentNieuweFAQMail(FAQ faq);
    
    /**
     * Zendt een mail naar een RecordAid buddy of kernlid wanneer dit lid
     * gekozen is als verantwoordelijke voor een bepaalde aanvraag.
     *
     * @param aanvraag De aanvraag waar de buddy als verantwoordelijke voor
     *                 aangeduid is.
     * @param buddy    Buddy of kernlid waarnaar de mail verzonden moet worden.
     * @return True indien de mail verzonden is, false indien die niet verzonden
     *         werd.
     */
    public abstract boolean sendNieuweAanvraagMailNaarBuddy(Aanvraag aanvraag, Gebruiker buddy); 

    /**
     * Zendt een mail naar RecordAid wanneer een gebruiker een technisch
     * probleem op de website meldt.
     *
     * @param support Het support object dat aangemaakt werd omtrent dit
     *                technisch probleem.
     * @return True indien de mail verzonden is, false indien die niet verzonden
     *         werd.
     */
    public abstract boolean sendSupportMail(Support support);
    
    /**
     * Zendt een mail naar een leerkracht of een departementshoofd om aan te
     * vragen of een opname goedgekeurd kan worden.
     *
     * @param gebruiker Leerkracht of departementshoofd waarnaar de mail
     *                  verzonden moet worden.
     * @param aanvraag  De aanvraag die goedgekeurd moet worden.
     * @return True indien de mail verzonden is, false indien die niet verzonden
     *         werd.
     */
    public abstract boolean sendMailVoorGoedkeuring(String email, Aanvraag aanvraag); 

    /**
     * Zendt een mail naar de gebruiker die een opname aanvroeg wanneer de
     * aanvraag beschikbaar is en de gebruiker via de link de video kan
     * bekijken.
     *
     * @param aanvraag De aanvraag die beschikbaar is.
     * @return True indien de mail verzonden is, false indien die niet verzonden
     *         werd.
     */
    public abstract boolean sendAanvraagBeschikbaar(Aanvraag aanvraag);
    
    /**
     * Zendt een mail naar RecordAid wanneer een student een nieuwe aanvraag
     * indient om een les op te nemen.
     *
     * @param aanvraag De nieuwe aanvraag die ingediend werd.
     * @return True indien de mail verzonden is, false indien die niet verzonden
     *         werd.
     */
    public abstract boolean sendNieuweAanvraagIngediend(Aanvraag aanvraag);
    
    protected String getEmailadresRecordAid(){
        return this.emailadresRecordAid; 
    }
    
    protected String getUsername(){
        return this.username;  
    }
    
    protected String getPassword(){
        return this.password; 
    }
}