package common.model;

/**
 *
 * @author Glaskani
 */
public enum Property {
    SLIP, //glisser
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
    
   /* private final Rule rule;
    
    Property(Rule rule) {
        this.rule=rule;
    }*/
    
    
    
}
    

