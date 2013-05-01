package be.khleuven.recordaid.database.interfaces;

import java.util.List;

/**
 *  This interface defines an entrypoint for common operations on the database. 
 * 
 * @author Vincent Ceulemans
 */
public interface CommonDatabaseInterface {
    public <T> T create(T t); 
    public <T> T edit(T o);
    public void remove(Object o); 
    public <T> T find(Class<T> className, Object id); 
    public <T> List<T> findAll(Class<T> className); 
}
