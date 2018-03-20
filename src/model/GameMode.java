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
public abstract class GameMode {
    
    Board board;
    
    /**
     * 
     * @param map 
     * @throws exeptions.ElementsNotFoundException 
     */
    public GameMode(Maps map) throws ElementsNotFoundException {
        this.board = new Board(map);
    }
    
    /**
     * Revois le board.
     * @return Board
     */
    public Board getBoard() {
        return this.board;
    }
    
    abstract void lose();
}
