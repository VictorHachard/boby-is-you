package model;

import java.util.ArrayList;

/**
 *
 * @author Glaskani
 */
public class Element {
    protected final TypeElements te; //type de la case (mur)
    protected ArrayList<Property> ltr; //liste de regle
    protected final Directions dir;
    
    Element(TypeElements te) {
        this.te = te;
        this.ltr = new ArrayList<>();
        this.dir = Directions.RIGHT;
    }
    
    Element(TypeElements te,Property tr) {
        this.te = te;
        this.ltr = new ArrayList<>();
        this.dir = Directions.RIGHT;
        ltr.add(tr);
    }
    
    protected Element(TypeElements te,Directions dir) {
        this.te = te;
        this.ltr = new ArrayList<>();
        this.dir = dir;
    }
    
    Element(Element e) {
        this.ltr = new ArrayList<>(e.ltr);
        this.te = e.te;
        this.dir = e.dir;
    }
    
    //Getters
    public TypeElements getTypeElements() {
        return this.te;
    }
    
    public ArrayList<Property> getTypeRule() {
        return new ArrayList<>(this.ltr);
    }
    
    void deleteRule(Property tr){
        ltr.remove(tr);
    }
    
    void addRule(Property tr){
        ltr.add(tr);
    }
    
    /**
     * Verifie si la régle est bien dans l'element.
     * @param tr liste des regles.
     * @return True si l'element est dans la liste, False si elle n'y est pas.
     */
    public boolean isRule(Property tr) {
        return ltr.contains(tr);
    }
    
    Directions getDirections(){
        return this.dir;
    }
}
