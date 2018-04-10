package common.model;

import common.exeptions.TypeElementNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Windows
 */
public class Fly extends Rule {

    private List<List<Placement>> listGrid;
    private Board board;
    
    public Fly(Board board) {
        this.board=board;
        this.listGrid=board.getListGrid();
    }
    
    @Override
    boolean work(Position pos, Directions direction, TypeElement player) throws TypeElementNotFoundException, IOException {
    if (listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).find(TypeElement.WALLINJOUABLE)) {
        int y = pos.y;
        int x = pos.x;
        if (pos.y+direction.getDirVer()==0)
            y = board.getSizeY()-2;
        else if (pos.y+direction.getDirVer()==board.getSizeY()-1)
                y = 1;
        if (pos.x+direction.getDirHori()==0) //ok
            x = board.getSizeX()-2;
        else if (pos.x+direction.getDirHori()==board.getSizeX()-1)
                x = 1;
        //verifier si il y a pas un push ou stop a la sortie
        if (listGrid.get(y).get(x).find(Property.PUSH) || listGrid.get(y).get(x).find(Property.STOP))
            return true;
        listGrid.get(y).get(x)
                .addElement(listGrid.get(pos.y).get(pos.x).getElements(player));
        listGrid.get(pos.y).get(pos.x).removeElement(player);
            return false;
        }
        return true;  
    }

    @Override
    /**
     * Revois la Property de la regle.
     */
    Property getProperty() {
        return Property.FLY;
    }
    
}
