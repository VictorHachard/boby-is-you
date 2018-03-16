/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import view.javaFxMethodForGames;
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

/*
 *
 * @author Glaskani
 */
public class JavaBobyIsYou extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException 
    {
        Pane root =new Pane();
        Map map = new Map("map1");
        convertBoardToImage(map.getBoard(),root);
        
        Scene scene = new Scene(root, (map.getWidth())*64, (map.getHeight())*64);
        
        primaryStage.setTitle("BabaIsYou");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    /**
     * cree une image
     * @param name
     * @return ImageView une image prete a etre affichée
     */
    public static void addImage(String name,Pane root,int posx,int posy)
    {
        String imageURI = new File("image/"+name+".png").toURI().toString();
        Image image_= new Image(imageURI);
        ImageView image = new ImageView(image_);
        image.setFitHeight(64);
        image.setFitWidth(64);
        moveImageByCase(image,posx,posy);
        root.getChildren().add(image);
    }
    /**
     * 
     * @param board 
     */
    public static void convertBoardToImage(ArrayList[][] board,Pane root)
    {
        String objectName;
        ImageView image;

        int posx;
        int posy;
        
        for (int i = 0 ; i < board.length ; i++ )
        {
            for (int j = 0 ; j < board[0].length ; j++)
            {
                for (int k = 0 ;k < board[i][j].size() ; k++)
                {   
              
                    gameObject = (GameObject) board[i][j].get(k);
                    
                    posx = gameObject.getPosX();
                    posy = gameObject.getPosY();
                    
                    objectName = gameObject.getClass().getSimpleName();
                    // besoin d'une method qui retourne le nom de l'object
                    // liste des noms possible (Baba,Flag,Grass,Ice,Keke,Lava,Metal,Rock,TextBaba,TextFlag,TextIs,TextPush,TextRock,TextStop,TextWall,TextWin,TextYou,Wall,Water)
                    addImage(objectName,root,posx,posy);     
                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
