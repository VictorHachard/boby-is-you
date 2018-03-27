package model;

import java.util.List;

/**
 *
 * @author Windows
 */
public class Melt implements Rule {
    
 private List<List<Placement>> listGrid;
    private Board board;
    private TypeElement te;
    
    public Melt(Board board) {
        this.board=board;
        this.listGrid=board.getListGrid();
        this.te = TypeElement.WATER;
    }
    
    boolean check(Position pos,Directions direction,TypeElement player) {
        if (listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).findRule(Property.MELT)) {
            listGrid.get(pos.y).get(pos.x).removeElement(player);
            return true;
        }
        return false;
    }    
}
