package common.model;

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
    
    Directions(int d){
        direction = d;
    }
    
    /**
     * Tranforme et revois un int en Directions.
     * @param direction int, 0,1,2 ou 3.
     * @return Directions, la direction en fonction de direction. 
     */
    static Directions fromString(int dir) {
        for(Directions type : Directions.values())
            if (type.direction==dir)
                return type;
        LOGGER.log(Level.WARNING,"int {0} cannot be convert into a Directions\n"
                + "SOLVE: {0} convert into RIGHT", dir);
        return Directions.RIGHT;
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
    
    /**
     * Revois la direction opposée de la direction de l'element ou autre.
     * @return LEFT si la direction est RIGHT
     * RIGHT si la direction est LEFT
     * UP si la direction est DOWN
     * DOWN si la direction est UP
     * NONE si la direction est inconue
     */
    Directions getOpp() {
        if (null!=this)
            switch (this) {
            case DOWN:
                return Directions.UP;
            case UP:
                return Directions.DOWN;
            case LEFT:
                return Directions.RIGHT;
            case RIGHT:
                return Directions.LEFT;
            default:
                return Directions.NONE;
        }
        return Directions.NONE;
    }
    
    /**
     * Verifie si une direcion donné est bien dans l'axe perpendiculaire de la 
     * direction de l'element.
     * @param dir, direction perpendiculaire à l'axe à verifier
     * @return true si la direction donné est bien perpendiculaire à
     * la direction de l'element, 
     * false si la direction donné n'est pas perpendiculaire à la direction
     * de l'element ou si la direction n'existe pas
     */
    boolean getSide(Directions dir) {
        if (this==Directions.DOWN || this==Directions.UP)
            return (dir==Directions.LEFT || dir==Directions.RIGHT);
        else if (this==Directions.RIGHT || this==Directions.LEFT)
            return (dir==Directions.DOWN || dir==Directions.DOWN);
        return false;
    }
}
