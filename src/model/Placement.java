package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Glaskani
 */
public class Placement {
    
    private List<Element> listeContenu;
    
    /**
     * 
     * @param e Element
     */
    public Placement(Element e) {
        listeContenu = new ArrayList<>();
        listeContenu.add(new Element(e));
    }
    
    //Getters
 
    /**
     * Revois une liste d'Element.
     * @return ArrayListElement
     */
    public ArrayList<Element> getListeContenu() {
        return new ArrayList(this.listeContenu);
    }
    
    /**
     * Ajoute e si il n'est pas déja dans la liste d'Element
     * @param e Element
     */
    void addElement(Element e){
        if(this.listeContenu.contains(e))
            return;
        this.listeContenu.add(e);
    }
    
    /**
     * Revois l'Element ??
     * @param te TypeElements
     * @return Element ou null
     */
    Element get(TypeElements te) {
        for(Element e:this.listeContenu)
            if(e.getTypeElements()==te)
                return e;
        return null;
    }
    
    /**
     * Revois true si on peux push un element.
     * @return true ou false
     */
    boolean canPush() {
        return findRule(Property.PUSH);
    }
        
    /**
     * Revois true si on peux add un element.
     * @return true ou false
     */
    boolean canAdd() {
        return findRule(Property.STOP);        
    }
    
    /**
     * Revois true si tr est bien dans la liste des régles de l'element.
     * @param tr Property
     * @return true ou false
     */
    private boolean findRule(Property tr){
        for(Element e:this.listeContenu)
            if(e.getTypeRule().contains(tr))
                return true;
        return false;
    }
    
    /**
     * 
     * @return List<Element>
     */
    List<Element> getAllElement(){
        List<Element> le = new ArrayList<>();
        
        for(Element e:this.listeContenu)
            le.add(new Element(e));
        
        return le;
    }
}
