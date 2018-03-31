package common.model;

import common.exeptions.TypeElementNotFoundException;
import common.exeptions.WinException;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author Glaskani
 */
public abstract class Rule {
    private static HashMap<Property,Boolean> activeMap = new HashMap<>();
    public static boolean isActive(Property name){
        return activeMap.getOrDefault(name,false);
    }
    public static void desactivateAll() {
        activeMap = new HashMap<>();
        //System.out.println("desactivating all rules");
    }
    public static void setActivity(Property k, Boolean v){
        System.out.println("rule"+k+"->"+v);
        activeMap.put(k, v);
    }

    static boolean getBoolean(Property property) {
        if (activeMap.get(property)==null)
            return true;
        return false;
    }
    
    Rule nextRule = null;
    
    public void addRule(Rule next){
        if (nextRule == null){
            nextRule = next;
        }else{
            nextRule.addRule(next);
        }
    }
    
    public boolean checkPush(Position pos,Directions direction,TypeElement player) throws TypeElementNotFoundException, IOException{
        boolean res = true;
        if (isActive(this.getProperty())){
            res = workPush(pos,direction,player);
        }
        if (nextRule == null){
            return res;
        }
        return res && nextRule.checkPush(pos,direction,player);
    }
    
    public boolean check(Position pos,Directions direction,TypeElement player) throws WinException, TypeElementNotFoundException, IOException{
        boolean res = true;
        if (isActive(this.getProperty())){
            res = work(pos,direction,player);
        }
        if (nextRule == null){
            return res;
        }
        return res && nextRule.check(pos,direction,player);
    }
    
    abstract boolean work(Position pos,Directions direction,TypeElement player) throws WinException, TypeElementNotFoundException, IOException;
    abstract Property getProperty();

    boolean workPush(Position pos, Directions direction, TypeElement player){return true;};
}
