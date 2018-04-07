package common.model;

/**
 *
 * @author Windows
 */
public class AllPlayer {
        
    private Position pos;
    private TypeElement te;
            
    AllPlayer(Position pos, TypeElement te) {
            this.te=te;
            this.pos=pos;
        }
    
    /**
     * Revois la position.
     * @return Position
     */
    Position getPos() {
        return pos;
    }
    
    /**
     * Revois un TypeElement.
     * @return TypeElement
     */
    TypeElement getTypeElement() {
        return te;
    }
    }
