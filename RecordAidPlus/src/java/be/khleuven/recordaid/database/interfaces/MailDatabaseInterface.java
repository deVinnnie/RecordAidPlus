package be.khleuven.recordaid.database.interfaces;

import be.khleuven.recordaid.domain.mailing.MailMessage;

/**
 *
 * @author Vincent Ceulemans
 */
public interface MailDatabaseInterface {
     public MailMessage getMailMessage(String description); 
}
