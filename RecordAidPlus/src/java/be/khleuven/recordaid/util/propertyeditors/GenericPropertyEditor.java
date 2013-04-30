package be.khleuven.recordaid.util.propertyeditors;

import be.khleuven.recordaid.domain.Identifiable;
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
public class GenericPropertyEditor<T extends Identifiable> extends PropertyEditorSupport {
    private RecordAidDomainFacade domainFacade; 

    public GenericPropertyEditor() {
        super(); 
    }
   
    public GenericPropertyEditor(RecordAidDomainFacade domainFacade){
        super();
        this.domainFacade = domainFacade; 
    }
    
    @Override
    public void setAsText(String text) {
        Long l = Long.parseLong(text);
        T t = (T) domainFacade.getCommonDb().find(Identifiable.class, text); 
        if(t !=null){
            this.setValue(t); 
        }
    }
    
    @Override
    public String getAsText(){
        T t = (T) this.getValue();
        return t.getId()+""; 
    }
}