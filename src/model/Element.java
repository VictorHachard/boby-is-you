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
public class Element {
    private TypeElements tc; //type de la case (mur)
    private ArrayList<TypeRule> ls; //liste de regle
    private ArrayList<Placement> pl; // liste case ou la case ce trouve
    
    public Element() {
        
    }
    
    //Getters
    public TypeElements getTypeElements() {
        return this.tc;
    }
    public ArrayList<TypeRule> getTypeRule() {
        return this.ls;
    }    
    
    //Setters
    public void setTypeElements(TypeElements element) {
        this.tc = element;
    }
    public void setTypeRule(ArrayList<TypeRule> rule) {
        this.ls = rule;
    } 
    
    /**
     * Verifie si la régle est bien dans l'element.
     * @param tr liste des regles.
     * @return True si l'element est dans la liste, False si elle n'y est pas.
     */
    public boolean isRule(TypeRule tr) {
        //for(CalssA a : listeDeA) listeA = ArrayList<ClassA>
        for(TypeRule i:ls) 
           if (i == tr)
               return true;
        
        return false;
    }
    
    /**
     * 
     */
    public void preUpdateCase() {
        
    }
    
        
    /**
     * 
     */
    public void postUpdateCase() {
        
    }
    
    
    
    
    /*
    public static int bidon(){;
        do return 0; while(true);
        while(true) return 0;
        for(..;..;..) return 2;
        for(...:...) retunr 0;
    }*/
    
    //for(...)
    //  XXXXX   Block: {.....}
    //          Code : return treu;
    //          for
    //          if
    //          funrtionBidon(..);
}
