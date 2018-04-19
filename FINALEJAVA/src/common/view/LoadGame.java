package common.view;

import common.model.Board;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.Stage;
import common.model.Maps;

/**
 *
 * @author Glaskani
 */
public class LoadGame {
 
    
    private static final Logger LOGGER = Logger.getGlobal();
    
    /**
     * 
     * @param f
     * @param primaryStage 
     */
    LoadGame(BufferedReader f) {
        try {
            Display d = new Display(new Board(new Maps(f)));
            MenuInit.getInstance().getStage().setScene(d.scene);                
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE,"Error during the creation of the Maps : ",ex);
        }
    }    
    
    LoadGame(Maps m, Stage pr) {
        MenuInit.getInstance().setStage(pr);
    }    
}
