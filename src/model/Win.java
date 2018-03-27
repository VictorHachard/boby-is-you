package model;

import exeptions.TypeElementNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Windows
 */
public class Win implements Rule {
    
    private List<List<Placement>> listGrid;
    private Board board;
    private TypeElement te;
    
    public Win(Board board) {
        this.board=board;
        this.listGrid=board.getListGrid();
        this.te = TypeElement.WATER;
    }
    
    boolean check(Position pos,Directions direction,TypeElement player) throws TypeElementNotFoundException, IOException {
        if (listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).findRule(Property.WIN)) {
            Levels.getInstance().nextLevel();
            return true;
        }
        return false;
    }
    
}
