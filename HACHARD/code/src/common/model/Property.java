package common.model;

/**
 *
 * @author Glaskani
 */
public enum Property {
    SLIP,
    WIN,
    PUSH,
    STOP,
    SINK,
    MOVE,
    STRONG,
    WEAK,
    BEST,
    MELT,
    HOT,
    YOU,
    YOU1,
    YOU2,
    KILL,
    TP,
    DOWN,
    LEFT,
    RIGHT,
    UP,
    FLY,
    ;

    /**
     * Revois la Directions en fonction de la Porperty.
     * @param pro Property, utilisable uniquement avec les
     * Property UP, DOWN, LEFT, RIGHT
     * @return Directions correspondant à la Property
     */
    Directions getDirFromProperty(Property pro) {
        if (null==pro)
            return Directions.NONE;
        else switch (pro) {
            case DOWN:
                return Directions.DOWN;
            case UP:
                return Directions.UP;
            case LEFT:
                return Directions.LEFT;
            case RIGHT:
                return Directions.RIGHT;
            default:
                return Directions.NONE;
        }
    }    
}
    

