package common.view;

import common.exeptions.TypeElementNotFoundException;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import common.model.Board;
import common.model.Directions;
import common.model.Levels;
import common.model.TypeElement;
import javafx.scene.input.KeyCode;

/**
 *
 * @author Glaskani
 */
public class Display {
       
    public Scene scene;
    private GridPane root= new GridPane();
    private Board board;
    private double imageSize;
    private Stage primaryStage = MenuInit.getInstance().getStage();
    private Pane rootImage  = new Pane();;
    private double imageSizeX;
    
    /**
     * 
     * @param board
     */
    public Display(Board board) {
        this.board = board;
        MenuEsc menuEsc = new MenuEsc(this);
        
        //changement de la taille des image en fonction de la taille de la fenetre
        imageSizeX = JavaBobyIsYou.WIDTH/this.board.getSizeX();
        double imageSizeY = JavaBobyIsYou.HEIGHT/this.board.getSizeY();
        //recuperation du plus grand ImageSize
        if (imageSizeX < imageSizeY)
            imageSize = imageSizeX;
        else imageSize = imageSizeY;
        //ajout du backGroude dans rootImage
        //addBackground();
        
        //ajout de la grid dans rooImage
        rootImage.getChildren().addAll(root);
        
        scene = new Scene(rootImage, JavaBobyIsYou.WIDTH, JavaBobyIsYou.HEIGHT);
        convertBoardToImage();  
        
        scene.setOnKeyPressed(e -> {
            KeyCode keyCode = e.getCode();
            try {
                if (keyCode.equals(Key.getInstance().getKeyUP()))
                    this.board.movePlayer(Directions.UP);
                else if (keyCode.equals(Key.getInstance().getKeyDOWN()))
                    this.board.movePlayer(Directions.DOWN);
                else if (keyCode.equals(Key.getInstance().getKeyLEFT()))
                    this.board.movePlayer(Directions.LEFT);
                else if (keyCode.equals(Key.getInstance().getKeyRIGHT()))
                    this.board.movePlayer(Directions.RIGHT);
                else if (keyCode.equals(Key.getInstance().getKeyR())) 
                    Levels.getInstance().loadDisplay();
                else if (keyCode.equals(KeyCode.ESCAPE))
                    this.primaryStage.setScene(menuEsc.scene);
            }
            catch (TypeElementNotFoundException | IOException ex) {
                //deja traiter en amont
            }
            e.consume();
            convertBoardToImage();
        });
    }
    
    /**
     * importe une image et qui l'ajoute a un pane passer en parametre
     * @param name nom de l'image à importer
     * @param posx un entier qui indique la position sur l'axe des x
     * @param posy un entier qui indique la position sur l'axe des y 
     */
    private void addImage(TypeElement name,int posx,int posy) {
        ImageView image = new ImageView(JavaBobyIsYou.IMAGEMAP.getImageMap().get(name));
        image.setFitHeight(imageSize);
        image.setFitWidth(imageSize);
        moveImageByCase(image,posx,posy);
        root.getChildren().add(image);
    }
    
    /**
     * This method move one image by 64 pixels
     * @param image ImageView
     * @param x the number of case that you move this image 
     * @param y the 
     */
    private void moveImageByCase(ImageView image,double x,double y) {
        x = x*imageSize;
        y = y*imageSize;
        image.setTranslateX(x);
        image.setTranslateY(y);
    }
    
    /**
     * ajoute une image a la un objet Pane à chaque iteration c'est à dire pour
     * chaque objet present dans board
     */
    private void convertBoardToImage() {      
        //temporaire supprimer le root pour le rempalcer cela evite d'avoir tout les root en memoir
        rootImage.getChildren().remove(root);
        this.root = new GridPane();
        //Centrement de la Grid
        root.setTranslateX((JavaBobyIsYou.WIDTH/2)-((board.getSizeX()*imageSizeX)/4));
        rootImage.getChildren().addAll(root);
        for(int i=0;i<this.board.getSizeX();i++) {
            for(int j=0;j<this.board.getSizeY();j++) {
                for(int k=0;k<this.board.getListGrid().get(j).get(i)
                        .getListeContenu().size();k++) {   
                    TypeElement objectName = this.board.getListGrid().get(j)
                            .get(i).getListeContenu().get(k).getTypeElements();
                    addImage(objectName,i,j);
                }
            }
        }
    }
    
}
