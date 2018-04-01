package common.model;

import common.exeptions.TypeElementNotFoundException;
import java.io.BufferedReader;
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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.stream.Collectors.toList;
import static jdk.nashorn.internal.objects.NativeString.toUpperCase;

/**
 *
 * @author Glaskani
 */
public class Maps {

    private int x;
    private int y;
    
    private Map<Position, List<Element>> Element = new HashMap<>();
    private ArrayList<Element> listAllElement;
    private Element unplayable = new Element(TypeElement.WALLINJOUABLE,Directions.RIGHT);
    private Element empty = new Element(TypeElement.EMPTY,Directions.RIGHT);
    private static final Logger LOGGER = Logger.getGlobal();
    
    public Maps(Maps map){
    this.Element = map.Element;
    this.empty = map.empty;
    this.listAllElement = map.listAllElement;
    this.x = map.x;
    this.y =map.y;
    this.unplayable=map.unplayable;
            }
    
    /**
     * Charge un fichier (fileName) et crée une Maps.
     * @param buffer
     * @throws TypeElementNotFoundException 
     * @throws java.io.IOException 
     */
    public Maps(BufferedReader buffer) throws TypeElementNotFoundException, IOException {
        listAllElement = new ArrayList<>();
        

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
                addMap(Integer.parseInt(parts[1])+1,
                        Integer.parseInt(parts[2])+1,
                        new Element(TypeElement.fromString(
                                toUpperCase(parts[0])),
                                Directions.fromString(movingDirection)));
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
        this.x = x;
        this.y = y;
        generateMap(x-2, y-2);
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
     * @param object Element, object à ajouter.
     * @throws TypeElementNotFoundException 
     */
    public void addMap(int x, int y, Element object)throws TypeElementNotFoundException {   
        checkIfPosIsInMap(x,y);
        if (!(object.getTypeTypeElements()==TypeTypeElement.PLAYER || object.getTypeElements()==TypeElement.ROCK))
            for(Element e:this.listAllElement) {
                if(e.equals(object)) {
                    putObjects (Element, new Position(y,x), e);
                    return;
                }
            }
        listAllElement.add(object);
        putObjects (Element, new Position(y,x), object);
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
     * @param elem Element, elme à supprimer.
     * @throws TypeElementNotFoundException 
     */
    public void removeMap(int x, int y, Element elem) throws TypeElementNotFoundException {
        checkIfPosIsInMap(x,y);
        removeObjects (Element, new Position(y,x), elem);
    }
    
    /**
     * Ajoute un te à la MultiMaps.
     * @param typeElem MapPosition, ListElement, la map pour ajouter te.
     * @param key Position, la position ou on ajoute te. 
     * @param te Elemnt, l'element que l'on veut ajouter.
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
        checkIfPosIsInMap(x,y);
        /*List<Element> copy = new ArrayList<>();
        for (Element e:Element.get(new Position(y,x))) {
            copy.add(new Element(e.typeElement,e.direction));
            for (Element e1:)
                }*/
        return deepCopy(Element.get(new Position(y,x)));
    }
    
    private List<Element> deepCopy(List<Element> a){
        return a.stream().map(val -> new Element(val)).collect(toList());
    }
    
    /**
     * 
     * @param x
     * @param y 
     */
    private void checkIfPosIsInMap(int x, int y) {
        if ((x < 0 || x > this.x-1) || (y < 0 || y > this.y-1)) {
            if ((x < 0 || x > this.x-1) && (y < 0 || y > this.y-1)) {
                LOGGER.log(Level.SEVERE, "int x,y " + x + "," + y + " are out of the hashMap");
                throw new ArithmeticException("int x,y " + x + "," + y + " are out of the hashMap");
            }
            else if (y < 0 || y > this.y-1) {
                LOGGER.log(Level.SEVERE, "int y " + y + " is out of the hashMap");
                throw new ArithmeticException("int y " + y + " is out of the hashMap");
            }
            else {
                LOGGER.log(Level.SEVERE, "int x " + x + " is out of the hashMap");
                throw new ArithmeticException("int x " + x + " is out of the hashMap");
            }
        }
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
     * Revois une chaine de charactére de la Maps pour afficher la map en console.
     * @return String
     */
    public String Affichage(){
        StringBuilder  sb = new StringBuilder();
        
        for(int i=0;i<y;i++){
            for(int j=0;j<x;j++){
                List<Element> te =  this.Element.get(new Position(i,j));
                sb.append(te.get(te.size()-1).getTypeElements().getLetter()).append("|");
            }
            sb.append("\n");
        }
        
        return sb.toString();
    }
    
    /**
     * Revois une chaine de charactére du Board en adresse memoire.
     * @return String
     */
    public String AffichageAdresse(){
        StringBuilder  sb = new StringBuilder();
        
        for(int i=0;i<y;i++){
            for(int j=0;j<x;j++){
                List<Element> te =  this.Element.get(new Position(i,j));
                sb.append(te.get(te.size()-1)).append("|");
            }
            sb.append("\n");
        }
        
        return sb.toString();
    }

    /**
     * Revois une liste vide ou avec des elements.
     * @return ListTypeElements, les type elements obligatoires,
     * si la liste est vide c'est qu'il ne manque rien,
     * si il y a des type elments cela veut dire qu'il manque ces type elements.
     * Element possible : PLAYER1, IS, WIN, TEXT_PLAYER1, TEXT_YOU.
     */
    public List<TypeElement> getForget() {
        List<TypeElement> find = new ArrayList<>();
        List<TypeElement> need = new ArrayList<>();
        need.add(TypeElement.PLAYER1);
        need.add(TypeElement.IS);
        need.add(TypeElement.WIN);
        need.add(TypeElement.TEXT_YOU);
        need.add(TypeElement.TEXT_PLAYER1);
        
        //parcourire tout la map pour cree la liste find
        for(int i=0;i<y;i++){
            for(int j=0;j<x;j++){
                List<Element> te =  this.Element.get(new Position(i,j));
                for(int k=0;k<te.size();k++){
                    for(TypeElement e:need)
                        if (te.get(k).getTypeElements()==e)
                            find.add(te.get(k).getTypeElements());
                }
            }
        }
        //supprimer des type element de find si ils sont dans need
        for(TypeElement e:find)
            if (need.contains(e))
                find.remove(e);
        //return liste de ce qui manque
        return find; 
    }
    
    /**
     * Sauvegarde la partie actuel, le nomde fichier sera la date et l'heure actuel.
     * @throws IOException 
     */
    public void save() throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        save(dateFormat.format(new Date()) +".txt");
    }
    
    /**
     * Sauvegarde la partie actuel, le nomde fichier sera fileName.
     * @param fileName String, le nom de fichier desirée.
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
        for(int i=0;i<y;i++){
            for(int j=0;j<x;j++){
                List<Element> te =  this.Element.get(new Position(i,j));
                for(int k=0;k<te.size();k++){
                    //ne save pas les EMPTY
                    if (!(te.get(k).getTypeElements()==TypeElement.EMPTY ||
                          te.get(k).getTypeElements()==TypeElement.WALLINJOUABLE)) {
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
