/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.Placement;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import static jdk.nashorn.internal.objects.NativeString.toUpperCase;

/**
 *
 * @author Glaskani
 */
public class Board {
    private ArrayList<String> listeMove;
    private ArrayList<ArrayList<Placement>> listeGrid;
    private int x;
    private int y;
        
    public Board(int x, int y) {
        this.x = x;
        this.y = y;
        listeMove = new ArrayList<String>();
        listeGrid = new ArrayList<ArrayList<Placement>>();
        
    }
    
    //geteur

    /**
     * return une liste avec les elments.
     * @param x
     * @param y
     * @return ArrayList<Placement>
     */
    public ArrayList<Placement> getElement(int x, int y) {
        return null;
        //return listeGrid.get(x)).get(y));
    }
    
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
    
    public void modify(String name, int x, int y, int movingDirection) {
        
        //listeGrid.add(x)
        
        
    }
    
        /**
         *
         * @param fileName
         * @return
         * @throws FileNotFoundException
         * @throws IOException
         */
        public static Board load(String fileName) throws FileNotFoundException, IOException {

        String nextLine;
        
        try (BufferedReader buffer = new BufferedReader(new FileReader(fileName))) {
            //lecture de la premier ligne pour determiner et crée le board.
            String line = buffer.readLine();
            String[] size = line.split(" ");
            int sizeX = Integer.parseInt(size[0]);
            int sizeY = Integer.parseInt(size[1]);
            Board board = new Board(sizeX,sizeY);
            
            //lecture de toutes les autres lignes pour ajouter les elments dans le board.
            while ((nextLine = buffer.readLine()) != null) {
                String[] parts = nextLine.split(" ");
                String name = toUpperCase(parts[0]);
                int x = Integer.parseInt(parts[1]);
                int y = Integer.parseInt(parts[2]);
                int movingDirection = Integer.parseInt(parts[3]);
                board.modify(name, x, y ,movingDirection);
            }
            
            buffer.close(); 
            return board;
        }
        
    }

        /**
         *
         * @param fileName
         * @throws IOException
         */
       /* public void save(String fileName) throws IOException {
        
        try {
            BufferedWriter save = new BufferedWriter(new FileWriter(new File(fileName)));
            // si le fichier n'existe pas, il est crée à la racine du projet.
            save.write(x + " " + y);
            
            while (true) {
              
            save.write(name + " " + placementX + " " + placementY + " " + movingDirection);
            
            }
            
            save.close();
        }
       catch (IOException e) {
            e.printStackTrace();
        }
        
        
    }*/
    
}
