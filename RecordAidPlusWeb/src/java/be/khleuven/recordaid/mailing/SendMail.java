package be.khleuven.recordaid.mailing;

import be.khleuven.eindwerk.domain.*;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * Klasse die gebruikt wordt om een mail te sturen naar een gebruiker. Er zijn
 * verschillende methodes om verschillende berichten te kunnen sturen,
 * afhankelijk van de reden waarom de mail gestuurd wordt.
 *
 * @author Maxime Van den Kerkhof
 */
public class SendMail extends MailHandler
{   
    public SendMail(String path){
        super(path); 
    }
   
    //<editor-fold defaultstate="collapsed" desc="mailing">
    private Session getSSLSession()
    {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator()
                {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication()
                    {
                        return new PasswordAuthentication(getUsername(), getPassword());
                    }
                });

        return session;
    }


    private Session getTLSSession()
    {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator()
                {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication()
                    {
                        return new PasswordAuthentication(getUsername(), getPassword());
                    }
                });

        return session;
    }

    private boolean sendMyMessage(String emailAdres, String subject, String inhoud)
    {
        boolean succes = false;

        try
        {
            Message message = new MimeMessage(this.getTLSSession());
            //message.setFrom(new InternetAddress("recordaid@khleuven.be"));
            message.setFrom(new InternetAddress(getUsername()));
            message.reply(false);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailAdres));
            message.setSubject(subject);

            message.setContent(inhoud, "text/html");
            Transport.send(message);

            succes = true;
        }
        catch(MessagingException e)
        {
            throw new RuntimeException(e);
        }
        return succes;
    }

    //</editor-fold>

    /**
     * Zendt een mail naar een nieuw geregistreerde gebruiker met zijn unieke
     * validatiecode in het bericht.
     *
     * @param gebruiker Gebruiker waarnaar de mail verzonden moet worden.
     * @return True indien de mail verzonden is, false indien die niet verzonden
     *         werd.
     */
    @Override
    public boolean sendValidatieEmail(Gebruiker gebruiker)
    {
        StringBuilder inhoud = new StringBuilder();
        inhoud.append("Beste ").append(gebruiker.getVoornaam()).append(",<br><br>");
        inhoud.append("Dank u om de tijd te nemen om bij RecordAid een account aan te maken!<br>");
        inhoud.append("Met deze validatie code kan je jouw account activeren: <b>").append(gebruiker.getValidatieCode()).append("</b><br>");
        inhoud.append("Valideren kan via volgende link: <a href='http://recordaid.khleuven.be/valideren.jsp'>Valideren</a> <br><br>");

        inhoud.append("Met vriendelijke groeten,<br>Het RecordAid Team");

        return this.sendMyMessage(gebruiker.getEmailadres(), "Validatie RecordAid account", inhoud.toString());
    }


    /**
     * Zendt een mail naar RecordAid dat één van de gebruikers graag buddy zou
     * willen worden.
     *
     * @param gebruiker Gebruiker waarnaar de mail verzonden moet worden.
     * @return True indien de mail verzonden is, false indien die niet verzonden
     *         werd.
     */
    @Override
    public boolean sendNewBuddyMail(Gebruiker gebruiker)
    {
        StringBuilder inhoud = new StringBuilder();
        inhoud.append("Student ").append(gebruiker.getVoornaam()).append(" ").append(gebruiker.getNaam());
        inhoud.append(" heeft via de website opgegeven dat hij buddy wil worden. <br><br>");
        inhoud.append("Je kan ").append(gebruiker.getVoornaam()).append(" contacteren op volgend e-mail adres:<b> ").append(gebruiker.getEmailadres()).append("</b>");
        return this.sendMyMessage(this.getEmailadresRecordAid(), "Aanmelding nieuwe buddy", inhoud.toString());
    }


    /**
     * Zendt een mail naar een gebruiker wanneer een FAQ vraag die deze
     * gebruiker stelde beantwoord is.
     *
     * @param faq   Het FAQ bericht dat beantwoord is.
     * @param buddy Gebruiker die de mail verstuurd.
     * @return True indien de mail verzonden is, false indien die niet verzonden
     *         werd.
     */
    @Override
    public boolean sendAntwoordFAQMail(FAQ faq, Gebruiker buddy)
    {

        StringBuilder inhoud = new StringBuilder();
        inhoud.append("Beste ").append(faq.getGebruiker().getVoornaam()).append(",<br><br>");
        inhoud.append("De vraag die u recent stelde op onze website werd beantwoord.<br>");

        inhoud.append("Wij hechten veel belang aan uw vragen! <br><br>");
        inhoud.append("Onlangs stelde u volgende vraag:<br><b>").append(faq.getVraag()).append("</b><br><br>");
        inhoud.append("Ons antwoord op uw vraag:<br><b>").append(faq.getAntwoord()).append("</b><br><br>");
        inhoud.append("Met vriendelijke groeten,<br><br>Het RecordAid Team<br>");
        inhoud.append(buddy.getVoornaam()).append(" ").append(buddy.getNaam()).append("<br>");
        inhoud.append("Is het antwoord niet duidelijk? Aarzel dan niet om te mailen naar: ").append(buddy.getEmailadres());

        return this.sendMyMessage(faq.getGebruiker().getEmailadres(), "Antwoord op uw vraag", inhoud.toString());
    }


    /**
     * Zendt een mail naar Recordaid wanneer een FAQ vraag gesteld werd.
     *
     * @param faq Het nieuw FAQ bericht.
     * @return True indien de mail verzonden is, false indien die niet verzonden
     *         werd.
     */
    @Override
    public boolean sentNieuweFAQMail(FAQ faq)
    {

        StringBuilder inhoud = new StringBuilder();

        inhoud.append("Gebruiker <b>").append(faq.getGebruiker().getVoornaam()).append(" ").append(faq.getGebruiker().getNaam()).append("</b> heeft een nieuwe vraag gesteld.<br>");
        inhoud.append("De gebruiker had volgende vraag:<br>");
        inhoud.append("<b>").append(faq.getVraag()).append("</b><br><br>");
        inhoud.append("E-mail van de gebruiker: ").append(faq.getGebruiker().getEmailadres());

        return sendMyMessage(this.getEmailadresRecordAid(), "Nieuwe aanvraag", inhoud.toString());
    }


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
    @Override
    public boolean sendNieuweAanvraagMailNaarBuddy(Aanvraag aanvraag, Gebruiker buddy)
    {
        StringBuilder inhoud = new StringBuilder();

        inhoud.append("Hey ").append(buddy.getVoornaam()).append("!<br>");
        inhoud.append("Je bent aangewezen door een RecordAid kernlid om een opname op te volgen.<br>");
        inhoud.append("Hieronder vind je de details van de opname:<br>");
        inhoud.append("Vak: ").append(aanvraag.getOptenemenVak()).append("<br>");
        inhoud.append("Datum: ").append(aanvraag.getAanvraagDatum().getTime()).append("<br>");
        inhoud.append("Beginuur: ").append(aanvraag.getBeginUur()).append(" uur.<br>");
        inhoud.append("Lokaal: ").append(aanvraag.getLokaal()).append("<br>");
        inhoud.append("Huidige status: ").append(aanvraag.getStatus()).append("<br><br>");
        inhoud.append("E-mail aanvrager: ").append(aanvraag.getAanvrager().getEmailadres()).append("<br><br>");

        inhoud.append("Met vriendelijke groeten,<br><br>Het RecordAid Team");


        return this.sendMyMessage(buddy.getEmailadres(), "Je hebt een nieuwe aanvraag toegewezen gekregen", inhoud.toString());
    }


    /**
     * Zendt een mail naar RecordAid wanneer een gebruiker een technisch
     * probleem op de website meldt.
     *
     * @param support Het support object dat aangemaakt werd omtrent dit
     *                technisch probleem.
     * @return True indien de mail verzonden is, false indien die niet verzonden
     *         werd.
     */
    @Override
    public boolean sendSupportMail(Support support)
    {
        StringBuilder inhoud = new StringBuilder();
        inhoud.append("Gebruiker ").append(support.getZender().getVoornaam()).append(" ").append(support.getZender().getNaam());
        inhoud.append(" heeft het volgende support bericht gestuurd: ").append("<br>");
        inhoud.append("Een bericht over volgend lokaal: ");
        inhoud.append(support.getLokaal()).append(("<br>"));
        inhoud.append(support.getReport());

        return this.sendMyMessage(this.getEmailadresRecordAid(), "Nieuw support bericht", inhoud.toString());
    }


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
    @Override
    public boolean sendMailVoorGoedkeuring(String email, Aanvraag aanvraag)
    {
        StringBuilder inhoud = new StringBuilder();

        inhoud.append("Beste, <br><br>");
        inhoud.append("Student <b>").append(aanvraag.getAanvrager().getVoornaam()).append(" ").append(aanvraag.getAanvrager().getNaam()).append("</b>");
        inhoud.append(" heeft een aanvraag ingediend om de les ").append(aanvraag.getOptenemenVak()).append(" te laten opnemen door RecordAid.<br>");
        inhoud.append("De reden die de student opgegeven heeft is volgende: ").append(aanvraag.getReden()).append("<br>");
        inhoud.append("Zou u ons kunnen laten weten of we deze les mogen opnemen?");

        inhoud.append("<br><br>Met vriendelijke groeten,<br>Het RecordAid Team");
        return this.sendMyMessage(email, "Aanvraag lesopname", inhoud.toString());
    }


    /**
     * Zendt een mail naar de gebruiker die een opname aanvroeg wanneer de
     * aanvraag beschikbaar is en de gebruiker via de link de video kan
     * bekijken.
     *
     * @param aanvraag De aanvraag die beschikbaar is.
     * @return True indien de mail verzonden is, false indien die niet verzonden
     *         werd.
     */
    @Override
    public boolean sendAanvraagBeschikbaar(Aanvraag aanvraag)
    {
        StringBuilder inhoud = new StringBuilder();

        if(aanvraag.getStatus() == Status.GOEDGEKEURD_VOOR_OPNAME)
        {
            inhoud.append("Hey ").append(aanvraag.getToegewezenLid().getVoornaam()).append(",<br><br>");
            inhoud.append("De aanvraag waarvoor jij instaat is <b>goedgekeurt voor opname</b>.<br><br>");
            inhoud.append("Hieronder vind je nog even de details van de opname:<br>");
            inhoud.append("Vak: ").append(aanvraag.getOptenemenVak()).append("<br>");
            inhoud.append("Datum: ").append(aanvraag.getAanvraagDatum().getTime()).append("<br>");
            inhoud.append("Beginuur: ").append(aanvraag.getBeginUur()).append(" uur.<br>");
            inhoud.append("Lokaal: ").append(aanvraag.getLokaal()).append("<br>");
            inhoud.append("Huidige status: ").append(aanvraag.getStatus()).append("<br><br>");
            inhoud.append("E-mail aanvrager: ").append(aanvraag.getAanvrager().getEmailadres()).append("<br><br>");

            return this.sendMyMessage(aanvraag.getToegewezenLid().getEmailadres(), "Aanvraag goedgekeurt", inhoud.toString());

        }
        else
        {
            inhoud.append("Hey ").append(aanvraag.getAanvrager().getVoornaam()).append(",<br><br>");
            inhoud.append("De aanvraag die je hebt gedaan is van status verandert.<br>");
            inhoud.append("De status van jouw aanvraag is nu: <b>").append(aanvraag.getStatus()).append("</b><br>");
            inhoud.append("Indien je vragen hebt, mag je je vragen altijd sturen naar recordaid@khleuven.be.<br>");
            inhoud.append("Je kan ook altijd een vraag stellen op onze website.<br>");
            inhoud.append("<br><br>Met vriendelijke groeten,<br>Het RecordAid Team");

            return this.sendMyMessage(aanvraag.getAanvrager().getEmailadres(), "Uw aanvraag is van status verandert", inhoud.toString());
        }
    }


    /**
     * Zendt een mail naar RecordAid wanneer een student een nieuwe aanvraag
     * indient om een les op te nemen.
     *
     * @param aanvraag De nieuwe aanvraag die ingediend werd.
     * @return True indien de mail verzonden is, false indien die niet verzonden
     *         werd.
     */
    @Override
    public boolean sendNieuweAanvraagIngediend(Aanvraag aanvraag)
    {
        StringBuilder inhoud = new StringBuilder();

        inhoud.append("Er is een nieuwe aanvraag ingediend.<br>");
        inhoud.append("Dit zijn de details van de nieuwe ingediende opname:<br>");
        inhoud.append("Vak: ").append(aanvraag.getOptenemenVak()).append("<br>");
        inhoud.append("Datum: ").append(aanvraag.getAanvraagDatum().getTime()).append("<br>");
        inhoud.append("Beginuur: ").append(aanvraag.getBeginUur()).append(" uur.<br>");
        inhoud.append("Einduur: ").append(aanvraag.getEindUur()).append(" uur.<br>");
        inhoud.append("Lokaal: ").append(aanvraag.getLokaal()).append("<br>");
        inhoud.append("Reden van aanvraag: ").append(aanvraag.getReden()).append("<br><br>");
        inhoud.append("E-mail aanvrager: ").append(aanvraag.getAanvrager().getEmailadres()).append("<br><br>");

        Boolean sent1 = sendMyMessage(this.getEmailadresRecordAid(), "Nieuwe aanvraag", inhoud.toString());

        StringBuilder inhoud2 = new StringBuilder();

        inhoud2.append("U diende een nieuwe aanvraag in.<br>");
        inhoud2.append("Dit zijn de details van de  ingediende opname:<br>");
        inhoud2.append("Vak: ").append(aanvraag.getOptenemenVak()).append("<br>");
        inhoud2.append("Datum: ").append(aanvraag.getAanvraagDatum().getTime()).append("<br>");
        inhoud2.append("Beginuur: ").append(aanvraag.getBeginUur()).append(" uur.<br>");
        inhoud2.append("Einduur: ").append(aanvraag.getEindUur()).append(" uur.<br>");
        inhoud2.append("Lokaal: ").append(aanvraag.getLokaal()).append("<br>");
        inhoud2.append("Reden van aanvraag: ").append(aanvraag.getReden()).append("<br><br>");
        inhoud2.append("Het RecordAid team bekijkt uw aanvraag en zal u op de hoogte houden van ontwikkelingen.");

        Boolean sent2 = sendMyMessage(aanvraag.getAanvrager().getEmailadres(), "U diende een nieuwe RecordAid aanvraag in.", inhoud2.toString());

        return sent1 && sent2;
    }
}