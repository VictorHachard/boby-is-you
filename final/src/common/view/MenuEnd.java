package common.view;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author Glaskani
 */
public class MenuEnd {
 
    private Pane root = new Pane();
    public Scene scene;
    private static MenuEnd INSTANCE = null;
    
    public static MenuEnd getInstance() {           
        if (INSTANCE == null)
            INSTANCE = new MenuEnd();
        return INSTANCE;
    }
    
    MenuEnd() {
        VBox b = new VBox();
        this.scene = new Scene(root,JavaBobyIsYou.WIDTH,JavaBobyIsYou.HEIGHT);
        scene.getStylesheets().add(JavaBobyIsYou.THEME);
        root.getChildren().add(b);
        b.setMinWidth(JavaBobyIsYou.WIDTH);
        b.setMinHeight(JavaBobyIsYou.HEIGHT);
        b.setBackground(JavaFXMethode.addColoredBackGround(Color.BLACK));
        b.setSpacing(40);
        b.getChildren().addAll(JavaFXMethode.addTitle("Vous avez gagne",Color.WHITE),JavaFXMethode.addTitle("appuiez sur 'enter' pour retourner au menu.",Color.WHITE,16));
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER)
                MenuInit.getInstance().getStage().setScene(MenuInit.getInstance().scene);
            e.consume();
        });
    }
}
