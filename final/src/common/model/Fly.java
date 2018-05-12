package common.model;

import java.util.List;

/**
 *
 * @author Windows
 */
public class Fly extends Rule {

    private List<List<Cell>> listGrid;
    private Board board;
    
    public Fly(Board board) {
        this.board=board;
        this.listGrid=board.getListGrid();
    }
    
    @Override
    boolean work(Position pos, Directions dir, TypeElement te) {
    if (listGrid.get(pos.y+dir.getDirVer()).get(pos.x+dir.getDirHori())
            .find(TypeElement.WALLINJOUABLE)) {
        int y = pos.y;
        int x = pos.x;
        if (pos.y+dir.getDirVer()==0)
            y = board.getSizeY()-2;
        else if (pos.y+dir.getDirVer()==board.getSizeY()-1)
                y = 1;
        if (pos.x+dir.getDirHori()==0) //ok
            x = board.getSizeX()-2;
        else if (pos.x+dir.getDirHori()==board.getSizeX()-1)
                x = 1;
        //verifier si il y a pas un push ou stop a la sortie
        if (listGrid.get(y).get(x).find(Property.PUSH)
                || listGrid.get(y).get(x).find(Property.STOP))
            return true;
        listGrid.get(y).get(x)
                .addElement(listGrid.get(pos.y).get(pos.x).getElements(te));
        listGrid.get(pos.y).get(pos.x).removeElement(te);
            return false;
        } return true;  
    }

    @Override
    /**
     * Revois la Property de la regle.
     */
    Property getProperty() {
        return Property.FLY;
    }
    
}
