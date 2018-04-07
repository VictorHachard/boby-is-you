package common.view;

import common.exeptions.TypeElementNotFoundException;
import common.model.Board;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import common.model.Levels;
import common.model.Maps;
import javafx.scene.input.KeyCode;

/**
 * 
 * @author Glaskani basé sur le model de Almas Baimagambetov (almaslvl@gmail.com)
 */
public class MenuInit extends Menu {
    
    public Scene scene;
    private Stage primaryStage;
    private Pane root = new Pane();
    private static MenuInit INSTANCE = null;
    
    public static MenuInit getInstance() {           
        if (INSTANCE == null)  
            INSTANCE = new MenuInit();
        return INSTANCE;
    }
    
    public Stage getStage() {
        return this.primaryStage;
    }

    MenuInit() {
        //creatation des autre menus
        this.scene = new Scene(root,JavaBobyIsYou.WIDTH,JavaBobyIsYou.HEIGHT);
        scene.getStylesheets().add(JavaBobyIsYou.THEME);
        root.getChildren().add(JavaFXMethode.addBackground("common/images/empty.png"));
        root.getChildren().add(JavaFXMethode.addTitle("Boby Is You"));
        addMenu();
    }

    private void addMenu() {
        VBox vbox = new VBox();

        Button buttonContinue = new Button("Continuer");
        Button buttonNew = new Button("Nouveau");
        Button buttonExit = new Button("Exit");
        Button buttonLoad = new Button("Charger");
        Button buttonEditor = new Button("Editeur");
	Button buttonParameter = new Button("Parametre");
            
        vbox.getChildren().addAll(
                buttonContinue,
                buttonNew,
                buttonLoad,
                buttonParameter,
                buttonEditor,
                buttonExit);
        
        vbox.setSpacing(20);
        vbox.setMinWidth(200);
        
        root.getChildren().addAll(vbox);
        vbox.setTranslateX((JavaBobyIsYou.WIDTH/2)-100);
	vbox.setTranslateY((JavaBobyIsYou.HEIGHT/2)-70);
	
        buttonParameter.setOnAction(event -> {    
            this.primaryStage.setScene(Parameter.getInstance().scene);
        });
        buttonContinue.setOnAction(event -> {    
            Levels.getInstance().loadGame();
        });
        buttonNew.setOnAction(event -> {
                System.out.println("Option 3 selected via Lambda");
        });
        buttonLoad.setOnAction(event -> {
                System.out.println("Option 3 selected via Lambda");
        });
        buttonEditor.setOnAction(event -> {
           /* File f = new File("." + File.separator + "maps" + File.separator + "map1.txt"); //get last save
            f.getAbsolutePath();
            Maps m;
            try {
                m = new Maps(f);
                Editor editor = new Editor(m);
                this.primaryStage.setScene(editor.scene);  
            } catch (TypeElementNotFoundException ex) {
                Logger.getLogger(MenuInit.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MenuInit.class.getName()).log(Level.SEVERE, null, ex);
            }*/
                
        });
        buttonExit.setOnAction(event -> {
            Logger.getLogger(MenuInit.class.getName()).log(Level.INFO, "Exit of the application");
            primaryStage.close();
        });
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                Logger.getLogger(MenuInit.class.getName()).log(Level.INFO, "Exit of the application");
                primaryStage.close();
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
    
    public void LoadGame(Maps m) {
        try {
            Display d = new Display(new Board(m));
            this.primaryStage.setScene(d.scene);                
        } catch (TypeElementNotFoundException ex) {
            //RIEN Erreur deja traiter en amont
        }
    }    
}
