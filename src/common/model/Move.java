package common.model;

import java.util.ArrayList;
import java.util.Collections;
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
    public boolean work(Position pos,Directions dir,TypeElement player) {
        if (isMonster) {
            if (checkRule(Property.MOVE)) {
                getMonster();
                moveMonster();
            }
        }
        return true;
    }
    
    private void sort(Directions dir) {
        Collections.sort(this.listMonster, (Position o1, Position o2) -> {
            if (null!=dir)
                switch (dir) {
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
        });  
    }
    
    void moveMonster() {
        Directions dir = listGrid.get(listMonster.get(0).y).get(listMonster.get(0).x).getElements(this.te).getDirections();
        //trie pour ne pas addi les player
        sort(dir);
        for(Position pos:this.listMonster) {
            dir = listGrid.get(pos.y).get(pos.x).getElements(this.te).getDirections();
            if(pos.y+dir.getDirVer() < board.getSizeY() && pos.x+dir.getDirHori() < board.getSizeX()) {
                if (listGrid.get(pos.y+dir.getDirVer()).get(pos.x+dir.getDirHori()).canAdd()){ //verifie si il peut add la case suivante
                    board.editPlacement(pos,dir,this.te);
                }
                else if (listGrid.get(pos.y+dir.getDirVer()).get(pos.x+dir.getDirHori()).canPush()) { //verifie si il peut push la case suivante
                    if (board.push(new Position(pos.x+dir.getDirHori(),pos.y+dir.getDirVer()),dir))
                        board.editPlacement(pos,dir,this.te);
                    else {
                        listGrid.get(pos.y).get(pos.x).getElements(this.te).setDirections(dir.getOpp());
                        board.editPlacement(pos,dir.getOpp(),this.te);
                        }
                }
                else {
                    if (listGrid.get(pos.y+dir.getOpp().getDirVer()).get(pos.x+dir.getOpp().getDirHori()).canAdd()) {
                        listGrid.get(pos.y).get(pos.x).getElements(this.te).setDirections(dir.getOpp());
                        board.editPlacement(pos,dir.getOpp(),this.te);
                    }
                }
            }
        }
    }
    
    boolean checkRule(Property pro) {
        for (Element e:board.getListAllElement())
            if (e.getTypeRule().contains(pro)) {
                this.te = e.getTypeElement();
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
    /**
     * Revois la Property de la regle.
     */
    Property getProperty() {
        return Property.MOVE;
    }
}
