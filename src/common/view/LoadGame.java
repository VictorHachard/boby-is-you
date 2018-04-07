package common.view;

import common.exeptions.TypeElementNotFoundException;
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
 
    /**
     * 
     * @param f
     * @param primaryStage 
     */
    LoadGame(BufferedReader f) {
        try {
                Maps m = new Maps(f);
                Display d = new Display(new Board(m));
                MenuInit.getInstance().getStage().setScene(d.scene);                
            } catch (TypeElementNotFoundException ex) {
                //RIEN Erreur deja traiter en amont
            } catch (IOException ex) {
                Logger.getLogger(MenuInit.class.getName()).log(Level.SEVERE, null, ex);
            }
    }    
    
    LoadGame(Maps m, Stage pr) {
        MenuInit.getInstance().setStage(pr);
    }    
}
