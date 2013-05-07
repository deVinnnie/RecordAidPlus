package be.khleuven.recordaid.util;

/**
 *
 * @author Vincent Ceulemans
 */
public class Boodschap {
    private String message;
    private String type; 

    public Boodschap(){}
    
    public Boodschap(String message, String type) {
        this.message = message;
        this.type = type;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    //</editor-fold>
}