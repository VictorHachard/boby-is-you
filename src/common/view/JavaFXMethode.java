package common.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author Windows
 */
public class JavaFXMethode {
    
    public static ImageView addBackground(String string) {
        ImageView imageView = new ImageView(new Image(string));
        imageView.setFitWidth(JavaBobyIsYou.WIDTH);
        imageView.setFitHeight(JavaBobyIsYou.HEIGHT);
        return imageView;
    }

    public static Text addTitle(String string) {
        Text title = new Text(string);
        String file = "common/fonts/Minecraft.ttf";
        /*title.setFont(Font.loadFont(file,40));
        text.setFill(Color.WHITE);*/
        title.setTranslateX(JavaBobyIsYou.WIDTH / 2 - title.getLayoutBounds().getWidth() / 2);
        title.setTranslateY(JavaBobyIsYou.HEIGHT / 3);
        return title;
    }
}
