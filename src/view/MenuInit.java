package view;

import exeptions.TypeElementNotFoundException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import javafx.stage.Stage;
import model.Board;
import model.GameModeNormal;
import model.Maps;
/**
 *
 * @author Glaskani
 */
public class MenuInit extends Menu {
    
    Scene scene;
    private Stage primaryStage;
    private Pane root;
    private Board b;
    
    public MenuInit(int x, int y) {
        root = new Pane();
        scene = new Scene(root, x, y);
        
            /*File fi = new File("src" + File.separator + "images" + File.separator + "WALL.png"); //get last save
            ImageView img = new ImageView(new Image(fi.getAbsolutePath()));
            img.setFitWidth(1050);
            img.setFitHeight(600);
            root.getChildren().add(img);*/
            
	Text title = new Text ("C A M P A I G N");
	title.setTranslateX(50);
	title.setTranslateY(200);
               
	VBox vbox = new VBox();
        
        Button buttonContinue = new Button("Continuer");
            buttonContinue.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
        Button buttonNew = new Button("Nouveau");
            buttonNew.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
        Button buttonExit = new Button("Exit");
            buttonExit.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
        Button buttonLoad = new Button("Charger");
            buttonLoad.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
        Button buttonEditor = new Button("Editeur");
            buttonEditor.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
                
	vbox.setTranslateX(0);
	vbox.setTranslateY(0);
	
        vbox.getChildren().addAll(title,
                buttonContinue,
                buttonNew,
                buttonLoad,
                buttonEditor,
                buttonExit);
	root.getChildren().addAll(vbox);
	
        buttonContinue.setOnAction(event -> {
            File f = new File("src" + File.separator + "maps" + File.separator + "map1.txt"); //get last save
            f.getAbsolutePath();
            LoadGame l = new LoadGame(f, primaryStage);
        });
        buttonNew.setOnAction(event -> {
                System.out.println("Option 3 selected via Lambda");
        });
        buttonLoad.setOnAction(event -> {
                System.out.println("Option 3 selected via Lambda");
        });
        buttonEditor.setOnAction(event -> {
                System.out.println("Option 3 selected via Lambda");
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
