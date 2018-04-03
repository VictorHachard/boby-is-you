package common.view;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import common.model.Element;
import common.model.Maps;
import common.model.TypeElement;

/**
 *
 * @author Glaskani
 */
public class ImageHashMap {
    
    private Map<TypeElement, Image> imageMap = new HashMap<>();
    private static final Logger LOGGER = Logger.getGlobal();
    
    /**
     * Charge toute le image du board dans une map,
     * revois une map avec comme key le TypeElement et comme value l'image.
     * @param board Board
     */
    ImageHashMap() {
        TypeElement[] listAllElement = TypeElement.getAll();
        
        for(TypeElement e:listAllElement) {
            try {
            this.imageMap.put(e, new Image("common/images/"+e.toString().toLowerCase()+".png"));
            } catch (IllegalArgumentException ex) {
            LOGGER.log( Level.WARNING, "Image not fond");
            }
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
            try {
            TypeElement te = e.getTypeElements();
            this.imageMap.put(te, new Image("common/images/"+te.toString().toLowerCase()+".png"));
            } catch (IllegalArgumentException ex) {
                LOGGER.log( Level.WARNING, "Image not fond");
            }
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
