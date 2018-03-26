package model;

import java.util.List;

/**
 *
 * @author Glaskani
 */
public class Kill {
    
    private List<List<Placement>> listGrid;
    private Board board;
    
    public Kill(Board board) {
        this.board=board;
        this.listGrid=board.getListGrid();
    }
    
    boolean check(Position pos,Directions direction,TypeElement player) {
        if (listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).findRule(Property.KILL)) {
            listGrid.get(pos.y).get(pos.x).removeElement(player);
            return true;
        }
        return false;
    }
}
