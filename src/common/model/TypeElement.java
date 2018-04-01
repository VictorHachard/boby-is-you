package common.model;
import common.exeptions.TypeElementNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * @author Glaskani
 */
public enum TypeElement {
    
    /*
     * Convention 0 : fond,
     * 1 : materiaux sur le fond,
     * 2 : objects, 
     * 3 : joueurs, ia(s), régles et defintions.
     */
    
    ROCK("ROCK","E_R ",2,null,null,TypeTypeElement.BLOCK),
    LAVA("LAVA","E_L ",1,null,null,TypeTypeElement.BLOCK),
    WALL("WALL","E_W ",1,null,null,TypeTypeElement.BLOCK),
    WALLINJOUABLE("WALLINJOUABLE","E_WI",0,null,null,TypeTypeElement.BLOCK),
    ICE("ICE","E_I ",1,null,null,TypeTypeElement.BLOCK),
    SPIKE("SPIKE","E_S ",1,null,null,TypeTypeElement.BLOCK),
    WATER("WATER","E_WA",1,null,null,TypeTypeElement.BLOCK),
    ERROR("ERROR","E_ER",1,null,null,TypeTypeElement.BLOCK),
    SKULL("SKULL","E_S ",2,null,null,TypeTypeElement.BLOCK),
    GRASS("GRASS","E_G ",1,null,null,TypeTypeElement.BLOCK),
    FLAG("FLAG","E_F ",2,null,null,TypeTypeElement.BLOCK),
    EMPTY("EMPTY","    ",0,null,null,TypeTypeElement.BLOCK),
    EMPTYINJOUABLE("EMPTYINJOUABLE","    ",0,null,null,TypeTypeElement.BLOCK),
    METAL("METAL","E_M ",1,null,null,TypeTypeElement.BLOCK),
    STAR("STAR","E_ST",2,null,null,TypeTypeElement.BLOCK),
    LOVE("LOVE","E_L ",2,null,null,TypeTypeElement.BLOCK),
    PORTAL_IN("PORTAL_IN","E_PI",1,null,null,TypeTypeElement.BLOCK),
    PORTAL_OUT("PORTAL_OUT","E_PO",1,null,null,TypeTypeElement.BLOCK),
    
    PLAYER1("BABA","E_P ",3,null,null,TypeTypeElement.PLAYER),
    MONSTER("MONSTER","E_M ",3,null,null,TypeTypeElement.PLAYER),
    ANNI("ANNI","E_A ",3,null,null,TypeTypeElement.PLAYER),
    
    TEXT_ROCK("TEXT_ROCK","T_R ",3,TypeElement.ROCK,null,TypeTypeElement.TEXT),
    TEXT_STAR("TEXT_STAR","T_ST",3,TypeElement.STAR,null,TypeTypeElement.TEXT),
    TEXT_LAVA("TEXT_LAVA","T_LA",3,TypeElement.LAVA,null,TypeTypeElement.TEXT),
    TEXT_WALL("TEXT_WALL","T_W ",3,TypeElement.WALL,null,TypeTypeElement.TEXT),
    TEXT_YOU("TEXT_YOU","T_Y ",3,null,null,TypeTypeElement.TEXT),
    TEXT_PLAYER1("TEXT_BABA","T_P ",3,TypeElement.PLAYER1,null,TypeTypeElement.TEXT),
    TEXT_GRASS("TEXT_GRASS","T_G ",3,TypeElement.GRASS,null,TypeTypeElement.TEXT),
    TEXT_FLAG("TEXT_FLAG","T_F ",3,TypeElement.FLAG,null,TypeTypeElement.TEXT),
    TEXT_ERROR("TEXT_ERROR","T_ER",3,TypeElement.ERROR,null,TypeTypeElement.TEXT),
    TEXT_EMPTY("TEXT_EMPTY","T_EM",3,TypeElement.EMPTY,null,TypeTypeElement.TEXT),
    TEXT_LOVE("TEXT_LOVE","T_L ",3,TypeElement.LOVE,null,TypeTypeElement.TEXT),
    TEXT_WATER("TEXT_WATER","T_WA",3,TypeElement.WATER,null,TypeTypeElement.TEXT),
    TEXT_ANNI("TEXT_ANNI","T_A ",3,TypeElement.ANNI,null,TypeTypeElement.TEXT),
    TEXT_SKULL("TEXT_SKULL","T_S ",3,TypeElement.SKULL,null,TypeTypeElement.TEXT),
    TEXT_ICE("TEXT_ICE","T_I ",3,TypeElement.ICE,null,TypeTypeElement.TEXT),
    TEXT_MONSTER("TEXT_MONSTER","T_M ",3,TypeElement.MONSTER,null,TypeTypeElement.TEXT),
    
    SLIP("SLIP","R_SL",3,null,Property.SLIP,TypeTypeElement.RULE),
    WIN("WIN","R_W ",3,null,Property.WIN,TypeTypeElement.RULE),
    PUSH("PUSH","R_P ",3,null,Property.PUSH,TypeTypeElement.RULE),
    STOP("STOP","R_ST",3,null,Property.STOP,TypeTypeElement.RULE),
    SINK("SINK","R_SI",3,null,Property.SINK,TypeTypeElement.RULE),
    MOVE("MOVE","R_M ",3,null,Property.MOVE,TypeTypeElement.RULE),
    BEST("BEST","R_B ",3,null,Property.BEST,TypeTypeElement.RULE),
    MELT("MELT","R_M ",3,null,Property.MELT,TypeTypeElement.RULE),
    HOT("HOT","R_H ",3,null,Property.HOT,TypeTypeElement.RULE),
    YOU("YOU","R_Y ",3,null,Property.YOU,TypeTypeElement.RULE),
    GRAB("GRAB","R_G ",3,null,Property.GRAB,TypeTypeElement.RULE),
    KILL("KILL","R_K ",3,null,Property.KILL,TypeTypeElement.RULE),
    
    UP("UP","UP  ",3,null,Property.UP,TypeTypeElement.RULE),
    DOWN("DOWN","DOWN",3,null,Property.DOWN,TypeTypeElement.RULE),
    LEFT("LEFT","LEFT",3,null,Property.LEFT,TypeTypeElement.RULE),
    RIGHT("RIGHT","RIGH",3,null,Property.RIGHT,TypeTypeElement.RULE),
    
    IS("IS","IS  ",3,null,null,TypeTypeElement.CONNECTER),
    AND("AND","AND ",3,null,null,TypeTypeElement.CONNECTER),
    MAKE("MAKE","MAKE",3,null,null,TypeTypeElement.CONNECTER);

    private final String fromString;
    private final TypeElement te;
    private final String stringConsole;
    private static final Logger LOGGER = Logger.getGlobal();
    private final int intType;
    private final Property rule;
    private final TypeTypeElement type;
    
    /**
     * 
     * @param fromString
     * @param stringConsole 
     * @param intType
     */
    TypeElement(String fromString,String stringConsole, int intType, TypeElement te,Property rule,TypeTypeElement type) {
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
     * @throws TypeElementNotFoundException Si l'erreur est throw cela return TypeElement EMPTY.
     */
    static TypeElement fromString(String element) throws TypeElementNotFoundException {

        try {
            for(TypeElement type : TypeElement.values()) {
                if (type.getElements().equals(element)) {
                    return type;
                }
            }

            throw new TypeElementNotFoundException();
        }
        catch(TypeElementNotFoundException ex) {
            LOGGER.log( Level.WARNING, "TypeElements " + element + " was not found \n" + element + " replaced by EMPTY",ex);
            return TypeElement.EMPTY;
        }
    }

    //Getters
    
    /**
     * 
     * @return 
     */
    public TypeTypeElement getType() {
        return type;
    }
    
    /**
     * 
     * @return 
     */
    public Property getRule() {
        return rule;
    }
    
    /**
     * 
     * @return 
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