package model;

import exeptions.TypeElementNotFoundException;
import java.io.IOException;
import java.util.List;
import model.Board;

import model.Directions;
import model.Levels;
import model.Placement;
import model.Position;
import model.Property;
import model.Rule;
import model.TypeElement;

/**
 *
 * @author Windows
 */
public class Win implements Rule {
    
    private List<List<Placement>> listGrid;
    private Board board;
    
    public Win(Board board) {
        this.board=board;
        this.listGrid=board.getListGrid();
    }
    
    boolean check(Position pos,Directions direction,TypeElement player) throws TypeElementNotFoundException, IOException {
        if (listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).findRule(Property.WIN)) {
            Levels.getInstance().nextLevel();
            return true;
        }
        return false;
    }
    
}
