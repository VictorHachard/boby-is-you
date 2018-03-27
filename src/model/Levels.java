package model;

import exeptions.TypeElementNotFoundException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.stage.Stage;
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
    
    public static Levels getInstance() throws TypeElementNotFoundException, IOException {           
        if (INSTANCE == null)
            INSTANCE = new Levels();
        return INSTANCE;
    }
    
    /**
     * 
     * @return 
     *//*
    public static Level getInstance() {           
        return INSTANCE;
    }*/
    
    /**
     * 
     */
    public static void ReloadInstance() {           
        INSTANCE = null;
    }
    
    Levels() throws TypeElementNotFoundException, IOException {
        this.primaryStage=MenuInit.getInstance().getStage();
        this.listMap=new ArrayList<>();
        File f = new File("." + File.separator + "maps" + File.separator + "map1.txt");
        File f2 = new File("." + File.separator + "maps" + File.separator + "map2.txt");
        File f3 = new File("." + File.separator + "maps" + File.separator + "map3.txt");
        File f4 = new File("." + File.separator + "maps" + File.separator + "map4.txt");
        this.listMap.add(new Maps(f));
        this.listMap.add(new Maps(f2));
        this.listMap.add(new Maps(f3));
        this.listMap.add(new Maps(f4));
        //load le file config lire et toruver le int pour savoir ou on en est dans la liste des map de jeu
        this.indice =0;
        //load Maps avec le bon
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
    
    void loadMap() {
        //this.listMap.add(e)
    }
}
