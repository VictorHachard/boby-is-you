package common.view;

import common.model.Achievement;
import common.model.CheckAchievement;
import java.util.HashMap;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author Glaskani
 */
public class MenuAchievement {
    
    private Pane root = new Pane();
    Scene scene;
    
    MenuAchievement() {
        this.scene = new Scene(root,JavaBobyIsYou.WIDTH,JavaBobyIsYou.HEIGHT);
        scene.getStylesheets().add(JavaBobyIsYou.THEME);
        root.getChildren().add(JavaFXMethode.addTitle("Achievement",Color.BLACK));
        HashMap<Achievement, Integer> data = CheckAchievement.getInstance().getData();
        VBox b = new VBox();
        b.setTranslateY(40);
        b.setSpacing(10);
        b.setMinWidth(JavaBobyIsYou.WIDTH);
        b.setMinHeight(JavaBobyIsYou.HEIGHT-40);
        for(HashMap.Entry<Achievement, Integer> d : data.entrySet())
            b.getChildren().add(JavaFXMethode.addTitle(d.getKey()+" "+d.getValue(),Color.BLACK,20));
        root.getChildren().add(b);
    }
    
}
