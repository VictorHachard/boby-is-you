package common.model;

import java.util.List;

/**
 *
 * @author Glaskani
 */
public class Weak extends Rule {
    
    private List<List<Cell>> listGrid;
    
    public Weak(Board board) {
        this.listGrid=board.getListGrid();
    }
    
    @Override
    public boolean work(Position pos,Directions dir,TypeElement te) {
        if (listGrid.get(pos.y+dir.getDirVer()).get(pos.x+dir.getDirHori())
                .find(Property.WEAK)) {
            if (listGrid.get(pos.y).get(pos.x).getElements(te).getTypeRule()
                    .contains(Property.STRONG)) {
                for (Element e:listGrid.get(pos.y+dir.getDirVer())
                        .get(pos.x+dir.getDirHori()).getZ())
                    if (e.isRule(Property.WEAK)) {
                        listGrid.get(pos.y+dir.getDirVer()).get(pos.x+dir
                               .getDirHori()).removeElement(e.getTypeElement());
                        MusicHashMap.getInstance().play(Music.DESTROY);
                    }
            }
        }
        else if (listGrid.get(pos.y+dir.getDirVer()).get(pos.x+dir.getDirHori())
                .find(Property.STRONG)) {
            if (listGrid.get(pos.y).get(pos.x).getElements(te).getTypeRule()
                    .contains(Property.WEAK)) {
                listGrid.get(pos.y).get(pos.x).removeElement(te);
                MusicHashMap.getInstance().play(Music.DESTROY);
            }
        }
        return true;
    }  
    
    @Override
    boolean workPush(Position pos,Directions dir,TypeElement te) {
        if (listGrid.get(pos.y+dir.getDirVer()).get(pos.x+dir.getDirHori())
                .find(Property.WEAK)) {
            if (listGrid.get(pos.y).get(pos.x).getElements(te).getTypeRule()
                    .contains(Property.STRONG)) {
                for (Element e:listGrid.get(pos.y+dir.getDirVer())
                        .get(pos.x+dir.getDirHori()).getZ())
                    if (e.isRule(Property.WEAK)) {
                        listGrid.get(pos.y+dir.getDirVer()).get(pos.x+dir
                               .getDirHori()).removeElement(e.getTypeElement());
                        MusicHashMap.getInstance().play(Music.DESTROY);
                    }
            }
        }
        return true;
    }

    @Override
    /**
     * Revois la Property de la regle.
     */
    Property getProperty() {
        return Property.WEAK;
    }
}
