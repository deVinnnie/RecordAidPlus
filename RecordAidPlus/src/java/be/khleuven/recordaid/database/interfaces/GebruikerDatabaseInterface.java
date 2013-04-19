package be.khleuven.recordaid.database.interfaces;

import be.khleuven.recordaid.database.DatabaseException;
import be.khleuven.recordaid.domain.gebruiker.Gebruiker;
import be.khleuven.recordaid.domain.gebruiker.Rollen;
import be.khleuven.recordaid.domain.gebruiker.Dossier;
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