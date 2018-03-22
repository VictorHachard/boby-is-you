package view;

import exeptions.TypeElementNotFoundException;
import java.io.IOException;
import static javafx.application.Application.launch;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 *
 * @author Glaskani
 */
public class JavaBobyIsYou extends Application {
    
    Stage primaryStage;
    Scene scene;

    @Override
    public void start(Stage primaryStage) throws IOException, TypeElementNotFoundException {       
        MenuInit d = new MenuInit(1280, 720);
        d.setStage(primaryStage);
        scene = d.scene;
        
        primaryStage.setTitle("BobyIsYou");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     *
     * @param args
     * @throws TypeElementNotFoundException
     * @throws IOException
     */
    public static void main(String[] args) throws TypeElementNotFoundException, IOException {
        launch(args);        
    }
}