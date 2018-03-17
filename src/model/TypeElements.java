/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import JavaBobyIsYou.exeptions.Elements;

/**
 *
 * @author Glaskani
 */
public enum TypeElements {ROCK,LAVA,WALL,ICE,PLAYER1,MONSTER,SPIKE,WATER,ERROR,SKULL,GRASS,FLAG,EMPTY,METAL,HEART,ANNI;
    	private final String ID;
    private TypeElements(String id, TypeElements elements) {
		ID=id;
	}

    public static TypeElements fromString(String element) throws ElementsNotFoundException {

        for(TypeElements type : TypeElements.values()) {
            if (type.getElements().equals(element)) {
                return type;
            }
        }
    //Si aucun bloc n'a l'id correspondant.
    throw new ElementsNotFoundException();
    }

    //Setters
    public String getElements() {
        return ID;
    }
    

}