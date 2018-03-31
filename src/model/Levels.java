package model;

import exeptions.TypeElementNotFoundException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
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
        Rule.desactivateAll();
        MenuInit.getInstance().LoadGame(new Maps(this.listMap.get(indice)));
    }
    
    void nextLevel() throws TypeElementNotFoundException, IOException {
        this.indice = indice+1;
        if (indice==this.listMap.size()) {
            this.primaryStage.setScene(MenuInit.getInstance().scene);
            return;
        }     
        loadDisplay();
    }
    
    private static File[] getResourceFolderFiles (String folders) {
        
    URL url = JavaBobyIsYou.class.getResource(folders);
    String path = url.getPath();
    LOGGER.log( Level.INFO, " "+ path);
    File folder = new File(path);
    LOGGER.log(Level.INFO, "folder:"+ folder.toString());
    LOGGER.log(Level.INFO, "folder:"+ folder.isDirectory());
    LOGGER.log(Level.INFO, "folderSize:"+ folder.listFiles());
    File[] file = folder.listFiles();
    LOGGER.log(Level.INFO, file.toString());
    LOGGER.log(Level.INFO, Integer.toString(file.length));
    for (File f:file)
    LOGGER.log( Level.INFO, " "+ f);
    return file;
  }
    
    void loadMap() throws TypeElementNotFoundException, IOException, URISyntaxException {
        URI uri = JavaBobyIsYou.class.getResource("/maps")
                .toURI();
        Path myPath;
        if (uri.getScheme().equals("jar")) {
            FileSystem fileSystem = FileSystems.newFileSystem(uri, Collections.<String, Object>emptyMap());
            myPath = fileSystem.getPath("/maps");
        } else {
            myPath = Paths.get(uri);
        }
        Stream<Path> walk = Files.walk(myPath, 1);
        for (Iterator<Path> it = walk.iterator(); it.hasNext();){
            File current = it.next().toFile();
            if (current.isFile()) {
                this.listMap.add(new Maps(current));
            }
        }/*
        for (File f : getResourceFolderFiles("/maps")) {
            this.listMap.add(new Maps(f));
        //LOGGER.log( Level.INFO, " "+ f);
        }*/

    }
}
