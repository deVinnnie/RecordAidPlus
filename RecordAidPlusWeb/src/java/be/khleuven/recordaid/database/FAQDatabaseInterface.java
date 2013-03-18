package be.khleuven.recordaid.database;

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
     * Voegt een FAQ toe aan de database.
     *
     * @param faq De FAQ die toegevoegt moet worden.
     */
    public void addFAQ(FAQ faq);


    /**
     * Geeft een opgeslagen FAQ terug op basis van het id.
     *
     * @param id Het id van de terug te geven FAQ.
     * @return FAQ met het id.
     */
    public FAQ findFAQ(Long id);


    /**
     * Past een opgeslagen FAQ aan met de nieuwe waarden van die FAQ.
     *
     * @param faq De FAQ die gewijzigd is en waarvan het opgeslagen object
     * aangepast moet worden.
     */
    public void updateFAQ(FAQ faq);


    /**
     * Verwijdert een FAQ uit de database.
     *
     * @param faq De FAQ die verwijderd moet worden.
     */
    public void removeFAQ(FAQ faq);


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


