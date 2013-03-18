package be.khleuven.recordaid.database;

import be.khleuven.recordaid.domain.Item;
import java.util.Collection;


/**
 * Een interface voor een database die Items bijhoudt.
 *
 * @author Maxime
 */
public interface ItemDatabaseInterface
{
    /**
     * Voegt een Item toe aan de database.
     *
     * @param item Item dat toegevoegd moet worden.
     * @throws DatabaseException indien het item reeds bestaat.
     */
    public void addItem(Item item) throws DatabaseException;


    /**
     * Verwijdert een Item uit de database.
     *
     * @param item Item dat verwijdert moet worden.
     */
    public void removeItem(Item item);


    /**
     * Verwijdert een Item uit de database op basis van de naam.
     *
     * @param naam String met de naam van het Item dat verwijdert moet worden.
     */
    public void removeItem(String naam);


    /**
     * Geeft een Item uit de database terug op basis van de naam van het Item.
     *
     * @param naam String met de naam van het Item.
     * @return Item in de database met deze naam.
     */
    public Item findItem(String naam);


    /**
     * Wijzigt de gegevens van een Item dat is opgeslagen in de database met
     * aangepaste gegevens van het Item.
     *
     * @param oudeNaam De oude naam van het Item, deze kan in de nieuwe versie
     * gewijzigd zijn.
     * @param item Het nieuwe Item object met de gewijzigde gegevens.
     */
    public void updateItem(String oudeNaam, Item item);


    /**
     * Geeft een collection terug van alle Items in de database.
     *
     * @return Collection<Item> van alle Items in de database.
     */
    public Collection<Item> getItems();
}


