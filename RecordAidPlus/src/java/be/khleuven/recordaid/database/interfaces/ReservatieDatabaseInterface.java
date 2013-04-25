package be.khleuven.recordaid.database.interfaces;

import be.khleuven.recordaid.domain.items.*;
import java.util.*;

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

    public Collection<ReservatieDag> getReservaties(Item item);
    
    /**
     * Geeft een collection terug van alle Reservaties op een bepaalde datum en
     * voor een bepaald Item.
     *
     * @return Collection<Reservatie> van alle Reservaties op een bepaalde datum
     * en voor een bepaald Item.
     */
    public Collection<Reservatie> getReservaties(Calendar datum, Item item);
    
    public Collection<Reservatie> getReservaties(Calendar start, Calendar end, Item item);  

    public Collection<Reservatie> getReservaties(Calendar start, Calendar end);
}