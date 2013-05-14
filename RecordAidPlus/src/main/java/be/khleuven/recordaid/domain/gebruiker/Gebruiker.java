package be.khleuven.recordaid.domain.gebruiker;

import be.khleuven.recordaid.util.StringUtils;
import be.khleuven.recordaid.util.ValidatieCodeGenerator;
import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Gebruiker is een klasse die alle informatie bijhoudt over een bepaalde
 * gebruiker, en aan de hand van het type gebruiker wordt ook bepaald wat deze
 * gebruiker wel en niet kan.
 *
 * @author Hannes, Vincent
 */
@Entity
public class Gebruiker implements Serializable, UserDetails {
    @ElementCollection(targetClass = Rollen.class)
    @Enumerated(EnumType.STRING)
    private List<Rollen> rollen = new ArrayList<Rollen>();
    /*EnumType denotes the mapping between objects and database. 
     Choosing String means the value of the enum will be stored as string 
     instead of the default integer. 
     */
    
    @Id
    private String emailadres;
    
    private String voornaam;
    private String achternaam;
    
    /*@Column(length=70)    */
    private String wachtwoordHash;
    private String validatieCode;
    private boolean gevalideerd = false;
    
    /**
     * Constructor met parameters, dit is de constructor die gebruikt dient te
     * worden om een nieuwe Gebruiker aan te maken.
     *
     * @param rol De rol van de gebruiker, kan één van de mogelijke rollen
     * bepaald in de Enum Rollen zijn.
     * @param emailadres Het emailadres van de gebruiker.
     * @param naam De naam van de gebruiker.
     * @param voornaam De voornaam van de gebruiker.
     * @param wachtwoord Het wachtwoord van de gebruiker.
     */
    public Gebruiker(Rollen rol, String emailadres, String naam, String voornaam, String wachtwoord) {
        this(); 
        this.rollen.add(rol);
        this.emailadres = emailadres;
        this.achternaam = naam;
        this.voornaam = voornaam;
        this.wachtwoordHash = wachtwoord;
    }

    public Gebruiker() {
        this.validatieCode = ValidatieCodeGenerator.generateValidatieCode();
    }
    
    public Gebruiker(String emailadres){
        this.setEmailadres(emailadres);
        String[] split = emailadres.split("@"); //Results in voornaam.achternaam|khleuven.be
        String[] split2 = split[0].split("\\."); //Results in voornaam | achternaam
        this.setVoornaam(StringUtils.firstLetterToUpperCase(split2[0])); 
        String achternaam=""; 
        for(int i =1; i<split2.length; i++){
            if(i > 1){
                //Do not add space for the first part. 
                achternaam+=" "; 
            }
            achternaam += StringUtils.firstLetterToUpperCase(split2[i]); 
        }
        this.setAchternaam(achternaam); 
    }

    /**
     * Methode om een gebruiker te valideren op basis van een validatieCode. De
     * methode geeft een boolean terug om aan te geven of de code juist is.
     * Indien juist zal de validatie boolean instantievariabele aangepast
     * worden.
     *
     * @param validatieCode De validatiecode die vergeleken moet worden met de
     * code aangemaakt bij initialisatie van het object.
     * @return True indien de gebruiker nu gevalideerd is, false indien de
     * validatiecodes niet overeen kwamen.
     */
    public boolean valideer(String validatieCode) {
        boolean valid = false;

        if (validatieCode.equals(this.validatieCode)) {
            valid = true;
            this.gevalideerd = true;
        }

        return valid;
    }

    /**
     * Methode om een gebruiker te valideren zonder de validatieCode. Deze
     * methode is bedoelt zo dat een admin manueel een gebruiker kan valideren.
     */
    public void valideer() {
        this.validatieCode = "***";
        this.gevalideerd = true;
    }

    /**
     * Methode om een gebruiker te devalideren. Deze methode is bedoelt zodat
     * een admin een gebruiker manueel kan devalideren, bijvoorbeeld een
     * gebruiker die niet meer toegelaten mag worden op de website.
     */
    public void deValideer() {
        this.validatieCode = "***";
        this.gevalideerd = false;
    }

    // <editor-fold defaultstate="collapsed" desc="Setters">
    /**
     * Setter om de rol van de gebruiker te setten.
     *
     * @param rol De rol kan één van de rollen zoals bepaald in Enum Rollen
     * zijn.
     */
    public void addRol(Rollen rol) {
        this.rollen.add(rol);
    }

    /**
     * Setter om de naam van de gebruiker te veranderen.
     *
     * @param naam String die de aangepaste naam van de gebruiker voorstelt.
     */
    public void setNaam(String naam) {
        this.achternaam = naam;
    }

    public void setEmailadres(String emailadres) {
        this.emailadres = emailadres;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    /**
     * Setter om de voornaam van de gebruiker te veranderen.
     *
     * @param voornaam String die de aangepaste voornaam van de gebruiker
     * voorstelt.
     */
    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    /**
     * Setter om het wachtwoord van de gebruiker te veranderen.
     *
     * @param wachtwoord String die het aangepaste wachtwoord van de gebruiker
     * voorstelt.
     */
    public void setWachtwoordHash(String wachtwoordHash) {
        this.wachtwoordHash = wachtwoordHash;
    }

    public void setRollen(List<Rollen> rollen) {
        this.rollen = rollen;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getters">
    /**
     * Geeft het emailadres van de gebruiker terug.
     *
     * @return String die het emailadres van de gebruiker teruggeeft.
     */
    public String getEmailadres() {
        return emailadres;
    }

    public String getValidatieCode() {
        return this.validatieCode;
    }

    /**
     * Geeft weer of het emailadres van een gebruiker reeds gevalideerd is.
     *
     * @return True indien het emailadres gevalideerd is, false indien het niet
     * gevalideerd is.
     */
    public boolean isGevalideerd() {
        return gevalideerd;
    }

    /**
     * Geeft de naam van de gebruiker terug.
     *
     * @return String die de achternaam van de gebruiker is.
     */
    public String getAchternaam() {
        return achternaam;
    }

    /**
     * Geeft de rol van de gebruiker terug.
     *
     * @return Eén van de mogelijke rollen die de gebruiker is zoals bepaalt in
     * de Enum Rollen.
     */
    public List<Rollen> getRollen() {
        return rollen;
    }

    /**
     * Geeft de voornaam van de gebruiker terug.
     *
     * @return String die de voornaam van de gebruiker is.
     */
    public String getVoornaam() {
        return voornaam;
    }

    /**
     * Geeft het wachtwoord van de gebruiker terug.
     *
     * @return String die het wachtwoord van de gebruiker is.
     */
    //@Length(max=60)
    public String getWachtwoordHash() {
        return wachtwoordHash;
    }

    /**
     * Returns the name and surname combined.
     */
    public String getVolledigeNaam() {
        return this.voornaam + " " + this.achternaam;
    }

    public boolean isGeinteresseerd() {
        return this.rollen.contains(Rollen.GEINTERESSEERDE); 
    }

    public void setGeinteresseerd(boolean geinteresseerd) {
        if(geinteresseerd){
            this.addRol(Rollen.GEINTERESSEERDE);
        }
        else{
            if(this.isGeinteresseerd()){
                this.rollen.remove(Rollen.GEINTERESSEERDE);
            }
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Overridden methods">
    /**
     * Methode die gebruikt wordt om een text representatie van het object te
     * verkrijgen, geeft een aantal instantievariabelen terug.
     *
     * @return String die een text representatie van het object is.
     */
    @Override
    public String toString() {
        return this.voornaam + " " + this.achternaam + " (" + this.emailadres + "), " + this.rollen;
    }

    /**
     * Methode die bepaalt wanneer 2 objecten hetzelfde zijn op basis van de
     * waarden van de instantievariabelen.
     *
     * @param obj Object dat met dit object vergeleken moet worden.
     * @return Yes indien ze hetzelfde zijn, false indien ze niet hetzelfde
     * zijn.
     */
    @Override
    public boolean equals(Object object) {
        boolean equals = false;

        if (object != null && object instanceof Gebruiker) {
            Gebruiker gebruiker = (Gebruiker) object;

            equals = gebruiker.getEmailadres().equals(this.emailadres);
        }

        return equals;
    }

    /**
     * Methode die gebruikt wordt in bepaalde Collection<> klassen.
     *
     * @return integer gebaseerd op waarden van de instantievariabelen.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (this.emailadres != null ? this.emailadres.hashCode() : 0);
        return hash;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Implemented UserDetails methods">
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        for (Rollen rol : this.rollen) {
            GrantedAuthority a = new SimpleGrantedAuthority(rol.name());
            list.add(a);
        }
        return list;
    }

    @Override
    public String getPassword() {
        return this.wachtwoordHash;
    }

    @Override
    public String getUsername() {
        return this.emailadres;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isGevalideerd();
    }
    // </editor-fold> 
}