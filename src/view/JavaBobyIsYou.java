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
    
    static Board b;
    private GameModeNormal g;
    Scene scene;

    @Override
    public void start(Stage primaryStage) throws IOException, TypeElementNotFoundException {
        
        File f = new File("src" + File.separator + "maps" + File.separator + "map1.txt");
        f.getAbsolutePath();
        System.out.println(f.getAbsolutePath());
        
        Maps m = new Maps(f);
        g = new GameModeNormal(m);
        System.out.println(m.getAffichageAdresse());
        this.b = g.getBoard();
        Display d = new Display(b);
        Scene scene = d.scene;
        
        /*MenuInit d = new MenuInit(500, 500);
        scene = d.scene;*/
        
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