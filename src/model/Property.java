package model;

/**
 *
 * @author Glaskani
 */
public enum Property {
    SLIP, //glisser
    WIN,
    PUSH,
    STOP,
    SINK,
    MOVE,
    BEST,
    MELT,
    HOT,
    YOU,
    GRAB,
    KILL,
    TP
    ;

    private Tp tp;
    private Slip ice;
    private Kill kill;
    private Sink sink;
    private Move move;
    private Melt melt;
    private Win win;
   /* private final Rule rule;
    
    Property(Rule rule) {
        this.rule=rule;
    }*/
}
    

