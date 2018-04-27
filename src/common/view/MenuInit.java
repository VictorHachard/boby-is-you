package common.view;

import common.model.Board;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import common.model.Levels;
import common.model.Maps;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

/**
 * 
 * @author Glaskani
 */
public class MenuInit {
    
    public Scene scene;
    private Stage primaryStage;
    private final Pane root = new Pane();
    private static MenuInit INSTANCE = null;
    
    public static MenuInit getInstance() {           
        if (INSTANCE == null)  
            INSTANCE = new MenuInit();
        return INSTANCE;
    }
    
    public Stage getStage() {
        return this.primaryStage;
    }

    private MenuInit() {
        this.scene = new Scene(root,JavaBobyIsYou.WIDTH,JavaBobyIsYou.HEIGHT);
        scene.getStylesheets().add(JavaBobyIsYou.THEME);
        root.getChildren().add(JavaFXMethode.addBackGround("common/ressources/images/empty.png"));
        BackGround b = new BackGround();
        root.getChildren().add(b.getPane());
        root.getChildren().add(JavaFXMethode.addTitle("Boby Is You",Color.WHITE));
        addMenu();
    }

    private void addMenu() {
        VBox vbox = new VBox();

        Button buttonContinue = new Button("Continuer");
        Button buttonNew = new Button("Nouveau");
        Button buttonExit = new Button("Exit");
	Button buttonParameter = new Button("Parametre");
        Button buttonAcheivement = new Button("Succe");
            
        vbox.getChildren().addAll(
                buttonContinue,
                buttonNew,
                buttonAcheivement,
                buttonParameter,
                buttonExit);
        vbox.getStyleClass().add("vbox");
        root.getChildren().addAll(vbox);
        vbox.setTranslateX((JavaBobyIsYou.WIDTH/2)-90);
	vbox.setTranslateY((JavaBobyIsYou.HEIGHT/2)-70);
	
        buttonParameter.setOnAction(event -> {    
            this.primaryStage.setScene(MenuParameter.getInstance().scene);
        });
        buttonAcheivement.setOnAction(event -> {    
            this.primaryStage.setScene(new MenuAchievement().scene);
        });
        buttonContinue.setOnAction(event -> {
            if (Levels.getInstance().getContinue())
                Levels.getInstance().loadGame();
        });
        buttonNew.setOnAction(event -> {
            this.primaryStage.setScene(MenuNew.getInstance().scene);
        });
        buttonExit.setOnAction(event -> {
            primaryStage.close();
        });
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE)
                primaryStage.close();
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
    
    /**
     * Load graphiqument une Map
     * @param m Maps
     */
    public void LoadGame(Maps m) {
        Display d = new Display(new Board(m));
        this.primaryStage.setScene(d.scene);                
    }    
}
