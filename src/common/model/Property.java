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
    BEST,
    MELT,
    HOT,
    YOU,
    GRAB,
    KILL,
    TP,
    OPEN,
    SHUT,
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
        if (pro==Property.DOWN)
            return Directions.DOWN;
        else if (pro==Property.UP)
            return Directions.UP;
        else if (pro==Property.LEFT)
            return Directions.LEFT;
        else if (pro==Property.RIGHT)
            return Directions.RIGHT;
        else return Directions.NONE;
    }    
}
    

