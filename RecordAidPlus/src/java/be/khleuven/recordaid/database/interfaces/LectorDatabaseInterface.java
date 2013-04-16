package be.khleuven.recordaid.database.interfaces;

import be.khleuven.recordaid.database.DatabaseException;
import be.khleuven.recordaid.domain.Lector; 
import java.util.Collection;


/**
 * Een interface voor een database die Lectoren bijhoudt.
 *
 * @author Hannes
 */
public interface LectorDatabaseInterface
{
    public Lector getLector(String emailadres);
    public void addLector(Lector gebruiker) throws DatabaseException;
    public void removeLector(Lector gebruiker) throws DatabaseException;
    public void updateLector(Lector gebruiker) throws DatabaseException;
    public Collection<Lector> getLectoren();
}