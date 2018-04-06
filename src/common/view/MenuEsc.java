package common.view;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import common.model.Levels;
import javafx.scene.input.KeyCode;

/**
 *
 * @author Glaskani
 */
public class MenuEsc extends Menu {

    private Stage primaryStage = MenuInit.getInstance().getStage();
    private Pane root = new Pane();
    private Display continuer;
    Scene scene;
    private static MenuEsc INSTANCE = null;
    
    static MenuEsc getInstance(Display continuer) {           
        if (INSTANCE == null)  
            INSTANCE = new MenuEsc(continuer);
        return INSTANCE;
    }
    
    /**
     * 
     * @param primaryStage
     * @param continuer
     * @param board 
     */
    MenuEsc(Display continuer) {
        this.scene = new Scene(root,JavaBobyIsYou.WIDTH,JavaBobyIsYou.HEIGHT);
        this.continuer= continuer;
        //addBackground();
        addTitle();
        addMenu();
    }
    
    private void addTitle() {
        Title title = new Title("BOBY IS YOU");
        title.setTranslateX(JavaBobyIsYou.WIDTH / 2 - title.getTitleWidth() / 2);
        title.setTranslateY(JavaBobyIsYou.HEIGHT / 3);

        root.getChildren().add(title);
    }

    private void addMenu() {
        VBox vbox = new VBox();

        Button buttonContinue = new Button("Continuer");
            buttonContinue.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
            buttonContinue.setMaxWidth(Double.MAX_VALUE);
        Button buttonExit = new Button("Exit");
            buttonExit.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
            buttonExit.setMaxWidth(Double.MAX_VALUE);
        Button buttonMenu = new Button("Menu");
            buttonMenu.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
            buttonMenu.setMaxWidth(Double.MAX_VALUE);
        /*Button buttonSave = new Button("Sauvegarder");
            buttonSave.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
            buttonContinue.setMaxWidth(Double.MAX_VALUE);*/
	
        vbox.getChildren().addAll(
                buttonContinue,
                buttonMenu,
                buttonExit);
        
        vbox.setSpacing(20);
        vbox.setMinWidth(200);
        
        root.getChildren().addAll(vbox);
        vbox.setTranslateX((JavaBobyIsYou.WIDTH/2)-100);
	vbox.setTranslateY((JavaBobyIsYou.HEIGHT/2)-70);

        buttonContinue.setOnAction(event -> {
            this.primaryStage.setScene(continuer.scene);
        });
        buttonMenu.setOnAction(event -> {
           Levels.instance().stopGame();
        });
        buttonExit.setOnAction(event -> {
            Logger.getLogger(MenuInit.class.getName()).log(Level.INFO, "Exit of the application");
            primaryStage.close();
        });
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE)
                this.primaryStage.setScene(continuer.scene);
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
