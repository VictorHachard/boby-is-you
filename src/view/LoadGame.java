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
 * @author Windows
 */
public class LoadGame {
    
    private Stage primaryStage;
 
    LoadGame(File f, Stage primaryStage) {
        this.primaryStage = primaryStage;
        try {
                Maps m = new Maps(f);
                GameModeNormal g = new GameModeNormal(m);
                Display d = new Display(g.getBoard());
                this.primaryStage.setScene(d.scene);
            } catch (TypeElementNotFoundException ex) {
                Logger.getLogger(MenuInit.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MenuInit.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    void setStage(Stage stage) {
        this.primaryStage = stage;
    }
    
}
