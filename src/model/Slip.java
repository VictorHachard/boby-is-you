package model;

import exeptions.TypeElementNotFoundException;
import java.util.List;

/**
 *
 * @author Glaskani
 */
public class Slip {
    
    private boolean isSlip = false;
    private Directions dirSlip;
    private List<List<Placement>> listGrid;
    private Board board;
    
    public Slip(Board board) {
        this.board=board;
        this.listGrid=board.getListGrid();
    }
    
    /*if (isSlip) {
                        //si c est pas de l ice
                        if (!(listGrid.get(pos.y+this.dirSlip.getDirVer()).get(pos.x+this.dirSlip.getDirHori()).findRule(Property.SLIP))) {
                            //isSlip=false;
                            //si on peut pas add return le player opp
                            System.out.println("1 "+listGrid.get(pos.y+dirSlip.getDirVer()).get(pos.x+dirSlip.getDirHori()).canAdd());
                            System.out.println("2"+listGrid.get(pos.y+this.dirSlip.getDirVer()).get(pos.x+this.dirSlip.getDirHori()).findRule(Property.PUSH));
                            if ((listGrid.get(pos.y+dirSlip.getDirVer()).get(pos.x+dirSlip.getDirHori()).canAdd())) { //si stop return false
                                editPlacement(pos,dirSlip,player);
                                isSlip=false;
                            }
                            
                            else if (listGrid.get(pos.y+this.dirSlip.getDirVer()).get(pos.x+this.dirSlip.getDirHori()).findRule(Property.PUSH)) { //verifie si il peut push la case suivante
                                if (push(new Position(pos.x+dirSlip.getDirHori(),pos.y+dirSlip.getDirVer()),dirSlip))       {                    
                                    editPlacement(pos,dirSlip,player);
                                    isSlip=false;
                                } 
                            }
                            else if (!(listGrid.get(pos.y+dirSlip.getDirVer()).get(pos.x+dirSlip.getDirHori()).canAdd())) { //si stop return false
                                System.out.println("enter the mother fucking methode");
                                dirSlip = direction;
                                
                                
                                
                                if (listGrid.get(pos.y+this.dirSlip.getDirVer()).get(pos.x+this.dirSlip.getDirHori()).findRule(Property.PUSH)) { //verifie si il peut push la case suivante
                                if (push(new Position(pos.x+dirSlip.getDirHori(),pos.y+dirSlip.getDirVer()),dirSlip))       {                    
                                    editPlacement(pos,dirSlip,player);
                                    isSlip=false;
                                }
                            }
                            else editPlacement(pos,dirSlip,player);
                            }
                        }
                        else if (listGrid.get(pos.y+this.dirSlip.getDirVer()).get(pos.x+this.dirSlip.getDirHori()).findRule(Property.PUSH)) { //verifie si il peut push la case suivante
                            if (push(new Position(pos.x+dirSlip.getDirHori(),pos.y+dirSlip.getDirVer()),dirSlip))       {                    
                                editPlacement(pos,dirSlip,player);
                            }
                        }
                    else editPlacement(pos,dirSlip,player);
                    }
                    
                    else if (listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).findRule(Property.SLIP)) {
                        isSlip = true;
                        dirSlip = direction;
                        editPlacement(pos,dirSlip,player);
                    } */
    
    
    boolean check(Position pos,Directions direction,TypeElement player) throws TypeElementNotFoundException {
        //ICE
        boolean ret = true;
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
        else return false;
    }
    
}
