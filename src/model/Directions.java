package model;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Glaskani
 */
public enum Directions {
    RIGHT(0),
    UP(1),
    LEFT(2),
    DOWN(3),
    NONE(-100);
    
    private final int direction;
    private static final Logger LOGGER = Logger.getGlobal();
    
    /**
     * 
     * @param d int
     */
    Directions(int d){
        direction = d;
    }
    
    /**
     * Tranforme et revois un int en Directions.
     * @param direction int, 0,1,2 ou 3.
     * @return Directions, la direction en fonction de direction. 
     */
    static Directions fromString(int direction) {
        try {
            for(Directions type : Directions.values()) {
                if (type.getDir()==direction) {
                    return type;
                }
            }
            throw new IllegalArgumentException();
        }
        catch (IllegalArgumentException e) {
            LOGGER.log( Level.WARNING, "int " + direction + " cannot be convert into a Directions \n" + direction + " convert into RIGHT",e);
            return Directions.RIGHT;
        }
    }
    
    /**
     * Revois la direction.
     * @return int, 0,1,2 ou 3.
     */
    int getDir(){
        return direction;
    }
    
    /**
     * Revois 1 ou -1 en focntion de la direction d'axe horizontal.
     * @return int 1 si la direction est DOWN
     * -1 si la direction est UP
     */
    int getDirVer() {
        if (direction == 1)
            return -1;
        else if(direction == 3)
            return 1;
        return 0;
    }
    
    /**
     * Revois 1 ou -1 en focntion de la direction d'axe horizontal.
     * @return int 1 si la direction est RIGHT
     * -1 si la direction est LEFT
     */
    int getDirHori(){
        if(direction ==0)
            return 1;
        else if(direction == 2)
            return -1;
        return 0;
    }
    
}
