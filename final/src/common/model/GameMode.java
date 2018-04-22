package common.model;

import java.util.HashMap;

/**
 *
 * @author Windows
 */
public abstract class GameMode {
    
    private static HashMap<Game,Boolean> activeMode = new HashMap<>();
    
    public static boolean isActive(Game name){
        return activeMode.getOrDefault(name,false);
    }
    public static void desactivateAll() {
        activeMode = new HashMap<>();
    }
    public static void setActivity(Game k, Boolean v){
        activeMode.put(k, v);
    }
    
    GameMode nextRule = null;
    
    /**
     * Ajoute un GameMode ou une infinite a la liste next Rule.
     * @param next GameMode à ajouter
     */
    void addGameMode(GameMode ... next){
        for (GameMode r:next) {
            if (nextRule == null)
                nextRule = r;
            else
                nextRule.addGameMode(r);
        }
    }
    
    public boolean check(){
        boolean res = true;
        if (isActive(this.getGame())){
            res = work();
        }
        if (nextRule == null){
            return res;
        }
        return res && nextRule.check();
    }
    
    abstract boolean work();
    abstract Game getGame();
}
