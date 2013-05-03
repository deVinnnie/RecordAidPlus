package be.khleuven.recordaid.util.propertyeditors;

import be.khleuven.recordaid.domain.departement.Departement;
import be.khleuven.recordaid.domain.facade.RecordAidDomainFacade;
import java.beans.PropertyEditorSupport;

/**
 * Provides conversion between a Departement object and the textual representation in a HTML-form. 
 * 
 * Register an instance of this class in your Spring Controller in 
 * a method with the @InitBinder annotation. 
 * 
 * @author Vincent Ceulemans
 */
public class DepartementPropertyEditor extends PropertyEditorSupport {
    private RecordAidDomainFacade domainFacade; 

    public DepartementPropertyEditor() {
        super(); 
    }
   
    public DepartementPropertyEditor(RecordAidDomainFacade domainFacade){
        super();
        this.domainFacade = domainFacade; 
    }
    
    @Override
    public void setAsText(String text) {
        this.setValue(domainFacade.getDepartement(text)); 
    }
    
    @Override
    public String getAsText(){
        Departement departement = (Departement) this.getValue(); 
        return departement.getNaam();
    }
}