package model;

import exeptions.TypeElementNotFoundException;

/**
 *
 * @author Windows
 */
public abstract class GameMode {
    
    Board board;
    
    /**
     * 
     * @param map 
     * @throws exeptions.TypeElementNotFoundException 
     */
    public GameMode(Maps map) throws TypeElementNotFoundException {
        this.board = new Board(map);
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
