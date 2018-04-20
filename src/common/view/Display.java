package common.view;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import common.model.Board;
import common.model.Directions;
import common.model.Element;
import common.model.Game;
import common.model.GameMode;
import common.model.Levels;
import common.model.TypeElement;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author Glaskani
 */
public class Display {
       
    private Text move = new Text();
    private Text time = new Text();
    public Scene scene;
    private GridPane root= new GridPane();
    private Board board;
    private double imageSize;
    private Stage primaryStage = MenuInit.getInstance().getStage();
    private Pane rootImage  = new Pane();;
    private double imageSizeX;
    private HBox hbox = new HBox();
    
    /**
     * 
     * @param board
     */
    public Display(Board board) {
        this.board = board;
        MenuEsc menuEsc = new MenuEsc(this);
        
        //changement de la taille des image en fonction de la taille de la fenetre
        imageSizeX = JavaBobyIsYou.WIDTH/this.board.getSizeX();
        double imageSizeY = (JavaBobyIsYou.HEIGHT-30)/this.board.getSizeY();
        //recuperation du plus grand ImageSize
        if (imageSizeX < imageSizeY)
            imageSize = imageSizeX;
        else imageSize = imageSizeY;
        //ajout du backGroude dans rootImage
        //addBackground();

        Text title = new Text(board.title);
        title.setFont(Font.loadFont(JavaFXMethode.loadFont(), 30));
        title.setFill(Color.BLACK);
        
        Text gm = new Text();
        if (GameMode.isActive(Game.TIMER) || GameMode.isActive(Game.PLAYERMOVE)) {
            gm = new Text(" - GM ");
            gm.setFont(Font.loadFont(JavaFXMethode.loadFont(), 20));
        }
        
        Text time = new Text();
        if (GameMode.isActive(Game.TIMER)) {
            time.setText("temps"+" ");
            time.setFont(Font.loadFont(JavaFXMethode.loadFont(), 20));
            time.setFill(Color.RED);
        }
        
        hbox.setTranslateX(-((JavaBobyIsYou.WIDTH/2)-((board.getSizeX()*imageSizeX)/4)));
        hbox.setTranslateY(-30);
        hbox.setLayoutX(imageSize*board.getSizeX());
        hbox.setLayoutY(30);
        hbox.getChildren().addAll(title,gm);
        
        rootImage.getChildren().addAll(hbox,root);
        scene = new Scene(rootImage, JavaBobyIsYou.WIDTH, JavaBobyIsYou.HEIGHT);
        convertBoardToImage();  
        gmMove();
        
        scene.setOnKeyPressed(e -> {
            KeyCode keyCode = e.getCode();
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
            else if (keyCode.equals(KeyCode.SUBTRACT))
                Levels.getInstance().switchMaps(false);
            else if (keyCode.equals(KeyCode.ADD))
                Levels.getInstance().switchMaps(true);
            else if (keyCode.equals(KeyCode.ESCAPE))
                this.primaryStage.setScene(menuEsc.scene);
            e.consume();
            convertBoardToImage();
            gmMove();
        });
    }
    
    private void gmMove() {
        if (GameMode.isActive(Game.PLAYERMOVE)) {
            move.setText("deplacement "+Integer.toString(board.limitedDeplacement)+" ");
            move.setFont(Font.loadFont(JavaFXMethode.loadFont(), 20));
            move.setFill(Color.RED);
        }
        hbox.getChildren().remove(move);
        hbox.getChildren().add(move);
    }
    
    private void gmTime() {
        if (GameMode.isActive(Game.TIMER)) {
            time.setText("temps "+Integer.toString(board.getTime())+" ");
            time.setFont(Font.loadFont(JavaFXMethode.loadFont(), 20));
            time.setFill(Color.RED);
        }
        hbox.getChildren().remove(time);
        hbox.getChildren().add(time);
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
        root.setTranslateY(30);
        rootImage.getChildren().addAll(root);
        for(int i=0;i<this.board.getSizeX();i++) {
            for(int j=0;j<this.board.getSizeY();j++) {
                for (Element e:this.board.getListGrid().get(j).get(i).getZ()) {
                    addImage(e.getTypeElement(),i,j);
                    if (this.board.best(e.getTypeElement()))
                        addImage(TypeElement.BESTELEME,i,j);
                }
            }
        }
    }
    
}
