package model;

import exeptions.ElementsNotFoundException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static jdk.nashorn.internal.objects.NativeString.toUpperCase;

/**
 *
 * @author Glaskani
 */
public class Board {
    private List<String> listMove;
    private List<List<Placement>> listGrid;
    private int x;
    private int y;
    
    /**
     * Créé un Board en fonction du fileName.
     * @param fileName String
     */
    public Board(String fileName) {
        listMove = new ArrayList<>();
        listGrid = new ArrayList<>();
        
        try (BufferedReader buffer = new BufferedReader(new FileReader(fileName))) {
            String nextLine;
            //lecture de la premier ligne pour determiner et crée le board.
            String line = buffer.readLine();
            String[] size = line.split(" ");
            this.x = Integer.parseInt(size[0])+2;
            this.y = Integer.parseInt(size[1])+2;
            generateGrid(Integer.parseInt(size[0]),Integer.parseInt(size[1]));
            
            //lecture de toutes les autres lignes pour ajouter les elments dans le board.
            while ((nextLine = buffer.readLine()) != null) {
                String[] parts = nextLine.split(" ");
                int movingDirection = parts.length > 3  ? Integer.parseInt(parts[3]) : 0;
                addPlacement(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), movingDirection, toUpperCase(parts[0]));
            }

            buffer.close(); 
        } catch (IOException | ElementsNotFoundException ex) {
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Crée un Board de la taille x, y.
     * @param x int
     * @param y int
     */
    public Board(int x, int y) {
        listMove = new ArrayList<>();
        listGrid = new ArrayList<>();

        generateGrid(x, y);
    } 
    
    /**
     * Crée un Board de taille x,y et ajoute les elements injouable et EMPTY.
     * @param x int
     * @param y int
     */
    private void generateGrid(int x,int y){        
            for(int j=0;j<y+2;j++){
                listGrid.add(new ArrayList<>());
                for(int i=0;i<x+2;i++)
                    if(i==0 || j==0 || j==y+1 || i==x+1)
                        listGrid.get(j).add(new Placement(new Unplayable()));
                    else
                        listGrid.get(j).add(new Placement(new Empty()));
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
     * 
     * @param x
     * @param y
     * @param d
     * @param object
     * @throws ElementsNotFoundException 
     */
    private void addPlacement(int x, int y, int d, String object) throws ElementsNotFoundException {
        listGrid.get(y+1).get(x+1).addElement(new Element(TypeElements.fromString(object),Directions.fromString(d)));
    }
    
    /**
     * Revois une chaine de charactére du Board.
     * @return String
     */
    public String getAffichage(){
        StringBuffer  sb = new StringBuffer();
        
        for(List<Placement> lp:this.listGrid){
            for(Placement p:lp) {
                sb.append(p.getAllElement().get(p.getAllElement().size()-1).getTypeElements().getLetter());
                sb.append("|");
            }
            sb.append('\n');
        }
        
        return sb.toString();
    }
    
    private List<Position> getPositionOf(TypeElements te){
        List<Position> lp = new ArrayList<>();
        
        for(int i=0;i<this.y;i++)
            for(int j=0;j<this.x;j++)
                if(this.listGrid.get(i).get(j).findElements(te))
                    lp.add(new Position(j,i));
        
        return lp;
    }
    
    private TypeElements getPlayerType(){
        return TypeElements.PLAYER1;
    }
    
    public void movePlayer(Directions direction){
        TypeElements player = getPlayerType();
        List<Position> lp = getPositionOf(player);
        
        for(Position pos:lp)
            if(pos.y+direction.getDirVer() < y && pos.x+direction.getDirHori() < x)
                if (listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).canAdd()){
                    listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).addElement(listGrid.get(pos.y).get(pos.x).get(player));
                    listGrid.get(pos.y).get(pos.x).removeElement(player);
                } else if (listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).canPush()) {
                    push(new Position(pos.x+direction.getDirHori(),pos.y+direction.getDirVer()),direction);
                    listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).addElement(listGrid.get(pos.y).get(pos.x).get(player));
                    listGrid.get(pos.y).get(pos.x).removeElement(player);
                }
    }
      
    private boolean push(Position pos,Directions direction) {
        if(pos.y+direction.getDirVer() < y && pos.x+direction.getDirHori() < x){
            if (!listGrid.get(pos.y).get(pos.x).canPush()){
                if(push(new Position(pos.x+direction.getDirHori(),pos.y+direction.getDirVer()),direction)){
                    for(Element e:listGrid.get(pos.y).get(pos.x).getElementsOf(Property.PUSH)){
                        listGrid.get(pos.y+direction.getDirVer()).get(pos.x+direction.getDirHori()).addElement(listGrid.get(pos.y).get(pos.x).get(e.getTypeElements()));
                        listGrid.get(pos.y).get(pos.x).removeElement(e.getTypeElements());
                    }
                    return true;
                }
            }else if (listGrid.get(pos.y).get(pos.x).canAdd()) {
                return true;
            }
        }
        
        return false;    
    }
    
    /**
     * 
     * @param fileName
     * @param board
     * @throws IOException 
     *
    public void save(String fileName) throws IOException {
        
    try {
        BufferedWriter save = new BufferedWriter(new FileWriter(new File(fileName)));
        //si le fichier n'existe pas, il est crée à la racine du projet.
        //save la taille du Board.
        save.write(x + " " + y);
        
        //save chaque element.
        for (int i=0;i==x;i++) {
            for (int j=0;j==y;j++) {
               // board.read(i,j);  
                int e = listGrid.get(i).get(j).getListeContenu().size();
                for (int p=0;p==e;p++) {
                    TypeElements name = listGrid.get(i).get(j).getListeContenu().get(e).getTypeElements();
                    //movingDirection =
                    //save.write(name + " " + i + " " + j + " " + movingDirection);
                }           
        }}
            
        save.close();
    }
    catch (IOException e) {
        e.printStackTrace();
    }   
    }*/
    
}
