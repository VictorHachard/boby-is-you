package model;

/**
 *
 * @author Glaskani
 */
public enum Directions {
    RIGHT(0),
    UP(1),
    LEFT(2),
    DOWN(3);
    
    private final int direction;
    
    /**
     * 
     * @param d int
     */
    Directions(int d){
        direction = d;
    }
    
    /**
     * Tranforme et revois un int en Directions.
     * @param direction int
     * @return Directions
     */
    public static Directions fromString(int direction) {

        for(Directions type : Directions.values()) {
            if (type.getDir()==direction) {
                return type;
            }
        }
        throw new IllegalArgumentException();
    }
    
    //Getters
    
    /**
     * Revois la direction.
     * @return int
     */
    int getDir(){
        return direction;
    }
    
}
