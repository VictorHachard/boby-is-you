package model;

import exeptions.TypeElementNotFoundException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.Stage;
import view.JavaBobyIsYou;
import view.MenuInit;

/**
 *
 * @author Windows
 */
public class Levels {
    
    private List<Maps> listMap;
    private int indice;
    private static Levels INSTANCE = null;
    private File file;
    private Stage primaryStage;
    private static final Logger LOGGER = Logger.getGlobal();
    
    public static Levels getInstance() throws TypeElementNotFoundException, IOException {           
        if (INSTANCE == null)
            INSTANCE = new Levels();
        return INSTANCE;
    }
    
    /**
     * 
     */
    public static void ReloadInstance() {           
        INSTANCE = null;
    }
    
    Levels() throws TypeElementNotFoundException, IOException {
        this.primaryStage=MenuInit.getInstance().getStage();
        this.listMap=new ArrayList<>();
        this.indice =0;
        try {
            loadMap();
        } catch (URISyntaxException ex) {
            Logger.getLogger(Levels.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadDisplay();
    }
    
     public void loadDisplay() throws TypeElementNotFoundException, IOException {
        MenuInit.getInstance().LoadGame(this.listMap.get(indice));
    }
    
    void nextLevel() throws TypeElementNotFoundException, IOException {
        this.indice = indice+1;
        if (indice==this.listMap.size()) {
            this.primaryStage.setScene(MenuInit.getInstance().scene);
            return;
        }     
        loadDisplay();
    }
    
    private static File[] getResourceFolderFiles (String folder) {
        
    URL url = JavaBobyIsYou.class.getResource(folder);
    String path = url.getPath();
    LOGGER.log( Level.INFO, " "+ path);
    File[] file = new File(path).listFiles();
    for (File f:file)
    LOGGER.log( Level.INFO, " "+ f);
    return file;
  }
    
    void loadMap() throws TypeElementNotFoundException, IOException, URISyntaxException {
        for (File f : getResourceFolderFiles("/maps")) {
            this.listMap.add(new Maps(f));
        LOGGER.log( Level.INFO, " "+ f);
        }

    }
}
