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
        this.indice =0;
        loadMap();
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
    
    void loadMap() throws TypeElementNotFoundException, IOException {
        File repertoire = new File("." + File.separator + "maps");
        File[] files=repertoire.listFiles();
        for (File f:files)
            this.listMap.add(new Maps(f));
    }
}
