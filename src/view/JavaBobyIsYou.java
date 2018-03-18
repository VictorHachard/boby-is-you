package view;


import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Board;
import static view.Display.convertBoardToImage;

/*
 *
 * @author Glaskani
 */
public class JavaBobyIsYou extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        Board b = new Board("C:\\Users\\Glaskani\\OneDrive\\BobyIsYou\\src\\maps\\map1.txt");
        //System.out.println(b.getListGrid().get(5).get(5).getListeContenu().size());
        Scene scene = initScene(b);
        
        primaryStage.setTitle("BabaIsYou");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * cree une scene a partir d'un objet map
     * @param board
     * @return Scene
     */
    public static Scene initScene(Board board) {
        Pane root =new Pane();
        convertBoardToImage(board,root);  
        Scene scene = new Scene(root, (board.getSizeX())*64, (board.getSizeY())*64);
        return scene;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}