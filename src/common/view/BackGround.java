package common.view;

import common.exeptions.TypeElementNotFoundException;
import common.model.Element;
import common.model.Maps;
import common.model.TypeElement;
import common.model.TypeTypeElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Windows
 */
public class BackGround {
    
    private GridPane rootGrid = new GridPane();
    private int y;
    private int x;
    private List<TypeElement> listBlock;
    private Maps map;
    
    GridPane getPane() {
        return rootGrid;
    }
    
    BackGround() {
        listBlock = listBlock();
        x = (int)JavaBobyIsYou.WIDTH/32;
        y = (int)JavaBobyIsYou.HEIGHT/32;
        System.out.println(JavaBobyIsYou.WIDTH);System.out.println(JavaBobyIsYou.HEIGHT);
        System.out.println(x);System.out.println(y);
        map = new Maps(x,y);
        genrateBlock(map);
        convertMapToImage();
    }
    
    private void genrateBlock(Maps map) {
        for(int i=1;i<y-1;i++)
            for(int j=1;j<x-1;j++)
                try {
                    map.addMap(j,i, getRandomBlock());
            } catch (TypeElementNotFoundException ex) {
                Logger.getLogger(BackGround.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    private List<TypeElement> listBlock() {
        TypeElement[] listTemps = TypeElement.getAll();
        List<TypeElement> list = new ArrayList<>();
        for (TypeElement te:listTemps)
            if (te.getType()==TypeTypeElement.BLOCK)
                list.add(te);
        return list;
    }

    private Element getRandomBlock() {
        Random ran = new Random();
        int x = ran.nextInt((this.listBlock.size()-1));
        System.out.println(x);
        return new Element(listBlock.get(x));
    }
    
    private void addImage(TypeElement name,int posx,int posy) {
        ImageView image = new ImageView(ImageHashMap.getInstance().getImageMap().get(name));
        image.setFitHeight(32);
        image.setFitWidth(32);
        moveImageByCase(image,posx,posy);
        rootGrid.getChildren().add(image);
    }
    
    /**
     * This method move one image by 32 pixels
     * @param image ImageView
     * @param x the number of case that you move this image 
     * @param y the 
     */
    private void moveImageByCase(ImageView image,double x,double y) {
        x = x*32;
        y = y*32;
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
