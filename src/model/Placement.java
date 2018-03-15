/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Glaskani
 */
public class Placement {
    private ArrayList<Element> listeContenu;
    private Board board;
    
    /**
     * 
     * @param b 
     */
    public Placement(Board b) {
        listeContenu = new ArrayList<Element>();
        board = b;
               
    }
    
    /**
     * 
     * @return 
     */
    public boolean add() {
        return false;
    }
        
    public boolean pop() {
        return false;        
    }
        
    public boolean get() {
        return false;        
    }
        
    public boolean canPush() {
        return false;        
    }
        
    public boolean canAdd() {
        return false;        
    }
}
