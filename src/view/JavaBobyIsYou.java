package view;

import exeptions.TypeElementNotFoundException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import static javafx.application.Application.launch;
import javafx.scene.layout.Pane;
import model.Board;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import model.Directions;
import model.GameModeNormal;
import model.Maps;

/*
 *
 * @author Glaskani
 */
public class JavaBobyIsYou extends Application {
    
    Stage primaryStage;
    private GameModeNormal g;
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
    
    
    void test() throws TypeElementNotFoundException, IOException {
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