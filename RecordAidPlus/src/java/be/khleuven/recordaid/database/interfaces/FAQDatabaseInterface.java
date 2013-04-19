package be.khleuven.recordaid.database.interfaces;

import be.khleuven.recordaid.domain.FAQ;
import java.util.Collection;


/**
 * Een interface voor een database die FAQs bijhoudt.
 *
 * @author Maxime Van den Kerkhof
 */
public interface FAQDatabaseInterface
{
    /**
     * Geeft een collection van alle FAQs in de database terug.
     *
     * @return Collection<FAQ> van alle FAQs in de database.
     */
    public Collection<FAQ> getFAQs();


    /**
     * Geeft een collection van alle FAQs in de database terug die relevant zijn
     * voor andere gebruikers.
     *
     * @return Collection<FAQ> van alle FAQs in de database die relevant zijn
     * voor andere gebruikers.
     */
    public Collection<FAQ> getRelevanteFAQs();


    /**
     * Geeft een collection van alle FAQs in de database die beantwoord zijn
     * terug.
     *
     * @return Collection<FAQ> van alle FAQs in de database die beantwoord zijn.
     */
    public Collection<FAQ> getBeantwoordeFAQs();


    /**
     * Geeft een collection van alle FAQs in de database die niet beantwoord
     * zijn terug.
     *
     * @return Collection<FAQ> van alle FAQs in de database die niet beantwoord
     * zijn.
     */
    public Collection<FAQ> getNietBeantwoordeFAQs();
}