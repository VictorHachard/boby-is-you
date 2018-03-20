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
public class GameModeNormal extends GameMode {
    
    public GameModeNormal(Maps map) throws TypeElementNotFoundException {
        super(map);
    }

    @Override
    void lose() {}
    
}
