/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import exeptions.ElementsNotFoundException;

/**
 *
 * @author Windows
 */
public class GameModeNormal extends GameMode {
    
    public GameModeNormal(Maps map) throws ElementsNotFoundException {
        super(map);
    }

    @Override
    void lose() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
