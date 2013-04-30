package be.khleuven.recordaid.domain.aanvragen;

import be.khleuven.recordaid.opnames.OpnameMoment;
import be.khleuven.recordaid.domain.gebruiker.Dossier;
import be.khleuven.recordaid.domain.*; 
import be.khleuven.recordaid.util.CalendarUtils;
import java.util.*; 
import javax.persistence.*; 

/**
 * Een Aanvraag beschrijft de aanvraag van een gebruiker om een bepaalde les op
 * te nemen.
 *
 * @author Hannes
 */
@Entity
public class DagAanvraag extends AbstractAanvraag
{   
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar lesDatum = Calendar.getInstance(); 
    
    private boolean voorHeleReeks = false; 
    
    /**
     * Constructor zonder parameters, nodig voor Java Persistency.
     */
    public DagAanvraag(){}

    /**
     * Constructor met parameters, dit is de constructor die gebruikt dient te
     * worden om een nieuwe Aanvraag aan te maken.
     *
     * @param aanvrager Gebruiker die de aanvraag indient.
     * @param lesDatum Datum waarop de les plaatvindt.
     */
    public DagAanvraag(Dossier dossier, Departement departement)
    {
        this(dossier, departement,Calendar.getInstance()); 
    }
    
    public DagAanvraag(Dossier dossier, Departement departement, Calendar lesDatum)
    {
        super(dossier, departement); 
        this.lesDatum = lesDatum; 
    }

    /**
     *
     * @return Datum waarop de les plaatvindt.
     */
    public Calendar getLesDatum()
    {
        return this.lesDatum;
    }

    public void setLesDatum(Calendar lesDatum) {
        this.lesDatum = lesDatum;
    }

    public boolean isVoorHeleReeks() {
        return voorHeleReeks;
    }

    public void setVoorHeleReeks(boolean voorHeleReeks) {
        this.voorHeleReeks = voorHeleReeks;
    }
    
    @Override
    public void addOpnameMoment(OpnameMoment opnameMoment) throws DomainException{
        if(!(CalendarUtils.isSameDay(lesDatum, opnameMoment.getBeginTijdstip())
                && CalendarUtils.isSameDay(lesDatum, opnameMoment.getEindTijdstip()))){
            throw new DomainException("Opamemoment valt niet op zelfde dag als aanvraag."); 
        }
        super.addOpnameMoment(opnameMoment);
    }

    @Override
    public Calendar getDefaultOpnameMomentDag() {
        return (Calendar) this.lesDatum.clone(); 
    }
}