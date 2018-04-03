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
    private MusicHashMap music;
    
    Levels() {
        try {
            music = MusicHashMap.getInstance();
            this.primaryStage=MenuInit.getInstance().getStage();
            this.listMap=new ArrayList<>();
            this.indice =0;
            loadMap();
            loadDisplay();
            this.music.repet(Music.BACK);
        } catch (URISyntaxException | TypeElementNotFoundException | IOException ex) {
            Logger.getLogger(Levels.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadDisplay() throws TypeElementNotFoundException, IOException {
        Rule.desactivateAll();
        MenuInit.getInstance().LoadGame(new Maps(this.listMap.get(indice)));
    }
    
    void nextLevel() throws TypeElementNotFoundException, IOException {
        this.indice = indice+1;
        if (indice==this.listMap.size()) {
            this.music.stop(Music.BACK);
            this.primaryStage.setScene(MenuInit.getInstance().scene);
            return;
        }     
        loadDisplay();
    }
    
    void loadMap() throws TypeElementNotFoundException, IOException, URISyntaxException {
        char[] listchar = {'a','b','c','d','e','f',
            'g',
            'h','i','j',/*,'k',*//*,*/'l','m'};
        for (char a:listchar) {
            try {
                URL uri = JavaBobyIsYou.class.getResource("/common/maps/map"+a+".txt");
                InputStream is = uri.openStream();
                InputStreamReader ipsr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(ipsr);
                this.listMap.add(new Maps(br));
                br.close();
            } catch (Exception e) {
                System.out.println(e.toString());//genere une erreur
            }
        }
    }
}
