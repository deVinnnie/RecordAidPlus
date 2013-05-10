package be.khleuven.recordaid.util;

/**
 *
 * @author Vincent Ceulemans
 */
public class StringUtils {
    public static String firstLetterToUpperCase(String string){
        String firstLetter = string.substring(0, 1);
        firstLetter = firstLetter.toUpperCase(); 
        String result = firstLetter + string.substring(1); 
        return result; 
    }
}