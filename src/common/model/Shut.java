package common.model;

import common.exeptions.TypeElementNotFoundException;
import common.exeptions.WinException;
import java.io.IOException;
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
        if (listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).findRule(Property.SHUT)) {
            if (listGrid.get(pos.y).get(pos.x).getElements(player).getTypeRule().contains(Property.OPEN))
                for (Element e:listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).getListeContenu())
                    if (e.isRule(Property.SHUT)) {
                        listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).removeElement(e.getTypeElements());
                        //return true;    a voir
                    }
        }
        return true;
    }
    
    @Override
    boolean workPush(Position pos, Directions direction, TypeElement player) {
        if (listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).findRule(Property.SHUT)) {
            if (listGrid.get(pos.y).get(pos.x).getElements(player).getTypeRule().contains(Property.OPEN))
                for (Element e:listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).getListeContenu())
                    if (e.isRule(Property.SHUT)) {
                        listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).removeElement(e.getTypeElements());
                    }
        }
        return true;
    }

    @Override
    Property getProperty() {
        return Property.SHUT;
    }
    
}
