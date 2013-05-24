package be.khleuven.recordaid.domain.gebruiker;

/**
 * Enum die de mogelijke rollen beschrijft die een gebruiker kan hebben. 
 * De rol van de gebruiker bepaalt wat een gebruiker te zien 
 * krijgt op de website en wat een gebruiker mag en kan veranderen.
 *
 * @author Hannes
 */
public enum Rollen
{
    STUDENT ("Student"),
    LEERKRACHT ("Leerkracht"),
    BEGELEIDER ("Begeleider"), 
    OPLEIDINGSHOOFD ("Opleidingshoofd"),
    BUDDY ("Buddy"),
    KERNLID ("Kernlid"),
    GEINTERESSEERDE ("Geïnteresseerde"), 
    ADMIN ("Admin");
    
    private String name; 
    
    private Rollen(){}
    
    private Rollen(String name){
        this.name = name; 
    }

    public String getName() {
        return name;
    }
}