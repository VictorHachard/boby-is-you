package common.model;

/**
 *
 * @author Windows
 */
public class GameModeTimer extends GameMode {
    
    private double limitedTimer;
    private Board board;

    GameModeTimer(Board board) {
        this.board=board;
        //this.limitedTimer=;
    }
    
    @Override
    boolean work() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    Game getGame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
