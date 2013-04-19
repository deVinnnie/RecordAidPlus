package be.khleuven.recordaid.database.interfaces;

import be.khleuven.recordaid.database.DatabaseException;
import be.khleuven.recordaid.database.DatabaseException;
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