package model;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.image.ImageView;

/**
 *
 * @author Windows
 */
public class Image {
    
    private Map<TypeElement, ImageView> map = new HashMap<>();
    
    /**
     * Charge toute le image du board dans une map,
     * revois une map avec comme key le TypeElement et comme value l'image.
     * @param board Board
     */
    public Image(Board board) {
        List<Element> listAllElement = board.getListAllElement();
        
        for(Element e:listAllElement) {
            TypeElement te = e.getTypeElements();
            map.put(te, new ImageView(new javafx.scene.image.Image(new File("JavaBobyIsYou.images"+te+"png").toURI().toString())));
        }
    }
    
}
