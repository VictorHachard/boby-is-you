package view;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.image.Image;
import model.Board;
import model.Element;
import model.Maps;
import model.TypeElement;

/**
 *
 * @author Glaskani
 */
public class ImageHashMap {
    
    private Map<TypeElement, Image> imageMap = new HashMap<>();
    
    /**
     * Charge toute le image du board dans une map,
     * revois une map avec comme key le TypeElement et comme value l'image.
     * @param board Board
     */
    ImageHashMap(Board board) {
        List<Element> listAllElement = board.getListAllElement();
        listAllElement.add(new Element(TypeElement.EMPTY));
        listAllElement.add(new Element(TypeElement.WALLINJOUABLE));
        
        for(Element e:listAllElement) {
            TypeElement te = e.getTypeElements();
            this.imageMap.put(te, new Image("images/"+te.toString().toLowerCase()+".png"));  
        }
    }
    
    /**
     * Charge toute le image du board dans une map,
     * revois une map avec comme key le TypeElement et comme value l'image.
     * @param map Maps
     */
    ImageHashMap(Maps map) {
        List<Element> listAllElement = map.getListAllElement();
        listAllElement.add(new Element(TypeElement.EMPTY));
        listAllElement.add(new Element(TypeElement.WALLINJOUABLE));
        
        for(Element e:listAllElement) {
            TypeElement te = e.getTypeElements();
            this.imageMap.put(te, new Image("images" + File.separator + te.toString() + ".png"));  
        }
    }

    /**
     * 
     * @param is 
     */
    ImageHashMap(InputStream is) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * 
     * @return Map TypeElement, Image,
     */
    Map<TypeElement, Image> getImageMap() {
        return this.imageMap;
    }
}
