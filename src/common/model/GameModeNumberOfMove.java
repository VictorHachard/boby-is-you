package common.model;

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
     */
    public GameModeNumberOfMove(Board board) {
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
