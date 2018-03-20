package model;

import exeptions.TypeElementNotFoundException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
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
     * Charge un fichier (fileName) et crée une Maps.
     * @param fileName String, nom du fichier à charger.
     * @throws TypeElementNotFoundException 
     */
    public Maps(String fileName) throws TypeElementNotFoundException {
        
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
                addMap(Integer.parseInt(parts[1])+1, Integer.parseInt(parts[2])+1, Directions.fromString(movingDirection), TypeElement.fromString(toUpperCase(parts[0])));
            }

            buffer.close(); 
        } catch (IOException ex) {
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    /**
     * Crée une Maps de taille total x, y,
     * et génere de base les MURINJOUABLE et les EMPTY. 
     * @param x int, taille total de la map (aves les mur injouables).
     * @param y int, taille total de la map (aves les mur injouables).
     */
    public Maps(int x, int y) {
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
                    putObjects (Element, new Position(j,i), new Element(TypeElement.WALLINJOUABLE,Directions.RIGHT));
                else
                    putObjects (Element, new Position(j,i), new Element(TypeElement.EMPTY,Directions.RIGHT));
        }           
    }
    
    /**
     * Ajoute un object à la map,
     * prend en compte la prioriter de l'object avec les autres dans la liste.
     * @param x int, la case en abssice ou on ajoute l'object.
     * @param y int, la case en ordonnée ou on ajoute l'object.
     * @param directions Directions, la direction de l'object à ajouter.
     * @param object TypeElement, object à ajouter.
     * @throws TypeElementNotFoundException 
     */
    public void addMap(int x, int y, Directions directions, TypeElement object) throws TypeElementNotFoundException {       
        putObjects (Element, new Position(y,x), new Element(object,directions));
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
        try {
            if (x < 0 || x > this.x-1 || y < 0 || y > this.y-1) {
                throw new ArithmeticException();
            }
            else return Element.get(new Position(y,x));
        }
        catch (ArithmeticException e) {
            System.out.println("Error : getListElement in class Maps");
            if (x < 0 || x > this.x-1)
                System.out.println("    int " + x + " is out of the hashMap");
            else System.out.println("    int " + y + " is out of the hashMap");
            throw new RuntimeException();
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
