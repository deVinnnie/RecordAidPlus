package be.khleuven.recordaid.util.propertyeditors;

import be.khleuven.recordaid.domain.departement.Lector;
import be.khleuven.recordaid.domain.facade.RecordAidDomainFacade;
import java.beans.PropertyEditorSupport;

/**
 * Provides conversion between a Lector object and the textual representation in a HTML-form. 
 * 
 * Register an instance of this class in your Spring Controller in 
 * a method with the @InitBinder annotation. 
 * 
 * @author Vincent Ceulemans
 */
public class LectorPropertyEditor extends PropertyEditorSupport {
    private RecordAidDomainFacade domainFacade; 

    public LectorPropertyEditor() {
        super(); 
    }
   
    public LectorPropertyEditor(RecordAidDomainFacade domainFacade){
        super();
        this.domainFacade = domainFacade; 
    }
    
    @Override
    public void setAsText(String text) {
        Lector lector = domainFacade.getLector(text); 
        if(lector !=null){
            this.setValue(lector); 
        }
    }
    
    @Override
    public String getAsText(){
        Lector lector = (Lector) this.getValue();
        if(lector.getEmailadres()==null){
            return "";
        }
        return lector.getEmailadres();
    }
}