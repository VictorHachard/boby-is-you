package view;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import model.Board;
/**
 *
 * @author Glaskani
 */
public class MenuInit extends Menu {
    
    Scene scene;
    private Pane root;
    private Board b;
    
    public MenuInit(int x, int y) {
        root = new Pane();
        scene = new Scene(root, x, y);
        
        try(InputStream is = Files.newInputStream(Paths.get("WALL.png"))){
            ImageView img = new ImageView(new Image("src" + File.separator + "maps" + File.separator + "WALL.png"));
            img.setFitWidth(1050);
            img.setFitHeight(600);
            root.getChildren().add(img);
	}
	catch(IOException e) {
            System.out.println("Couldn't load image");
	}
		
	Text title = new Text ("C A M P A I G N");
	title.setTranslateX(50);
	title.setTranslateY(200);
        
        Button menuItem1;
        MenuItem menuItem2;
        MenuItem menuItem3;
               
	VBox vbox = new VBox();
        
                menuItem1 = new Button();
                menuItem1.setText("haha");
                menuItem1.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
                menuItem2 = new MenuItem();
                menuItem2.setText("Exit");
                menuItem3 = new MenuItem("Option 3");
	vbox.setTranslateX(100);
	vbox.setTranslateY(300);
	
        vbox.getChildren().addAll(title,menuItem1);
	root.getChildren().addAll(vbox);
	
        menuItem1.setOnAction(event -> {
                System.out.println("Option 3 selected via Lambda");
        });
        menuItem2.setOnAction(event -> {
                System.out.println("Option 3 selected via Lambda");
        });
        menuItem3.setOnAction(event -> {
                System.out.println("Option 3 selected via Lambda");
        });
    }        
    
    
}
