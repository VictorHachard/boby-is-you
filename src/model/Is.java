package model;

/**
 *
 * @author Glaskani
 */
public class Is extends SetupRule {
    
    /**
     * 
     * @param te TypeElement
     * @param dir Directions
     */
    public Is(TypeElement te, Directions dir) {
        super(te, dir);
    }
    
    @Override
    public void update(Subject subject) {}
}
