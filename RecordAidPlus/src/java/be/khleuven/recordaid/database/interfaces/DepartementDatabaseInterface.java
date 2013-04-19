package be.khleuven.recordaid.database.interfaces;

import be.khleuven.recordaid.domain.*; 
import java.util.*; 

/**
 *
 * @author Vincent Ceulemans
 */
public interface DepartementDatabaseInterface {
    public List<Departement> getAlleDepartementen(); 
    public List<Lector> getLectoren(); 
}
