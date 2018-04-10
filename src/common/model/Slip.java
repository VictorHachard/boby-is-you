package common.model;

import common.exeptions.TypeElementNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Glaskani
 */
public class Slip extends Rule {
    
    private boolean isSlip = false;
    private Directions dirSlip;
    private List<List<Placement>> listGrid;
    private Board board;
    
    public Slip(Board board) {
        this.board=board;
        this.listGrid=board.getListGrid();
    }
    
    @Override
    boolean work(Position pos,Directions direction,TypeElement player) throws TypeElementNotFoundException, IOException {
        boolean ret = false;
        if (isSlip) {
            System.out.println("coucou enter isslip");
            //si c est pas de l ice
            if (!(listGrid.get(pos.y+this.dirSlip.getDirVer()).get(pos.x+this.dirSlip.getDirHori()).find(Property.SLIP))) {
                //pour sortire de la ICCE
                if ((listGrid.get(pos.y+dirSlip.getDirVer()).get(pos.x+dirSlip.getDirHori()).canAdd())) { //si stop return false
                    board.editPlacement(pos,dirSlip,player);
                    isSlip = false;
                }
                //Si il y a un eleme a push en dehor de ICE
                else if (listGrid.get(pos.y+this.dirSlip.getDirVer()).get(pos.x+this.dirSlip.getDirHori()).canPush()) { //verifie si il peut push la case suivante
                    if (board.push(new Position(pos.x+dirSlip.getDirHori(),pos.y+dirSlip.getDirVer()),dirSlip)) {                    
                        board.editPlacement(pos,dirSlip,player);
                    }
                    isSlip = false;
                }
                //si renconter un mur
                else if (!(listGrid.get(pos.y+dirSlip.getDirVer()).get(pos.x+dirSlip.getDirHori()).canAdd())) { //si stop return false
                    if (dirSlip.getSide(direction)) {
                        dirSlip = direction;
                        //si c'est plus de la ice a gere
                        //board.editPlacement(pos,dirSlip,player);
                    }
                }
            }
            
            else if (listGrid.get(pos.y+this.dirSlip.getDirVer()).get(pos.x+this.dirSlip.getDirHori()).canPush()) { //verifie si il peut push la case suivante
                if (board.push(new Position(pos.x+dirSlip.getDirHori(),pos.y+dirSlip.getDirVer()),dirSlip))       {                    
                    board.editPlacement(pos,dirSlip,player);
                }
            }
            else board.editPlacement(pos,dirSlip,player);
            return ret;
        
        }
        //1ere fois q'un rentre dans ICE
        else if (listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).find(Property.SLIP)) {
            isSlip = true;
            dirSlip = direction;
            if (listGrid.get(pos.y+this.dirSlip.getDirVer()).get(pos.x+this.dirSlip.getDirHori()).canPush()) { //verifie si il peut push la case suivante
                if (board.push(new Position(pos.x+dirSlip.getDirHori(),pos.y+dirSlip.getDirVer()),dirSlip))       {                    
                    board.editPlacement(pos,dirSlip,player);
                }
            }
            else board.editPlacement(pos,dirSlip,player);
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
