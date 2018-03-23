package model;

/**
 *
 * @author Glaskani
 */
public enum Music {
    ADD();
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
