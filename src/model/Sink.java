package model;

import java.util.List;

/**
 *
 * @author Glaskani
 */
public class Sink implements Rule {
    
    private List<List<Placement>> listGrid;
    private Board board;
    private TypeElement te;
    
    public Sink(Board board) {
        this.board=board;
        this.listGrid=board.getListGrid();
        this.te = TypeElement.WATER;
    }
    
    boolean check(Position pos,Directions direction,TypeElement player) {
        if (listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).findRule(Property.SINK)) {
            listGrid.get(pos.y).get(pos.x).removeElement(player);
            return true;
        }
        return false;
    }
    
    boolean checkPush(Position pos,Directions direction,TypeElement player) {
        if (listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).findRule(Property.SINK)) {
            listGrid.get(pos.y).get(pos.x).removeElement(player);
            for (Element e:board.getListGrid().get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).getListeContenu())
            if (e.getTypeElements()==te)
                listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).removeElement(e.getTypeElements());
            return true;
        }
        return false;
    }
}

