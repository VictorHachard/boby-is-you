package model;

/**
 *
 * @author Glaskani
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Board b = new Board("C:\\Users\\Glaskani\\OneDrive\\BobyIsYou\\src\\maps\\map1.txt");
        
        System.out.println(b.getAffichage());
        //TypeElements objectName = b.getListGrid().get(1).get(1).getListeContenu().get(0).getTypeElements();
        //System.out.println(objectName);
    }
    
}
