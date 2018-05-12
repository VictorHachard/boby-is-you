package common.model;

import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Convention 0 : fond,
 * 1 : materiaux sur le fond,
 * 2 : objects, 
 * 3 : joueurs, ia(s), régles et defintions.
 * @author Glaskani
 */
public enum TypeElement {
    
    ROCK("ROCK","E_R ",2,null,null,Type.BLOCK),
    LAVA("LAVA","E_L ",1,null,null,Type.BLOCK),
    WALL("WALL","E_W ",1,null,null,Type.BLOCK),
    WALLINJOUABLE("WALLINJOUABLE","E_WI",0,null,null,Type.BLOCK),
    ICE("ICE","E_I ",1,null,null,Type.BLOCK),
    WATER("WATER","E_WA",1,null,null,Type.BLOCK),
    SKULL("SKULL","E_S ",2,null,null,Type.BLOCK),
    GRASS("GRASS","E_G ",1,null,null,Type.BLOCK),
    FLAG("FLAG","E_F ",2,null,null,Type.BLOCK),
    EMPTY("EMPTY","    ",0,null,null,Type.BLOCK),
    EMPTYINJOUABLE("EMPTYINJOUABLE","    ",0,null,null,Type.BLOCK),
    METAL("METAL","E_M ",1,null,null,Type.BLOCK),
    LOVE("LOVE","E_L ",2,null,null,Type.BLOCK),
    PORTAL_IN("PORTAL_IN","E_PI",1,null,null,Type.BLOCK),
    PORTAL_OUT("PORTAL_OUT","E_PO",1,null,null,Type.BLOCK),
    
    BESTELEME("BESTELEME","BEST",5,null,null,Type.BLOCK),
    
    PLAYER1("BABA","E_P ",3,null,null,Type.PLAYER),
    MONSTER("MONSTER","E_M ",3,null,null,Type.BLOCK),
    ANNI("ANNI","E_A ",3,null,null,Type.PLAYER),
   
    TEXT_ROCK("TEXT_ROCK","T_R ",3,TypeElement.ROCK,null,Type.TEXT),
    TEXT_LAVA("TEXT_LAVA","T_LA",3,TypeElement.LAVA,null,Type.TEXT),
    TEXT_WALL("TEXT_WALL","T_W ",3,TypeElement.WALL,null,Type.TEXT),
    TEXT_PLAYER1("TEXT_BABA","T_P ",3,TypeElement.PLAYER1,null,Type.TEXT),
    TEXT_GRASS("TEXT_GRASS","T_G ",3,TypeElement.GRASS,null,Type.TEXT),
    TEXT_FLAG("TEXT_FLAG","T_F ",3,TypeElement.FLAG,null,Type.TEXT),
    TEXT_EMPTY("TEXT_EMPTY","T_EM",3,TypeElement.EMPTY,null,Type.TEXT),
    TEXT_LOVE("TEXT_LOVE","T_L ",3,TypeElement.LOVE,null,Type.TEXT),
    TEXT_GOOP("TEXT_WATER","T_WA",3,TypeElement.WATER,null,Type.TEXT),
    TEXT_ANNI("TEXT_ANNI","T_A ",3,TypeElement.ANNI,null,Type.TEXT),
    TEXT_SKULL("TEXT_SKULL","T_S ",3,TypeElement.SKULL,null,Type.TEXT),
    TEXT_ICE("TEXT_ICE","T_I ",3,TypeElement.ICE,null,Type.TEXT),
    TEXT_MONSTER("TEXT_MONSTER","T_M ",3,TypeElement.MONSTER,null,Type.TEXT),
    
    STRONG("STRONG","R_ST",3,null,Property.STRONG,Type.RULE),
    WEAK("WEAK","R_WE",3,null,Property.WEAK,Type.RULE),
    SLIP("SLIP","R_SL",3,null,Property.SLIP,Type.RULE),
    FLY("FLY","R_FL",3,null,Property.FLY,Type.RULE),
    WIN("WIN","R_W ",3,null,Property.WIN,Type.RULE),
    PUSH("PUSH","R_P ",3,null,Property.PUSH,Type.RULE),
    STOP("STOP","R_ST",3,null,Property.STOP,Type.RULE),
    SINK("SINK","R_SI",3,null,Property.SINK,Type.RULE),
    MOVE("MOVE","R_M ",3,null,Property.MOVE,Type.RULE),
    BEST("BEST","R_B ",3,null,Property.BEST,Type.RULE),
    MELT("MELT","R_M ",3,null,Property.MELT,Type.RULE),
    HOT("HOT","R_H ",3,null,Property.HOT,Type.RULE),
    YOU("YOU","R_Y ",3,null,Property.YOU,Type.RULE),
    YOU1("YOU1","R_Y ",3,null,Property.YOU1,Type.RULE),
    YOU2("YOU2","R_Y ",3,null,Property.YOU2,Type.RULE),
    KILL("KILL","R_K ",3,null,Property.KILL,Type.RULE),
    
    UP("UP","UP  ",3,null,Property.UP,Type.RULE),
    DOWN("DOWN","DOWN",3,null,Property.DOWN,Type.RULE),
    LEFT("LEFT","LEFT",3,null,Property.LEFT,Type.RULE),
    RIGHT("RIGHT","RIGH",3,null,Property.RIGHT,Type.RULE),
    
    IS("IS","IS  ",3,null,null,Type.CONNECTER),
    AND("AND","AND ",3,null,null,Type.CONNECTER),
    //MAKE("MAKE","MAKE",3,null,null,Type.CONNECTER),
    
    NONEINRANGE("NONE",null,-1,null,null,null);

    private final String fromString;
    private final TypeElement te;
    private final String stringConsole;
    private static final Logger LOGGER = Logger.getGlobal();
    private final int intType;
    private final Property rule;
    private final Type type;
    
    TypeElement(String fromString,String stringConsole, int intType, TypeElement te,Property rule,Type type) {
	this.fromString = fromString;
        this.stringConsole = stringConsole;
        this.intType = intType;
        this.te = te;
        this.rule = rule;
        this.type = type;
    }
    
    /**
     * Tranforme et revois un string en TypeElments.
     * @param element String
     * @return TypeElments
     */
    static TypeElement fromString(String element) {
        for(TypeElement type : TypeElement.values())
            if (type.getElements().equals(element))
                return type;
        LOGGER.log(Level.WARNING, "TypeElements {0} was not found\nSOLVE: {0} was replaced by EMPTY",element);
        return TypeElement.EMPTY;
    }
    
    /**
     * Revois le Type de l'element.
     * @return Type
     */
    public Type getType() {
        return type;
    }
    
    /**
     * Revois le Property d'un Type rule.
     * @return Property
     */
    public Property getRule() {
        return rule;
    }
    
    /**
     * Revois le TypeElement de l'application d'un Type text.
     * @return TypeElement
     */
    public TypeElement getText() {
        return te;
    }
    
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
     * Revois une liste de toutes les valeurs de TypeElement.
     * @return TypeElement[]
     */
    public static TypeElement[] getAll(){
        return TypeElement.values();
    }
}