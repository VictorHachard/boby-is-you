package view;

import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.Board;
import model.TypeElement;

/**
 *
 * @author Glaskani
 */
public class Display {
    
    /**
     * importe une image et qui l'ajoute a un pane passer en parametre
     * @param name nom de l'image à importer
     * @param root object de type Pane
     * @param posx un entier qui indique la position sur l'axe des x
     * @param posy un entier qui indique la position sur l'axe des y 
     */
    public static void addImage(TypeElement name,Pane root,int posx,int posy) {
        ImageView image = new ImageView(new Image(new File("C:\\Users\\Windows\\Documents\\NetBeansProjects\\BobyIsYou\\src\\images\\"+name+".png").toURI().toString()));
        //error clear les images plus utile
        image.setFitHeight(64);
        image.setFitWidth(64);
        moveImageByCase(image,posx,posy);
        root.getChildren().add(image);
    }
    
    /**
     * This method move one image by 64 pixels
     * @param image ImageView
     * @param x the number of case that you move this image 
     * @param y the 
     */
    public static void moveImageByCase(ImageView image,int x,int y) {
        x = x*64;
        y = y*64;
        image.setTranslateX(x);
        image.setTranslateY(y);
    }
    
    /**
     * ajoute une image a la un objet Pane à chaque iteration c'est à dire pour chaque objet present dans board
     * @param board un plateau de jeux contenatn les elements du jeux
     * @param root un objet de type Pane
     */
    public static void convertBoardToImage(Board board,Pane root) {       
        for(int i=0;i<board.getSizeX();i++) {
            for(int j=0;j<board.getSizeY();j++) {
                for(int k=0;k<board.getListGrid().get(j).get(i).getListeContenu().size();k++) {   
                    TypeElement objectName = board.getListGrid().get(j).get(i).getListeContenu().get(k).getTypeElements();
                    addImage(objectName,root,i+1,j+1);
                }
            }
        }
    }
    
}
