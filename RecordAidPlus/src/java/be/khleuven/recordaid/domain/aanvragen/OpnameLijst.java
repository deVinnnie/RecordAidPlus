package be.khleuven.recordaid.domain.aanvragen;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vincent Ceulemans
 */
public class OpnameLijst {
    private List<OpnameMoment> opnameMomenten = new ArrayList<OpnameMoment>(); 

    public OpnameLijst(){}
    
    public OpnameLijst(List<OpnameMoment> opnameMomenten){
        this.setOpnameMomenten(opnameMomenten);
    }
    
    public List<OpnameMoment> getOpnameMomenten() {
        return opnameMomenten;
    }

    public void setOpnameMomenten(List<OpnameMoment> opnameMomenten) {
        this.opnameMomenten = opnameMomenten;
    }
}