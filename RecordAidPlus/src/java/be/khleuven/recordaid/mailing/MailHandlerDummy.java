package be.khleuven.recordaid.mailing;

import be.khleuven.recordaid.domain.aanvragen.Aanvraag;
import be.khleuven.recordaid.domain.FAQ;
import be.khleuven.recordaid.domain.Gebruiker;
import be.khleuven.recordaid.domain.Support;

/**
 *
 * @author Vincent Ceulemans
 */
public class MailHandlerDummy extends MailHandler{
    public MailHandlerDummy(){
        super(); 
    }
    
    public MailHandlerDummy(String path){
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