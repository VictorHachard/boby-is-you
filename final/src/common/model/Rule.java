package common.model;

import common.exeptions.WinException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Glaskani
 */
public abstract class Rule {
    
    private static HashMap<Property,Boolean> activeMap = new HashMap<>();
    
    /**
     * Revois true si la Property est active dans activeMap, false dans le
     * cas contraire
     * @param name Property
     * @return boolean true ou false
     */
    public static boolean isActive(Property name) {
        return activeMap.getOrDefault(name,false);
    }
    
    /**
     * Desactive toutes les regles presentent dans activeMap.
     */
    public static void desactivateAll() {
        activeMap = new HashMap<>();
    }
    
    /**
     * Change l'activiter de la Property donner k, en focntion de v.
     * @param k Property à changer.
     * @param v Boolean true ou false pour activer ou non.
     */
    static void setActivity(Property k, Boolean v) {
        activeMap.put(k, v);
    }
    
    /**
     * Desactive les Property pro et revois une liste des pro desactiver
     * @param pro Property, (plusieur peuvent etre donner)
     * @return ListProperty
     */
    static List<Property> desactivatePlayerList(Property ... pro) {
        List<Property> temps = new ArrayList<>();
        for (Property p : pro)
            if (activeMap.containsKey(p)) {
                activeMap.put(p, false);
                temps.add(p);
            }
        return temps;
    }
    
    /**
     * Active les Property contenue dans la list
     * @param list ListProperty, Property à activer
     */
    static void activatePlayerList(List<Property> list) {
        if (list==null)
            return;
        for (Property p : list)
            if (activeMap.containsKey(p))
                activeMap.put(p, true);
    }
    
    Rule nextRule = null;
    
    /**
     * 
     * @param next 
     */
    void addRule(Rule ... next){
        for (Rule r:next) {
            if (nextRule == null)
                nextRule = r;
            else
                nextRule.addRule(r);
            }
    }
    
    /**
     * 
     * @param pos
     * @param direction
     * @param player
     * @return 
     */
    public boolean checkPush(Position pos,Directions direction,TypeElement player) {
        boolean res = true;
        if (isActive(this.getProperty())){
            res = workPush(pos,direction,player);
        }
        if (nextRule == null){
            return res;
        }
        return res && nextRule.checkPush(pos,direction,player);
    }
    
    /**
     * 
     * @param pos
     * @param direction
     * @param player
     * @return
     * @throws WinException
     */
    public boolean check(Position pos,Directions direction,TypeElement player) throws WinException {
        boolean res = true;
        if (isActive(this.getProperty())){
            res = work(pos,direction,player);
        }
        if (nextRule == null){
            return res;
        }
        return res && nextRule.check(pos,direction,player);
    }
    
    abstract boolean work(Position pos,Directions direction,TypeElement player) throws WinException;
    abstract Property getProperty();

    boolean workPush(Position pos, Directions direction, TypeElement player) {
        return true;
    };
}
