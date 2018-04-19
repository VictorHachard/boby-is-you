package common.model;

/**
 *
 * @author Glaskani
 */
public class Unplayable extends Element {
    
    /**
     * Crée un MurInjouable, Directions RIGHT, Property STOP.
     */
    public Unplayable() {
        super(TypeElement.WALLINJOUABLE,Directions.RIGHT);
        super.ltr.add(Property.STOP);
    }    
}
