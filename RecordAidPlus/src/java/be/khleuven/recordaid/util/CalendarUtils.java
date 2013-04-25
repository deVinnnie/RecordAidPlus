package be.khleuven.recordaid.util;

import java.util.Calendar;
import static java.util.Calendar.*;

/**
 *
 * @author Vincent Ceulemans
 */
public class CalendarUtils {
    /**
     * <p>Checks if two calendars represent the same day (ignoring time)</p>
     *
     * @param calendar1 The first calendar
     * @param calendar2 The second calendar
     * @return true if both represent the same day
     */
    public static boolean isSameDay(Calendar calendar1, Calendar calendar2) {
        if (calendar1 == null || calendar2 == null) {
            return false; 
        }

        boolean isSameDay = false;
        if (calendar1.get(ERA) == calendar2.get(ERA)
                && calendar1.get(YEAR) == calendar2.get(YEAR)
                && calendar1.get(DAY_OF_YEAR) == calendar2.get(DAY_OF_YEAR)) {
            isSameDay = true;
        }
        return isSameDay;
    }
    
    /**
     * 
     * <p>Remove information about time from the given Calendar object, leaving only the date information. </p>
     * 
     * @param calendar 
     * @return Trimmed calendar. 
     */
    public static Calendar trim(Calendar calendar){
         calendar.set(HOUR_OF_DAY, 0);
         calendar.set(MINUTE, 0);
         calendar.set(SECOND, 0);
         calendar.set(MILLISECOND, 0);
         return calendar; 
    }
}
