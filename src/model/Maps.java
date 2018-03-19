package model;

import exeptions.ElementsNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static jdk.nashorn.internal.objects.NativeString.toUpperCase;

/**
 *
 * @author Glaskani
 */
public class Maps {

    private int x;
    private int y;
    
    private Map<Position, List<Element>> Element = new HashMap<>();
    
    /**
     * 
     * @param fileName
     * @throws ElementsNotFoundException 
     */
    public Maps(String fileName) throws ElementsNotFoundException {
        
        try (BufferedReader buffer = new BufferedReader(new FileReader(fileName))) {
            String nextLine;
            //lecture de la premier ligne pour determiner et crée le board.
            String line = buffer.readLine();
            String[] size = line.split(" ");
            this.x = Integer.parseInt(size[0])+2;
            this.y = Integer.parseInt(size[1])+2;
            generateMap(Integer.parseInt(size[0]),Integer.parseInt(size[1]));
            
            //lecture de toutes les autres lignes pour ajouter les elments dans le board.
            while ((nextLine = buffer.readLine()) != null) {
                String[] parts = nextLine.split(" ");
                int movingDirection = parts.length > 3  ? Integer.parseInt(parts[3]) : 0;
                addMap(Integer.parseInt(parts[1])+1, Integer.parseInt(parts[2])+1, Directions.fromString(movingDirection), TypeElements.fromString(toUpperCase(parts[0])));
            }

            buffer.close(); 
        } catch (IOException ex) {
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    /**
     * 
     * @param x int, taille total de la map (aves les mur injouables).
     * @param y int, taille total de la map (aves les mur injouables).
     */
    public Maps(int x, int y) {
        generateMap(x-2, y-2);
    }

    /**
     * 
     * @param x
     * @param y 
     */
    private void generateMap(int x,int y) {        
        for(int j=0;j<y+2;j++){
            for(int i=0;i<x+2;i++)
                if(i==0 || j==0 || j==y+1 || i==x+1)
                    putObjects (Element, new Position(j,i), new Element(TypeElements.WALLINJOUABLE,Directions.RIGHT));
                else
                    putObjects (Element, new Position(j,i), new Element(TypeElements.EMPTY,Directions.RIGHT));
        }           
    }
    
    /**
     * 
     * @param x
     * @param y
     * @param directions
     * @param object
     * @throws ElementsNotFoundException 
     */
    public void addMap(int x, int y, Directions directions, TypeElements object) throws ElementsNotFoundException {       
        putObjects (Element, new Position(y,x), new Element(object,directions));
    }
    
    /**
     * 
     * @param x
     * @param y
     * @param directions
     * @param object
     * @throws ElementsNotFoundException 
     */
    public void removeMap(int x, int y, Element elem) throws ElementsNotFoundException {
        removeObjects (Element, new Position(y,x), elem);
    }
    
    /**
     * 
     * @param typeElem
     * @param key
     * @param te 
     */
    private void putObjects(Map<Position, List<Element>> typeElem, Position key, Element te) {
        List<Element> temps = typeElem.get(key);
        if(temps == null) {
            temps = new ArrayList<>();
            typeElem.put(key, temps);
        }
        else if (temps.contains(te))
            return;
        temps.add(te);
        
        Collections.sort(temps, new Comparator<Element>() {
            @Override
            public int compare(Element e1, Element e2) {
                return e1.getTypeElements().getPriority() - e2.getTypeElements().getPriority();
            }
        });
    }
    
    /**
     * 
     * @param typeElem
     * @param key
     * @param te 
     */
    private void removeObjects(Map<Position, List<Element>> typeElem, Position key, Element te) {
        List<Element> temps = typeElem.get(key);
        if(temps == null) {
            throw new IllegalStateException();
        }
        temps.remove(te);
    }
       
    //Getters

    /**
     * Pour le public
     * @param x
     * @param y
     * @return 
     */
    public List<Element> getListElement(int x, int y) { 
        return Element.get(new Position(y,x));
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
     * Revois une chaine de charactére du Board.
     * @return String
     */
    public String getAffichage(){
        StringBuilder  sb = new StringBuilder();
        
        for(int i=0;i<y;i++){
            for(int j=0;j<x;j++){
                List<Element> te =  this.Element.get(new Position(i,j));
                sb.append(te.get(te.size()-1).getTypeElements().getLetter()).append("|");
            }
            sb.append('\n');
        }
        
        return sb.toString();
    }
}
