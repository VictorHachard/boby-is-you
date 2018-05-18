package common.model;

import common.view.Display;
import common.view.JavaBobyIsYou;
import common.view.JavaFXMethode;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * 
 * @author Thomas Lavend'Homme and Victor Hachard
 */
public class CheckAchievement {
    
    private static final Logger LOGGER = Logger.getGlobal();
    private HashMap<Achievement, Integer> data = new HashMap<>();
    public Display d;
    private Text text = new Text();
    private Text description = new Text();
    private static CheckAchievement INSTANCE = null;
    
    public HashMap<Achievement, Integer> getData() {
        return data;
    }
    
    public static CheckAchievement getInstance() {           
        if (INSTANCE == null)  
            INSTANCE = new CheckAchievement();
        return INSTANCE;
    }
    
    CheckAchievement() {
        load();
    }
    
    private void show(Achievement e,String j,String i) {
        MusicHashMap.getInstance().play(Music.ACHIEVEMENT);
        VBox b = new VBox();
        b.setBackground(JavaFXMethode.addColoredBackGround(new Color(0,0,0,0.7)));
        b.setMinWidth(JavaBobyIsYou.WIDTH);
        b.setMinHeight(JavaBobyIsYou.HEIGHT);
        b.setSpacing(16);
        if (j.length()==0)
            j=e.getName();
        b.getChildren().addAll(JavaFXMethode.addTitle(j, Color.WHITE),
                JavaFXMethode.addTitle(e.getDescription()+" "+i,Color.WHITE,20));
        d.show(b);
    }
    
    public void checkMove() {
        data.put(Achievement.MOVE, data.get(Achievement.MOVE) + 1);
        //Sauvegarder tout les 50 deplacements
	if (data.get(Achievement.MOVE) % 50 == 0)
            save();
        switch (data.get(Achievement.MOVE)) {
            case 10: 
            show(Achievement.MOVE,"","dix fois");
            break;
	case 100: 
            show(Achievement.MOVE,"","100 fois");
            break;
	case 1000: 
            show(Achievement.MOVE,"","1000 fois");
            break;
	case 10000: 
            show(Achievement.MOVE,"","10000 fois");
            break;
	}
    }
    
    public void checkReload() {
        data.put(Achievement.RELOAD, data.get(Achievement.RELOAD) + 1);
        switch (data.get(Achievement.RELOAD)) {
            case 1: 
            show(Achievement.RELOAD,"","une fois");
            break;
	case 10: 
            show(Achievement.RELOAD,"","dix fois");
            break;
	case 100: 
            show(Achievement.RELOAD,"","100 fois");
            break;
	case 1000: 
            show(Achievement.RELOAD,"","1000 fois");
            break;
	}
    }
    
    public void checkWin() {
        data.put(Achievement.WIN, data.get(Achievement.WIN) + 1);
        switch (data.get(Achievement.WIN)) {
            case 1: 
            show(Achievement.WIN,"","une partie");
            break;
	case 10: 
            show(Achievement.WIN,"","dix parties");
            break;
	case 100: 
            show(Achievement.WIN,"","100 parties");
            break;
	case 1000: 
            show(Achievement.WIN,"","1000 parties");
            break;
	}
    }
    
    public void save() {
	try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream("achievements"))) {
            writer.writeObject(data);
	} catch (IOException e) {
            LOGGER.log(Level.SEVERE,"Could not write achievement data",e);
	}
    }
    
    private void load() {
        Object data;
        try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream("achievements"))) {
            data = reader.readObject();
        } catch (ClassNotFoundException | IOException e) {
            this.data.put(Achievement.MOVE, 0);
            this.data.put(Achievement.RELOAD, 0);
            this.data.put(Achievement.WIN, 0);
            return;
	} 
	try {
            this.data = (HashMap<Achievement, Integer>) data;
	} catch (Exception e) {
            this.data.put(Achievement.MOVE, 0);
            this.data.put(Achievement.RELOAD, 0);
            this.data.put(Achievement.WIN, 0);
	}
    }

}
