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
    
    Board(String fileName){
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
                addPlacement(nextLine.toUpperCase().split(" "));
            }

            buffer.close(); 
        } catch (IOException | ElementsNotFoundException ex) {
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void generateGrid(int x,int y){        
            for(int j=0;j<y+2;j++){
                listGrid.add(new ArrayList<>());
                for(int i=0;i<x+2;i++)
                    if(i==0 || j==0 || j==y+1 || i==x+1)
                        listGrid.get(j).add(new Placement(new Unplayable()));
                    else
                        listGrid.get(j).add(new Placement(new Element(TypeElements.EMPTY)));
            }
                    
    }
    
    //getters
       
    /**
     * return x la taille du tableau board
     * @return x
     */
    public int getSizeX() {
        return this.x;
    }
    
    /**
     * return y la taille du tableau board
     * @return y
     */
    public int getSizeY() {
        return this.y;
    }
    
    /**
     * return List<List<Placement>> la liste board
     * @return List<List<Placement>>
     */
    public List<List<Placement>> getListGrid() {
        return this.listGrid;
    }
    
    /**
     * 
     * @param name
     * @param x la cordonner x dans la listGrid.
     * @param y la cordonner y dans la listGrid.
     * @param movingDirection chiffre qui a la direction de l'elements.
     * @param board ou les modifications doivent etre faites.
     */
    private void addPlacement(String[] line) throws ElementsNotFoundException {
        int x = Integer.parseInt(line[1])+1;
        int y = Integer.parseInt(line[2])+1;
        int d = line.length > 3  ? Integer.parseInt(line[3]) : 0;
        listGrid.get(y).get(x).addElement(new Element(TypeElements.fromString(line[0]),Directions.fromString(d)));
    }
    
    String getAff(){
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
