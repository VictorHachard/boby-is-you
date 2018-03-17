package model;
import exeptions.ElementsNotFoundException;

 /*
 * @author Glaskani
 */
public enum TypeElements {
    ROCK("ROCK","E_R "),
    LAVA("LAVA","E_L "),
    WALL("WALL","E_W "),
    WALLINJOUABLE("WALLINJOUABLE","E_WI"),
    ICE("ICE","E_I "),
    PLAYER1("BABA","E_P "),
    MONSTER("MONSTER","E_M "),
    SPIKE("SPIKE","E_S "),
    WATER("WATER","E_WA"),
    ERROR("ERROR","E_ER"),
    SKULL("SKULL","E_S "),
    GRASS("GRASS","E_G "),
    FLAG("FLAG","E_F "),
    EMPTY("EMPTY","    "),
    METAL("METAL","E_M "),
    HEART("HEART","E_H "),
    ANNI("ANNI","E_A "),
    
    TEXT_ROCK("TEXT_ROCK","T_R "),
    TEXT_WALL("TEXT_WALL","T_W "),
    TEXT_YOU("TEXT_YOU","T_Y "),
    TEXT_PLAYER1("TEXT_BABA","T_P "),
    TEXT_GRASS("TEXT_GRASS","T_G "),
    TEXT_FLAG("TEXT_FLAG","T_F "),
    TEXT_ERROR("TEXT_ERROR","T_ER"),
    TEXT_EMPTY("TEXT_EMPTY","T_EM"),
    TEXT_HEART("TEXT_HEART","T_H "),
    TEXT_WATER("TEXT_WATER","T_WA"),
    TEXT_ANNI("TEXT_ROCK","T_A "),
    TEXT_SKULL("TEXT_SKULL","T_S "),
    TEXT_ICE("TEXT_ICE","T_I "),
    TEXT_MONSTER("TEXT_MONSTER","T_M "),
    
    SLIP("SLIP","R_SL"),
    WIN("WIN","R_W "),
    PUSH("PUSH","R_P "),
    STOP("STOP","R_ST"),
    SINK("SINK","R_SI"),
    MOVE("MOVE","R_M "),
    BEST("BEST","R_B "),
    MELT("MELT","R_M "),
    HOT("HOT","R_H "),
    YOU("YOU","R_Y "),
    GRAB("GRAB","R_G "),
    KILL("KILL","R_K "),
    
    IS("IS","I   ")
    ;

    private final String id;
    private final String letter;
    
    TypeElements(String id,String a) {
		this.id=id;
                letter = a;
    }

    static TypeElements fromString(String element) throws ElementsNotFoundException {

        for(TypeElements type : TypeElements.values()) {
            if (type.getElements().equals(element)) {
                return type;
            }
        }
        
        throw new ElementsNotFoundException();
    }

    //Setters
    public String getElements() {
        return id;
    }
    
    String getLetter(){
        return letter;
    }
    

}