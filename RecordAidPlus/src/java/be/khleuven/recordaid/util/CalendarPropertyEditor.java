package be.khleuven.recordaid.util;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Provides conversion between a Calendar object and the textual representation in a HTML-form. 
 * 
 * Register an instance of this class in your Spring Controller in 
 * a method with the @InitBinder annotation. 
 * 
 * @author Vincent Ceulemans
 */
public class CalendarPropertyEditor extends PropertyEditorSupport {
    private String format = "yyyy-MM-dd"; 
    
    public CalendarPropertyEditor(){
        super(); 
    }

    public CalendarPropertyEditor(String format) {
        super(); 
        this.format = format;
    }
    
    @Override
    public void setAsText(String text) {
        try {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat fmt = new SimpleDateFormat(format); 
            calendar.setTime(fmt.parse(text)); 
            setValue(calendar);
        } catch (ParseException ex) {
            Logger.getLogger(CalendarPropertyEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public String getAsText(){
        SimpleDateFormat fmt = new SimpleDateFormat(format); 
        Calendar calendar = (Calendar) this.getValue(); 
        String text = fmt.format(calendar.getTime()); 
        return text;
    }
}