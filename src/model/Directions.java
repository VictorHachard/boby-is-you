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
    
    private final int dir;
    
    Directions(int d){
        dir = d;
    }
    
    public static Directions fromString(int dir) {

        for(Directions type : Directions.values()) {
            if (type.getDir()==dir) {
                return type;
            }
        }
        throw new IllegalArgumentException();
    }
    
    int getDir(){
        return dir;
    }
    
}
