package common.model;

import common.exeptions.TypeElementNotFoundException;
import java.io.IOException;

/**
 *
 * @author Windows
 */
public abstract class GameMode {
    
    Board board;
    
    /**
     * 
     * @param map 
     * @throws common.exeptions.TypeElementNotFoundException 
     */
    public GameMode(Maps map) throws TypeElementNotFoundException, IOException {
        Board.reloadInstance();
        this.board = Board.getInstance(map);
    }
    
    /**
     * Revois le board.
     * @return Board
     */
    public Board getBoard() {
        return this.board;
    }
    
    abstract void lose();
}
