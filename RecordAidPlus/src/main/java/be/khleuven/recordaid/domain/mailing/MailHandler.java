package be.khleuven.recordaid.domain.mailing;

import java.io.IOException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*; 


/**
 * Klasse die gebruikt wordt om een mail te sturen naar een gebruiker. Er zijn
 * verschillende methodes om verschillende berichten te kunnen sturen,
 * afhankelijk van de reden waarom de mail gestuurd wordt.
 *
 * @author Maxime Van den Kerkhof
 * @author Vincent Ceulemans
 */
public class MailHandler extends AbstractMailHandler
{   
    public MailHandler(String path) throws IOException{
        super(path); 
    }
   
    //<editor-fold defaultstate="collapsed" desc="Mailing">
    private Session getSSLSession()
    {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
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
    //</editor-fold>
    
    @Override
    public boolean sendMessage(MailMessage mailMessage){
        boolean succes = false;
        mailMessage.setSender(this.getUsername());
        
        try
        {
            Message message = new MimeMessage(this.getTLSSession());
            message.setFrom(new InternetAddress(mailMessage.getSender()));
            message.reply(false);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailMessage.getRecipient()));
            message.setSubject(mailMessage.renderSubject());

            message.setContent(mailMessage.renderMessage(), "text/html");
            Transport.send(message);

            succes = true;
        }
        catch(MessagingException e)
        {
            throw new RuntimeException(e);
        }
        return succes;
    }
}