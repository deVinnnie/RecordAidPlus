package be.khleuven.recordaid.util;

import java.util.Calendar;

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
        if (calendar1.get(Calendar.ERA) == calendar2.get(Calendar.ERA)
                && calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
                && calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR)) {
            isSameDay = true;
        }
        return isSameDay;
    }
}
