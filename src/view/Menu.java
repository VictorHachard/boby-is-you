/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import static java.awt.SystemColor.menu;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

/**
 *
 * @author Glaskani
 */
public class Menu {
    private ArrayList<String> listeTextBouton;
    //private final ArrayList<String> listeActionBouton;
    
    public Menu(String ... text) { // ... peut recevoir une infiniter d'args
        listeTextBouton = new ArrayList<String>();
        listeTextBouton.addAll(Arrays.asList(text));       
      //  listeActionBouton = new ArrayList<String>();
       // listeActionBouton.addAll(Arrays.asList(arg));  
    }
  
    public VBox genarateBox(){
        VBox listeVerticale = new VBox();
        
        Text t = new Text();
        
        t.setText(listeTextBouton.get(0));
        listeVerticale.getChildren().add(t);
             
        for(int i = 1; i<listeTextBouton.size();i++) {
            Button b = new Button();
        
            b.setMinSize(200,50);
            b.setText(listeTextBouton.get(i));
            b.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
            listeVerticale.getChildren().add(b);
        }
        
        return listeVerticale;
    }
}
