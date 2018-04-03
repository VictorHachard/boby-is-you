package common.model;

/**
 *
 * @author Glaskani
 */
public enum Music {
    ADD(),
    BACK();
    //PUSH(),
    //WIN(),
    //ICE(),
    //MELT(),
    //SINK(),
    //LOSE();
     
    /**
     * 
     */
    Music() {}
    
    /**
     * 
     * @return 
     */
    Music[] getMusicAll() {
        return Music.values();
    }    
}
