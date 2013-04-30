package be.khleuven.recordaid.util.propertyeditors;

import be.khleuven.recordaid.domain.Lector;
import be.khleuven.recordaid.domain.facade.RecordAidDomainFacade;
import be.khleuven.recordaid.domain.gebruiker.Gebruiker;
import java.beans.PropertyEditorSupport;

/**
 * Provides conversion between a Lector object and the textual representation in a HTML-form. 
 * 
 * Register an instance of this class in your Spring Controller in 
 * a method with the @InitBinder annotation. 
 * 
 * @author Vincent Ceulemans
 */
public class GebruikerPropertyEditor extends PropertyEditorSupport {
    private RecordAidDomainFacade domainFacade; 

    public GebruikerPropertyEditor() {
        super(); 
    }
   
    public GebruikerPropertyEditor(RecordAidDomainFacade domainFacade){
        super();
        this.domainFacade = domainFacade; 
    }
    
    @Override
    public void setAsText(String text) {
        Gebruiker gebruiker = domainFacade.getGebruiker(text); 
        if(gebruiker !=null){
            this.setValue(gebruiker); 
        }
    }
    
    @Override
    public String getAsText(){
        Gebruiker gebruiker = (Gebruiker) this.getValue();
        if(gebruiker == null || gebruiker.getEmailadres()==null){
            return "";
        }
        return gebruiker.getEmailadres();
    }
}