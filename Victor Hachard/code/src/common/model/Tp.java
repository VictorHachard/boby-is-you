package common.model;

import java.util.List;

/**
 *
 * @author Glaskani
 */
public class Tp extends Rule {
    
    private Position portalPos;
    private boolean isPortal = false;
    private boolean removePortal = false;
    private List<List<Cell>> listGrid;
    private Board board;
    
    public Tp(Board board) {
        this.board=board;
        this.listGrid=board.getListGrid();
        getPortal();
    }
    
    @Override
    public boolean work(Position pos,Directions dir,TypeElement te) {
        getPortal();
        if (removePortal) {
            listGrid.get(this.portalPos.y).get(this.portalPos.x)
                    .removeElement(TypeElement.PORTAL_IN);
            listGrid.get(this.portalPos.y).get(this.portalPos.x)
                    .removeElement(TypeElement.PORTAL_OUT);
            return true;
        } else if (isPortal && listGrid.get(pos.y+dir.getDirVer())
                .get(pos.x+dir.getDirHori()).find(Property.TP)) {
            //ajoute te
            listGrid.get(this.portalPos.y+dir.getDirVer())
                    .get(this.portalPos.x+dir.getDirHori())
            .addElement(listGrid.get(pos.y).get(pos.x).getElements(te));
            //ajoute portail in
            listGrid.get(this.portalPos.y).get(this.portalPos.x)
            .addElement(listGrid.get(pos.y+dir.getDirVer())
                    .get(pos.x+dir.getDirHori()).getElements(TypeElement.PORTAL_IN));
            //supprimer te et portal in
            listGrid.get(pos.y).get(pos.x).removeElement(te);
            listGrid.get(pos.y+dir.getDirVer()).get(pos.x+dir.getDirHori())
                    .removeElement(TypeElement.PORTAL_IN);
            //changement true false
            isPortal = false;
            removePortal = true;      
            return false;
        } else return true;
    }
    
    private void getPortal() {
        List<Position> lp = board.getPositionOf(TypeElement.PORTAL_IN);
        List<Position> lp2 = board.getPositionOf(TypeElement.PORTAL_OUT);
        if (!(lp==null && lp==null)) {
            int i = lp2.size()-1;
            if (i>0)
                i = (int)(Math.random() * (i+1));
            this.portalPos = lp2.get(i);
            this.isPortal = true;
            for (Element e:listGrid.get(lp.get(0).y).get(lp.get(0).x).getZ())
                if (e.getTypeElement()==TypeElement.PORTAL_IN)
                    e.addRule(Property.TP);
        }
    }

    @Override
    /**
     * Revois la Property de la regle.
     */
    Property getProperty() {
        return Property.TP;
    }
}
