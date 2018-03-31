package common.model;

import common.exeptions.TypeElementNotFoundException;
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
import common.view.JavaBobyIsYou;
import common.view.MenuInit;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;

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
    
    void loadMap() throws TypeElementNotFoundException, IOException, URISyntaxException {
        char[] listchar = {'a','b','c','d','e','f','g','h','i','j'};
        for (char a:listchar) {
            try {
                URL uri = JavaBobyIsYou.class.getResource("/common/maps/map"+a+".txt");
                InputStream is = uri.openStream();
                InputStreamReader ipsr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(ipsr);
                this.listMap.add(new Maps(br));
                br.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }
}
