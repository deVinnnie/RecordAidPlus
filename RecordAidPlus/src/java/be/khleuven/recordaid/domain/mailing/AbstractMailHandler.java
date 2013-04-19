package be.khleuven.recordaid.domain.mailing;

import java.io.*;
import java.util.Properties;

/**
 *
 * @author Vincent Ceulemans
 */
public abstract class AbstractMailHandler {
    private String username, password, path;
    private String emailadresRecordAid; 
    
    protected AbstractMailHandler(){}
    
    /**
     * Constructor.
     *
     * @param path String die het path naar het project weergeeft.
     */
    public AbstractMailHandler(String path)
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
    
    public abstract boolean sendMessage(MailMessage mailMessage); 
    
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