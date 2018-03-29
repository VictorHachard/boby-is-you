package model;

import exeptions.TypeElementNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Glaskani
 */
public class Move {
    
    private boolean isMonster = false;
    private List<List<Placement>> listGrid;
    private Board board;
    private List<Position> listMonster =new ArrayList<>();
    private TypeElement te;
    
    public Move(Board board) {
        this.board=board;
        this.listGrid=board.getListGrid();
        checkRule(Property.MOVE);
        getMonster();
    }
    
    boolean check(Position pos,Directions direction,TypeElement player) throws TypeElementNotFoundException {
        if (isMonster) {
            if (checkRule(Property.MOVE)) {
                getMonster();
                moveMonster();
            }
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
                    else {
                        listGrid.get(pos.y).get(pos.x).getElements(this.te).setDirections(direction.getOpp());
                        board.editPlacement(pos,direction.getOpp(),this.te);
                        }
                }
                else {
                    listGrid.get(pos.y).get(pos.x).getElements(this.te).setDirections(direction.getOpp());
                    board.editPlacement(pos,direction.getOpp(),this.te);
                }
            }
        }
    }
    
    boolean checkRule(Property pro) {
        for (ElementRule e:board.getElementRule())
            if (e.getProperty()==pro) {
                this.te = e.getTypeElement();
                this.isMonster=true;
                return true;
            }
        return false;
    }
    
    void getMonster() {
        /*if (!(this.listMonster.isEmpty()))
            this.listMonster.clear();*/
        this.listMonster = board.getPositionOf(this.te);
        if (!(listMonster.isEmpty())) {
            this.isMonster = true;
        }
    } 
}
