package be.khleuven.recordaid.database.interfaces;

import be.khleuven.recordaid.database.DatabaseException;
import be.khleuven.recordaid.database.DatabaseException;
import be.khleuven.recordaid.domain.aanvragen.Aanvraag;
import be.khleuven.recordaid.domain.Gebruiker;
import be.khleuven.recordaid.domain.aanvragen.Status;
import java.util.Collection;


/**
 * Een interface voor een database die aanvragen bijhoudt.
 *
 * @author Koen
 */
public interface AanvraagDatabaseInterface
{
    /**
     * Voeg een aanvraag toe aan de database.
     *
     * @param aanvraag Het aanvraag object om toe te voegen aan de database.
     * @see aanvraag
     */
    public void addAanvraag(Aanvraag aanvraag);


    /**
     * Vind een aanvraag op basis van het ID. Geeft NULL terug de aanvraag niet
     * gevonden is.
     *
     * @param aanvraagID Het ID van de aanvraag om op te zoeken.
     * @see aanvraag
     */
    public Aanvraag findAanvraag(long aanvraagID);


    /**
     * Wijzig de megegeven aanvraag, gooit een exception als de aanvraag niet
     * gevonden is.
     *
     * @param aanvraag Het ID van de aanvraag om te wijzigen.
     * @see aanvraag
     */
    public void updateAanvraag(Aanvraag aanvraag) throws DatabaseException;


    /**
     * Verwijder een aanvraag op basis van het ID van het object.
     *
     * @param aanvraagID Het ID van de aanvraag om te verwijderen.
     * @see aanvraag
     */
    public void removeAanvraag(long aanvraagID);


    /**
     * Verwijder een aanvraag op basis van het object.
     *
     * @param aanvraag Een aanvraag object om te verwijderen.
     * @see aanvraag
     */
    public void removeAanvraag(Aanvraag aanvraag);


    /**
     * Returnt alle aanvragen.
     *
     * @return Een collection<Aanvraag> van aanvragen.
     */
    public Collection<Aanvraag> getAanvragen();


    /**
     * Returnt alle aanvragen met de megegeven status.
     *
     * @param status Een enum waarde van Status die de status van een aanvraag
     * bepaald.
     * @return Een collection<Aanvraag> van aanvragen.
     */
    public Collection<Aanvraag> getAanvragen(Status status);


    /**
     * Returnt alle aanvragen van de aanvrager.
     *
     * @param user De aanvrager van de aanvraag.
     * @return Een lijst van alle aanvragen van de meegegeven aanvrager.
     * @see Een collection<Aanvraag> van aanvragen.
     */
    public Collection<Aanvraag> getAanvragenAanvrager(Gebruiker user);


    /**
     * Geeft een collection terug van alle aanvragen die toegewezen zijn aan een
     * bepaalde RecordAid buddy of een kernlid.
     *
     * @param toegewezenLid De gebruiker aan wie de aanvragen toegewezen moeten
     * zijn.
     * @return Een collection<Aanvraag> van aanvragen.
     */
    public Collection<Aanvraag> getAanvragenToegewezenLid(Gebruiker toegewezenLid);
}


