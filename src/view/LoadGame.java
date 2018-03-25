package view;

import exeptions.TypeElementNotFoundException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.Stage;
import model.GameModeNormal;
import model.Maps;

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
    LoadGame(File f, Stage primaryStage) {
        this.primaryStage = primaryStage;
        try {
                Maps m = new Maps(f);
                GameModeNormal g = new GameModeNormal(m);
                Display d = new Display(g.getBoard(),primaryStage,f);
                this.primaryStage.setScene(d.scene);                
            } catch (TypeElementNotFoundException ex) {
                //RIEN Erreur deja traiter en amont
            } catch (IOException ex) {
                Logger.getLogger(MenuInit.class.getName()).log(Level.SEVERE, null, ex);
            }
    }    
}
