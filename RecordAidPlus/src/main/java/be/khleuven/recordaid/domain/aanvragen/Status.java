package be.khleuven.recordaid.domain.aanvragen;

/**
 * Enum die de statussen beschrijft die een aanvraag kan hebben. De status van
 * een aanvraag beschrijft in welk stadium een aanvraag zich bevindt en wat er
 * voor die aanvraag kan gebeuren.
 *
 * @author Koen Verheyden
 * @author Vincent Ceulemans
 */
public enum Status
{
    NIEUW("Nieuw"),
    GOEDGEKEURD("Goedgekeurd"), 
    AFGEHANDELD("Afgehandeld"),
    AFGEKEURD("Afgekeurd");
    
    private String name; 
    
    private Status(){}
    
    private Status(String name){
        this.name = name; 
    }

    public String getName() {
        return name;
    }
}