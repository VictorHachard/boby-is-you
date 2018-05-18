package common.model;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.Stage;
import common.view.JavaBobyIsYou;
import common.view.MenuEnd;
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
    private final List<Maps> listMap = new ArrayList<>();;
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
    
    public static void ReloadInstance() {           
        INSTANCE = null;
    }
    
    /**
     * Recupere le lecteur de music et active la music de fond, la Stage,
     * la prograision du joueur si il y en a une,
     * load toutes les maps pas encore jouée.
     */
    private Levels() {
        indice=JavaBobyIsYou.indice;
        carryOn=JavaBobyIsYou.carryOn;
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
        carryOn=true;
        music.repet(Music.BACK);
    }
    
    /**
     * Change la maps en focntion de b.
     * @param b true ou false, change la direction dans la quel la Map va etre 
     * changer
     */
    public void switchMaps(boolean b) {
        if (b)
            indice= (indice+1)%listMap.size();
        else {
            indice= (indice-1);
            if (indice<0)
                indice=listMap.size()-1;
        }
        loadDisplay();
    }
    
    /**
     * Desactive toutes les reglées, appele LoadGame de MenuInit pour charge la
     * prochaine map.
     */
    public void reload() {
        loadDisplay();
        CheckAchievement.getInstance().checkReload();
    }
    
    /**
     * Desactive toutes les reglées, appele LoadGame de MenuInit pour charge la
     * prochaine map.
     */
    public void loadDisplay() {
        Rule.desactivateAll();
        MenuInit.getInstance().LoadGame(new Maps(listMap.get(indice)));
    }
    
    /**
     * Execute toutes les actions pour arreter une partie.
     */
    public void stopGame() {
        JavaBobyIsYou.save();
        CheckAchievement.getInstance().save();
        Rule.desactivateAll();
        GameMode.desactivateAll();
        primaryStage.setScene(MenuInit.getInstance().scene);
        music.stop(Music.BACK);
    }
    
    /**
     * Verifie si la prochaine map existe, si elle existe save la progression et
     * appele "loadDisplay", si il n'y a plus de map : stop la music, return au
     * menuInit et reset la save.
     */
    void nextLevel() {
        this.indice = indice+1;
        if (indice==this.listMap.size()) {
            indice=0;
            carryOn=false;
            stopGame();
            primaryStage.setScene(MenuEnd.getInstance().scene);
            return;
        }     
        music.play(Music.WIN);
        JavaBobyIsYou.save();
        CheckAchievement.getInstance().save();
        loadDisplay();
    }
    
    /**
     * Ouvre dans le fichier "maps", ajoute toues les maps sous formats "txt"
     * dans la liste des maps "listMap" qui sont prealablement transformer et 
     * Map.
     */
    void loadMap() {
        char[] listchar = {'2','a','b','c','d','e','f','g','h','1','i','j','k','l','m','o'};
        for (char a:listchar) {
            InputStream is = null;
            try {
                URL uri = JavaBobyIsYou.class.getResource("/common/ressources/maps/map"+a+".txt");
                is = uri.openStream();
                InputStreamReader ipsr = new InputStreamReader(is);
                try (BufferedReader br = new BufferedReader(ipsr)) {
                    listMap.add(new Maps(br));
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
        return carryOn;
    }
}
