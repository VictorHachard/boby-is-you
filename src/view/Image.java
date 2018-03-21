package view;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.image.ImageView;
import model.Board;
import model.Element;
import model.Maps;
import model.TypeElement;

/**
 *
 * @author Glaskani
 */
public class Image {
    
    private Map<TypeElement, ImageView> imageMap = new HashMap<>();
    
    /**
     * Charge toute le image du board dans une map,
     * revois une map avec comme key le TypeElement et comme value l'image.
     * @param board Board
     */
    Image(Board board) {
        List<Element> listAllElement = board.getListAllElement();
        
        for(Element e:listAllElement) {
            TypeElement te = e.getTypeElements();
            this.imageMap.put(te, new ImageView(new javafx.scene.image.Image(new File
                    ("JavaBobyIsYou.images"+te+"png").toURI().toString())));
        }
    }
    
    /**
     * Charge toute le image du board dans une map,
     * revois une map avec comme key le TypeElement et comme value l'image.
     * @param map Maps
     */
    Image(Maps map) {
        List<Element> listAllElement = map.getListAllElement();
        
        for(Element e:listAllElement) {
            TypeElement te = e.getTypeElements();
            this.imageMap.put(te, new ImageView(new javafx.scene.image.Image(new File
                    ("JavaBobyIsYou.images"+te+"png").toURI().toString())));
        }
    }

    Image(InputStream is) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    Map<TypeElement, ImageView> getImageMap() {
        return this.imageMap;
    }
}
