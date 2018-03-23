package model;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Glaskani
 */
public class MusicHashMap {
    
    private Map<Music, MediaPlayer> musicMap = new HashMap<>();
    private boolean isPlaying= false;
    
    /**
     * Charge toute le image du board dans une map,
     * revois une map avec comme key le TypeElement et comme value l'image.
     * @param map Maps
     */
    MusicHashMap() {
        Music[] listAllMusic = Music.values();
        
        for(Music m:listAllMusic) {
            try {
                this.musicMap.put(m, new MediaPlayer(new Media(new File("C:\\Users\\Windows\\Documents\\NetBeansProjects\\BobyIsYou\\src\\music\\"+m+".mp3").toURI().toString())));
            }
            catch (MediaException ex) {
           // System.out.println("Unable to load"+ ,ex);
            }
        }
    }
    
    void play(Music music) {
        MediaPlayer mediaPlayer = musicMap.get(music);
        if(isPlaying){
            mediaPlayer.stop();
            isPlaying = false;
        }
            mediaPlayer.play();
            isPlaying = true;
    }   
}

