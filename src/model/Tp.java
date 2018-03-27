package model;

import java.util.List;

/**
 *
 * @author Glaskani
 */
public class Tp implements Rule {
    
    private Position portalPos;
    private boolean isPortal = false;
    private boolean removePortal = false;
    private List<List<Placement>> listGrid;
    private Board board;
    
    public Tp(Board board) {
        this.board=board;
        this.listGrid=board.getListGrid();
        getPortal();
    }
    
    boolean check(Position pos,Directions direction,TypeElement player) {
        if (removePortal) {
            listGrid.get(this.portalPos.y).get(this.portalPos.x).removeElement(TypeElement.PORTAL_IN);
            listGrid.get(this.portalPos.y).get(this.portalPos.x).removeElement(TypeElement.PORTAL_OUT);
            return false;
        }
        else if (isPortal && listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).findRule(Property.TP)) {
            //ajoute player
            listGrid.get(this.portalPos.y+direction.getOpp().getDirVer()).get(this.portalPos.x+direction.getOpp().getDirHori())
            .addElement(listGrid.get(pos.y).get(pos.x).getElements(player));
            //ajoute portail in
            listGrid.get(this.portalPos.y).get(this.portalPos.x)
            .addElement(listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).getElements(TypeElement.PORTAL_IN));
            //supprimer player et portal in
            listGrid.get(pos.y).get(pos.x).removeElement(player);
            listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).removeElement(TypeElement.PORTAL_IN);
            //changement true false
            isPortal = false;
            this.removePortal = true;         
            return true;
        }
        else return false;
    }
    
    /**
     * 
     */
    void getPortal() {
        List<Position> lp = board.getPositionOf(TypeElement.PORTAL_IN);
        List<Position> lp2 = board.getPositionOf(TypeElement.PORTAL_OUT);
        if (!(lp.isEmpty())) {
            System.out.println(lp2.size());
            int i = lp2.size()-1;
            if (i>0)
                i = (int)(Math.random() * (i+1)); 
            this.portalPos = lp2.get(i);
            this.isPortal = true;
            for (Element e:listGrid.get(lp.get(0).y).get(lp.get(0).x).getListeContenu())
                if (e.getTypeElements()==TypeElement.PORTAL_IN)
                    e.addRule(Property.TP);
        }
    } 
    
}
