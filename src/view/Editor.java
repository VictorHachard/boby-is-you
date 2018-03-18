/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.TypeElements;

/**
 *
 * @author Glaskani
 */
public class Editor {
    
    
    Editor() {
        
        //demande la taille du board avant de le cree ou load un fichier a editer.
        // creation d'un board board  Board board = new Board(x,y) x y doivent uniquement etre l'espace de jeu.
        // creation d'un board board  Board board = new Board(fileName) filename fichier a editer
        //TODO consteructeur qui cree un layout de 3 un avec tout les object possiable a mettre un avec l affichage de la map et enfin un qui contien les element present dans la cell (Placement)
        // pour avoir tout la liste des elment for(int k=0;k<board.getListGrid().get(x).get(y).getListeContenu().size();k++) { k = 1 premier elem k = 2 second ... donner x et y en fonction de quel case et demander
        //                      TypeElements objectName = board.getListGrid().get(x).get(y).getListeContenu().get(k).getTypeElements();
        //pour avjouter une elme board.addPlacement( int x, int y, int movingDirection, object);
        // pour avour une liste de tout les elments et bloc disponible board TypeElements[] all = TypeElements.getAll();
    }
    
}
