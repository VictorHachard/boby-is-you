package model;

import exeptions.TypeElementNotFoundException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * //TODO ergerister le dernier deplacment pour la save direction
 * @author Glaskani
 */
public class Board extends Subject {
    
    private List<Element> listAllElement;
    private List<Position> is;
    private List<Position> make;
    private List<List<Placement>> listGrid;
    private final int x;
    private final int y;
    private final Placement unplayable = new Placement(new Unplayable());
    private final Element empty = new Empty();
    private MusicHashMap music;
    private List<ElementRule> listRule;
    private HashMap<Property, Rule> rule;
    private static Board INSTANCE = null;
    
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
    
    private Tp tp;
    private Slip ice;
    private Kill kill;
    private Sink sink;
    private Move move;
    private MeltHot melt;
    private Win win;
    
    /**
     * 
     * @param map
     * @throws TypeElementNotFoundException 
     */
    private Board(Maps map) throws TypeElementNotFoundException, IOException {
        this.listGrid = new ArrayList<>();
        this.listRule = new ArrayList<>();
        this.listAllElement = map.getListAllElement();  
        music = MusicHashMap.getInstance();
        
        this.x = map.getSizeX();
        this.y = map.getSizeY();
        
        generateGrid(x-2,y-2);

        for(int i=1;i<y-1;i++){
            for(int j=1;j<x-1;j++){
                List<Element> te =  map.getListElement(j,i);
                for(int k=0;k<te.size();k++){
                    //ne re load pas les EMPTY
                    if (!(te.get(k).getTypeElements()==TypeElement.EMPTY)) {
                        addPlacement(j,i,te.get(te.size()-k));    
                    //Ajoute les pushs sur les texte et les texte regles.
                    if (te.get(k).getTypeTypeElements()==TypeTypeElement.CONNECTER ||
                            te.get(k).getTypeTypeElements()==TypeTypeElement.TEXT ||
                            te.get(k).getTypeTypeElements()==TypeTypeElement.RULE) {
                        listGrid.get(i).get(j).getListeContenu().get(k).addRule(Property.PUSH); 
                        /*if (te.get(k).getTypeTypeElements()==TypeTypeElement.RULE)
                            this.rule.put(te.get(k).getTypeElements().getRule(), );*/
                    }
                    }
                }
            }
        }       
              
        is = getAllPos(TypeElement.IS);
        make = getAllPos(TypeElement.MAKE);
        for (Position p:is)
            rule(p,TypeElement.IS);
        tp = new Tp(this);
        ice = new Slip(this);
        kill = new Kill(this);
        sink = new Sink(this);
        move = new Move(this);
        melt = new MeltHot(this);
        win = new Win(this);
    }   
    
    /**
     * 
     */
    private void deleteAllRule() {
        listRule.clear();
        for (Element e:this.listAllElement)
            if (!(e.getTypeTypeElements()==TypeTypeElement.CONNECTER||e.getTypeTypeElements()==TypeTypeElement.RULE||e.getTypeTypeElements()==TypeTypeElement.TEXT))
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
    private void checkAnd(int x,int y,TypeTypeElement te, TypeTypeElement te1) {
        int i=0;
        if (listGrid.get(x).get(y-2).findElements(TypeElement.AND))
            if (listGrid.get(x).get(y-3).findTypeType(te)) {
                addRule(listGrid.get(x).get(y-3).findTypeElement(te),
                listGrid.get(x).get(y+1).findTypeElement(te1));
        i++;
            }
        if (listGrid.get(x).get(y+2).findElements(TypeElement.AND))
            if (listGrid.get(x).get(y+3).findTypeType(te1)) {
                addRule(listGrid.get(x).get(y-1).findTypeElement(te),
                listGrid.get(x).get(y+3).findTypeElement(te1));
                i++;
            }
        if (i==2)
            addRule(listGrid.get(x).get(y-3).findTypeElement(te),
            listGrid.get(x).get(y+3).findTypeElement(te1));
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
    private boolean checkRule(int x,int y,TypeTypeElement te, TypeTypeElement te1) throws TypeElementNotFoundException {
        boolean check = false;
        if (listGrid.get(x).get(y-1).findTypeType(te)
                && listGrid.get(x).get(y+1).findTypeType(te1)) {
            if (listGrid.get(x).get(y+1).findTypeType(TypeTypeElement.TEXT))
                changeType(listGrid.get(x).get(y-1).findTypeElement(te),
                    listGrid.get(x).get(y+1).findTypeElement(te1));
            else {
                if (listGrid.get(x).get(y+1).findElements(TypeElement.UP) || 
                        listGrid.get(x).get(y+1).findElements(TypeElement.LEFT) ||
                        listGrid.get(x).get(y+1).findElements(TypeElement.DOWN) ||
                        listGrid.get(x).get(y+1).findElements(TypeElement.RIGHT)) {
                    changeDirections(listGrid.get(x).get(y-1).findTypeElement(te),
                    listGrid.get(x).get(y+1).findTypeElement(te1));
                    System.out.println("coucou");
                }
                else addRule(listGrid.get(x).get(y-1).findTypeElement(te),
                    listGrid.get(x).get(y+1).findTypeElement(te1));
            }
            //checkAnd(x,y,te,te1);
            check = true;
        }
        if (listGrid.get(x-1).get(y).findTypeType(te)
                && listGrid.get(x+1).get(y).findTypeType(te1)) {
            if (listGrid.get(x+1).get(y).findTypeType(TypeTypeElement.TEXT))
                changeType(listGrid.get(x-1).get(y).findTypeElement(te),
                    listGrid.get(x+1).get(y).findTypeElement(te1));
            else { 
                if (listGrid.get(x+1).get(y).findElements(TypeElement.UP) || 
                        listGrid.get(x+1).get(y).findElements(TypeElement.LEFT) ||
                        listGrid.get(x+1).get(y).findElements(TypeElement.DOWN) ||
                        listGrid.get(x+1).get(y).findElements(TypeElement.RIGHT))
                    changeDirections(listGrid.get(x-1).get(y).findTypeElement(te),
                    listGrid.get(x+1).get(y).findTypeElement(te1));
                else addRule(listGrid.get(x-1).get(y).findTypeElement(te),
                    listGrid.get(x+1).get(y).findTypeElement(te1));
            }
            check = true;
        }
        return check;
    }
    
    private void test(int x,int y,TypeTypeElement te, TypeTypeElement te1) throws TypeElementNotFoundException {
        System.out.println(listGrid.get(x+1).get(y).findTypeType(TypeTypeElement.TEXT));
        System.out.println(listGrid.get(x-1).get(y).findTypeType(TypeTypeElement.TEXT));
            if (listGrid.get(x+1).get(y).findTypeType(TypeTypeElement.TEXT) && listGrid.get(x-1).get(y).findTypeType(TypeTypeElement.TEXT))
                    addElementOnElement(listGrid.get(x-1).get(y).findTypeElement(te).getText(), new Element(listGrid.get(x+1).get(y).findTypeElement(te1).getText()));
        }
    
    private void addElementOnElement(TypeElement e1, Element e2) throws TypeElementNotFoundException {
        //e2 a faire poper sur e1
        for(int i=1;i<y-1;i++)
            for(int j=1;j<x-1;j++)
                for(int k=0;k<listGrid.get(i).get(j).getListeContenu().size();k++)
                    if (listGrid.get(i).get(j).getListeContenu().get(k).getTypeElements()==e1) {
                        System.out.println("enter add "+e2.toString()+ " "+e1.name());
                        addPlacement(j,i,e2);
                    }
    }
    
    /**
     * 
     * 
     */
    private void rule(Position pos, TypeElement te) throws TypeElementNotFoundException {
        if (checkRule(pos.y,pos.x,TypeTypeElement.TEXT,TypeTypeElement.RULE))
            return;
        checkRule(pos.y,pos.x,TypeTypeElement.TEXT,TypeTypeElement.TEXT);
    }
    
    /**
     * BUGGER
     * @param text
     * @param text2
     * @throws TypeElementNotFoundException 
     */
    private void changeType(TypeElement text,TypeElement text2) throws TypeElementNotFoundException { //e1 a mettre e a enlever
        TypeElement bef=text.getText();
        Element aft=null;
        List<Element> listAllElementDelete = new ArrayList<>();
        //trouve text
        for(Element e:this.listAllElement)
            if (e.getTypeElements()==text.getText())
                listAllElementDelete.add(e);
                 
        //trouve text2
        for(Element e:this.listAllElement)
            if (e.getTypeElements()==text2.getText())
                 aft = e;
        if (aft==null)
                aft = new Element(text2.getText());
        for(int i=1;i<y-1;i++)
            for(int j=1;j<x-1;j++)
                for(int k=0;k<listGrid.get(i).get(j).getListeContenu().size();k++)
                    if (listGrid.get(i).get(j).getListeContenu().get(k).getTypeElements()==bef) {
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
        listRule.add(new ElementRule(text.getText(), rule.getRule()));
        for(Element e:listAllElement)
            if (e.getTypeElements()==text.getText())
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
       
    List<ElementRule> getElementRule() {
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
        for(Element e:this.listAllElement)
                if(e.equals(object)) {
                    listGrid.get(y).get(x).addElement(object);
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
                sb.append(p.getListeContenu().get(p.getListeContenu().size()-1).getTypeElements().getLetter());
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
                if(this.listGrid.get(i).get(j).findElements(te))
                    lp.add(new Position(j,i));
        return lp;
    }
    
    /**
     * 
     * @return 
     */
    private List<AllPlayer> getPlayerType(){        
        List<AllPlayer> tempsList = new ArrayList<>();
        for (ElementRule er:this.listRule)
            if (er.getProperty()==Property.YOU)
                for (Position p:getPositionOf(er.getTypeElement()))
                    tempsList.add(new AllPlayer(p,er.getTypeElement()));
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
     */
    public void movePlayer(Directions direction) throws TypeElementNotFoundException, IOException{
        List<AllPlayer> player = getPlayerType();   
        if (player==null)
            return;
        //trie pour ne pas addi les player
        Collections.sort(player, new Comparator<AllPlayer>() {
            @Override
            public int compare(AllPlayer o1, AllPlayer o2) {
                if (direction==Directions.RIGHT)
                    return o2.pos.x - o1.pos.x;
                else if (direction==Directions.LEFT)
                    return o1.pos.x - o2.pos.x;
                else if (direction==Directions.UP)
                    return o1.pos.y - o2.pos.y;
                return o2.pos.y - o1.pos.y;
            }
        });     
        Position pos;
        TypeElement te;
        for(AllPlayer all:player) {
            pos = all.pos;
            te = all.te;
            if(pos.y+direction.getDirVer() < y && pos.x+direction.getDirHori() < x) {
                if (this.win.check(pos, direction, te))
                    return;
                if (this.tp.check(pos, direction, te))
                    continue;
                if (this.melt.check(pos, direction, te))
                    continue;
                if (this.sink.check(pos, direction, te))
                    continue;
                if (this.move.check(pos, direction, te))
                    continue;
                if (this.kill.check(pos, direction, te))
                    continue;
                /*if (this.ice.check(pos, direction, te))
                    continue;*/
                //Depalcement ADD
                if (listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).canAdd()){ //verifie si il peut add la case suivante
                    //Depalcement ADD
                    editPlacement(pos,direction,te);
                    this.music.play(Music.ADD);
                } 
                //Depalcement PUSH
                else if (listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).canPush()) { //verifie si il peut push la case suivante
                    if (push(new Position(pos.x+direction.getDirHori(),pos.y+direction.getDirVer()),direction))
                        editPlacement(pos,direction,te);
                }
            }
        }
        test(make.get(0).x,make.get(0).y,TypeTypeElement.TEXT,TypeTypeElement.TEXT);
        deleteAllRule();
        for (Position p:is)
            rule(p,TypeElement.IS);
        player = getPlayerType();
        
        if (player==null)
            return;
        checkKill(player);
    }
    
    /**
     * Methode recurcive qui deplace un TypeElement d'un Elements dans le sens
     * de la direction.
     * @param pos Position, de l'element initial
     * @param direction Directions, sens du déplacemnt 
     * @return true ou flase
     */
    boolean push(Position pos,Directions direction) throws TypeElementNotFoundException {
        if(pos.y+direction.getDirVer() < y && pos.x+direction.getDirHori() < x){
            if (listGrid.get(pos.y).get(pos.x).canPush()){
                if(push(new Position(pos.x+direction.getDirHori(),pos.y+direction.getDirVer()),direction)){
                    for(Element e:listGrid.get(pos.y).get(pos.x).getElementsOf(Property.PUSH)){
                        editPlacement(pos,direction,e.getTypeElements());
                        if (e.getTypeElements()==TypeElement.IS) {
                            for (int i=0;i<this.is.size();i++)
                                if (pos.equals(this.is.get(i)))
                                    this.is.remove(i);
                                    this.is.add(new Position(pos.x+direction.getDirHori(),pos.y+direction.getDirVer()));

                        }
                            if (this.sink.checkPush(pos, direction, TypeElement.ANNI)) { //ajouter sink a push
                                listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).removeElement(e.getTypeElements());
                                return true;
                            }
                    }
                    return true;
                }
            } else if (listGrid.get(pos.y).get(pos.x).canAdd()) {
                return true;
            }
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
                for(int k=0;k<te.size();k++){
                    //ne save pas les EMPTY
                    if (!(te.get(k).getTypeElements()==TypeElement.EMPTY)) {
                        save.newLine();
                        int j1 = j-1;
                        int i1 = i-1;
                        String name = te.get(te.size()-k).getTypeElements().getElements().toLowerCase();
                        int dir = te.get(te.size()-k).getDirections().getDir();
                        if (dir == 0)
                            save.write(name + " " + j1 + " " + i1);
                        else save.write(name + " " + j1 + " " + i1 + " " + dir);
                    }
                }
            }
        }
            
        save.close();
    }
    catch (IOException e) {
    }   
    }

    /**
     * 
     * @param player
     * @throws TypeElementNotFoundException
     * @throws IOException 
     */
    private void checkKill(List<AllPlayer> player) throws TypeElementNotFoundException, IOException {
        loop: for (AllPlayer p:player) {
            if (this.win.check(p.pos, Directions.NONE, p.te))
                continue loop;
            else if (this.melt.check(p.pos, Directions.NONE, p.te))
                continue loop;
            else if (this.sink.check(p.pos, Directions.NONE, p.te))
                continue loop;
            else this.kill.check(p.pos, Directions.NONE, p.te);
        }
            
    }

    /**
     * 
     * @param te
     * @param dir 
     */
    private void changeDirections(TypeElement te, TypeElement dir) {
        for(Element e:listAllElement)
            if (e.getTypeElements()==te.getText())
                e.setDirections(dir.getRule().getDirFromProperty(dir.getRule()));
    }
}
