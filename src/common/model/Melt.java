package common.model;

import java.util.List;

/**
 *
 * @author Windows
 */
public class Melt extends Rule {
    
    private List<List<Placement>> listGrid;
    private Board board;
    
    public Melt(Board board) {
        this.board=board;
        this.listGrid=board.getListGrid();
    }
    
    
    @Override
    public boolean work(Position pos,Directions direction,TypeElement player) {
        if (listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).find(Property.MELT)) {
            if (listGrid.get(pos.y).get(pos.x).getElements(player).getTypeRule().contains(Property.HOT)) {
                for (Element e:listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).getListeContenu())
                    if (e.isRule(Property.MELT))
                        listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).removeElement(e.getTypeElement());
                return true;
            }
        }
        else if (listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).find(Property.HOT)) {
            if (listGrid.get(pos.y).get(pos.x).getElements(player).getTypeRule().contains(Property.MELT)) {
                listGrid.get(pos.y).get(pos.x).removeElement(player);
                return false;
            }
        }
        return true;
    }    

    @Override
    /**
     * Revois la Property de la regle.
     */
    Property getProperty() {
        return Property.MELT;
    }
}
