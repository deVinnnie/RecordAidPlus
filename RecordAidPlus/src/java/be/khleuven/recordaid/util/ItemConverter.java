package be.khleuven.recordaid.util;

import be.khleuven.recordaid.domain.facade.RecordAidDomainFacade;
import be.khleuven.recordaid.domain.items.Item;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author Vincent Ceulemans
 */
public class ItemConverter implements Formatter<Item> {

    private RecordAidDomainFacade domainFacade;

    public ItemConverter() {
        super(); 
    }

    public ItemConverter(RecordAidDomainFacade domainFacade) {
        this.domainFacade = domainFacade;
    }

    @Override
    public String print(Item t, Locale locale) {
        if (t.getId() <= 0) {
            throw new IllegalArgumentException("Item heeft geen ID");
        }
        return "" + t.getId();
    }

    @Override
    public Item parse(String string, Locale locale) throws ParseException {
        try {
            Long id = Long.parseLong(string);
            Item item = domainFacade.findItem(id);
            return item;
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Geen geldige id.", ex);
        }
    }
}