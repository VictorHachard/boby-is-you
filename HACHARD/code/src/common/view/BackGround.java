package common.view;

import common.model.Board;
import common.model.Element;
import common.model.Maps;
import common.model.TypeElement;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Windows
 */
public class BackGround {
    
    private Maps maps;
    private GaussianBlur gb = new GaussianBlur(4);
    private Board board;
    private GridPane root= new GridPane();
    private double imageSize;
    private Pane rootImage  = new Pane();
    private double imageSizeX;
    private static BackGround INSTANCE = null;
    
    public static BackGround getInstance() {           
        if (INSTANCE == null)
            INSTANCE = new BackGround();
        return INSTANCE;
    }
    
    private BackGround() {
        InputStream is = null;
            try {
                URL uri = JavaBobyIsYou.class.getResource("/common/ressources/maps/mapa.txt");
                is = uri.openStream();
                InputStreamReader ipsr = new InputStreamReader(is);
                try (BufferedReader br = new BufferedReader(ipsr)) {
                    maps = new Maps(br);
                }
                is.close();
            } catch (IOException ex) {
                //LOGGER.log(Level.SEVERE,"coud not load file",ex);
            } 
        final Rectangle r = new Rectangle((int)JavaBobyIsYou.WIDTH,(int)JavaBobyIsYou.HEIGHT);
        r.setFill(new Color(0,0,0,0.7));
        board = new Board(maps);
        imageSizeX = JavaBobyIsYou.WIDTH/this.board.getSizeX();
        double imageSizeY = (JavaBobyIsYou.HEIGHT)/this.board.getSizeY();
        //recuperation du plus grand ImageSize
        if (imageSizeX < imageSizeY)
            imageSize = imageSizeX;
        else imageSize = imageSizeY;
        convertBoardToImage(); 
        rootImage.setBackground(JavaFXMethode.addColoredBackGround(Color.BROWN));
        rootImage.getChildren().addAll(r);
    }
    
    Pane getPane() {
        return this.rootImage;
    }
    
    /**
     * importe une image et qui l'ajoute a un pane passer en parametre
     * @param name nom de l'image à importer
     * @param posx un entier qui indique la position sur l'axe des x
     * @param posy un entier qui indique la position sur l'axe des y 
     */
    private void addImage(TypeElement name,int posx,int posy) {
        ImageView image = new ImageView(ImageHashMap.getInstance().getImageMap().get(name));
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
        image.setEffect(gb);
    }
    
    /**
     * ajoute une image a la un objet Pane à chaque iteration c'est à dire pour
     * chaque objet present dans board
     */
    private void convertBoardToImage() {    
        //temporaire supprimer le root pour le rempalcer cela evite d'avoir tout les root en memoir
        rootImage.getChildren().remove(root);
        this.root = new GridPane();
        rootImage.getChildren().add(root);
        for(int i=0;i<this.board.getSizeX();i++)
            for(int j=0;j<this.board.getSizeY();j++)
                for (Element e:this.board.getListGrid().get(j).get(i).getZ()) {
                    addImage(e.getTypeElement(),i,j);
                    if (this.board.best(e.getTypeElement()))
                        addImage(TypeElement.BESTELEME,i,j);
                }
    }
    
}
