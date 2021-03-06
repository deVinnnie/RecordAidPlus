package be.khleuven.recordaid.mvc; 

import be.khleuven.recordaid.domain.gebruiker.*; 
import be.khleuven.recordaid.domain.*;
import be.khleuven.recordaid.domain.facade.RecordAidDomainFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService{
    @Autowired
    private RecordAidDomainFacade domainFacade; 
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(15); 
    
    public MyUserDetailsService(){
    }
    
    public MyUserDetailsService(RecordAidDomainFacade domainFacade){
        this.domainFacade = domainFacade; 
    }
    
    @Override
    public UserDetails loadUserByUsername(String username){
        // assumes that Gebruiker class implements UserDetails
        UserDetails u =  domainFacade.getGebruiker(username);
        return u;
    }
    
    public void changePassword(Gebruiker gebruiker, String wachtwoord){
        String hash = this.passwordEncoder.encode(wachtwoord);
        gebruiker.setWachtwoordHash(hash);
        gebruiker.setForcePasswordChange(false);
        domainFacade.edit(gebruiker);
    }
    
    public void createUser(Gebruiker gebruiker, String wachtwoord, String wachtwoordConfirmation)throws DomainException{
        Rollen rol = Rollen.STUDENT; 
        if (gebruiker.getEmailadres().matches("^.+@khleuven.be$")) {
            rol = Rollen.LEERKRACHT;
        }
        gebruiker.addRol(rol);
        
        if(!wachtwoord.equals(wachtwoordConfirmation)){
            throw new DomainException("Uw wachtwoorden komen niet overeen."); 
        }
        
        String hash = this.passwordEncoder.encode(wachtwoord);
        
        gebruiker.setWachtwoordHash(hash);
        domainFacade.addGebruiker(gebruiker);
    }
    
    public void createUser(Gebruiker gebruiker, String wachtwoord, Gebruiker begeleider) throws DomainException{
        Rollen rol = Rollen.STUDENT; 
        if (gebruiker.getEmailadres().matches("^.+@khleuven.be$")) {
            rol = Rollen.LEERKRACHT;
        }
        gebruiker.addRol(rol);
        
        String hash = this.passwordEncoder.encode(wachtwoord);
        
        gebruiker.setWachtwoordHash(hash);
        gebruiker.setForcePasswordChange(true);
        domainFacade.addGebruiker(gebruiker, begeleider);
    }
}