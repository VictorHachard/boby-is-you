package common.model;

import common.exeptions.TypeElementNotFoundException;
import common.exeptions.WinException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * //TODO ergerister le dernier deplacment pour la save direction
 * @author Glaskani
 */
public class Board {
    private int i=0;
    private List<Element> listAllElement = new ArrayList<>();
    private GameMode listLose;
    private List<Position> is;
    private List<Position> make;
    private List<List<Placement>> listGrid = new ArrayList<>();
    private final int x;
    private final int y;
    private final Placement unplayable = new Placement(new Unplayable());
    private final Element empty = new Empty();
    private MusicHashMap music;
    private Rule listRule;
    private static Board INSTANCE = null;
    private Element emptyPlayable=new Element(TypeElement.EMPTY);
    public int limitedDeplacement;
    public String title="";
    
    /**
     * 
     * @param map
     * @return
     * @throws TypeElementNotFoundException
     * @throws IOException 
     */
    public static Board getInstance(Maps map) throws TypeElementNotFoundException, IOException {           
        if (INSTANCE == null)
            INSTANCE = new Board(map);
        return INSTANCE;
    }
    
    /**
     * 
     * @return 
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
     * @throws TypeElementNotFoundException 
     */
    public Board(Maps map) throws TypeElementNotFoundException {
        try {
            music = MusicHashMap.getInstance();
        } catch (IOException ex) {
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                        listGrid.get(i).get(j).getListeContenu().get(k).addRule(Property.PUSH); 
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
        try {
            listLose = new GameModeNumberOfMove(this);
            //listRule.addRule(new Shut(this));
            //make = getAllPos(TypeElement.MAKE);
        } catch (IOException ex) {
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
    private void fillEmpty() {
        //remove tout les empty
        for(int i=1;i<y-1;i++)
            for(int j=1;j<x-1;j++)
                if (this.listGrid.get(i).get(j).getListeContenu().contains(this.emptyPlayable))
                    this.listGrid.get(i).get(j).removeElement(TypeElement.EMPTY);
        for(int i=1;i<y-1;i++)
            for(int j=1;j<x-1;j++)
                if (this.listGrid.get(i).get(j).getListeContenu().size()==1)
                    this.listGrid.get(i).get(j).addElement(this.emptyPlayable);
    }
    
    /**
     * 
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
     * @throws TypeElementNotFoundException 
     */
    private boolean checkRule(int x,int y,Type te, Type te1) throws TypeElementNotFoundException {
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
    
    private void test(int x,int y,Type te, Type te1) throws TypeElementNotFoundException {
            if (listGrid.get(x+1).get(y).find(Type.TEXT) && listGrid.get(x-1).get(y).find(Type.TEXT))
                    addElementOnElement(listGrid.get(x-1).get(y).getType(te).getText(), new Element(listGrid.get(x+1).get(y).getType(te1).getText()));
        }
    
    /**
     * 
     * @param e1
     * @param e2
     * @throws TypeElementNotFoundException 
     */
    private void addElementOnElement(TypeElement e1, Element e2) throws TypeElementNotFoundException {
        //e2 a faire poper sur e1
        for(int i=1;i<y-1;i++)
            for(int j=1;j<x-1;j++)
                for(int k=0;k<listGrid.get(i).get(j).getListeContenu().size();k++)
                    if (listGrid.get(i).get(j).getListeContenu().get(k).getTypeElement()==e1)
                        addPlacement(j,i,e2);
    }
    
    /**
     * 
     * 
     */
    private void rule(Position pos, TypeElement te) throws TypeElementNotFoundException {
        if (checkRule(pos.y,pos.x,Type.TEXT,Type.RULE))
            return;
        checkRule(pos.y,pos.x,Type.TEXT,Type.TEXT);
    }
    
    /**
     * BUGGER
     * @param text
     * @param text2
     * @throws TypeElementNotFoundException 
     */
    private void changeType(TypeElement text,TypeElement text2) throws TypeElementNotFoundException { //e1 a mettre e a enlever
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
                for(int k=0;k<listGrid.get(i).get(j).getListeContenu().size();k++)
                    if (listGrid.get(i).get(j).getListeContenu().get(k).getTypeElement()==bef) {
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
                    listGrid.get(j).add(new Placement (this.empty));
        }
    }
      
    /**
     * 
     * @return 
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
     * Revois la liste contenant Placement.
     * @return ListListPlacement
     */
    public List<List<Placement>> getListGrid() {
        return this.listGrid;
    }
    
    /**
     * Revois la liste contenant Placement.
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
     * @throws TypeElementNotFoundException 
     */
    private void addPlacement(int x, int y, Element object) throws TypeElementNotFoundException {
        //if (!this.listAllElement.isEmpty())
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
    public String Affichage(){
        StringBuilder  sb = new StringBuilder();
        
        for(List<Placement> lp:this.listGrid){
            for(Placement p:lp) {
                sb.append(p.getListeContenu().get(p.getListeContenu().size()-1).getTypeElement().getLetter());
                sb.append("|");
            }
            sb.append('\n');
        }
        return sb.toString();
    }
    
    /**
     * Revois une chaine de charactére du Board en adresse memoire.
     * @return String
     */
    public String AffichageAdresse(){
        StringBuilder  sb = new StringBuilder();
        
        for(List<Placement> lp:this.listGrid){
            for(Placement p:lp) {
                sb.append(p.getListeContenu().get(p.getListeContenu().size()-1));
                sb.append("|");
            }
            sb.append('\n');
        }
        return sb.toString();
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
    private List<AllPlayer> getPlayerType(){        
        List<AllPlayer> tempsList = new ArrayList<>();
        List<Position> temp;
        List<TypeElement> alredycheck = new ArrayList<>();
        for (Element e:this.listAllElement)
            for (Property p:e.getTypeRule())
                if (p==Property.YOU && (!(alredycheck.contains(e.typeElement)))) {
                    alredycheck.add(e.typeElement);
                    temp = getPositionOf(e.getTypeElement());
                    if (!(temp==null))
                        for (Position pos:temp)
                            tempsList.add(new AllPlayer(pos,e.getTypeElement()));
                }
        if (tempsList.isEmpty())
            return null;
        return tempsList;
    }
    
    /**
     * 
     * @param pos
     * @param direction
     * @param element 
     */
    void editPlacement(Position pos, Directions direction, TypeElement element) {
        listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori())
                .addElement(listGrid.get(pos.y).get(pos.x).getElements(element));
        listGrid.get(pos.y).get(pos.x).removeElement(element);
    }
    
    /**
     * 
     * @param direction 
     * @throws common.exeptions.TypeElementNotFoundException 
     * @throws java.io.IOException 
     */
    public void movePlayer(Directions direction) throws TypeElementNotFoundException, IOException{
        //verifier si on a pas fini un gamemode
        if (!this.listLose.check())
            return;
        List<AllPlayer> player = sortPlayer(direction, getPlayerType());   
        if (player==null)
            return;
        i++;
        System.out.println(i);
        Position pos;
        TypeElement te;
        //just executer move
        loop: for(AllPlayer all:player) {
            pos = all.getPos();
            te = all.getTypeElement();
            if(pos.y+direction.getDirVer() < y && pos.x+direction.getDirHori() < x) {
                List<Property> temps1 = null;
                temps1 = Rule.desactivatePlayerList(Property.MOVE);
                try {
                if (!this.listRule.check(pos, direction, te))
                    continue loop;
                } catch (WinException e) {
                    return;
                }
                Rule.activatePlayerList(temps1);
                //Depalcement ADD
                if (listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).canAdd()){ //verifie si il peut add la case suivante
                    editPlacement(pos,direction,te);
                    //this.music.play(Music.ADD);
                } 
                //Depalcement PUSH
                else if (listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).canPush()) { //verifie si il peut push la case suivante
                    if (push(new Position(pos.x+direction.getDirHori(),pos.y+direction.getDirVer()),direction))
                        editPlacement(pos,direction,te);
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
        for (AllPlayer p:player)
            try {
                if (this.listRule.check(p.getPos(), Directions.NONE, p.getTypeElement()))
                    continue; 
            } catch (WinException ex) {
                return;
            }
        Rule.activatePlayerList(temps);
        this.limitedDeplacement--;
    }
    
    /**
     * Methode recurcive qui deplace un TypeElement d'un Elements dans le sens
     * de la direction.
     * @param pos Position, de l'element initial
     * @param direction Directions, sens du déplacemnt 
     * @return true ou flase
     * @throws IOException
     * @throws TypeElementNotFoundException 
     */
    boolean push(Position pos,Directions direction) throws TypeElementNotFoundException, IOException {
        if(pos.y+direction.getDirVer() < y && pos.x+direction.getDirHori() < x){
            if (listGrid.get(pos.y).get(pos.x).canPush()){
                if(push(new Position(pos.x+direction.getDirHori(),pos.y+direction.getDirVer()),direction)){
                    for(Element e:listGrid.get(pos.y).get(pos.x).getElementsOf(Property.PUSH)){
                        if (e.getTypeElement()==TypeElement.IS) {
                            for (int i=0;i<this.is.size();i++)
                                if (pos.equals(this.is.get(i)))
                                    this.is.remove(i);
                            this.is.add(new Position(pos.x+direction.getDirHori(),pos.y+direction.getDirVer()));
                        }
                        if (!this.listRule.checkPush(pos, direction, e.getTypeElement()))
                            return true;
                        else editPlacement(pos,direction,e.getTypeElement());
                    }
                    return true;
                }
            }
            else if (listGrid.get(pos.y).get(pos.x).canAdd())
                return true;
        }
        return false;    
    }
    
    /**
     * Sauvegarde la partie actuel.
     * @throws IOException 
     */
    public void save() throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        save(dateFormat.format(new Date()) +".txt");     
    }
    
    /**
     * 
     * @param fileName
     * @throws IOException 
     */
    public void save(String fileName) throws IOException {   
    try {
        BufferedWriter save = new BufferedWriter(new FileWriter(new File(fileName)));
        //si le fichier n'existe pas, il est crée à la racine du projet.
        //save la taille du Board.
        int x1 = this.x-2;
        int y1 = this.y-2;
        save.write(x1 + " " + y1);
        
        //save chaque element.
        for(int i=1;i<y-1;i++){
            for(int j=1;j<x-1;j++){
                List<Element> te =  listGrid.get(i).get(j).getListeContenu();
                for(int k=1;k<te.size();k++){
                    //ne save pas les EMPTY
                    if (!(te.get(k).getTypeElement()==TypeElement.EMPTY)) {
                        save.newLine();
                        int j1 = j-1;
                        int i1 = i-1;
                        String name = te.get(te.size()-k).getTypeElement().getElements().toLowerCase();
                        int dir = te.get(te.size()-k).getDirections().getDir();
                        if (dir == 0)
                            save.write(name + " " + j1 + " " + i1);
                        else save.write(name + " " + j1 + " " + i1 + " " + dir);
                    }
                }
            }
        }
        save.close();
    } catch (IOException e) {
    }   
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

    private List<AllPlayer> sortPlayer(Directions direction, List<AllPlayer> player) {
        if (player.isEmpty())
            return null;
        Collections.sort(player, (AllPlayer o1, AllPlayer o2) -> {
            if (null!=direction)
                switch (direction) {
                    case RIGHT:
                        return o2.getPos().x - o1.getPos().x;
                    case LEFT:
                        return o1.getPos().x - o2.getPos().x;
                    case UP:
                        return o1.getPos().y - o2.getPos().y;
                    default:
                        break;
                }
            return o2.getPos().y - o1.getPos().y;
        });   
        return player;
    }
}
