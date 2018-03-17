package view;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Board;
import model.Placement;
import model.TypeElements;
import static view.javaFxMethodForGames.moveImageByCase;

/*
 *
 * @author Glaskani
 */
public class JavaBobyIsYou extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Map map = new Map("map1");
       
        Scene scene = initScene(map);
        
        primaryStage.setTitle("BabaIsYou");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * importe une image et qui l'ajoute a un pane passer en parametre
     * @param name nom de l'image à importer
     * @param root object de type Pane
     * @param posx un entier qui indique la position sur l'axe des x
     * @param posy un entier qui indique la position sur l'axe des y 
     */
    public static void addImage(TypeElements name,Pane root,int posx,int posy) {
        String imageURI = new File("image/"+name+".png").toURI().toString();
        Image image_= new Image(imageURI);
        ImageView image = new ImageView(image_);
        image.setFitHeight(64);
        image.setFitWidth(64);
        moveImageByCase(image,posx,posy);
        root.getChildren().add(image);
    }
    
    /**
     * ajoute une image a la un objet Pane a chaque iteration c'est à dire pour chaque objet present dans board
     * @param board un plateau de jeux contenatn les elements du jeux
     * @param root un objet de type Pane
     */
    public static void convertBoardToImage(Board board,Pane root) {       
        for(int i=0;i<board.getSizeX();i++) {
            for(int j=0;j<board.getSizeY();j++) {
                for(int k=0;k<board.getListGrid().get(i).get(j).getListeContenu().size();k++) {   
                    TypeElements objectName = board.getListGrid().get(i).get(j).getListeContenu().get(k).getTypeElements();
                    addImage(objectName,root,i,j);     
                }
            }
        }
    }
    
    /**
     * cree une scene a partir d'un objet map
     * @param map un objet qui a un attribut qui contient board
     * @return 
     */
    public static Scene initScene(Map map) {
        Pane root =new Pane();
        convertBoardToImage(map.getBoard(),root);  
        Scene scene = new Scene(root, (map.getWidth())*64, (map.getHeight())*64);
        return scene;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}