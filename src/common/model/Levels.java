package common.model;

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
public final class Levels {

    private boolean carryOn;
    private final List<Maps> listMap;
    private int indice;
    private static Levels INSTANCE = null;
    private Stage primaryStage = MenuInit.getInstance().getStage();
    private final MusicHashMap music = MusicHashMap.getInstance();
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
    
    /**
     * Recupere le lecteur de music et active la music de fond, la Stage,
     * la prograision du joueur si il y en a une,
     * load toutes les maps pas encore jouée.
     */
    private Levels() {
        this.listMap=new ArrayList<>();
        this.indice=JavaBobyIsYou.indice;
        this.carryOn=JavaBobyIsYou.carryOn;
        loadMap();
    }
    
    /**
     * Revois l'indice actutel de la liste des levels.
     * @return int
     */
    public int getIndice() {
        return indice;
    }
    
    public void setIndice(int i) {
        indice=i;
    }
    
    public void loadGame() {
        loadDisplay();
        this.carryOn=true;
        this.music.repet(Music.BACK);
    }
    
    /**
     * Change la maps en focntion de b.
     * @param b true ou false, change la direction dans la quel la Maps va etre 
     * changer
     */
    public void switchMaps(boolean b) {
        if (b)
            this.indice= (this.indice+1)%this.listMap.size();
        else {
            this.indice= (this.indice-1);
            if (this.indice<0)
                this.indice=this.listMap.size()-1;
        }
        loadDisplay();
    }
    
    /**
     * Desactive toutes les reglées, appele LoadGame de MenuInit pour charge la
     * prochaine map.
     */
    public void loadDisplay() {
        Rule.desactivateAll();
        MenuInit.getInstance().LoadGame(new Maps(this.listMap.get(indice)));
    }
    
    /**
     * Execute toutes les actions pour arreter une partie.
     */
    public void stopGame() {
        Rule.desactivateAll();
        GameMode.desactivateAll();
        this.primaryStage.setScene(MenuInit.getInstance().scene);
        this.music.stop(Music.BACK);
    }
    
    /**
     * Verifie si la prochaine map existe, si elle existe save la progression et
     * appele "loadDisplay", si il n'y a plus de map : stop la music, return au
     * menuInit et reset la save.
     */
    void nextLevel() {
        this.indice = indice+1;
        if (indice==this.listMap.size()) {
            this.indice=0;
            this.carryOn=false;
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
     */
    void loadMap() {
        char[] listchar = {'a','b','c','d','e','f','g','h','i','j','l','m'};
        for (char a:listchar) {
            InputStream is = null;
            try {
                URL uri = JavaBobyIsYou.class.getResource("/common/maps/map"+a+".txt");
                is = uri.openStream();
                InputStreamReader ipsr = new InputStreamReader(is);
                try (BufferedReader br = new BufferedReader(ipsr)) {
                    this.listMap.add(new Maps(br));
                }
                is.close();
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE,"coud not load file",ex);
            } 
        }
    }
    
    /**
     * Revois carryOn.
     * @return boolean
     */
    public boolean getContinue() {
        return this.carryOn;
    }
}
