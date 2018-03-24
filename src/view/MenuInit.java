package view;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import javafx.stage.Stage;
import model.Board;
import model.Directions;

/**
 * //singleton
 * @author Glaskani
 */
public class MenuInit extends Menu {
    
    Scene scene;
    private Stage primaryStage;
    private Pane root;
    private Board b;
    private static MenuInit INSTANCE = null;
    
    public static MenuInit getInstance(double x, double y) {           
        if (INSTANCE == null)  
            INSTANCE = new MenuInit(x,y);
        return INSTANCE;
    }
    
    /**
     * 
     * @param x
     * @param y 
     */
    private MenuInit(double x, double y) {
        root = new Pane();
        scene = new Scene(root, x, y);
        
            /*File fi = new File("." + File.separator + "images" + File.separator + "WALL.png"); //get last save
            ImageView img = new ImageView(new Image(fi.getAbsolutePath()));
            img.setFitWidth(x);
            img.setFitHeight(y);
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
                
	vbox.setTranslateX(x/2);
	vbox.setTranslateY(y/2);
	
        vbox.getChildren().addAll(title,
                buttonContinue,
                buttonNew,
                buttonLoad,
                buttonEditor,
                buttonExit);
	root.getChildren().addAll(vbox);
	
        buttonContinue.setOnAction(event -> {
            File f = new File("." + File.separator + "maps" + File.separator + "map1.txt"); //get last save
            f.getAbsolutePath();
            LoadGame l = new LoadGame(f, primaryStage,x,y);
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
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ESCAPE:
                    Logger.getLogger(MenuInit.class.getName()).log(Level.INFO, "Exit of the application");
                    primaryStage.close();
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
