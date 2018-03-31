package common.view;

import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import common.model.Board;
import common.model.Element;
import common.model.Maps;
import common.model.Property;
import common.model.TypeElement;
import common.model.TypeTypeElement;

/**
 *
 * @author Glaskani
 */
public class Editor {
    
    Scene scene;
    private GridPane root;
    //private Label rootGrid;
    private GridPane rootGrid;
    private Maps map;
    private ImageHashMap imageMap;
    private double imageSize;
    private Stage primaryStage;
    private VBox rootList;
    
    Editor(Maps map) {
        this.map = map;
        this.root = new GridPane();
        this.rootGrid = new GridPane();
        this.rootList = new VBox();
        this.primaryStage = primaryStage;
        this.imageMap = new ImageHashMap(map);
        
        //changement de la taille des image en fonction de la taille de la fenetre
        double imageSizeX = JavaBobyIsYou.WIDTH/this.map.getSizeX();
        double imageSizeY = JavaBobyIsYou.HEIGHT/this.map.getSizeY();
        //recuperation du plus grand ImageSize
        if (imageSizeX < imageSizeY)
            imageSize = imageSizeX;
        else imageSize = imageSizeY;
        
        scene = new Scene(root, JavaBobyIsYou.WIDTH,JavaBobyIsYou.HEIGHT);
        
       // double y = JavaBobyIsYou.WIDTH - JavaBobyIsYou.WIDTH/4;
        
        rootList.setMinSize(250, JavaBobyIsYou.HEIGHT);
        
        //Put on cell (0,0)
        root.add(rootList, 0, 0);
        // Put on cell (0,1)
        root.add(rootGrid, 1, 0);
        
        /*//ajout scroll pour Vbox
        ScrollBar sc = new ScrollBar();
        sc.setMin(0);
        sc.setOrientation(Orientation.VERTICAL);
        rootList.getChildren().add(sc);
        rootList.setBackground(new Background(new BackgroundFill(Color.color(0.00, 0.00, 0.00, 0.82),CornerRadii.EMPTY, Insets.EMPTY)));
        */
        buttonVbox();
        
        convertMapToImage();

        
        
        //demande la taille du board avant de le cree oum load un fichier a editer.
        // creation d'un board board  Board board = new Maps(x,y) x y La taille total.
        // creation d'un board board  Board board = new Maps(fileName) filename fichier a editer
        //TODO consteructeur qui cree un layout de 3 un avec tout les object possiable a mettre un avec l affichage de la map et enfin un qui contien les element present dans la cell (Placement)
        // pour avoir tout la liste des elment       
        //pour avjouter une elme board.addMaps( int x, int y, Element elem); retier board.removeMaps( int x, int y, Element elem);
        // pour avoir un element board.getListElement(int x ,int y); et apres tu joue avec les indice d'une liste pour avoir un elme de la liste
        // pour avour une liste de tout les elments et bloc disponible board TypeElement[] all = TypeElement.getAll();
        
    }
    
    
    private void buttonVbox() {
        Image NORMAL_IMAGE = this.imageMap.getImageMap().get("EMPTY");
        ImageView iv = new ImageView(NORMAL_IMAGE);
        this.rootList.getChildren().add(iv);

        iv.setOnMousePressed(event -> {
                
        });
    }
    
    private void addImage(TypeElement name,int posx,int posy) {
        ImageView image = new ImageView(this.imageMap.getImageMap().get(name));
        image.setFitHeight(imageSize);
        image.setFitWidth(imageSize);
        moveImageByCase(image,posx,posy);
        rootGrid.getChildren().add(image);
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
    private void convertMapToImage() {     
        for(int i=0;i<this.map.getSizeX();i++) {
            for(int j=0;j<this.map.getSizeY();j++) {
                List<Element> te =  map.getListElement(i,j);
                for(int k=0;k<te.size();k++){
                    TypeElement objectName = this.map.getListElement(i,j).get(k).getTypeElements();
                    addImage(objectName,i,j);
                }
            }
        }       
    }
    
}
