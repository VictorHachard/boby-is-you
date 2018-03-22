package view;

import java.awt.Panel;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Glaskani
 */
public class MenuEsc extends Menu {

    private Stage primaryStage;
    private final Panel root;
    
    MenuEsc(Stage primaryStage) {
        this.primaryStage = primaryStage;
        root = new Panel();
        
        
        Text title = new Text ("C A M P A I G N");
	title.setTranslateX(1280/2);
	title.setTranslateY(720/3);
               
	VBox vbox = new VBox();
        
        Button buttonContinue = new Button("Continuer");
            buttonContinue.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
        Button buttonExit = new Button("Exit");
            buttonExit.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
        Button buttonSave = new Button("Sauvegarder");
            buttonSave.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
                
	vbox.setTranslateX(1280/2);
	vbox.setTranslateY(720/2);
	
        vbox.getChildren().addAll(title,
                buttonContinue,
                buttonSave,
                buttonExit);
	//root.getChildren().addAll(vbox);
	
        buttonContinue.setOnAction(event -> {
            
        });
        buttonSave.setOnAction(event -> {
                System.out.println("Save");
        });
        buttonExit.setOnAction(event -> {
            Logger.getLogger(MenuInit.class.getName()).log(Level.INFO, "Exit of the application");
            primaryStage.close();
        });
        
    }
    
    void setStage(Stage stage) {
        primaryStage = stage;
    }
}
