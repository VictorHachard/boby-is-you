package view;

import exeptions.TypeElementNotFoundException;
import java.io.IOException;
import static javafx.application.Application.launch;
import javafx.scene.layout.Pane;
import model.Board;
import static view.Display.convertBoardToImage;
import javafx.application.Application;
import javafx.scene.Scene;
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

    public void start(Stage primaryStage) throws IOException, TypeElementNotFoundException {
        
        Maps m = new Maps("C:\\Users\\Windows\\Documents\\NetBeansProjects\\BobyIsYou\\src\\maps\\map1.txt");
        g = new GameModeNormal(m);
        this.b = g.getBoard();
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
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case UP:
                    b.movePlayer(Directions.UP);
                    break;
                case DOWN:
                    b.movePlayer(Directions.DOWN);
                    break;
                case RIGHT:
                    b.movePlayer(Directions.RIGHT);
                    break;
                case LEFT:
                    b.movePlayer(Directions.LEFT);
                    break;
                default :
                    //NE RIEN FAIRE
            }
            convertBoardToImage(board,root);
            e.consume();
        });
        return scene;
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