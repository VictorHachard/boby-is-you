package view;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 * font https://www.dafont.com/fr/minecraft.font libre de droit
 */
public class Title extends Pane {
    private Text text;

    public Title(String name) {
        String spread = "";
        for (char c : name.toCharArray()) {
            spread += c + " ";
        }

        text = new Text(spread);
        String file = "C:\\Users\\Glaskani\\OneDrive\\BobyIsYou\\src\\font\\Minecraft.ttf";
        text.setFont(Font.loadFont(file,40));
        text.setFill(Color.WHITE);
        //text.setEffect(new DropShadow(30, Color.BLACK));

        getChildren().addAll(text);
    }

    double getTitleWidth() {
        return text.getLayoutBounds().getWidth();
    }

    double getTitleHeight() {
        return text.getLayoutBounds().getHeight();
    }
}
