package model;

/**
 *
 * @author Glaskani
 */

public class Position {
    
    final int x, y;
    
    /**
     * 
     * @param x int
     * @param y int
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public int hashCode(){
        int hashcode = 0;
        hashcode = x;
        hashcode += y;
        return hashcode;
    }
     
    /**
     * Compare deux Positions en fonction de x et y,
     * return true si x et y sont égeaux,
     * return false si x et y ne sont pas egeaux.
     * @param obj Position à comparer.
     * @return boolean
     */
    @Override
    public boolean equals(Object obj){
        if (obj instanceof Position) {
            Position p = (Position) obj;
            return (p.x == this.x && p.y == this.y);
        } else {
            return false;
        }
    }
    
}
