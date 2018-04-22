package common.model;

/**
 *
 * @author Windows
 */
public class GameModeTimer extends GameMode {
    
    private long limitedTimer;
    private Board board;
    private Chrono chrono;
    private long duree=0;
    
    public int getTime() {
        return (int) limitedTimer/1000;
    }

    GameModeTimer(Board board) {
        this.board=board;
        this.limitedTimer=10000;
        chrono = new Chrono();
        chrono.start();
    }
    
    @Override
    boolean work() {
        chrono.stop();
        duree = chrono.getDureeMs();
        System.out.println(limitedTimer);
        if (!(duree>limitedTimer)) {
            limitedTimer=limitedTimer-duree;
            chrono = new Chrono();
            chrono.start();
        }
        return !(duree>limitedTimer);
    }

    @Override
    Game getGame() {
        return Game.TIMER;
    }
    
}
