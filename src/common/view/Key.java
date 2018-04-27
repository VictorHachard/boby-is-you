package common.view;

import javafx.scene.input.KeyCode;

/**
 *
 * @author Windows
 */
public class Key {
    
    private static Key INSTANCE = null;
    
    static Key getInstance() {           
        if (INSTANCE == null)  
            INSTANCE = new Key();
        return INSTANCE;
    }
    
    private KeyCode LEFT = KeyCode.LEFT;
    private KeyCode RIGHT = KeyCode.RIGHT;
    private KeyCode UP = KeyCode.UP;
    private KeyCode DOWN = KeyCode.DOWN;
    private KeyCode R = KeyCode.R;
    
    private Key() {}
    
    KeyCode getKeyLEFT() {
        return LEFT;
    }
    KeyCode getKeyRIGHT() {
        return RIGHT;
    }
    KeyCode getKeyUP() {
        return UP;
    }
    KeyCode getKeyDOWN() {
        return DOWN;
    }
    KeyCode getKeyR() {
        return R;
    }
    
    void setKeyLEFT(KeyCode param) {
        if (!(param==null))
            this.LEFT = param;
    }
    void setKeyRIGHT(KeyCode param) {
        if (!(param==null))
            this.RIGHT = param;
    }
    void setKeyUP(KeyCode param) {
        if (!(param==null))
            this.UP = param;
    }
    void setKeyDOWN(KeyCode param) {
        if (!(param==null))
            this.DOWN = param;
    }
    void setKeyR(KeyCode param) {
        this.R = param;
    }    
}
