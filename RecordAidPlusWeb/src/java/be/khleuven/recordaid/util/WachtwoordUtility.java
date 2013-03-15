/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package be.khleuven.recordaid.util;

import be.khleuven.recordaid.util.jBCrypt.BCrypt;

/**
 *
 * @author Vincent Ceulemans
 */
public class WachtwoordUtility {
    /**
     * Methode die gebruikt wordt om een wachtwoord dat een gebruiker ingeeft te
     * salten en te hashen zodat het wachtwoord niet plain-text in de database
     * staat. Gebruikt Jbcrypt (Blowfish implemenatie)
     *
     * @param wachtwoord String die het wachtwoord is dat de gebruiker ingaf.
     * @return String die het aangepaste wachtwoord bevat.
     */
    public String hashWachtwoord(String wachtwoord)
    {
        String hash = BCrypt.hashpw(wachtwoord, BCrypt.gensalt(15)); 
        //Note: the salt is apparantly incorperated into the hash. 
        //So it is not necessary to store it seperatly. 
        return hash; 
    }
    
    public boolean controleerWachtwoord(String wachtwoord, String hash){
        return BCrypt.checkpw(wachtwoord, hash); 
    }
}