package model;

import exeptions.TypeElementNotFoundException;
import java.io.IOException;

/**
 *
 * @author Windows
 */
public class GameModeAdd extends GameMode {
    
    private double time;
    private int limitedDeplacement;
    
    /**
     * temps
     * @param map
     * @param time
     * @throws TypeElementNotFoundException 
     */
    public GameModeAdd(Maps map,double time) throws TypeElementNotFoundException, IOException {
        super(map);
        this.time = time;
        lose();
    }
    
    /**
     * nm de coup
     * @param map
     * @param limitedDeplacement
     * @throws TypeElementNotFoundException 
     */
    public GameModeAdd(Maps map,int limitedDeplacement) throws TypeElementNotFoundException, IOException {
        super(map);
        this.limitedDeplacement = limitedDeplacement;
        lose();
    }

    @Override
    void lose() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
