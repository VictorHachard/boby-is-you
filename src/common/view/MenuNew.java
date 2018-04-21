package common.view;

import common.model.Game;
import common.model.GameMode;
import common.model.Levels;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Windows
 */
public class MenuNew {
    
    Scene scene;
    private Pane root = new Pane();
    private static MenuNew INSTANCE = null;
    
    public static MenuNew getInstance() {           
        if (INSTANCE == null)  
            INSTANCE = new MenuNew();
        return INSTANCE;
    }
    
    MenuNew() {
        this.scene = new Scene(root,JavaBobyIsYou.WIDTH,JavaBobyIsYou.HEIGHT);
        scene.getStylesheets().add(JavaBobyIsYou.THEME);
        root.getChildren().add(JavaFXMethode.addTitle("ESC",Color.WHITE));
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
        vbox.getStyleClass().add("vbox");
        root.getChildren().addAll(vbox);
        vbox.setTranslateX((JavaBobyIsYou.WIDTH/2)-90);
	vbox.setTranslateY((JavaBobyIsYou.HEIGHT/2)-70);
        
        GameMode.desactivateAll();
        
        buttonBack.setOnAction(event -> {
            MenuInit.getInstance().getStage().setScene(MenuInit.getInstance().scene);
        });
        buttonNormal.setOnAction(event -> {
           game();
        });
        buttonLimited.setOnAction(event -> {
           GameMode.setActivity(Game.PLAYERMOVE, Boolean.TRUE);
           game();
        });
        buttonTimer.setOnAction(event -> {
           GameMode.setActivity(Game.TIMER, Boolean.TRUE);
           game();
        });
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE)
                MenuInit.getInstance().getStage().setScene(MenuInit.getInstance().scene);
            e.consume();
        });

    }                     
    
    /**
     * Creation d'une partie.
     */ 
    private void game() {
        Levels.getInstance().setIndice(0);
        Levels.instance().loadGame();
        JavaBobyIsYou.save();
    } 
    
}
