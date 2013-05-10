package be.khleuven.recordaid.mvc;

import be.khleuven.recordaid.domain.facade.RecordAidDomainFacade;
import be.khleuven.recordaid.domain.gebruiker.Dossier;
import be.khleuven.recordaid.domain.gebruiker.Gebruiker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Vincent Ceulemans
 */
public abstract class AbstractController {
    @Autowired
    protected RecordAidDomainFacade domainFacade;

    public AbstractController() {
    }

    public AbstractController(RecordAidDomainFacade domainFacade) {
        this(); 
        this.domainFacade = domainFacade;
    }
    
    /**
     * Fetches the dossier of the current user in this session.
     *
     * @return The dossier of the current user.
     */
    protected Dossier getCurrentDossier() {
        Gebruiker gebruiker = (Gebruiker) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Dossier dossier = domainFacade.getDossier(gebruiker);
        return dossier;
    }
}
