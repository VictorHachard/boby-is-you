package common.model;

import common.exeptions.TypeElementNotFoundException;
import common.exeptions.WinException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Glaskani
 */
public abstract class Rule {
    
    private static HashMap<Property,Boolean> activeMap = new HashMap<>();
    
    public static boolean isActive(Property name){
        return activeMap.getOrDefault(name,false);
    }
    static void desactivateAll() {
        activeMap = new HashMap<>();
        //System.out.println("desactivating all rules");
    }
    static void setActivity(Property k, Boolean v){
       // System.out.println("rule"+k+"->"+v);
        activeMap.put(k, v);
    }

    static List<Property> desactivatePlayerList(Property ... pro) {
        List<Property> temps = new ArrayList<>();
        for (Property p : pro)
            if (activeMap.containsKey(p)) {
                activeMap.put(p, false);
                temps.add(p);
            }
        return temps;
    }
    
    static void activatePlayerList(List<Property> list) {
        if (list==null)
            return;
        for (Property p : list)
            if (activeMap.containsKey(p))
                activeMap.put(p, true);
    }
    
    Rule nextRule = null;
    
    void addRule(Rule ... next){
        for (Rule r:next) {
            if (nextRule == null)
                nextRule = r;
            else
                nextRule.addRule(r);
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
