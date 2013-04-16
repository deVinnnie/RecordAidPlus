package be.khleuven.recordaid.database.interfaces;

import be.khleuven.recordaid.database.DatabaseException;
import be.khleuven.recordaid.domain.Gebruiker;
import be.khleuven.recordaid.domain.Rollen;
import java.util.Collection;


/**
 * Een interface voor een database die Gebruikers bijhoudt.
 *
 * @author Hannes
 */
public interface GebruikerDatabaseInterface
{
    /**
     * Geeft een gebruiker terug op basis van zijn emailadres. Gooit een
     * DatabaseException indien de gebruiker niet bestaat.
     *
     * @param emailadres Het emailadres van de gebruiker, dit is uniek voor elke
     * gebruiker.
     * @return De gebruiker met het emailadres, indien deze bestaat, of null als de gebruiker niet bestaat. 
     */
    public Gebruiker getGebruiker(String emailadres);


    public Gebruiker getGebruikerByValidatiecode(String validatiecode);


    /**
     * Voegt een gebruiker toe aan de database. Gooit een DatabaseException
     * indien de gebruiker reeds bestaat.
     *
     * @param gebruiker De gebruiker die toegevoegd moet worden.
     * @throws DatabaseException indie de gebruiker reeds bestaat.
     */
    public void addGebruiker(Gebruiker gebruiker) throws DatabaseException;


    /**
     * Verwijdert een gebruiker uit de database op basis van zijn emailadres.
     * Gooit een DatabaseException indien de gebruiker niet bestaat.
     *
     * @param emailadres Het emailadres van de gebruiker die verwijderd moet
     * worden.
     * @throws DatabaseException indien de gebruiker niet bestaat.
     */
    public void removeGebruiker(String emailadres) throws DatabaseException;


    /**
     * Verwijdert een gebruiker uit de database op basis van het object. Gooit
     * een DatabaseException indien de gebruiker niet bestaat.
     *
     * @param gebruiker De gebruiker die verwijderd moet worden.
     * @throws DatabaseException indien de gebruiker niet bestaat.
     */
    public void removeGebruiker(Gebruiker gebruiker) throws DatabaseException;


    /**
     * Update een gebruiker in de database. Gooit een DatabaseException indien
     * de gebruiker niet bestaat.
     *
     * @param gebruiker De gebruiker die aangepast moet worden.
     * @throws DatabaseException indien de gebruiker niet bestaat.
     */
    public void updateGebruiker(Gebruiker gebruiker) throws DatabaseException;


    /**
     * Geeft alle gebruikers in de database terug.
     *
     * @return Collection<Gebruiker> met alle gebruikers in de database.
     */
    public Collection<Gebruiker> getGebruikers();


    /**
     * Geeft alle gebruikers in de database met een specifieke rol terug.
     *
     * @param rol
     * @return Collection<Gebruiker> met alle gebruikers in de database met deze
     * rol.
     */
    public Collection<Gebruiker> getGebruikers(Rollen rol);
}


