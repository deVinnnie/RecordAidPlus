package be.khleuven.recordaid.util;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vincent
 */
public class StringUtilsTest {

    public StringUtilsTest() {
    }

    /**
     * Test of firstLetterToUpperCase method, of class StringUtils.
     */
    @Test
    public void testFirstLetterToUpperCase() {
        String res = StringUtils.firstLetterToUpperCase("test");
        assertEquals("Test", res);
    }

    @Test
    public void testFirstLetterToUpperCase_when_first_letter_is_already_upper_case() {
        String res = StringUtils.firstLetterToUpperCase("Test");
        assertEquals("Test", res);
    }
}