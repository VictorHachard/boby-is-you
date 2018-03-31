package model;



import exeptions.WinException;
import exeptions.TypeElementNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Windows
 */
public class Win extends Rule {

    private List<List<Placement>> listGrid;
    private Board board;
    
    public Win(Board board) {
        this.board=board;
        this.listGrid=board.getListGrid();
    }
    
    
    @Override
    public boolean work(Position pos,Directions direction,TypeElement player) throws WinException, TypeElementNotFoundException, IOException {
        if (listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).findRule(Property.WIN)) {
            Levels.getInstance().nextLevel();
            throw new WinException();
        }
        return true;
    }

    @Override
    Property getProperty() {
        return Property.WIN;
    }
}
