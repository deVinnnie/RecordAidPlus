package be.khleuven.recordaid.mailing;

import be.khleuven.eindwerk.domain.Aanvraag;
import be.khleuven.eindwerk.domain.FAQ;
import be.khleuven.eindwerk.domain.Gebruiker;
import be.khleuven.eindwerk.domain.Support;

/**
 *
 * @author Vincent Ceulemans
 */
public class MailhandlerDummy extends MailHandler{
    public MailhandlerDummy(){
        super(); 
    }
    
    public MailhandlerDummy(String path){
        super(path);
    }
    
    @Override
    public boolean sendValidatieEmail(Gebruiker gebruiker) {
        return true; 
    }

    @Override
    public boolean sendNewBuddyMail(Gebruiker gebruiker) {
        return true; 
    }

    @Override
    public boolean sendAntwoordFAQMail(FAQ faq, Gebruiker buddy) {
        return true; 
    }

    @Override
    public boolean sentNieuweFAQMail(FAQ faq) {
        return true; 
    }

    @Override
    public boolean sendNieuweAanvraagMailNaarBuddy(Aanvraag aanvraag, Gebruiker buddy) {
        return true; 
    }

    @Override
    public boolean sendSupportMail(Support support) {
        return true;
    }

    @Override
    public boolean sendMailVoorGoedkeuring(String email, Aanvraag aanvraag) {
        return true;
    }

    @Override
    public boolean sendAanvraagBeschikbaar(Aanvraag aanvraag) {
        return true;
    }

    @Override
    public boolean sendNieuweAanvraagIngediend(Aanvraag aanvraag) {
       return true;
    }
}