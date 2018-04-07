package common.model;

import common.exeptions.TypeElementNotFoundException;
import common.view.JavaBobyIsYou;
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
     * Charge toute les musiques dans une HashMap
     */
    MusicHashMap()  {
        this.isPlaying = new HashMap<>();
        
        Music[] listAllMusic = Music.values();
        for(Music m:listAllMusic) {
            try {
                this.isPlaying.put(m, Boolean.FALSE);
                this.musicMap.put(m, new MediaPlayer(
                new Media(JavaBobyIsYou.class.getResource("/common/music/"+m.toString().toLowerCase()+".mp3").toString())));
            }
            catch (MediaException ex) {
                LOGGER.log( Level.WARNING, "Unable to load : " + m,ex);
            } 
        }
    }
    
    /**
     * Verifie si la music est bien dans la HashMap, arrete la music.
     * @param music Music, la music à stopé
     */
    void stop(Music music) {
        if (!musicMap.containsKey(music))
            return;
        MediaPlayer mediaPlayer = musicMap.get(music);
        mediaPlayer.stop();
        isPlaying.replace(music, Boolean.FALSE);
    }
    
    /**
     * Verifie si la music est bien dans la HashMap, lance la music en boucle.
     * @param music Music, la music à bouclé
     */
    void repet(Music music) {
        if (!musicMap.containsKey(music))
            return;
        MediaPlayer mediaPlayer = musicMap.get(music);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        play(music);
    }
    
    /**
     * Verifie si la music est bien dans la HashMap, si la music joue deja elle
     * sera couper et relancer.
     * @param music Music, la music à lancer
     */
    void play(Music music) {
        if (!musicMap.containsKey(music))
            return;
        MediaPlayer mediaPlayer = musicMap.get(music);
        if(isPlaying.get(music)){
            mediaPlayer.stop();
            isPlaying.replace(music, Boolean.FALSE);
        }
        mediaPlayer.play();
        isPlaying.replace(music, Boolean.TRUE);
    }   
}

