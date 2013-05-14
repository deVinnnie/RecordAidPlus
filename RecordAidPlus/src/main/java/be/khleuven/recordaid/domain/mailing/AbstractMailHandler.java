package be.khleuven.recordaid.domain.mailing;

import java.io.*;
import java.util.Properties;

/**
 *
 * @author Vincent Ceulemans
 */
public abstract class AbstractMailHandler {

    private String username;
    private String password;

    protected AbstractMailHandler() {
    }

    /**
     * Constructor.
     *
     * @param path String die het path naar het project weergeeft.
     */
    public AbstractMailHandler(String path) throws IOException {
        this.readProperties(path);
    }

    private void readProperties(String path) throws IOException {
        Properties userProperties = new Properties();
        userProperties.load(new FileInputStream(path));
        this.password = userProperties.getProperty("email_wachtwoord");
        this.username = userProperties.getProperty("email");
    }

    public abstract boolean sendMessage(MailMessage mailMessage);

    protected String getUsername() {
        return this.username;
    }

    protected String getPassword() {
        return this.password;
    }
}