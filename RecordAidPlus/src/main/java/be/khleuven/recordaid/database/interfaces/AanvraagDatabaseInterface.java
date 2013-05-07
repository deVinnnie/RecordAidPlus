package be.khleuven.recordaid.database.interfaces;

import be.khleuven.recordaid.domain.aanvragen.*; 
import be.khleuven.recordaid.domain.gebruiker.Gebruiker;
import be.khleuven.recordaid.opnames.*; 
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
    public Collection<AbstractAanvraag> getAanvragen(Status status);

    /**
     * Geeft een collection terug van alle aanvragen die toegewezen zijn aan een
     * bepaalde RecordAid buddy of een kernlid.
     *
     * @param toegewezenLid De gebruiker aan wie de aanvragen toegewezen moeten
     * zijn.
     * @return Een collection<Aanvraag> van aanvragen.
     */
    public Collection<AbstractAanvraag> getAanvragenToegewezenLid(Gebruiker toegewezenLid);
    
    public Collection<MultiPeriodeAanvraag> getAanvragen(Gebruiker begeleider);
    
    public Collection<Opname> getOpnames(OpnameMethode opnameMethode); 
}