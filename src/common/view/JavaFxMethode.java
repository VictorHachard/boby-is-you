package common.view;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author Windows
 */
public class JavaFXMethode {
    
    public static ImageView addBackGround(String string) {
        ImageView imageView = new ImageView(new Image(string));
        imageView.setFitWidth(JavaBobyIsYou.WIDTH);
        imageView.setFitHeight(JavaBobyIsYou.HEIGHT);
        return imageView;
    }

    public static Background addColoredBackGround(Color c) {
        return new Background(new BackgroundFill(c,CornerRadii.EMPTY, Insets.EMPTY));
    }
    
    
    public static Text addTitle(String s,Color c,int i) {
        Text t = new Text(s);
        t.setFont(Font.loadFont(loadFont(),i));
        t.setFill(c);
        t.setTranslateX(JavaBobyIsYou.WIDTH/2-t.getLayoutBounds().getWidth()/2);
        t.setTranslateY(JavaBobyIsYou.HEIGHT/3);
        return t;
    }
    
    public static Text addTitle(String string,Color color) {
        return addTitle(string,color,40);
    }
    
    public static InputStream loadFont() {
        InputStream is = null;
        try {
            URL uri = JavaBobyIsYou.class.getResource("/common/ressources/fonts/Minecraft.ttf");
            is = uri.openStream();
            return is;
        } catch (IOException ex) {
            //TODO
        } return null;
    }
}
