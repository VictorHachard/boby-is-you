package model;

import java.util.List;

/**
 *
 * @author Windows
 */
public class MeltHot implements Rule {
    
 private List<List<Placement>> listGrid;
    private Board board;
    
    public MeltHot(Board board) {
        this.board=board;
        this.listGrid=board.getListGrid();
    }
    
    boolean check(Position pos,Directions direction,TypeElement player) { //machin ver truc donc melt ver hot
        //melt ver hot
        if (listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).findRule(Property.HOT)) {
            if (listGrid.get(pos.y).get(pos.x).getElements(player).getTypeRule().contains(Property.MELT)) {
                for (Element e:listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).getListeContenu())
                        if (e.isRule(Property.HOT))
                            listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).removeElement(e.getTypeElements());
                return false;
            }   
            listGrid.get(pos.y).get(pos.x).removeElement(player);
            return true;
        }
        return false;
    }    
}
