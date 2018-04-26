package common.model;

/**
 *
 * @author Glaskani
 */
public enum Music {
    ADD,
    BACK,
    PUSH,
    WIN,
    KILL,
    DESTROY,
    MELT,
    PORTAL,
    MELTBLOCK,
    SINK,
    SINKBLOCK,
    ACHIEVEMENT;
     
    /**
     * 
     */
    Music() {}
    
    /**
     * Revois une liste de toutes les musiques de l'enum Music
     * @return Music[]
     */
    Music[] getMusicAll() {
        return Music.values();
    }    
}
