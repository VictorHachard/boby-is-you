package common.view;

import common.model.Game;
import common.model.GameMode;
import common.model.Levels;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Windows
 */
public class MenuNew {
    
    Scene scene;
    private Stage primaryStage = MenuInit.getInstance().getStage();
    private Pane root = new Pane();
    private static MenuNew INSTANCE = null;
    
    public static MenuNew getInstance() {           
        if (INSTANCE == null)  
            INSTANCE = new MenuNew();
        return INSTANCE;
    }
    
    public Stage getStage() {
        return this.primaryStage;
    }
    
    MenuNew() {
        this.scene = new Scene(root,JavaBobyIsYou.WIDTH,JavaBobyIsYou.HEIGHT);
        scene.getStylesheets().add(JavaBobyIsYou.THEME);
        root.getChildren().add(JavaFXMethode.addTitle("ESC"));
        addMenu();
    }
    
     private void addMenu() {
        VBox vbox = new VBox();

        Button buttonBack = new Button("Menu");
        Button buttonNormal = new Button("Normal");
        Button buttonTimer = new Button("Temps limité");
        Button buttonLimited = new Button("Deplacement limité");
	
        vbox.getChildren().addAll(
                buttonNormal,
                buttonTimer,
                buttonLimited,
                buttonBack);
        
        vbox.setSpacing(20);
        vbox.setMinWidth(200);
        
        root.getChildren().addAll(vbox);
        vbox.setTranslateX((JavaBobyIsYou.WIDTH/2)-100);
	vbox.setTranslateY((JavaBobyIsYou.HEIGHT/2)-70);

        buttonBack.setOnAction(event -> {
            this.primaryStage.setScene(MenuInit.getInstance().scene);
        });
        buttonNormal.setOnAction(event -> {
           Levels.getInstance().setIndice(0);
           Levels.instance().loadGame();
        });
        buttonLimited.setOnAction(event -> {
           Levels.getInstance().setIndice(0);
           GameMode.setActivity(Game.PLAYERMOVE, Boolean.TRUE);
           Levels.instance().loadGame();
        });
        buttonTimer.setOnAction(event -> {
           Levels.getInstance().setIndice(0);
           GameMode.setActivity(Game.TIMER, Boolean.TRUE);
           Levels.instance().loadGame();
        });
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE)
                this.primaryStage.setScene(MenuInit.getInstance().scene);
            e.consume();
        });

    }                       
    
}
