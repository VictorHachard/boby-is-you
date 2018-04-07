package common.model;

import java.util.List;

/**
 *
 * @author Glaskani
 */
public class Kill extends Rule {
    
    private List<List<Placement>> listGrid;
    private Board board;
    
    public Kill(Board board) {
        this.board=board;
        this.listGrid=board.getListGrid();
    }
    
    @Override
    public boolean work(Position pos,Directions direction,TypeElement player) {
        if (listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).findRule(Property.KILL)) {
            listGrid.get(pos.y).get(pos.x).removeElement(player);
            return false;
        }
        return true;
    }

    @Override
    /**
     * Revois la Property de la regle.
     */
    Property getProperty() {
        return Property.KILL;
    }
       
}
