package common.model;

import common.exeptions.WinException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.util.Pair;

/**
 * @author Glaskani
 */
public class Board {
    
    private List<Element> listAllElement = new ArrayList<>();
    private Element best = new Element(TypeElement.BESTELEME);
    private final GameMode listLose;
    private List<Position> is;
    private List<Position> make;
    private List<List<Cell>> listGrid = new ArrayList<>();
    private final int x;
    private final int y;
    private final Cell unplayable = new Cell(new Unplayable());
    private final Element empty = new Empty();
    private final MusicHashMap music= MusicHashMap.getInstance();;
    private Rule listRule;
    private static Board INSTANCE = null;
    private final Element emptyPlayable=new Element(TypeElement.EMPTY);
    public int limitedDeplacement;
    public String title="";
    
    /**
     * 
     * @param map
     * @return
     */
    public static Board getInstance(Maps map) {           
        if (INSTANCE == null)
            INSTANCE = new Board(map);
        return INSTANCE;
    }
    
    /**
     * 
     * @return l'incentence de Board
     */
    public static Board getInstance() {           
        return INSTANCE;
    }
    
    /**
     * 
     */
    public static void reloadInstance() {
        INSTANCE = null;
    }
    
    /**
     * 
     * @param map
     */
    public Board(Maps map) {
        this.x = map.getSizeX();
        this.y = map.getSizeY();
        //genre le empty jouable
        this.listAllElement.add(emptyPlayable);
        
        generateGrid(x-2,y-2);

        for(int i=1;i<y-1;i++){
            for(int j=1;j<x-1;j++){
                List<Element> te =  map.getListElement(j,i);
                for(int k=1;k<te.size();k++){
                    addPlacement(j,i,te.get(te.size()-k));
                    //Ajoute les pushs sur les texte et les texte regles.
                    if (te.get(k).getType()==Type.CONNECTER ||
                            te.get(k).getType()==Type.TEXT ||
                            te.get(k).getType()==Type.RULE)
                        listGrid.get(i).get(j).getZ().get(k).addRule(Property.PUSH); 
                }
            }
        }
        fillEmpty();
        is = getAllPos(TypeElement.IS);
        for (Position p:is)
            rule(p,TypeElement.IS);
        
        listRule = new Tp(this);
        listRule.addRule(new Fly(this),
                new Slip(this),
                new Kill(this),
                new Sink(this),
                new Move(this),
                new Melt(this),
                new Win(this),
                new Shut(this));
        this.limitedDeplacement=map.limitedDeplacement;
        this.title=map.title;
        listLose = new GameModeNumberOfMove(this);
        //listRule.addRule(new Shut(this));
        //make = getAllPos(TypeElement.MAKE);
        
    }   
    
    private void fillEmpty() {
        //remove tout les empty
        for(int i=1;i<y-1;i++)
            for(int j=1;j<x-1;j++)
                if (this.listGrid.get(i).get(j).getZ().contains(this.emptyPlayable))
                    this.listGrid.get(i).get(j).removeElement(TypeElement.EMPTY);
        for(int i=1;i<y-1;i++)
            for(int j=1;j<x-1;j++)
                if (this.listGrid.get(i).get(j).getZ().size()==1)
                    this.listGrid.get(i).get(j).addElement(this.emptyPlayable);
    }
    
    /**
     * Supprime et reset toutes les regles.
     */
    private void deleteAllRule() {
        Rule.desactivateAll();
        for (Element e:this.listAllElement)
            if (!(e.getType()==Type.CONNECTER||e.getType()==Type.RULE||e.getType()==Type.TEXT))
                if (!(e.getTypeRule().isEmpty()))
                    for (int i=0;i<e.getTypeRule().size();i++)
                        e.deleteRule(e.getTypeRule().get(i));   
    }
    
    /**
     * Ajout a obsMap les observer et les Positions
     */
    private List<Position> getAllPos(TypeElement te) {
        List<Position> lp = new ArrayList<>();
        for (Position p:getPositionOf(te))
            lp.add(p);
        return lp;
    }
    
    /**
     * 
     * @param x
     * @param y
     * @param te
     * @param te1 
     */
    private void checkAndHori(int x,int y,Type te, Type te1) {
        int i=0;
        if (listGrid.get(x).get(y-2).find(TypeElement.AND))
            if (listGrid.get(x).get(y-3).find(te)) {
                addRule(listGrid.get(x).get(y-3).getType(te),
                listGrid.get(x).get(y+1).getType(te1));
                i++;
            }
        if (listGrid.get(x).get(y+2).find(TypeElement.AND))
            if (listGrid.get(x).get(y+3).find(te1)) {
                addRule(listGrid.get(x).get(y-1).getType(te),
                listGrid.get(x).get(y+3).getType(te1));
                i++;
            }
        if (i==2)
            addRule(listGrid.get(x).get(y-3).getType(te),
            listGrid.get(x).get(y+3).getType(te1));
    }
    
    /**
     * 
     * @param x
     * @param y
     * @param te
     * @param te1 
     */
    private void checkAndVer(int x,int y,Type te, Type te1) {
        int i=0;
        if (listGrid.get(x-2).get(y).find(TypeElement.AND))
            if (listGrid.get(x-3).get(y).find(te)) {
                addRule(listGrid.get(x-3).get(y).getType(te),
                listGrid.get(x+1).get(y).getType(te1));
                i++;
            }
        if (listGrid.get(x+2).get(y).find(TypeElement.AND))
            if (listGrid.get(x+3).get(y).find(te1)) {
                addRule(listGrid.get(x-1).get(y).getType(te),
                listGrid.get(x+3).get(y).getType(te1));
                i++;
            }
        if (i==2)
            addRule(listGrid.get(x-3).get(y).getType(te),
            listGrid.get(x+3).get(y).getType(te1));
    }
    
    /**
     * 
     * @param x
     * @param y
     * @param te
     * @param te1
     * @return
     */
    private boolean checkRule(int x,int y,Type te, Type te1) {
        boolean check = false;
        if (listGrid.get(x).get(y-1).find(te)
                && listGrid.get(x).get(y+1).find(te1)) {
            if (listGrid.get(x).get(y+1).find(Type.TEXT))
                changeType(listGrid.get(x).get(y-1).getType(te),
                    listGrid.get(x).get(y+1).getType(te1));
            else {                        
                if (listGrid.get(x).get(y+1).find(TypeElement.UP) || 
                        listGrid.get(x).get(y+1).find(TypeElement.LEFT) ||
                        listGrid.get(x).get(y+1).find(TypeElement.DOWN) ||
                        listGrid.get(x).get(y+1).find(TypeElement.RIGHT)) {
                    changeDirections(listGrid.get(x).get(y-1).getType(te),
                    listGrid.get(x).get(y+1).getType(te1));
                }
                else addRule(listGrid.get(x).get(y-1).getType(te),
                    listGrid.get(x).get(y+1).getType(te1));
            }
            checkAndHori(x,y,te,te1);
            check = true;
        }
        if (listGrid.get(x-1).get(y).find(te)
                && listGrid.get(x+1).get(y).find(te1)) {
            if (listGrid.get(x+1).get(y).find(Type.TEXT))
                changeType(listGrid.get(x-1).get(y).getType(te),
                    listGrid.get(x+1).get(y).getType(te1));
            else { 
                if (listGrid.get(x+1).get(y).find(TypeElement.UP) || 
                        listGrid.get(x+1).get(y).find(TypeElement.LEFT) ||
                        listGrid.get(x+1).get(y).find(TypeElement.DOWN) ||
                        listGrid.get(x+1).get(y).find(TypeElement.RIGHT))
                    changeDirections(listGrid.get(x-1).get(y).getType(te),
                    listGrid.get(x+1).get(y).getType(te1));
                else addRule(listGrid.get(x-1).get(y).getType(te),
                    listGrid.get(x+1).get(y).getType(te1));
            }
            checkAndVer(x,y,te,te1);
            check = true;
        }
        return check;
    }
    
    private void test(int x,int y,Type te, Type te1) {
            if (listGrid.get(x+1).get(y).find(Type.TEXT) && listGrid.get(x-1).get(y).find(Type.TEXT))
                    addElementOnElement(listGrid.get(x-1).get(y).getType(te).getText(), new Element(listGrid.get(x+1).get(y).getType(te1).getText()));
        }
    
    /**
     * 
     * @param e1
     * @param e2
     */
    private void addElementOnElement(TypeElement e1, Element e2) {
        //e2 a faire poper sur e1
        for(int i=1;i<y-1;i++)
            for(int j=1;j<x-1;j++)
                for(int k=0;k<listGrid.get(i).get(j).getZ().size();k++)
                    if (listGrid.get(i).get(j).getZ().get(k).getTypeElement()==e1)
                        addPlacement(j,i,e2);
    }
    
    /**
     * 
     * 
     */
    private void rule(Position pos, TypeElement te) {
        if (checkRule(pos.y,pos.x,Type.TEXT,Type.RULE))
            return;
        checkRule(pos.y,pos.x,Type.TEXT,Type.TEXT);
    }
    
    /**
     * BUGGER
     * @param text
     * @param text2
     */
    private void changeType(TypeElement text,TypeElement text2) { //e1 a mettre e a enlever
        TypeElement bef=text.getText();
        Element aft=new Element(text2.getText());
        List<Element> listAllElementDelete = new ArrayList<>();
        //trouve text
        for(Element e:this.listAllElement)
            if (e.getTypeElement()==text.getText())
                listAllElementDelete.add(e);
        //trouve text2
        for(Element e:this.listAllElement)
            if (e.getTypeElement()==text2.getText())
                 aft = e;
        for(int i=1;i<y-1;i++)
            for(int j=1;j<x-1;j++)
                for(int k=0;k<listGrid.get(i).get(j).getZ().size();k++)
                    if (listGrid.get(i).get(j).getZ().get(k).getTypeElement()==bef) {
                        listGrid.get(i).get(j).removeElement(bef);
                        addPlacement(j,i,aft);  
                    }
        //supprimer tout les text
        for(Element e:listAllElementDelete)
            if (this.listAllElement.contains(e))
                 this.listAllElement.remove(e);
    }
    
    /**
     * 
     * @param text
     * @param rule 
     */
    private void addRule(TypeElement text,TypeElement rule) {
        if (!(rule.getRule()==Property.STOP||rule.getRule()==Property.PUSH||rule.getRule()==Property.YOU))
            Rule.setActivity(rule.getRule(), true);
        for(Element e:listAllElement)
            if (e.getTypeElement()==text.getText())
                e.addRule(rule.getRule());
    }
    
    /**
     * Crée le Board de taille x,y et ajoute les elements injouable et EMPTY.
     * @param x int
     * @param y int
     */
    private void generateGrid(int x,int y) {
        for(int j=0;j<y+2;j++) {
            listGrid.add(new ArrayList<>());
            for(int i=0;i<x+2;i++)
                if(i==0 || j==0 || j==y+1 || i==x+1)
                    listGrid.get(j).add(this.unplayable);
                else
                    listGrid.get(j).add(new Cell (this.empty));
        }
    }
      
    /**
     * Revois la liste general des regles.
     * @return Rule
     */
    Rule getElementRule() {
        return this.listRule;
    }
    
    /**
     * Revois la taille du tableau board en abscisse.
     * @return int
     */
    public int getSizeX() {
        return this.x;
    }
    
    /**
     * Revois la limite de deplacment.
     * @return int
     */
    int getLimitedDeplacement() {
        return this.limitedDeplacement;
    }
    
    /**
     * Revois la taille du tableau board en ordonnée.
     * @return int
     */
    public int getSizeY() {
        return this.y;
    }
    
    /**
     * Revois la liste contenant Cell.
     * @return ListListPlacement
     */
    public List<List<Cell>> getListGrid() {
        return this.listGrid;
    }
    
    /**
     * Revois la liste contenant Cell.
     * @return ListListPlacement
     */
    public List<Element> getListAllElement() {
        return this.listAllElement;
    }
    
    /**
     * 
     * @param x
     * @param y
     * @param object
     */
    private void addPlacement(int x, int y, Element object) {
        for(Element e:this.listAllElement)
                if(e.equals(object)) {
                    listGrid.get(y).get(x).addElement(e);
                    return;
                }
        listAllElement.add(object);
        listGrid.get(y).get(x).addElement(object);
    }
    
    /**
     * Revois une chaine de charactére du Board.
     * @return String
     */
    @Override
    public String toString(){
        StringBuilder  sb = new StringBuilder();
        for(List<Cell> lp:this.listGrid){
            for (Cell p : lp) {
                sb.append(p.getZ().get(p.getZ().size()-1)
                        .getTypeElement().getLetter()).append("|");
            }
            sb.append('\n');
        } return sb.toString();
    }
    
    /**
     * 
     * @param te
     * @return 
     */
    List<Position> getPositionOf(TypeElement te){
        List<Position> lp = new ArrayList<>();
        for(int i=0;i<this.y;i++)
            for(int j=0;j<this.x;j++)
                if(this.listGrid.get(i).get(j).find(te))
                    lp.add(new Position(j,i));
        if (lp.isEmpty())
            return null;
        return lp;
    }
    
    /**
     * 
     * @return 
     */
    private List<Pair<Position,TypeElement>> getPlayerType(){        
        List<Pair<Position,TypeElement>> tempsList = new ArrayList<>();
        List<Position> temp;
        List<TypeElement> alredycheck = new ArrayList<>();
        for (Element e:this.listAllElement)
            for (Property p:e.getTypeRule())
                if (p==Property.YOU && (!(alredycheck.contains(e.getTypeElement())))) {
                    alredycheck.add(e.getTypeElement());
                    temp = getPositionOf(e.getTypeElement());
                    if (!(temp==null))
                        for (Position pos:temp)
                            tempsList.add(new Pair<>(pos,e.getTypeElement()));
                }
        if (tempsList.isEmpty())
            return null;
        return tempsList;
    }
    
    /**
     * 
     * @param pos
     * @param dir
     * @param element 
     */
    void editPlacement(Position pos, Directions dir, TypeElement element) {
        listGrid.get(pos.y+dir.getDirVer()).get(pos.x+dir.getDirHori())
                .addElement(listGrid.get(pos.y).get(pos.x).getElements(element));
        listGrid.get(pos.y).get(pos.x).removeElement(element);
    }
    
    /**
     * Execute toutes les actions et verification qui doivent entre faite à
     * chaque tour.
     * @param dir Direction de l'input de l'utilsateur.
     */
    public void movePlayer(Directions dir) {
        for(int i=1;i<y-1;i++)
            for(int j=1;j<x-1;j++)
                if (this.listGrid.get(j).get(i).find(Property.BEST) && !this.listGrid.get(j).get(i).find(TypeElement.BESTELEME))
                   this.listGrid.get(j).get(i).addElement(best);
                else if (!this.listGrid.get(j).get(i).find(Property.BEST) && this.listGrid.get(j).get(i).find(TypeElement.BESTELEME))
                    this.listGrid.get(j).get(i).removeElement(TypeElement.BESTELEME);
        //verifier si on a pas fini un gamemode
        if (!this.listLose.check())
            return;
        List<Pair<Position,TypeElement>> player = sort(dir, getPlayerType());   
        if (player==null)
            return;
        Position pos;
        TypeElement te;
        //just executer move
        for(Pair<Position,TypeElement> a:player) {
            pos = a.getKey();
            te = a.getValue();
            if(pos.y+dir.getDirVer() < y && pos.x+dir.getDirHori() < x) {
                List<Property> temps1 = null;
                temps1 = Rule.desactivatePlayerList(Property.MOVE);
                try {
                if (!this.listRule.check(pos, dir, te))
                    continue;
                } catch (WinException e) {
                    return;
                }
                Rule.activatePlayerList(temps1);
                System.out.println(listGrid.get(pos.y+dir.getDirVer()).get(pos.x+dir.getDirHori()).canAdd());
                if (listGrid.get(pos.y+dir.getDirVer()).get(pos.x+dir.getDirHori()).canAdd()){ //verifie si il peut add la case suivante
                    editPlacement(pos,dir,te);
                    //this.music.play(Music.ADD);
                }
                else if (listGrid.get(pos.y+dir.getDirVer()).get(pos.x+dir.getDirHori()).canPush()) { //verifie si il peut push la case suivante
                    if (push(new Position(pos.x+dir.getDirHori(),pos.y+dir.getDirVer()),dir))
                        editPlacement(pos,dir,te);
                }
            
            }
        }
        deleteAllRule();
        fillEmpty();
        for (Position p:is)
            rule(p,TypeElement.IS);
        Rule.setActivity(Property.TP, true);
        player = getPlayerType();
        if (player==null)
            return;
        //desactiver les regle a pas checker 
        List<Property> temps = Rule.desactivatePlayerList(Property.TP,Property.SLIP);
        for (Pair<Position,TypeElement> p:player)
            try {
                if (this.listRule.check(p.getKey(), Directions.NONE, p.getValue()))
                    continue; 
            } catch (WinException ex) {
                return;
            }
        Rule.activatePlayerList(temps);
        this.limitedDeplacement--;
        for (Element e:this.listAllElement)
            System.out.println(e.getTypeElement());
        //System.out.println(this.listGrid.get(0).get(0).getZ().get(0).getTypeRule().get(0));
    }
    
    /**
     * Methode recurcive qui deplace un TypeElement d'un Elements dans le sens
     * de la dir.
     * @param pos Position, de l'element initial
     * @param dir Directions, sens du déplacemnt 
     * @return true ou flase
     */
    boolean push(Position pos,Directions dir) {
        if(pos.y+dir.getDirVer() < y && pos.x+dir.getDirHori() < x)
            if (listGrid.get(pos.y).get(pos.x).canPush()){
                if(push(new Position(pos.x+dir.getDirHori(),pos.y+dir.getDirVer()),dir)){
                    for(Element e:listGrid.get(pos.y).get(pos.x).getElementsOf(Property.PUSH)){
                        if (e.getTypeElement()==TypeElement.IS) {
                            for (int i=0;i<this.is.size();i++)
                                if (pos.equals(this.is.get(i)))
                                    this.is.remove(i);
                            this.is.add(new Position(pos.x+dir.getDirHori(),pos.y+dir.getDirVer()));
                        }
                        if (!this.listRule.checkPush(pos, dir, e.getTypeElement()))
                            return true;
                        else editPlacement(pos,dir,e.getTypeElement());
                    }
                    return true;
                }
            }
            else if (listGrid.get(pos.y).get(pos.x).canAdd())
                return true;
        return false;    
    }

    /**
     * 
     * @param te
     * @param dir 
     */
    private void changeDirections(TypeElement te, TypeElement dir) {
        for(Element e:listAllElement)
            if (e.getTypeElement()==te.getText())
                e.setDirections(dir.getRule().getDirFromProperty(dir.getRule()));
    }

    /**
     * Revois une ListPairPosition,TypeElement trier en fonction de la direction
     * @param dir Direction, trie p en fonction de la direction.
     * @param p ListPairPosition,TypeElement, liste a trier
     * @return 
     */
    private List<Pair<Position,TypeElement>> sort(Directions dir, List<Pair<Position,TypeElement>> p) {
        if (p.isEmpty())
            return null;
        Collections.sort(p, (Pair<Position,TypeElement> o1, Pair<Position,TypeElement> o2) -> {
            if (null!=dir)
                switch (dir) {
                    case RIGHT:
                        return o2.getKey().x - o1.getKey().x;
                    case LEFT:
                        return o1.getKey().x - o2.getKey().x;
                    case UP:
                        return o1.getKey().y - o2.getKey().y;
                    default:
                        break;
                }
            return o2.getKey().y - o1.getKey().y;
        });   
        return p;
    }
}
