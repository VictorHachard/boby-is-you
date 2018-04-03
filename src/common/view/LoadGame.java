package common.view;

import common.exeptions.TypeElementNotFoundException;
import common.model.Board;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.Stage;
import common.model.Maps;
import java.net.URISyntaxException;

/**
 *
 * @author Glaskani
 */
public class LoadGame {
    
    private Stage primaryStage;
    private Display d;
 
    /**
     * 
     * @param f
     * @param primaryStage 
     */
    LoadGame(BufferedReader f, Stage primaryStage) {
        this.primaryStage = primaryStage;
        try {
                Maps m = new Maps(f);
                Display d = new Display(new Board(m),primaryStage);
                this.primaryStage.setScene(d.scene);                
            } catch (TypeElementNotFoundException ex) {
                //RIEN Erreur deja traiter en amont
            } catch (IOException ex) {
                Logger.getLogger(MenuInit.class.getName()).log(Level.SEVERE, null, ex);
            } catch (URISyntaxException ex) {
            Logger.getLogger(LoadGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    LoadGame(Maps m, Stage primaryStage) {
        this.primaryStage = primaryStage;
    }    
}
