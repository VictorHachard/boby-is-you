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
import javafx.scene.paint.Color;

/**
 *
 * @author Glaskani
 */
public class MenuEsc {

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
     * @param continuer
     */
    MenuEsc(Display continuer) {
        this.scene = new Scene(root,JavaBobyIsYou.WIDTH,JavaBobyIsYou.HEIGHT);
        scene.getStylesheets().add(JavaBobyIsYou.THEME);
        this.continuer= continuer;
        root.getChildren().add(JavaFXMethode.addTitle("ESC",Color.WHITE));
        addMenu();
    }

    private void addMenu() {
        VBox vbox = new VBox();

        Button buttonContinue = new Button("Continuer");
        Button buttonExit = new Button("Exit");
        Button buttonMenu = new Button("Menu");
        //Button buttonSave = new Button("Sauvegarder");
	
        vbox.getChildren().addAll(
                buttonContinue,
                buttonMenu,
                buttonExit);
        vbox.getStyleClass().add("vbox");
        root.getChildren().addAll(vbox);
        vbox.setTranslateX((JavaBobyIsYou.WIDTH/2)-90);
	vbox.setTranslateY((JavaBobyIsYou.HEIGHT/2)-70);

        buttonContinue.setOnAction(event -> {
            this.primaryStage.setScene(continuer.scene);vbox.getStyleClass().add("vbox");
        root.getChildren().addAll(vbox);
        vbox.setTranslateX((JavaBobyIsYou.WIDTH/2)-90);
	vbox.setTranslateY((JavaBobyIsYou.HEIGHT/2)-70);
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
