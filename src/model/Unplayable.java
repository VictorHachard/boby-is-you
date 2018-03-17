package model;

/**
 *
 * @author Glaskani
 */
public class Unplayable extends Element {
    
    /**
     * 
     */
    public Unplayable() {
        super(TypeElements.WALLINJOUABLE,Directions.RIGHT);
        super.ltr.add(Property.STOP);
    }
    
    @Override
    /**
     * 
     * @param tr Property
     */
    void deleteRule(Property tr){}
    
    @Override
    /**
     * 
     * @param tr Property
     */
    void addRule(Property tr){}
    
}
