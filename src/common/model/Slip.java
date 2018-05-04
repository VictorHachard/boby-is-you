package common.model;

import java.util.List;

/**
 *
 * @author Glaskani
 */
public class Slip extends Rule {
    
    private boolean isSlip = false;
    private Directions dirSlip;
    private List<List<Cell>> listGrid;
    private Board board;
    
    public Slip(Board board) {
        this.board=board;
        this.listGrid=board.getListGrid();
    }
    
    @Override
    boolean work(Position pos,Directions dir,TypeElement te) {
        boolean ret = false;
        if (isSlip) {
            System.out.println("coucou enter isslip");
            //si c est pas de l ice
            if (!(listGrid.get(pos.y+this.dirSlip.getDirVer()).get(pos.x+this.dirSlip.getDirHori()).find(Property.SLIP))) {
                //pour sortire de la ICCE
                if ((listGrid.get(pos.y+dirSlip.getDirVer()).get(pos.x+dirSlip.getDirHori()).canAdd())) { //si stop return false
                    board.editPlacement(pos,dirSlip,te);
                    isSlip = false;
                }
                //Si il y a un eleme a push en dehor de ICE
                else if (listGrid.get(pos.y+this.dirSlip.getDirVer()).get(pos.x+this.dirSlip.getDirHori()).canPush()) { //verifie si il peut push la case suivante
                    if (board.push(new Position(pos.x+dirSlip.getDirHori(),pos.y+dirSlip.getDirVer()),dirSlip)) {                    
                        board.editPlacement(pos,dirSlip,te);
                    }
                    isSlip = false;
                }
                //si renconter un mur
                else if (!(listGrid.get(pos.y+dirSlip.getDirVer()).get(pos.x+dirSlip.getDirHori()).canAdd())) { //si stop return false
                    if (dirSlip.getSide(dir)) {
                        dirSlip = dir;
                        //si c'est plus de la ice a gere
                        //board.editPlacement(pos,dirSlip,te);
                    }
                }
            }
            
            else if (listGrid.get(pos.y+this.dirSlip.getDirVer()).get(pos.x+this.dirSlip.getDirHori()).canPush()) { //verifie si il peut push la case suivante
                if (board.push(new Position(pos.x+dirSlip.getDirHori(),pos.y+dirSlip.getDirVer()),dirSlip))       {                    
                    board.editPlacement(pos,dirSlip,te);
                }
            }
            else board.editPlacement(pos,dirSlip,te);
            return ret;
        
        }
        //1ere fois q'un rentre dans ICE
        else if (listGrid.get(pos.y+dir.getDirVer()).get(pos.x+dir.getDirHori()).find(Property.SLIP)) {
            isSlip = true;
            dirSlip = dir;
            if (listGrid.get(pos.y+this.dirSlip.getDirVer()).get(pos.x+this.dirSlip.getDirHori()).canPush()) { //verifie si il peut push la case suivante
                if (board.push(new Position(pos.x+dirSlip.getDirHori(),pos.y+dirSlip.getDirVer()),dirSlip)) {                    
                    board.editPlacement(pos,dirSlip,te);
                }
            }
            else board.editPlacement(pos,dirSlip,te);
            return false;
        } 
        else return true;
    }

    @Override
    /**
     * Revois la Property de la regle.
     */
    Property getProperty() {
        return Property.SLIP;
    }
    
}
