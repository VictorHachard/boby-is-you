package common.model;

import common.view.JavaBobyIsYou;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.util.Pair;

/**
 *
 * @author Glaskani
 */
public class MusicHashMap {
    
    private Map<Music, Pair<MediaPlayer, Boolean>> musicMap = new HashMap<>();
    private static final Logger LOGGER = Logger.getGlobal();
    private static MusicHashMap INSTANCE = null;
    
    public static MusicHashMap getInstance() {           
        if (INSTANCE == null)
            INSTANCE = new MusicHashMap();
        return INSTANCE;
    }
    
    /**
     * Charge toute les musiques dans une HashMap
     */
    MusicHashMap()  {
        Music[] listAllMusic = Music.values();
        for(Music m:listAllMusic) {
            try {
                this.musicMap.put(m, new Pair<>(new MediaPlayer(
                new Media(JavaBobyIsYou.class.getResource("/common/ressources/music/"+m.toString().toLowerCase()+".wav").toString())),false));
            }
            catch (NullPointerException ex) {
                LOGGER.log(Level.WARNING, "Music not fond : {0}", m);
            } 
        }
    }
    
    /**
     * Verifie si la music est bien dans la HashMap, arrete la music.
     * @param music Music, la music à stopé
     */
    public void stop(Music music) {
        if (!musicMap.containsKey(music))
            return;
        MediaPlayer mediaPlayer = musicMap.get(music).getKey();
        mediaPlayer.stop();
        musicMap.replace(music, new Pair<>(mediaPlayer,true));
    }
    
    /**
     * Verifie si la music est bien dans la HashMap, lance la music en boucle.
     * @param music Music, la music à bouclé
     */
    public void repet(Music music) {
        if (!musicMap.containsKey(music))
            return;
        MediaPlayer mediaPlayer = musicMap.get(music).getKey();
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        play(music);
    }
    
    /**
     * Verifie si la music est bien dans la HashMap, si la music joue deja elle
     * sera couper et relancer.
     * @param music Music, la music à lancer
     */
    public void play(Music music) {
        if (!musicMap.containsKey(music))
            return;
        MediaPlayer mediaPlayer = musicMap.get(music).getKey();
        if(musicMap.get(music).getValue()){
            mediaPlayer.stop();
            musicMap.replace(music, new Pair<>(mediaPlayer,false));
        }
        mediaPlayer.play();
        musicMap.replace(music, new Pair<>(mediaPlayer,true));
    }   
    
    /**
     * 
     * @param music
     * @param d 
     */
    public void volume( double d, Music ... music) {
        for (Music m:music) {
            try {
            MediaPlayer mediaPlayer = musicMap.get(m).getKey();
            mediaPlayer.setVolume(d);
            } catch (NullPointerException e) {
                
            }
        }
    }
}

