package be.khleuven.recordaid.mvc;

import be.khleuven.recordaid.opnames.OpnameMoment;
import be.khleuven.recordaid.domain.gebruiker.Dossier;
import be.khleuven.recordaid.domain.gebruiker.Gebruiker;
import be.khleuven.recordaid.database.jpa.*; 
import be.khleuven.recordaid.domain.aanvragen.*;
import be.khleuven.recordaid.domain.departement.*; 
import be.khleuven.recordaid.domain.facade.RecordAidDomainFacade;
import be.khleuven.recordaid.util.TimeSpan;
import java.util.Calendar;
import javax.persistence.EntityManager;
import org.junit.Test;
import org.junit.Before;
import static org.mockito.Mockito.*;
import org.springframework.ui.ModelMap;

/**
 *
 * @author vincent
 */
public class AanvragenControllerTest {
    private AanvragenController aanvragenController; 
    private RecordAidDomainFacade domainFacade; 
    
    public AanvragenControllerTest() {
    }
    
    //@Before
    public void injectMockEntityManager() {
        /*EntityManager entityManager = mock(EntityManager.class);
        
        domainFacade = new RecordAidDomainFacade(
                new JpaCommonDao(entityManager),
                new JpaFAQDao(entityManager), 
                new JpaGebruikerDao(entityManager), 
                new JpaAanvragenDao(entityManager), 
                new JpaReservatieDao(entityManager), 
                new JpaDepartementDao(entityManager), 
                new JpaMailDao(entityManager)
                ); 
        aanvragenController = new AanvragenController(domainFacade); */
    }

    /**
     * Test of addNieuweOpname method, of class AanvragenController.
     */
    @Test
    public void testAddNieuweOpname() throws Exception { 
     /*   Gebruiker dummy = new Gebruiker(); 
        Dossier dossier = new Dossier(dummy); 
        Departement departement = new Departement("G&T", true);
        
        Calendar lesDatum = Calendar.getInstance(); 
        lesDatum.set(2010, 01, 05);
        
        DagAanvraag aanvraag = new DagAanvraag(dossier, departement, lesDatum); 
        aanvragenController.addNieuweAanvraag(aanvraag, null, new ModelMap()); 
        
        OpnameMoment opnameMoment = new OpnameMoment("OOD", new Lokaal("310", departement), "2", 
                new TimeSpan(lesDatum, lesDatum), new Lector("lector@khleuven.be")); 
        
        //aanvragenController.addNieuwOpnameMoment(opnameMoment, null, aanvraag, "Gereed");*/
    }
}