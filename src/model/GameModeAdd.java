/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import exeptions.TypeElementNotFoundException;

/**
 *
 * @author Windows
 */
public class GameModeAdd extends GameMode {
    
    private double time;
    private int limitedDeplacement;
    
    /**
     * temps
     * @param map
     * @param time
     * @throws TypeElementNotFoundException 
     */
    public GameModeAdd(Maps map,double time) throws TypeElementNotFoundException {
        super(map);
        this.time = time;
        lose();
    }
    
    /**
     * nm de coup
     * @param map
     * @param limitedDeplacement
     * @throws TypeElementNotFoundException 
     */
    public GameModeAdd(Maps map,int limitedDeplacement) throws TypeElementNotFoundException {
        super(map);
        this.limitedDeplacement = limitedDeplacement;
        lose();
    }

    @Override
    void lose() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
