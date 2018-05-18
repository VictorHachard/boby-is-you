package common.model;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Glaskani
 */
public class Element {
    
    protected final TypeElement te;
    protected ArrayList<Property> ltr; //liste de regle
    protected Directions dir;
    
    /**
     * 
     * @param te TypeElement
     */
    public Element(TypeElement te) {
        this.te = te;
        this.ltr = new ArrayList<>();
        this.dir = Directions.RIGHT;
    }
    
    /**
     * 
     * @param te TypeElement
     * @param tr Property
     */
    Element(TypeElement te,Property tr) {
        this.te = te;
        this.ltr = new ArrayList<>();
        this.dir = Directions.RIGHT;
        ltr.add(tr);
    }
    
    /**
     * 
     * @param te
     * @param tr
     * @param dir 
     */
    Element(TypeElement te,Property tr,Directions dir) {
        this.te = te;
        this.ltr = new ArrayList<>();
        this.dir = dir;
        ltr.add(tr);
    }
    
    /**
     * 
     * @param te TypeElement
     * @param dir Directions
     */
    protected Element(TypeElement te,Directions dir) {
        this.te = te;
        this.ltr = new ArrayList<>();
        this.dir = dir;
    }
    
    /**
     * 
     * @param e 
     */
    Element(Element e) {
        this.ltr = new ArrayList<>(e.ltr);
        this.te = e.te;
        this.dir = e.dir;
    }
    
    /**
     * Verifie si deux Element sont bien égale, la verification ce fait sur :
     * le TypeElement et la dir et ingore la liste des regles.
     * @param obj Element à comparée
     * @return true si les element sont bien egales, false sinon
     */
    @Override
    public boolean equals(Object obj){
        if (obj instanceof Element) {
            Element e = (Element) obj;
            return (e.getTypeElement()==this.te&&e.getDirections()==this.dir);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 42 + Objects.hashCode(this.te);
        hash = 42 + Objects.hashCode(this.ltr);
        hash = 42 + Objects.hashCode(this.dir);
        return hash;
    }
    
    /**
     * Revois le type de l'element.
     * @return TypeElement
     */
    public TypeElement getTypeElement() {
        return this.te;
    }
    
    /**
     * Revois le type de l'element.
     * @return TypeElement
     */
    public TypeElement getText() {
        return this.te.getText();
    }
    
    /**
     * Revois le type de l'element.
     * @return TypeElement
     */
    public Type getType() {
        return this.te.getType();
    }
    
    /**
     * Revois une liste des régles de l'element.
     * @return ArrayListProperty
     */
    public ArrayList<Property> getTypeRule() {
        return new ArrayList<>(this.ltr);
    }
    
    /**
     * Revois la dir de l'element.
     * @return dir
     */
    Directions getDirections(){
        return this.dir;
    }
    
    /**
     * Revois la dir de l'element.
     */
    void setDirections(Directions dir){
        this.dir = dir;
    }
    
    /**
     * Supprime la régle tr de la liste des régles de l'element.
     * @param tr Property
     */
    void deleteRule(Property tr){
        ltr.remove(tr);
    }
    
    /**
     * Ajoute la régle tr de la liste des régles de l'element.
     * @param tr Property
     */
    void addRule(Property tr){
        for (Property p:ltr)
            if (p==tr)
                return;
        ltr.add(tr);
    }
    
    /**
     * revois true si la régle est bien dans l'element.
     * @param listRule Property
     * @return true ou false.
     */
    boolean isRule(Property listRule) {
        return ltr.contains(listRule);
    }    
}
