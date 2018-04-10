package common.model;

import java.util.List;

/**
 *
 * @author Glaskani
 */
public class Sink extends Rule {
    
    private List<List<Placement>> listGrid;
    private Board board;
    
    public Sink(Board board) {
        this.board=board;
        this.listGrid=board.getListGrid();
    }
    
    @Override
    public boolean work(Position pos,Directions direction,TypeElement player) {
        if (listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).find(Property.SINK)) {
            listGrid.get(pos.y).get(pos.x).removeElement(player);
            return false;
        }
        return true;
    }
    
    @Override
    boolean workPush(Position pos,Directions direction,TypeElement player) {
        if (listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).find(Property.SINK)
                && (player.getType()==Type.BLOCK)) {
                listGrid.get(pos.y).get(pos.x).removeElement(player);
            for (Element e:listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).getListeContenu())
            if (e.isRule(Property.SINK))
                listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).removeElement(e.getTypeElement());
            listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).removeElement(player);
            return false;
        }
        return true;
    }

    @Override
    /**
     * Revois la Property de la regle.
     */
    Property getProperty() {
        return Property.SINK;
    }
}

