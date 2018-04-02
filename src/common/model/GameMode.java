package common.model;

import common.exeptions.TypeElementNotFoundException;
import common.exeptions.WinException;
import static common.model.Rule.isActive;
import java.io.IOException;
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
    static void desactivateAll() {
        activeMode = new HashMap<>();
        //System.out.println("desactivating all rules");
    }
    static void setActivity(Game k, Boolean v){
       // System.out.println("rule"+k+"->"+v);
        activeMode.put(k, v);
    }
    
    GameMode nextRule = null;
    
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
