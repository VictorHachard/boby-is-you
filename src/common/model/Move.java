package common.model;

import common.exeptions.TypeElementNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    
    private void sort(Directions direction) {
                Collections.sort(this.listMonster, new Comparator<Position>() {
            @Override
            public int compare(Position o1, Position o2) {
            if (null!=direction)
                switch (direction) {
                    case RIGHT:
                        return o2.x - o1.x;
                    case LEFT:
                        return o1.x - o2.x;
                    case UP:
                        return o1.y - o2.y;
                    default:
                        break;
                }
            return o2.y - o1.y;
            }
        });  
    }
    
    void moveMonster() throws TypeElementNotFoundException, IOException {
        Directions direction = listGrid.get(listMonster.get(0).y).get(listMonster.get(0).x).getElements(this.te).getDirections();
        //trie pour ne pas addi les player
        sort(direction);
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
                    if (listGrid.get(pos.y+direction.getOpp().getDirVer()).get(pos.x+direction.getOpp().getDirHori()).canAdd()) {
                        listGrid.get(pos.y).get(pos.x).getElements(this.te).setDirections(direction.getOpp());
                        board.editPlacement(pos,direction.getOpp(),this.te);
                    }
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
