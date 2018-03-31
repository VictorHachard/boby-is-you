package common.model;

import common.exeptions.TypeElementNotFoundException;
import java.io.IOException;

/**
 *
 * @author Windows
 */
public class GameModeNormal extends GameMode {
    
    public GameModeNormal(Maps map) throws TypeElementNotFoundException, IOException {
        super(map);
    }

    @Override
    void lose() {}
    
}
