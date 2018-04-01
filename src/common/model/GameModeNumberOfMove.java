package common.model;

import common.exeptions.TypeElementNotFoundException;
import java.io.IOException;

/**
 *
 * @author Windows
 */
public class GameModeNumberOfMove extends GameMode {

    private int limitedDeplacement;

    /**
     * nm de coup
     * @param map
     * @param limitedDeplacement
     * @throws TypeElementNotFoundException 
     */
    public GameModeNumberOfMove(Maps map,int limitedDeplacement) throws TypeElementNotFoundException, IOException {
        super(map);
        this.limitedDeplacement = limitedDeplacement;
        lose();
    }

    @Override
    boolean lose() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
