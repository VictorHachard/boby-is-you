package model;

/**
 *
 * @author Glaskani
 */
public class Unplayable extends Element {
    
    public Unplayable() {
        super(TypeElements.WALLINJOUABLE,Directions.RIGHT);
        super.ltr.add(Property.STOP);
    }
    
    @Override
    void deleteRule(Property tr){}
    
    @Override
    void addRule(Property tr){}
    
}
