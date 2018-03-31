package common.model;

import java.util.List;

/**
 *
 * @author Glaskani
 */
public class Sink extends Rule {
    
    private List<List<Placement>> listGrid;
    private Board board;
    private TypeElement te;
    
    public Sink(Board board) {
        this.board=board;
        this.listGrid=board.getListGrid();
    }
    
    @Override
    public boolean work(Position pos,Directions direction,TypeElement player) {
        if (listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).findRule(Property.SINK)) {
            listGrid.get(pos.y).get(pos.x).removeElement(player);
            return false;
        }
        return true;
    }
    
    @Override
    boolean workPush(Position pos,Directions direction,TypeElement player) {
        this.te = checkRule(Property.SINK);
        if (listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).findRule(Property.SINK)
                && (!listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).findTypeType(TypeTypeElement.CONNECTER))
                && (!listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).findTypeType(TypeTypeElement.RULE))
                && (!listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).findTypeType(TypeTypeElement.TEXT))) {
            listGrid.get(pos.y).get(pos.x).removeElement(player);
            for (Element e:board.getListGrid().get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).getListeContenu())
            if (e.getTypeElements()==te)
                listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).removeElement(e.getTypeElements());
            return false;
        }
        return true;
    }
    
    private TypeElement checkRule(Property pro) {
        for (Element e:this.board.getListAllElement())
            for (Property p:e.getTypeRule())
            if (p==pro)
                return e.getTypeElements();
        return null;
    }

    @Override
    Property getProperty() {
        return Property.SINK;
    }
}

