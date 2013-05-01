package be.khleuven.recordaid.database.interfaces;

import be.khleuven.recordaid.domain.aanvragen.*; 
import be.khleuven.recordaid.domain.gebruiker.Gebruiker;
import java.util.Collection;


/**
 * Een interface voor een database die aanvragen bijhoudt.
 *
 * @author Koen
 */
public interface AanvraagDatabaseInterface{
    /**
     * Returnt alle aanvragen met de megegeven status.
     *
     * @param status Een enum waarde van Status die de status van een aanvraag
     * bepaald.
     * @return Een collection<Aanvraag> van aanvragen.
     */
    public Collection<DagAanvraag> getAanvragen(Status status);

    /**
     * Geeft een collection terug van alle aanvragen die toegewezen zijn aan een
     * bepaalde RecordAid buddy of een kernlid.
     *
     * @param toegewezenLid De gebruiker aan wie de aanvragen toegewezen moeten
     * zijn.
     * @return Een collection<Aanvraag> van aanvragen.
     */
    public Collection<DagAanvraag> getAanvragenToegewezenLid(Gebruiker toegewezenLid);
}