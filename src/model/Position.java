package model;

/**
 *
 * @author Glaskani
 */

public class Position {
    
    public final int x, y;
    
    /**
     * 
     * @param x int
     * @param y int
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int hashCode(){
        int hashcode = 0;
        hashcode = x;
        hashcode += y;
        return hashcode;
    }
     
    public boolean equals(Object obj){
        if (obj instanceof Position) {
            Position p = (Position) obj;
            return (p.x == this.x && p.y == this.y);
        } else {
            return false;
        }
    }
    
}
