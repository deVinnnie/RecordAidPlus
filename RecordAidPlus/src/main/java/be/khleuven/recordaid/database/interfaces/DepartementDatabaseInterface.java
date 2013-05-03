package be.khleuven.recordaid.database.interfaces;

import be.khleuven.recordaid.domain.departement.*; 
import be.khleuven.recordaid.opnames.OpnameMoment;
import java.util.*; 

/**
 *
 * @author Vincent Ceulemans
 */
public interface DepartementDatabaseInterface {
    public List<Departement> getAlleDepartementen(); 
    public List<Lector> getLectoren(); 
    public List<OpnameMoment> getLessenVanLector(Lector lector);
    public Lector getLector(String emailadres);
}