package common.model;

import common.exeptions.TypeElementNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.Stage;
import common.view.JavaBobyIsYou;
import common.view.MenuInit;
import java.io.BufferedReader;
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
    private Stage primaryStage;
    private static final Logger LOGGER = Logger.getGlobal();
    
    public static Levels getInstance() {           
        if (INSTANCE == null)
            INSTANCE = new Levels();
        LOGGER.log(Level.INFO,"get instance : "+INSTANCE);
        return INSTANCE;
    }
    
    public static Levels instance() {  
        LOGGER.log(Level.INFO,"instance : "+INSTANCE);
        return INSTANCE;
    }
    
    /**
     * 
     */
    public static void ReloadInstance() {           
        INSTANCE = null;
    }
    private MusicHashMap music;
    
    private Levels() {
        try {
            music = MusicHashMap.getInstance();
            this.primaryStage=MenuInit.getInstance().getStage();
            this.listMap=new ArrayList<>();
            this.indice =JavaBobyIsYou.getSaveCampagne();
            LOGGER.log(Level.INFO, "indice du contructeur : "+indice);
            loadMap();
            loadDisplay();
            this.music.repet(Music.BACK);
        } catch (TypeElementNotFoundException | IOException ex) {
            LOGGER.log(Level.WARNING,"erreur",ex);
        }
    }
    
    public void loadDisplay() throws TypeElementNotFoundException, IOException {
        Rule.desactivateAll();
        MenuInit.getInstance().LoadGame(new Maps(this.listMap.get(indice)));
    }
    
    void nextLevel() throws TypeElementNotFoundException, IOException {
        this.indice = indice+1;
        LOGGER.log(Level.INFO, "indice de nouveau level : "+indice);
        if (indice==this.listMap.size()) {
            this.music.stop(Music.BACK);
            JavaBobyIsYou.save(0);
            this.primaryStage.setScene(MenuInit.getInstance().scene);
            return;
        }     
        JavaBobyIsYou.save(indice);
        loadDisplay();
    }
    
    void loadMap() throws IOException {
        char[] listchar = {'a','b','c','d','e','f',
            'g',
            'h','i','j',/*,'k',*//*,*/'l','m'};
        for (char a:listchar) {
            InputStream is = null;
            try {
                URL uri = JavaBobyIsYou.class.getResource("/common/maps/map"+a+".txt");
                is = uri.openStream();
                InputStreamReader ipsr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(ipsr);
                this.listMap.add(new Maps(br));
                br.close();
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE,"coud not load file",ex);
            } is.close();
        }
    }
}
