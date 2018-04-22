package common.view;

import common.model.Achievement;
import common.model.CheckAchievement;
import java.util.HashMap;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Glaskani
 */
public class MenuAchievement {
    
    private Pane root = new Pane();
    Scene scene;
    private static MenuAchievement INSTANCE = null;
    
    static MenuAchievement getInstance() {           
        //if (INSTANCE == null)  
            INSTANCE = new MenuAchievement();
        return INSTANCE;
    }
    
    MenuAchievement() {
        this.scene = new Scene(root,JavaBobyIsYou.WIDTH,JavaBobyIsYou.HEIGHT);
        scene.getStylesheets().add(JavaBobyIsYou.THEME);
        root.getChildren().add(JavaFXMethode.addTitle("Achievement",Color.WHITE));
        HashMap<Achievement, Integer> data = CheckAchievement.getInstance().getData();
        VBox b = new VBox();
        b.setMinWidth(JavaBobyIsYou.WIDTH);
        for(HashMap.Entry<Achievement, Integer> d : data.entrySet()) {
            b.getChildren().add(JavaFXMethode.addTitle(d.getKey()+" "+d.getValue(),Color.WHITE,14));
        }
    }
    
}
