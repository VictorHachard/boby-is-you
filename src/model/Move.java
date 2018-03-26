package model;

import exeptions.TypeElementNotFoundException;
import java.util.List;

/**
 *
 * @author Glaskani
 */
public class Move {
    
    private boolean isMonster = false;
    private List<List<Placement>> listGrid;
    private Board board;
    private List<Position> listMonster;
    private List<ElementRule> listRule;
    private TypeElement te;
    
    public Move(Board board) {
        this.board=board;
        this.listGrid=board.getListGrid();
        //checkRule(Property.MOVE); en amont normalement
        getMonster();
    }
    
    boolean check(Position pos,Directions direction,TypeElement player) throws TypeElementNotFoundException {
        if (isMonster) {
            //if (checkRule(Property.MOVE)) {
                getMonster();
                moveMonster();
            //}
        }
        return false;
    }
    
    void moveMonster() throws TypeElementNotFoundException {
        Directions direction;
        
        for(Position pos:this.listMonster) {
            direction = listGrid.get(pos.y).get(pos.x).getElements(this.te).getDirections();
            if(pos.y+direction.getDirVer() < board.getSizeY() && pos.x+direction.getDirHori() < board.getSizeX()) {
                if (listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).canAdd()){ //verifie si il peut add la case suivante
                    board.editPlacement(pos,direction,this.te);
                }
                else if (listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).canPush()) { //verifie si il peut push la case suivante
                    if (board.push(new Position(pos.x+direction.getDirHori(),pos.y+direction.getDirVer()),direction))
                        board.editPlacement(pos,direction,this.te);
                }
            }
        }
    }
    
    boolean checkRule(Property pro) {
        for (ElementRule e:this.listRule)
            if (e.getProperty()==pro) {
                this.te = e.getTypeElement();
                return true;
            }
        return false;
    }
    
    void getMonster() {
        this.listMonster = board.getPositionOf(this.te);
        if (!(listMonster==null)) {
            this.isMonster = true;
        }
    } 
}
