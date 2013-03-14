package be.khleuven.eindwerk.database;

import be.khleuven.eindwerk.domain.Support;
import java.util.Collection;


/**
 * Een interface voor een database die Supports bijhoudt.
 *
 * @author Koen
 */
public interface SupportDatabaseInterface
{
    /**
     * Geeft een Support terug die in de database is opgeslagen op basis van het
     * id.
     *
     * @param id Het id van het opgeslagen Support.
     * @return Support met dat bepaalde id.
     * @throws DatabaseException indien het Support niet gevonden kan worden.
     */
    public Support findSupport(long id) throws DatabaseException;


    /**
     * Voegt een Support toe aan de database.
     *
     * @param support Support die toegevoegt moet worden.
     * @throws DatabaseException indien het Support reeds bestaat in de
     * database.
     */
    public void addSupport(Support support) throws DatabaseException;


    /**
     * Verwijdert een Support uit de database op basis van het id.
     *
     * @param id Het id van het Support dat verwijderd moet worden.
     * @throws DatabaseException indien het Support niet gevonden kan worden.
     */
    public void removeSupport(long id) throws DatabaseException;


    /**
     * Verwijdert een Support uit de database.
     *
     * @param support Het Support dat verwijderd moet worden.
     * @throws DatabaseException indien het Support niet gevonden kan worden.
     */
    public void removeSupport(Support support) throws DatabaseException;


    /**
     * Wijzigt een Support dat opgeslagen is in de database met nieuwe gegevens
     * van het Support.
     *
     * @param support Het Support dat gewijzigd is en aangepast moet worden in
     * de database.
     * @throws DatabaseException indien het Support niet gevonden kan worden.
     */
    public void updateSupport(Support support) throws DatabaseException;


    /**
     * Geeft een collection terug van alle Supports in de database.
     *
     * @return Collection<Support> van alle Supports in de database.
     */
    public Collection<Support> getSupports();
}


