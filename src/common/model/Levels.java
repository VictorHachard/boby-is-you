package common.model;

import common.exeptions.TypeElementNotFoundException;
import java.io.IOException;
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
        return INSTANCE;
    }
    
    public static Levels instance() {  
        return INSTANCE;
    }
    
    /**
     * 
     */
    public static void ReloadInstance() {           
        INSTANCE = null;
    }
    private MusicHashMap music;
    
    /**
     * Recupere le lecteur de music et active la music de fond, la Stage,
     * la prograision du joueur si il y en a une,
     * load toutes les maps pas encore jouée.
     */
    private Levels() {
        try {
            music = MusicHashMap.getInstance();
            this.primaryStage=MenuInit.getInstance().getStage();
            this.listMap=new ArrayList<>();
            this.indice=JavaBobyIsYou.indice;
            loadMap();
        } catch (TypeElementNotFoundException | IOException ex) {
            LOGGER.log(Level.WARNING,"erreur",ex);
        }
    }
    
    public int getIndice() {
        return indice;
    }
    
    public void loadGame() {
        loadDisplay();
        this.music.repet(Music.BACK);
    }
    
    /**
     * Desactive toutes les reglées, appele LoadGame de MenuInit pour charge la
     * prochaine map.
     */
    public void loadDisplay() {
        Rule.desactivateAll();
        MenuInit.getInstance().LoadGame(new Maps(this.listMap.get(indice)));
    }
    
    public void stopGame() {
        Rule.desactivateAll();
        this.primaryStage.setScene(MenuInit.getInstance().scene);
        this.music.stop(Music.BACK);
    }
    
    /**
     * Verifie si la prochaine map existe, si elle existe save la progression et
     * appele "loadDisplay", si il n'y a plus de map : stop la music, return au
     * menuInit et reset la save.
     * @throws TypeElementNotFoundException
     * @throws IOException 
     */
    void nextLevel() throws TypeElementNotFoundException, IOException {
        this.indice = indice+1;
        if (indice==this.listMap.size()) {
            this.indice=0;
            JavaBobyIsYou.save();
            stopGame();
            return;
        }     
        JavaBobyIsYou.save();
        loadDisplay();
    }
    
    /**
     * Ouvre dans le fichier "maps", ajoute toues les maps sous formats "txt"
     * dans la liste des maps "listMap" qui sont prealablement transformer et 
     * Maps.
     * @throws IOException 
     */
    void loadMap() throws IOException {
        char[] listchar = {'a','b','c','d','e','f','g','h','i','j',/*,'k',*/'l','m'};
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
