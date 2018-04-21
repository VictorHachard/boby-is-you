package common.model;

import common.exeptions.TypeElementNotFoundException;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author Glaskani
 */
public class Maps {

    private int x;
    private int y;
    private Map<Position, List<Element>> Element = new HashMap<>();
    private ArrayList<Element> listAllElement = new ArrayList<>();
    private Element unplayable = new Element(TypeElement.WALLINJOUABLE,Directions.RIGHT);
    private Element empty = new Element(TypeElement.EMPTY,Directions.RIGHT);
    private static final Logger LOGGER = Logger.getGlobal();
    int limitedDeplacement;
    String title = "";
    
    /**
     * Réalise une copie d'une Maps donnée.
     * @param map 
     */
    public Maps(Maps map){
        this.Element = map.Element;
        this.empty = map.empty;
        this.listAllElement = map.listAllElement;
        this.x = map.x;
        this.y =map.y;
        this.unplayable=map.unplayable;
        this.limitedDeplacement=map.limitedDeplacement;
        this.title=map.title;
    }
    
    /**
     * Recupere un BufferedReader et crée une Maps.
     * @param buffer BufferedReader
     * @throws java.io.IOException 
     */
    public Maps(BufferedReader buffer) throws IOException {
            String nextLine;
            String line = buffer.readLine();
            String[] size = line.split(" ");
            this.x = Integer.parseInt(size[0])+2;
            this.y = Integer.parseInt(size[1])+2;
            generateMap(Integer.parseInt(size[0]),Integer.parseInt(size[1]));
            while ((nextLine = buffer.readLine()) != null) {
                if (nextLine.length()==0)
                    continue;
                String[] parts = nextLine.split(" ");
                if (parts.length<=1)
                    continue;
                if (parts[0].equals("config")) {
                    this.limitedDeplacement = Integer.parseInt(parts[1]);  
                    //this.limitedDeplacement = Integer.parseInt(parts[2]);  
                }
                else if (parts[0].equals("title")) {
                    this.title=parts[1].replaceAll("_", " ");
                }
                else if (parts.length<=2 || parts.length>=5)
                    continue;
                else {
                    int dir=parts.length>3 ? Integer.parseInt(parts[3]) : 0;
                    int tmpx=Integer.parseInt(parts[1])+1;
                    int tmpy=Integer.parseInt(parts[2])+1;
                    TypeElement te =TypeElement.fromString(
                                    parts[0]=parts[0].toUpperCase());
                    if (checkIfPosIsInMap(tmpx,tmpy,te))
                        addMap(tmpx,tmpy,
                            new Element(TypeElement.fromString(
                                    parts[0]=parts[0].toUpperCase()),
                                    Directions.fromString(dir)));
                } 
            }
            buffer.close(); 
    }
    
    /**
     * Crée une Maps de taille total x, y,
     * et génere de base les MURINJOUABLE et les EMPTY. 
     * @param x int, taille total de la map (aves les mur injouables).
     * @param y int, taille total de la map (aves les mur injouables).
     */
    public Maps(int x, int y) {
        this.x = x+2;
        this.y = y+2;
        generateMap(x, y);
    }

    /**
     * Genére le dictionaire en fonction de x, y,
     * et place de base les MURINJOUABLE et les EMPTY. 
     * @param x
     * @param y 
     */
    private void generateMap(int x,int y) {        
        for(int j=0;j<y+2;j++){
            for(int i=0;i<x+2;i++)
                if(i==0 || j==0 || j==y+1 || i==x+1)
                    putObjects (Element, new Position(j,i), this.unplayable);
        else
            putObjects (Element, new Position(j,i), this.empty);
        }           
    }
    
    /**
     * Ajoute un object à la map,
     * prend en compte la prioriter de l'object avec les autres dans la liste.
     * @param x int, la case en abssice ou on ajoute l'object.
     * @param y int, la case en ordonnée ou on ajoute l'object.
     * @param e Element, object à ajouter.
     */
    public void addMap(int x, int y, Element e) {   
        if (checkIfPosIsInMap(x,y,e.getTypeElement())) {
            if (!(e.getType()==Type.PLAYER || e.getTypeElement()==TypeElement.ROCK))
                for(Element el:this.listAllElement) {
                    if(el.equals(e)) {
                        putObjects (Element, new Position(y,x), el);
                        return;
                    }
                }
            listAllElement.add(e);
            putObjects (Element, new Position(y,x), e);
        }
    }
    
    /**
     * Revois la liste contenant Placement.
     * @return ListListPlacement
     */
    public List<Element> getListAllElement() {
        return new ArrayList(deepCopy(this.listAllElement));
    }
    
    /**
     * 
     * Supprimer un elem à la map,
     * @param x int, la case en abssice ou on supprime l'object.
     * @param y int, la case en ordonnée ou on supprime l'object.
     * @param e Element, elme à supprimer.
     * @throws TypeElementNotFoundException 
     */
    public void removeMap(int x, int y, Element e) throws TypeElementNotFoundException {
        if (checkIfPosIsInMap(x,y,e.getTypeElement()))
            removeObjects (Element, new Position(y,x), e);
    }
    
    /**
     * Ajoute un te à la MultiMaps.
     * @param te MapPosition, ListElement, la map pour ajouter te.
     * @param p Position, la position ou on ajoute te. 
     * @param e Elemnt, l'element que l'on veut ajouter.
     */
    private void putObjects(Map<Position,List<Element>> te, Position p, Element e) {
        List<Element> temps = te.get(p);
        if(temps == null) {
            temps = new ArrayList<>();
            te.put(p, temps);
        }
        else if (temps.contains(e))
            return;
        temps.add(e);
        Collections.sort(temps, (Element e1, Element e2) -> e1.getTypeElement().getPriority() - e2.getTypeElement().getPriority());
    }
    
    /**
     * Supprime un te à la MultiMaps.
     * @param typeElem MapPosition, ListElement, la map pour supprime te.
     * @param key Position, la position ou on supprime te. 
     * @param te Elemnt, l'element que l'on veut supprime.
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
     * Revois une liste d'element en position demander.
     * @param x int, la case en abssice ou se trouve la liste, en commensant par 0.
     * @param y int, la case en ordonnée ou se trouve la liste, en commensant par 0.
     * @return ListElement, liste de tout les element de cette position.
     */
    public List<Element> getListElement(int x, int y) { 
        if (checkIfPosIsInMap(x,y,TypeElement.NONEINRANGE))
            return deepCopy(Element.get(new Position(y,x)));
        return null;
    }
    
    /**
     * Realise une deepCopy d'une liste d'Element donnée.
     * @param a ListElement à copier
     * @return une nouvelle ListElement copier en profondeur.
     */
    private List<Element> deepCopy(List<Element> a){
        return a.stream().map(val -> new Element(val)).collect(toList());
    }
    
    /**
     * Verifie si les x et y sont bien dans la zone éditable,
     * si ce n'est pas le cas lance une ArithmeticException.
     * @param x int, position
     * @param y int, position
     */
    private boolean checkIfPosIsInMap(int x, int y,TypeElement te) {
        if ((x < 0 || x > this.x-1) || (y < 0 || y > this.y-1)) {
            if ((x < 0 || x > this.x-1) && (y < 0 || y > this.y-1)) {
                try  {
                    throw new IndexOutOfBoundsException();
                } catch (IndexOutOfBoundsException e) {
                    LOGGER.log(Level.SEVERE, "TypeElement "+te+" is out of the Maps, "+x+", "+y+"\nSOLVE: delete "+te);
                    return false;
                }
            }
            else if (y < 0 || y > this.y-1) {
                try  {
                    throw new IndexOutOfBoundsException();
                } catch (IndexOutOfBoundsException e) {
                    LOGGER.log(Level.SEVERE, "TypeElement "+te+" is out of the Maps, "+x+"\nSOLVE: delete "+te);
                    return false;
                }
            }
            else {
                try  {
                    throw new IndexOutOfBoundsException();
                } catch (IndexOutOfBoundsException e) {
                    LOGGER.log(Level.SEVERE, "TypeElement "+te+" is out of the Maps, "+x+"\nSOLVE: delete "+te);
                    return false;
                }
            }
        } return true;
    }
    
    /**
     * Revois la taille du tableau board en abscisse.
     * @return int, taille du tableau en x.
     */
    public int getSizeX() {
        return this.x;
    }
    
    /**
     * Revois la taille du tableau board en ordonnée.
     * @return int, taille du tableau en y.
     */
    public int getSizeY() {
        return this.y;
    }
    
    /**
     * Revois une chaine de charactére de la Map pour afficher la map en console.
     * @return String
     */
    @Override
    public String toString(){
        StringBuilder  sb = new StringBuilder();
        for(int i=0;i<y;i++){
            for(int j=0;j<x;j++){
                List<Element> te =  this.Element.get(new Position(i,j));
                sb.append(te.get(te.size()-1).getTypeElement().getLetter()).append("|");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
