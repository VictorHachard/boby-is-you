package view;

import java.awt.Panel;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Board;

/**
 *
 * @author Glaskani
 */
public class MenuEsc extends Menu {

    private Stage primaryStage;
    private Pane root;
    Scene scene;
    
    /**
     * 
     * @param primaryStage
     * @param continuer
     * @param board 
     */
    MenuEsc(Stage primaryStage,Display continuer,Board board) {
        this.primaryStage = primaryStage;
        root = new Pane();
        
        scene = new Scene(root, 1280, 720);
        //scene.setOpacity(0.2);

        Text title = new Text ("Parametre");
	title.setTranslateX(1280/2);
	title.setTranslateY(720/3);
               
	VBox vbox = new VBox();
        
        Button buttonContinue = new Button("Continuer");
            buttonContinue.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
        Button buttonExit = new Button("Exit");
            buttonExit.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
        Button buttonMenu = new Button("Menu");
            buttonMenu.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
        Button buttonSave = new Button("Sauvegarder");
            buttonSave.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
                
	vbox.setTranslateX(1280/2);
	vbox.setTranslateY(720/2);
	
        vbox.getChildren().addAll(title,
                buttonContinue,
                buttonSave,
                buttonExit);
	root.getChildren().addAll(vbox);
	
        buttonContinue.setOnAction(event -> {
            this.primaryStage.setScene(continuer.scene);
        });
        buttonSave.setOnAction(event -> {
            try {
                board.save();
            } catch (IOException ex) {
                Logger.getLogger(MenuEsc.class.getName()).log(Level.SEVERE, null, ex);//Pas le global log 
            }
        });
        buttonMenu.setOnAction(event -> {
            //this.primaryStage.setScene(.scene);
        });
        buttonExit.setOnAction(event -> {
            Logger.getLogger(MenuInit.class.getName()).log(Level.INFO, "Exit of the application");
            primaryStage.close();
        });
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ESCAPE:
                    this.primaryStage.setScene(continuer.scene);
                    break;
                    default :
                    //NE RIEN FAIRE
            }
            e.consume();
        });
        
        
    }
    
    /**
     * 
     * @param stage 
     */
    void setStage(Stage stage) {
        primaryStage = stage;
    }
}
