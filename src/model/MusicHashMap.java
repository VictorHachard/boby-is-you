package model;

import exeptions.TypeElementNotFoundException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Glaskani
 */
public class MusicHashMap {
    
    private Map<Music, MediaPlayer> musicMap = new HashMap<>();
    private static final Logger LOGGER = Logger.getGlobal();
    private HashMap<Music, Boolean> isPlaying;
    private static MusicHashMap INSTANCE = null;
    
    public static MusicHashMap getInstance() throws TypeElementNotFoundException, IOException {           
        if (INSTANCE == null)
            INSTANCE = new MusicHashMap();
        return INSTANCE;
    }
    
    /**
     * Charge toute les musiques du board dans une map,
     */
    MusicHashMap() {
        this.isPlaying = new HashMap<>();
        
        Music[] listAllMusic = Music.values();
        for(Music m:listAllMusic) {
            try {
                this.isPlaying.put(m, Boolean.FALSE);
                //this.musicMap.put(m, new MediaPlayer(new Media(new File("src"+File.separator+"music"+File.separator+m.toString().toLowerCase()+".mp3").toURI().toString())));
            }
            catch (MediaException ex) {
                LOGGER.log( Level.WARNING, "Unable to load " + m,ex);
            }
        }
    }
    
    /**
     * 
     * @param music 
     */
    void play(Music music) {
        /*MediaPlayer mediaPlayer = musicMap.get(music);
        if(isPlaying.get(music)){
            mediaPlayer.stop();
            isPlaying.replace(music, Boolean.FALSE);
        }
        mediaPlayer.play();
        isPlaying.replace(music, Boolean.TRUE);*/
    }   
}

