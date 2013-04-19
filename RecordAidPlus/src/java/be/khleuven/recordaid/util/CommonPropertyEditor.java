package be.khleuven.recordaid.util;

import be.khleuven.recordaid.domain.Identifiable;
import be.khleuven.recordaid.domain.facade.RecordAidDomainFacade;
import java.beans.PropertyEditorSupport;

/**
 * Provides conversion between a Identifyable object and the textual representation in a HTML-form. 
 * 
 * Register an instance of this class in your Spring Controller in 
 * a method with the @InitBinder annotation. 
 * 
 * @author Vincent Ceulemans
 */
public class CommonPropertyEditor extends PropertyEditorSupport {
    private RecordAidDomainFacade domainFacade; 

    public CommonPropertyEditor() {
        super(); 
    }
   
    public CommonPropertyEditor(RecordAidDomainFacade domainFacade){
        super();
        this.domainFacade = domainFacade; 
    }
    
    @Override
    public void setAsText(String text) {
        this.setValue(domainFacade.getCommonDb().find(null, text)); 
    }
    
    @Override
    public String getAsText(){
        Identifiable entity = (Identifiable) this.getValue(); 
        return entity.getId()+"";
    }
}