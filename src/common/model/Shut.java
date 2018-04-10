package common.model;

import java.util.List;

/**
 *
 * @author Windows
 */
public class Shut extends Rule {

    private List<List<Placement>> listGrid;
    private Board board;
    
    public Shut(Board board) {
        this.board=board;
        this.listGrid=board.getListGrid();
    }
    
    @Override
    boolean work(Position pos, Directions direction, TypeElement player) {
        if (listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).find(Property.SHUT)) {
            if (listGrid.get(pos.y).get(pos.x).getElements(player).getTypeRule().contains(Property.OPEN)) {
                for (Element e:listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).getListeContenu())
                    if (e.isRule(Property.SHUT)) {
                        listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).removeElement(e.getTypeElement());
                        return true;
                    }
            }
            return false;
        }
        return true;
    }
    
    @Override
    boolean workPush(Position pos, Directions direction, TypeElement player) {
        if (listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).find(Property.SHUT)) {
            if (listGrid.get(pos.y).get(pos.x).getElements(player).getTypeRule().contains(Property.OPEN))
                for (Element e:listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).getListeContenu())
                    if (e.isRule(Property.SHUT)) {
                        listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).removeElement(e.getTypeElement());
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
