package common.model;

import java.util.List;

/**
 *
 * @author Windows
 */
public class Shut extends Rule {

    private List<List<Cell>> listGrid;
    
    public Shut(Board board) {
        this.listGrid=board.getListGrid();
    }
    
    @Override
    boolean work(Position pos, Directions dir, TypeElement te) {
        if (listGrid.get(pos.y+dir.getDirVer()).get(pos.x+dir.getDirHori()).find(Property.SHUT)) {
            if (listGrid.get(pos.y).get(pos.x).getElements(te).getTypeRule().contains(Property.OPEN)) {
                for (Element e:listGrid.get(pos.y+dir.getDirVer()).get(pos.x+dir.getDirHori()).getZ())
                    if (e.isRule(Property.SHUT)) {
                        listGrid.get(pos.y+dir.getDirVer()).get(pos.x+dir.getDirHori()).removeElement(e.getTypeElement());
                        return true;
                    }
            }
            return false;
        }
        return true;
    }
    
    @Override
    boolean workPush(Position pos, Directions dir, TypeElement te) {
        if (listGrid.get(pos.y+dir.getDirVer()).get(pos.x+dir.getDirHori()).find(Property.SHUT)) {
            if (listGrid.get(pos.y).get(pos.x).getElements(te).getTypeRule().contains(Property.OPEN))
                for (Element e:listGrid.get(pos.y+dir.getDirVer()).get(pos.x+dir.getDirHori()).getZ())
                    if (e.isRule(Property.SHUT)) {
                        listGrid.get(pos.y+dir.getDirVer()).get(pos.x+dir.getDirHori()).removeElement(e.getTypeElement());
                        return false;
                    }
            return false;
        }
        return true;
    }

    @Override
    /**
     * Revois la Property de la regle.
     */
    Property getProperty() {
        return Property.SHUT;
    }
    
}
