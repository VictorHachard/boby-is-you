package common.model;

import common.exeptions.TypeElementNotFoundException;
import java.io.IOException;

/**
 *
 * @author Glaskani
 */
public class GameModeNumberOfMove extends GameMode {

    private int limitedDeplacement;
    private Board board;
    
    /**
     * nm de coup
     * @param board
     * @throws TypeElementNotFoundException 
     * @throws java.io.IOException 
     */
    public GameModeNumberOfMove(Board board) throws TypeElementNotFoundException, IOException {
        this.board=board;
        this.limitedDeplacement=board.getLimitedDeplacement();
    }

    @Override
    Game getGame() {
        return Game.PLAYERMOVE;
    }

    @Override
    boolean work() {
        if (board.getLimitedDeplacement()==0)
            return false;
        return true;
    }
    
}
