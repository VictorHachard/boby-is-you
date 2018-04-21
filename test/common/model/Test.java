package common.model;

import common.exeptions.WinException;
import javafx.embed.swing.JFXPanel;
import static org.junit.Assert.*;

/**
 * tests unitaires
 * @author Glaskani
 *
 */
public class Test {
    
    @org.junit.Test
    public void testRecognitionVerticalRule() {
        JFXPanel fxPanel = new JFXPanel();
        Maps m = new Maps(1,3);
        m.addMap(1, 1, new Element(TypeElement.TEXT_WALL));
        m.addMap(1, 2, new Element(TypeElement.IS));
        m.addMap(1, 3, new Element(TypeElement.WIN));
	Board b = new Board(m);
        boolean expResult = true;
        boolean result = Rule.isActive(Property.WIN);
        assertTrue("La regles n'a pas ete rajouter a la liste des regles active",
                result == expResult);
    }
    
    @org.junit.Test
    public void testRecognitionHorizontalRule() {
        JFXPanel fxPanel = new JFXPanel();
        Maps m = new Maps(4,1);
        m.addMap(1, 1, new Element(TypeElement.TEXT_SKULL));
        m.addMap(2, 1, new Element(TypeElement.IS));
        m.addMap(3, 1, new Element(TypeElement.KILL));
	Board b = new Board(m);
        boolean expResult = true;
        boolean result = Rule.isActive(Property.KILL);
        assertTrue("La regles n'a pas ete rajouter a la liste des regles active",
                result == expResult);
    }
    
    @org.junit.Test
    public void testMove() {
        JFXPanel fxPanel = new JFXPanel();
        Maps m = new Maps(2,4);
        m.addMap(1, 1, new Element(TypeElement.TEXT_PLAYER1));
        m.addMap(1, 2, new Element(TypeElement.IS));
        m.addMap(1, 3, new Element(TypeElement.YOU));
        m.addMap(1, 4, new Element(TypeElement.PLAYER1));
	Board b = new Board(m);
        b.movePlayer(Directions.RIGHT);
        boolean expResult = false;
        boolean result = b.getListGrid().get(4).get(1).find(TypeElement.PLAYER1);
        assertTrue("Le joueur n'est pas partie de la cellule de depart",
                result == expResult);
	expResult = true;
        result = b.getListGrid().get(4).get(2).find(TypeElement.PLAYER1);
	assertTrue("Le joueur n'est pas arrivé sur la cellule d'arrivée",
                result == expResult);  
    }
    
    @org.junit.Test
    public void testMoveOverTheMap() {
        JFXPanel fxPanel = new JFXPanel();
        Maps m = new Maps(1,4);
        m.addMap(1, 1, new Element(TypeElement.TEXT_PLAYER1));
        m.addMap(1, 2, new Element(TypeElement.IS));
        m.addMap(1, 3, new Element(TypeElement.YOU));
        m.addMap(1, 4, new Element(TypeElement.PLAYER1));
	Board b = new Board(m);
        b.movePlayer(Directions.DOWN);
        boolean expResult = true;
        boolean result = b.getListGrid().get(4).get(1).find(TypeElement.PLAYER1);
	assertTrue("Le joueur a bougé alors qu'il était bloqué par la limite de la carte",
                result == expResult);  
    }
    
    @org.junit.Test
    public void testWin() {
        JFXPanel fxPanel = new JFXPanel();
        Maps m1 = new Maps(3,4);
        m1.addMap(1, 1, new Element(TypeElement.TEXT_PLAYER1));
        m1.addMap(1, 2, new Element(TypeElement.IS));
        m1.addMap(1, 3, new Element(TypeElement.YOU));
        m1.addMap(1, 4, new Element(TypeElement.PLAYER1));
        m1.addMap(2, 1, new Element(TypeElement.TEXT_FLAG));
        m1.addMap(2, 2, new Element(TypeElement.IS));
        m1.addMap(2, 3, new Element(TypeElement.WIN));
        m1.addMap(2, 4, new Element(TypeElement.FLAG));
        Board b = new Board(m1);
        try {
            boolean test = !b.getRule().check(new Position(1,4), Directions.RIGHT, TypeElement.PLAYER1);
            assertTrue("Le board indique que le niveau n'est pas reussi alors qu'il devrais etre reussi", false);
        } catch (WinException e) {
        }
    }
    
}
