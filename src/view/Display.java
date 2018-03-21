package view;

import java.io.File;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.Board;
import model.Directions;
import model.TypeElement;
import static view.JavaBobyIsYou.b;

/**
 *
 * @author Glaskani
 */
public class Display {
       
    Scene scene;
    private Pane root;
    private Board board;
    
    /**
     * 
     * @param board
     */
    Display(Board board) {
        this.board = board;
        root =new Pane();
        convertBoardToImage();  
        scene = new Scene(root, (this.board.getSizeX())*64, (this.board.getSizeY())*64);
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
                //case r:
                    //reload
                    //break;
                //case escap:
                    //menu du board
                    //break;
                //case Win:
                    //niveau suivant + save
                    //break;
                default :
                    //NE RIEN FAIRE
            }
            //Observer et observateur 
            convertBoardToImage();
            e.consume();
        });
    }
        
    /**
     * importe une image et qui l'ajoute a un pane passer en parametre
     * @param name nom de l'image à importer
     * @param posx un entier qui indique la position sur l'axe des x
     * @param posy un entier qui indique la position sur l'axe des y 
     */
    private void addImage(TypeElement name,int posx,int posy) {
        ImageView image = new ImageView(new Image(new File("C:\\Users\\Glaskani\\OneDrive\\BobyIsYou\\src\\images\\"+name+".png").toURI().toString()));
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
    private void moveImageByCase(ImageView image,int x,int y) {
        x = x*64;
        y = y*64;
        image.setTranslateX(x);
        image.setTranslateY(y);
    }
    
    /**
     * ajoute une image a la un objet Pane à chaque iteration c'est à dire pour
     * chaque objet present dans board
     */
    private void convertBoardToImage() {       
        for(int i=0;i<this.board.getSizeX();i++) {
            for(int j=0;j<this.board.getSizeY();j++) {
                for(int k=0;k<this.board.getListGrid().get(j).get(i)
                        .getListeContenu().size();k++) {   
                    TypeElement objectName = this.board.getListGrid().get(j)
                            .get(i).getListeContenu().get(k).getTypeElements();
                    addImage(objectName,i+1,j+1);
                }
            }
        }
    }
    
}
