package model;
import exeptions.ElementsNotFoundException;

/*
 * @author Glaskani
 */
public enum TypeElements {
    
    /*
     * Convention 0 : fond,
     * 1 : materiaux sur le fond,
     * 2 : objects, 
     * 3 : joueurs, ia(s), régles et defintions.
     */
    
    ROCK("ROCK","E_R ",2),
    LAVA("LAVA","E_L ",0),
    WALL("WALL","E_W ",1),
    WALLINJOUABLE("WALLINJOUABLE","E_WI",0),
    ICE("ICE","E_I ",1),
    SPIKE("SPIKE","E_S ",1),
    WATER("WATER","E_WA",0),
    ERROR("ERROR","E_ER",1),
    SKULL("SKULL","E_S ",2),
    GRASS("GRASS","E_G ",1),
    FLAG("FLAG","E_F ",2),
    EMPTY("EMPTY","    ",0),
    METAL("METAL","E_M ",0),
    HEART("HEART","E_H ",2),
    
    PLAYER1("BABA","E_P ",3),
    MONSTER("MONSTER","E_M ",3),
    ANNI("ANNI","E_A ",3),
    
    TEXT_ROCK("TEXT_ROCK","T_R ",3),
    TEXT_WALL("TEXT_WALL","T_W ",3),
    TEXT_YOU("TEXT_YOU","T_Y ",3),
    TEXT_PLAYER1("TEXT_BABA","T_P ",3),
    TEXT_GRASS("TEXT_GRASS","T_G ",3),
    TEXT_FLAG("TEXT_FLAG","T_F ",3),
    TEXT_ERROR("TEXT_ERROR","T_ER",3),
    TEXT_EMPTY("TEXT_EMPTY","T_EM",3),
    TEXT_HEART("TEXT_HEART","T_H ",3),
    TEXT_WATER("TEXT_WATER","T_WA",3),
    TEXT_ANNI("TEXT_ROCK","T_A ",3),
    TEXT_SKULL("TEXT_SKULL","T_S ",3),
    TEXT_ICE("TEXT_ICE","T_I ",3),
    TEXT_MONSTER("TEXT_MONSTER","T_M ",3),
    
    SLIP("SLIP","R_SL",3),
    WIN("WIN","R_W ",3),
    PUSH("PUSH","R_P ",3),
    STOP("STOP","R_ST",3),
    SINK("SINK","R_SI",3),
    MOVE("MOVE","R_M ",3),
    BEST("BEST","R_B ",3),
    MELT("MELT","R_M ",3),
    HOT("HOT","R_H ",3),
    YOU("YOU","R_Y ",3),
    GRAB("GRAB","R_G ",3),
    KILL("KILL","R_K ",3),
    
    IS("IS","I   ",3)
    ;

    private final String fromString;
    private final String stringConsole;
    private final int intType;
    
    /**
     * 
     * @param fromString
     * @param stringConsole 
     * @param intType
     */
    TypeElements(String fromString,String stringConsole, int intType) {
		this.fromString = fromString;
                this.stringConsole = stringConsole;
                this.intType = intType;
    }
    
    /**
     * Tranforme et revois un string en TypeElments.
     * @param element String
     * @return TypeElments
     * @throws ElementsNotFoundException 
     */
    static TypeElements fromString(String element) throws ElementsNotFoundException {

        for(TypeElements type : TypeElements.values()) {
            if (type.getElements().equals(element)) {
                return type;
            }
        }
        
        throw new ElementsNotFoundException();
    }

    //Getters
    
    /**
     * Revois l'element sous la forme d'un String.
     * @return String
     */
    public String getElements() {
        return fromString;
    }
    
    /**
     * Revois l'element sous la forme d'un String à afficher en console.
     * @return String
     */
    public String getLetter(){
        return stringConsole;
    }
    
    /**
     * Revois la valeur priotiter de l'element.
     * @return int
     */
    public int getPriority(){
        return intType;
    }
    
    /**
     * Revois une liste de toutes les valeurs de TypeElements.
     * @return TypeElements[]
     */
    public static TypeElements[] getAll(){
        return TypeElements.values();
    }
       
}