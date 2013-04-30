package be.khleuven.recordaid.util.propertyeditors;

import be.khleuven.recordaid.opnames.OpnameMethode;
import be.khleuven.recordaid.domain.facade.RecordAidDomainFacade;
import java.beans.PropertyEditorSupport;

/**
 * Provides conversion between a OpnameMoment object and the textual representation in a HTML-form. 
 * 
 * Register an instance of this class in your Spring Controller in 
 * a method with the @InitBinder annotation. 
 * 
 * @author Vincent Ceulemans
 */
public class OpnameMethodePropertyEditor extends PropertyEditorSupport {
    private RecordAidDomainFacade domainFacade; 

    public OpnameMethodePropertyEditor() {
        super(); 
    }
   
    public OpnameMethodePropertyEditor(RecordAidDomainFacade domainFacade){
        super();
        this.domainFacade = domainFacade; 
    }
 
    @Override
    public void setAsText(String text) {
        OpnameMethode opnameMethode = null;
        try {
            Long id = Long.parseLong(text);
            opnameMethode = domainFacade.findOpnameMethode(id);
        } catch (NumberFormatException ex) {
        }
        this.setValue(opnameMethode);
    }
    
    @Override
    public String getAsText(){
        if(this.getValue()!=null){
            OpnameMethode opnameMethode = (OpnameMethode) this.getValue(); 
            return opnameMethode.getId().toString();
        }
        else{
            return ""; 
        }
    }
}