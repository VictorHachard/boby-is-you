package common.view;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import common.model.TypeElement;

/**
 *
 * @author Glaskani
 */
public class ImageHashMap {
    
    private Map<TypeElement, Image> imageMap = new HashMap<>();
    private static final Logger LOGGER = Logger.getGlobal();
    private static ImageHashMap INSTANCE = null;
    
    public static ImageHashMap getInstance() {           
        if (INSTANCE == null)
            INSTANCE = new ImageHashMap();
        return INSTANCE;
    }
    /**
     * Charge toute le image du board dans une map,
     * revois une map avec comme key le TypeElement et comme value l'image.
     */
    ImageHashMap() {
        loadImage();
    }
    
    private void loadImage() throws IllegalArgumentException {
        TypeElement[] listAllElement = TypeElement.getAll();
        for(TypeElement e:listAllElement)
            try {
                Image img = new Image("common/images/"+e.toString().toLowerCase()+".png");
                this.imageMap.put(e, img);
            } catch (IllegalArgumentException ex) {
                LOGGER.log(Level.WARNING,"Image not fond : "+e/*ex*/);
            }
    }
    
    /**
     * 
     * @return Map TypeElement, Image,
     */
    Map<TypeElement, Image> getImageMap() {
        return this.imageMap;
    }
}
