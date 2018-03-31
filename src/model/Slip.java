package model;

import exeptions.TypeElementNotFoundException;
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
        //ICE
       /* boolean ret = true;
        if (isSlip) {
            //si c est pas de l ice
            if (!(listGrid.get(pos.y+this.dirSlip.getDirVer()).get(pos.x+this.dirSlip.getDirHori()).findRule(Property.SLIP))) {
                //pour sortire de la ICCE
                if ((listGrid.get(pos.y+dirSlip.getDirVer()).get(pos.x+dirSlip.getDirHori()).canAdd())) { //si stop return false
                    board.editPlacement(pos,dirSlip,player);
                    ret = false;
                    isSlip = false;
                }
                //Si il y a un eleme a push en dehor de ICE
                else if (listGrid.get(pos.y+this.dirSlip.getDirVer()).get(pos.x+this.dirSlip.getDirHori()).canPush()) { //verifie si il peut push la case suivante
                    if (board.push(new Position(pos.x+dirSlip.getDirHori(),pos.y+dirSlip.getDirVer()),dirSlip)) {                    
                        board.editPlacement(pos,dirSlip,player);
                    } 
                    ret = false;
                    isSlip = false;
                }
                //continu
                else if (!(listGrid.get(pos.y+dirSlip.getDirVer()).get(pos.x+dirSlip.getDirHori()).canAdd())) { //si stop return false
                    dirSlip = direction;
                                
                                
                                
                    if (listGrid.get(pos.y+this.dirSlip.getDirVer()).get(pos.x+this.dirSlip.getDirHori()).canPush()) { //verifie si il peut push la case suivante
                        if (board.push(new Position(pos.x+dirSlip.getDirHori(),pos.y+dirSlip.getDirVer()),dirSlip)){                    
                            board.editPlacement(pos,dirSlip,player);
                        }
                        isSlip=false;
                    }
                    else board.editPlacement(pos,dirSlip,player);
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
        else if (listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).findRule(Property.SLIP)) {
            isSlip = true;
            dirSlip = direction;
            board.editPlacement(pos,dirSlip,player);
            return true;
        } 
        else return false;*/
       return false;
    }

    @Override
    Property getProperty() {
        return Property.SLIP;
    }
    
}
