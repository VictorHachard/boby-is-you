package common.test;

import common.exeptions.WinException;
import common.model.Board;
import common.model.Directions;
import common.model.Element;
import common.model.Maps;
import common.model.Position;
import common.model.Property;
import common.model.Rule;
import common.model.TypeElement;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javafx.embed.swing.JFXPanel;
import static org.junit.Assert.*;

/**
 * tests unitaires
 * @author Glaskani
 *
 */
public class Test {
    
    @org.junit.Test
    public void testRecognitionVerticalRuleAndMoveRule() {
        JFXPanel fxPanel = new JFXPanel();
        Maps m = new Maps(4,3);
        m.addMap(2, 1, new Element(TypeElement.TEXT_WALL));
        m.addMap(2, 2, new Element(TypeElement.IS));
        m.addMap(2, 3, new Element(TypeElement.WIN));
        m.addMap(4, 1, new Element(TypeElement.TEXT_PLAYER1));
        m.addMap(4, 2, new Element(TypeElement.IS));
        m.addMap(4, 3, new Element(TypeElement.YOU));
        m.addMap(1, 3, new Element(TypeElement.PLAYER1));
	Board b = new Board(m);
        boolean expResult = true;
        boolean result = Rule.isActive(Property.WIN);
        assertTrue("La regles n'a pas ete rajouter a la liste des regles active",
                result == expResult);
        b.movePlayer(Directions.RIGHT,1);
        expResult = false;
        result = Rule.isActive(Property.WIN);
        assertTrue("La regles n'a pas ete supprimer de la liste des regles active",
                result == expResult);
    }
    
    @org.junit.Test
    public void testRecognitionHorizontalRuleAndMoveRule() {
        JFXPanel fxPanel = new JFXPanel();
        Maps m = new Maps(3,4);
        m.addMap(1, 2, new Element(TypeElement.TEXT_SKULL));
        m.addMap(2, 2, new Element(TypeElement.IS));
        m.addMap(3, 2, new Element(TypeElement.KILL));
        m.addMap(1, 4, new Element(TypeElement.TEXT_PLAYER1));
        m.addMap(2, 4, new Element(TypeElement.IS));
        m.addMap(3, 4, new Element(TypeElement.YOU));
        m.addMap(3, 1, new Element(TypeElement.PLAYER1));
	Board b = new Board(m);
        boolean expResult = true;
        boolean result = Rule.isActive(Property.KILL);
        assertTrue("La regles n'a pas ete rajouter a la liste des regles active",
                result == expResult);
                b.movePlayer(Directions.DOWN,1);
        expResult = false;
        result = Rule.isActive(Property.KILL);
        assertTrue("La regles n'a pas ete supprimer de la liste des regles active",
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
        b.movePlayer(Directions.RIGHT,1);
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
        b.movePlayer(Directions.DOWN,1);
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
        } catch (WinException e) {}
    }
    
    @org.junit.Test
    public void testRecurcivePushAndPushStop() {
        JFXPanel fxPanel = new JFXPanel();
        Maps m1 = new Maps(1,5);
        m1.addMap(1, 1, new Element(TypeElement.PLAYER1));
        m1.addMap(1, 2, new Element(TypeElement.TEXT_PLAYER1));
        m1.addMap(1, 3, new Element(TypeElement.IS));
        m1.addMap(1, 4, new Element(TypeElement.YOU));
        Board b = new Board(m1);
        b.movePlayer(Directions.DOWN,1);
        boolean expResult = true;
        boolean result = b.getListGrid().get(5).get(1).find(TypeElement.YOU);
        assertTrue("La methode recurcive push n'a pas reussi",
                result == expResult);
        b.movePlayer(Directions.DOWN,1);
        expResult = false;
        result = b.getListGrid().get(6).get(1).find(TypeElement.YOU);
        assertTrue("La methode recurcive push c'est exuter alors qu'elle n'aurai pas du",
                result == expResult);
    }
    
    @org.junit.Test
    public void testMapLoadingFromFile() {
        File test = new File("tempsFile");
	try (BufferedWriter buffer = new BufferedWriter(new FileWriter(test))) {
            buffer.write("5 5\n");
            buffer.write("baba 2 4 12\n");
            buffer.write("bug\n");
            buffer.write("\n");
            buffer.write("is 4 12\n");
            buffer.write("salut 4 12\n");
            buffer.write("is 4\n");
            buffer.write("is 4 5 hg 5df\n");
	} catch (IOException e) {
            assertTrue("Le fichier n'as pas pu être écrit.", false);
	}
	try (BufferedReader br = new BufferedReader(new FileReader(test))) {
            Maps m = new Maps(br);
	} catch (Exception e) {
            assertTrue("Le niveau n'a pas été chargé correctement car il est corrompu", false);
	}
	test.delete();
    }

    
}
