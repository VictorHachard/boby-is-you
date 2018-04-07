package common.view;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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

    public static Text addTitle(String string,Color color) {
        Text title = new Text(string);
        title.setFont(Font.loadFont(loadFont(),40));
        title.setFill(color);
        title.setTranslateX(JavaBobyIsYou.WIDTH/2 - title.getLayoutBounds().getWidth()/2);
        title.setTranslateY(JavaBobyIsYou.HEIGHT/3);
        return title;
    }
    
    public static InputStream loadFont() {
        InputStream is = null;
        try {
            URL uri = JavaBobyIsYou.class.getResource("/common/fonts/Minecraft.ttf");
            is = uri.openStream();
            return is;
        } catch (IOException ex) {
            //TODO
        } return null;
    }
}
