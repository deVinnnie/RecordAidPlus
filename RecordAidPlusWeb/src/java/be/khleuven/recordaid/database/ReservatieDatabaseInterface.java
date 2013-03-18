package be.khleuven.recordaid.database;

import be.khleuven.recordaid.domain.Item;
import be.khleuven.recordaid.domain.Reservatie;
import java.util.Calendar;
import java.util.Collection;


/**
 * Een interface voor een database die Reservaties bijhoudt.
 *
 * @author Hannes
 */
public interface ReservatieDatabaseInterface
{
    /**
     * Geeft een Reservatie terug die opgeslagen is in de database op basis van
     * het id.
     *
     * @param id Het id van de opgeslagen Reservatie.
     * @return De Reservatie met dat bepaalde id.
     */
    public Reservatie getReservatie(long id);


    /**
     * Voegt een nieuwe Reservatie toe aan de database.
     *
     * @param reservatie Reservatie die toegevoegd dient te worden.
     * @throws DatabaseException indien de Reservatie reeds bestaat in de
     * database.
     */
    public void addReservatie(Reservatie reservatie) throws DatabaseException;


    /**
     * Past de waarden van een opgeslagen Reservatie aan met de nieuwe waarden
     * van het veranderde object.
     *
     * @param reservatie De Reservatie die gewijzigd werd.
     */
    public void updateReservatie(Reservatie reservatie);


    /**
     * Verwijdert een Reservatie uit de database.
     *
     * @param reservatie De Reservatie die verwijderd moet worden.
     */
    public void removeReservatie(Reservatie reservatie);


    /**
     * Verwijdert een Reservatie uit de database op basis van het id.
     *
     * @param id Het id van de Reservatie die verwijderd moet worden.
     */
    public void removeReservatie(long id);


    /**
     * Geeft een collection terug van alle Reservaties.
     *
     * @return Collection<Reservatie> van alle Reservaties.
     */
    public Collection<Reservatie> getReservaties();


    /**
     * Geeft een collection terug van alle Reservaties op een bepaalde datum.
     *
     * @return Collection<Reservatie> van alle Reservaties op een bepaalde
     * datum.
     */
    public Collection<Reservatie> getReservaties(Calendar datum);


    /**
     * Geeft een collection terug van alle Reservaties op een bepaalde datum en
     * voor een bepaald Item.
     *
     * @return Collection<Reservatie> van alle Reservaties op een bepaalde datum
     * en voor een bepaald Item.
     */
    public Collection<Reservatie> getReservaties(Calendar datum, Item item);
}


