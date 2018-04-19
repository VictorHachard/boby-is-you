package common.model;

import java.util.List;

/**
 *
 * @author Windows
 */
public class Melt extends Rule {
    
    private List<List<Cell>> listGrid;
    
    public Melt(Board board) {
        this.listGrid=board.getListGrid();
    }
    
    
    @Override
    public boolean work(Position pos,Directions dir,TypeElement te) {
        if (listGrid.get(pos.y+dir.getDirVer()).get(pos.x+dir.getDirHori()).find(Property.MELT)) {
            if (listGrid.get(pos.y).get(pos.x).getElements(te).getTypeRule().contains(Property.HOT)) {
                for (Element e:listGrid.get(pos.y+dir.getDirVer()).get(pos.x+dir.getDirHori()).getZ())
                    if (e.isRule(Property.MELT))
                        listGrid.get(pos.y+dir.getDirVer()).get(pos.x+dir.getDirHori()).removeElement(e.getTypeElement());
                return true;
            }
        }
        else if (listGrid.get(pos.y+dir.getDirVer()).get(pos.x+dir.getDirHori()).find(Property.HOT)) {
            if (listGrid.get(pos.y).get(pos.x).getElements(te).getTypeRule().contains(Property.MELT)) {
                listGrid.get(pos.y).get(pos.x).removeElement(te);
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
