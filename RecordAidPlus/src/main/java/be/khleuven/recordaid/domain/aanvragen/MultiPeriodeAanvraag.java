package be.khleuven.recordaid.domain.aanvragen;

import be.khleuven.recordaid.domain.departement.Lector;
import be.khleuven.recordaid.domain.departement.Departement;
import be.khleuven.recordaid.domain.*;
import be.khleuven.recordaid.domain.gebruiker.*; 
import be.khleuven.recordaid.util.TimeSpan;
import java.text.SimpleDateFormat;
import javax.persistence.*; 
import java.util.*; 
import org.springframework.util.AutoPopulatingList;

/**
 *
 * @author Vincent Ceulemans
 */
@Entity
public class MultiPeriodeAanvraag extends AbstractAanvraag{
    @OneToOne
    private TimeSpan periode; 
    
    @OneToOne
    private Gebruiker begeleider; 
    
    @OneToMany
    private List<Lector> lectoren = new AutoPopulatingList<Lector>(Lector.class); 

    public MultiPeriodeAanvraag() {
        super(); 
    }

    public MultiPeriodeAanvraag(Dossier dossier, Departement departement) {
        super(dossier, departement);
    }

    public TimeSpan getPeriode() {
        return periode;
    }

    public void setPeriode(TimeSpan periode) {
        this.periode = periode;
    }

    public Gebruiker getBegeleider() {
        return begeleider;
    }

    public void setBegeleider(Gebruiker begeleider) {
        this.begeleider = begeleider;
    }

    public List<Lector> getLectoren() {
        return lectoren;
    }

    public void setLectoren(List<Lector> lectoren) {
        this.lectoren = new AutoPopulatingList<Lector>(lectoren, Lector.class); 
    }

    @Override
    public Calendar getDefaultOpnameMomentDag() {
        return (Calendar) this.periode.getBeginTime().clone(); 
    }
    
    @Override
    public String getTijdsbepaling() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String result = dateFormat.format(getPeriode().getBeginTime().getTime()) + " <->" + 
                dateFormat.format(getPeriode().getEndTime().getTime());
        return result; 
    }
}