package model;

import java.util.List;

/**
 *
 * @author Windows
 */
public enum Music {
    ADD();
    //PUSH(),
    //WIN(),
    //LOSE();
 
    Music() {
        
    }
    
    Music[] getMusicAll() {
        return Music.values();
    }
    
}
