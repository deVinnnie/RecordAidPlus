package be.khleuven.recordaid.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import org.springframework.format.Formatter;

/**
 *
 * @author Vincent Ceulemans
 */
public class CalendarFormatter implements Formatter<Calendar> {
    public CalendarFormatter() {
        super(); 
    }

    @Override
    public String print(Calendar t, Locale locale) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd"); 
        String text = fmt.format(t.getTime()); 
        return text;
    }

    @Override
    public Calendar parse(String string, Locale locale) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd"); 
        calendar.setTime(fmt.parse(string)); 
        return calendar; 
    }
}