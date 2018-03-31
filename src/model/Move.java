package model;

import exeptions.TypeElementNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Glaskani
 */
public class Move extends Rule {
    
    private boolean isMonster = false;
    private List<List<Placement>> listGrid;
    private Board board;
    private List<Position> listMonster =new ArrayList<>();
    private TypeElement te;
    
    public Move(Board board) {
        this.board=board;
        this.listGrid=board.getListGrid();
        checkRule(Property.MOVE);
    }
    
    
    @Override
    public boolean work(Position pos,Directions direction,TypeElement player) throws TypeElementNotFoundException, IOException {
        if (isMonster) {
            if (checkRule(Property.MOVE)) {
                getMonster();
                moveMonster();
            }
        }
        return true;
    }
    
    void moveMonster() throws TypeElementNotFoundException, IOException {
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
        for (Element e:board.getListAllElement())
            if (e.getTypeRule().contains(pro)) {
                this.te = e.getTypeElements();
                this.isMonster=true;
                return true;
            }
        return false;
    }
    
    void getMonster() {
        this.listMonster = board.getPositionOf(this.te);
        if (!(listMonster.isEmpty())) {
            this.isMonster = true;
        }
    } 
    
    @Override
    Property getProperty() {
        return Property.MOVE;
    }
}
