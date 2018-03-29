package view;

import exeptions.TypeElementNotFoundException;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;
import model.GameModeNormal;
import model.Levels;
import model.Maps;

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
    
    
    private List<Pair<String, Runnable>> menuData = Arrays.asList(
            new Pair<String, Runnable>("Continue", () -> {}),
            new Pair<String, Runnable>("Nouveau", () -> {}),
            new Pair<String, Runnable>("Parametre", () -> {}),
            new Pair<String, Runnable>("Editeur", () -> {}),
            new Pair<String, Runnable>("Exit to Desktop", Platform::exit)
    );

    MenuInit() {
        this.scene = new Scene(root,JavaBobyIsYou.WIDTH,JavaBobyIsYou.HEIGHT);
        addBackground();
        addTitle();
        addMenu();
    }

    private void addButton() {
        for (Pair p:menuData) {
            
        }
            
    }
    
    private void addBackground() {
        Image file = new Image (new File("C:\\Users\\Glaskani\\OneDrive\\BobyIsYou\\src\\images\\EMPTY.png").toURI().toString());
        ImageView imageView = new ImageView(file);
        imageView.setFitWidth(JavaBobyIsYou.WIDTH);
        imageView.setFitHeight(JavaBobyIsYou.HEIGHT);

        root.getChildren().add(imageView);
    }

    private void addTitle() {
        Title title = new Title("CIVILIZATION VI");
        title.setTranslateX(JavaBobyIsYou.WIDTH / 2 - title.getTitleWidth() / 2);
        title.setTranslateY(JavaBobyIsYou.HEIGHT / 3);

        root.getChildren().add(title);
    }

    private void addMenu() {
        VBox vbox = new VBox();

        Button buttonContinue = new Button("Continuer");
            buttonContinue.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
            buttonContinue.setMaxWidth(Double.MAX_VALUE);
        Button buttonNew = new Button("Nouveau");
            buttonNew.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
        Button buttonExit = new Button("Exit");
            buttonExit.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
            buttonExit.setMaxWidth(Double.MAX_VALUE);
        Button buttonLoad = new Button("Charger");
            buttonLoad.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
        Button buttonEditor = new Button("Editeur");
            buttonEditor.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
	
        vbox.getChildren().addAll(
                buttonContinue,
                buttonNew,
                buttonLoad,
                buttonEditor,
                buttonExit);
        
        vbox.setSpacing(20);
        vbox.setMinWidth(200);
        
        root.getChildren().addAll(vbox);
        vbox.setTranslateX((JavaBobyIsYou.WIDTH/2)-100);
	vbox.setTranslateY((JavaBobyIsYou.HEIGHT/2)-70);
	
        buttonContinue.setOnAction(event -> {
            try {
                Levels lev = Levels.getInstance();
            } catch (TypeElementNotFoundException ex) {
                Logger.getLogger(MenuInit.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MenuInit.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        buttonNew.setOnAction(event -> {
                System.out.println("Option 3 selected via Lambda");
        });
        buttonLoad.setOnAction(event -> {
                System.out.println("Option 3 selected via Lambda");
        });
        buttonEditor.setOnAction(event -> {
            File f = new File("." + File.separator + "maps" + File.separator + "map1.txt"); //get last save
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
            }
                
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
    
    public void LoadGame(Maps m) {
        try {
                GameModeNormal g = new GameModeNormal(m);
                Display d = new Display(g.getBoard(),primaryStage);
                this.primaryStage.setScene(d.scene);                
            } catch (TypeElementNotFoundException ex) {
                //RIEN Erreur deja traiter en amont
            } catch (IOException ex) {
                Logger.getLogger(MenuInit.class.getName()).log(Level.SEVERE, null, ex);
            }
    }    
}
