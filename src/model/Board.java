package model;

import exeptions.TypeElementNotFoundException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * //TODO ergerister le dernier deplacment pour la save direction
 * @author Glaskani
 */
public class Board {
    
    private List<Element> listAllElement;
    private List<List<Placement>> listGrid;
    private int x;
    private int y;
    private Unplayable unplayable = new Unplayable();
    private Empty empty = new Empty();;
    
    /**
     * 
     * @param map
     * @throws TypeElementNotFoundException 
     */
    public Board(Maps map) throws TypeElementNotFoundException, IOException {
        this.listGrid = new ArrayList<>();
        this.listAllElement = map.getListAllElement();  
        
        this.x = map.getSizeX();
        this.y = map.getSizeY();
        
        generateGrid(x-2,y-2);

        for(int i=1;i<y-1;i++){
            for(int j=1;j<x-1;j++){
                List<Element> te =  map.getListElement(j,i);
                //ne re load pas les EMPTY
                for(int k=0;k<te.size();k++){
                if (!(te.get(k).getTypeElements()==TypeElement.EMPTY))
                    addPlacement(j,i,te.get(te.size()-k));
                /*if (te.get(k).getTypeElements()==TypeElement.WALL) {
                    System.out.println("salut");
                    System.out.println(listGrid.get(i).get(j).getListeContenu().get(1).getTypeElements().getElements());
                    listGrid.get(i).get(j).getListeContenu().get(1).addRule(Property.STOP);}*/
                if (te.get(k).getTypeElements()==TypeElement.ROCK) {
                    
                    System.out.println(listGrid.get(i).get(j).getListeContenu().get(2).getTypeElements().getElements());
                    listGrid.get(i).get(j).getListeContenu().get(2).addRule(Property.PUSH);
                System.out.println(listGrid.get(i).get(j).getListeContenu().get(2).ltr.get(0));}
                }
            }
        }
        
        //Ajout des regle pour la premier fois
        ArrayList<Element> listTempsIs = new ArrayList<>();
        /*
        for(int i=1;i<y-1;i++){
            for(int j=1;j<x-1;j++){
                List<Element> te =  listGrid.get(j).get(i).getListeContenu();
                for(Element e:te)
                    if(e.getTypeElements()==TypeElement.IS) 
                        for(Element o:this.listAllElement)
 
            }
        }*/

    }
    
    /**
     * Crée le Board de taille x,y et ajoute les elements injouable et EMPTY.
     * @param x int
     * @param y int
     */
    private void generateGrid(int x,int y){        
        for(int j=0;j<y+2;j++){
            listGrid.add(new ArrayList<>());
            for(int i=0;i<x+2;i++)
                if(i==0 || j==0 || j==y+1 || i==x+1)
                    listGrid.get(j).add(new Placement(this.unplayable));
                else
                    listGrid.get(j).add(new Placement(this.empty));
        }           
    }
    
    //getters
       
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
        listGrid.get(y).get(x).addElement(object);
    }
    
    /**
     * Revois une chaine de charactére du Board.
     * @return String
     */
    public String getAffichage(){
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
    public String getAffichageAdresse(){
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
    private List<Position> getPositionOf(TypeElement te){
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
    private TypeElement getPlayerType(){
        return TypeElement.PLAYER1;
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
    public void movePlayer(Directions direction){
        TypeElement player = getPlayerType();
        List<Position> lp = getPositionOf(player);
        
        for(Position pos:lp)
            if(pos.y+direction.getDirVer() < y && pos.x+direction.getDirHori() < x)
                if (listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).canAdd()){ //verifie si il peut add la case suivante
                    editPlacement(pos,direction,player);
                } else if (listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).canPush()) { //verifie si il peut push la case suivante
                    System.out.println("^push");
                    if (push(new Position(pos.x+direction.getDirHori(),pos.y+direction.getDirVer()),direction))
                        editPlacement(pos,direction,player);
                }
    }
    
    /**
     * Methode recurcive qui deplace un TypeElement d'un Elements dans le sens
     * de la direction.
     * @param pos Position, de l'element initial
     * @param direction Directions, sens du déplacemnt 
     * @return true ou flase
     */
    private boolean push(Position pos,Directions direction) {
        if(pos.y+direction.getDirVer() < y && pos.x+direction.getDirHori() < x){
            System.out.println("fjksdskufksldgfkdshgkjdshgh");
            if (listGrid.get(pos.y).get(pos.x).canPush()){
                
                if(push(new Position(pos.x+direction.getDirHori(),pos.y+direction.getDirVer()),direction)){
                    for(Element e:listGrid.get(pos.y).get(pos.x).getElementsOf(Property.PUSH)){
                        editPlacement(pos,direction,e.getTypeElements());
                    }
                    return true;
                }
            } else if (listGrid.get(pos.y).get(pos.x).canAdd()) {
                System.out.println("fjksgh");
                return true;
            }
        }
        System.out.println("fjksgh111");
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
    
}
