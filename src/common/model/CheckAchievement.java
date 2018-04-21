package common.model;

import common.view.Display;
import common.view.JavaBobyIsYou;
import common.view.JavaFXMethode;
import java.util.HashMap;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
/**
 *
 * @author Glaskani
 */
public class CheckAchievement {
    
    private HashMap<Achievement, Integer> data = new HashMap<>();
    public Display d;
    private Text text = new Text();
    private Text description = new Text();
    private static CheckAchievement INSTANCE = null;
    
    public static CheckAchievement getInstance() {           
        if (INSTANCE == null)  
            INSTANCE = new CheckAchievement();
        return INSTANCE;
    }
    
    CheckAchievement() {
        this.data.put(Achievement.MOVE, 0);
        this.data.put(Achievement.WIN, 0);
    }
    
    private void show(Achievement e,String i) {
        VBox b = new VBox();
        b.setMinWidth(800);
        b.setSpacing(16);
        text.setText(e.getName());
        text.setFont(Font.loadFont(JavaFXMethode.loadFont(), 40));
        text.setFill(Color.WHITE);
        text.setTranslateX(400-(text.getLayoutBounds().getWidth()/2));
        description.setText(e.getDescription()+" "+i);
        description.setFont(Font.loadFont(JavaFXMethode.loadFont(), 20));
        description.setFill(Color.WHITE);
        description.setTranslateX(400-(description.getLayoutBounds().getWidth()/2));
        b.setTranslateX((JavaBobyIsYou.WIDTH/2)-400);
        b.setTranslateY((JavaBobyIsYou.HEIGHT/2)-100);
        b.getChildren().addAll(text,description);
        d.show=true;
        d.vb=b;
    }
    
    public void checkMove() {
        data.put(Achievement.MOVE, data.get(Achievement.MOVE) + 1);
        switch (data.get(Achievement.MOVE)) {
            case 10: 
            show(Achievement.MOVE,"10");
            break;
	case 100: 
            show(Achievement.MOVE,"100");
            break;
	case 1000: 
            show(Achievement.MOVE,"1000");
            break;
	case 10000: 
            show(Achievement.MOVE,"10000");
            break;
	}
    }
    
    public void checkWin() {
        data.put(Achievement.WIN, data.get(Achievement.WIN) + 1);
        switch (data.get(Achievement.WIN)) {
            case 1: 
            show(Achievement.WIN,"a game");
            break;
	case 10: 
            show(Achievement.WIN,"10");
            break;
	case 100: 
            show(Achievement.WIN,"100");
            break;
	case 1000: 
            show(Achievement.WIN,"1000");
            break;
	}
    }
    
}
