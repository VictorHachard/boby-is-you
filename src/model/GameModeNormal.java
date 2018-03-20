package model;

import exeptions.TypeElementNotFoundException;

/**
 *
 * @author Windows
 */
public class GameModeNormal extends GameMode {
    
    public GameModeNormal(Maps map) throws TypeElementNotFoundException {
        super(map);
    }

    @Override
    void lose() {}
    
}
