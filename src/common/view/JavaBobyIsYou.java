package common.view;

import common.exeptions.TypeElementNotFoundException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import static javafx.application.Application.launch;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

/*
 *
 * @author Glaskani
 */
public class JavaBobyIsYou extends Application {
    
    static Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();   
    Stage primaryStage;
    Scene scene;
    static ImageHashMap IMAGEMAP = new ImageHashMap();
    static double WIDTH = visualBounds.getWidth()-30;
    static double HEIGHT = visualBounds.getHeight()-30;
    private static final Logger LOGGER = Logger.getGlobal();
    private static int SAVECAMPAGNE = 0;
    
    public static int getSaveCampagne() {
        return SAVECAMPAGNE;
    }
    
    public static void save(int i) {
        try {
            URL uri = JavaBobyIsYou.class.getResource("/config.txt");
            String uri1 = uri.toString().substring(6);
                    BufferedWriter writer = new BufferedWriter(new FileWriter(new File(uri1)));
                    // normalement si le fichier n'existe pas, il est crée à la racine du projet
                  
                    writer.write("level "+i);
                    writer.close();
                } catch (IOException ex){
                    LOGGER.log(Level.SEVERE,"Coud not create file : config.txt",ex);
                }
    }
    
    private void config() {
        try {
                URL uri = JavaBobyIsYou.class.getResource("/config.txt");
                 System.out.println(uri);
                InputStream is = uri.openStream();
                InputStreamReader ipsr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(ipsr);
                
                String nextLine = br.readLine();
                String[] parts = nextLine.split(" ");
                if (parts[0].equals("level")) {
                    System.out.println(parts[1]);
                    SAVECAMPAGNE = Integer.parseInt(parts[1]); 
                }

                br.close();
            } catch (Exception e) {
                /*System.out.println("not fond");
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(new File("config.txt")));
                    // normalement si le fichier n'existe pas, il est crée à la racine du projet
                    writer.close();
                } catch (IOException ex){
                    ex.printStackTrace();
                }*/
            }
    }

    @Override
    /**
     * 
     */
    public void start(Stage primaryStage) throws IOException, TypeElementNotFoundException {   
        config();
        System.out.println(WIDTH);
        System.out.println(HEIGHT);
        MenuInit d = MenuInit.getInstance();
        d.setStage(primaryStage);
        scene = d.scene;
      
        primaryStage.setTitle("BobyIsYou");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     *
     * @param args
     * @throws TypeElementNotFoundException
     * @throws IOException
     */
    public static void main(String[] args) throws TypeElementNotFoundException, IOException {
        //Looger
        FileHandler hdl = new FileHandler("BIS.log");
        hdl.setFormatter(new SimpleFormatter());
        LOGGER.addHandler(hdl);
        
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                LOGGER.log(Level.SEVERE,"Exeption Uncaught : ",e);
            }
        });
                /* Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String filename = "crashlogs/"+sdf.format(cal.getTime())+".txt";
        PrintStream writer;
        try {
        writer = new PrintStream(filename, "UTF-8");
        writer.println(e.getClass() + ": " + e.getMessage());
        for (int i = 0; i < e.getStackTrace().length; i++) {
        writer.println(e.getStackTrace()[i].toString());
        }
        } catch (FileNotFoundException | UnsupportedEncodingException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
        }
        });*/ launch(args);       
    }
}