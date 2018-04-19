package common.model;

import java.util.List;

/**
 *
 * @author Glaskani
 */
public class Kill extends Rule {
    
    private List<List<Cell>> listGrid;
    
    public Kill(Board board) {
        this.listGrid=board.getListGrid();
    }
    
    @Override
    public boolean work(Position pos,Directions dir,TypeElement te) {
        if (listGrid.get(pos.y+dir.getDirVer()).get(pos.x+dir.getDirHori()).find(Property.KILL)) {
            listGrid.get(pos.y).get(pos.x).removeElement(te);
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
