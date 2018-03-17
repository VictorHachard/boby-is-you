package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Glaskani
 */
public class Placement {
    private List<Element> listeContenu;
    
    public Placement(Element e) {
        listeContenu = new ArrayList<>();
        listeContenu.add(new Element(e));
    }
    
    //Getters
    public ArrayList<Element> getListeContenu() {
        return new ArrayList(this.listeContenu);
    }
    
    void addElement(Element e){
        if(this.listeContenu.contains(e))
            return;
        this.listeContenu.add(e);
    }
        
    Element get(TypeElements te) {
        for(Element e:this.listeContenu)
            if(e.getTypeElements()==te)
                return e;
        return null;
    }
        
    boolean canPush() {
        return findRule(Property.PUSH);
    }
        
    /**
     * Dit si l on peut rajouté(metre) un object dans la case
     * @return ton Q.
     */
    boolean canAdd() {
        return findRule(Property.STOP);        
    }
    
    private boolean findRule(Property tr){
        for(Element e:this.listeContenu)
            if(e.getTypeRule().contains(tr))
                return true;
        return false;
    }
    
    List<Element> getAllElement(){
        List<Element> le = new ArrayList<>();
        
        for(Element e:this.listeContenu)
            le.add(new Element(e));
        
        return le;
    }
}
