package common.view;

import common.model.Game;
import common.model.GameMode;
import common.model.Levels;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import static javafx.scene.input.KeyCode.getKeyCode;
import javafx.stage.Screen;
import javafx.stage.Stage;

/*
 *
 * @author Glaskani
 */
public class JavaBobyIsYou extends Application {
    
    static final String THEME = "/common/ressources/css/theme.css";
    static final Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();   
    public static final double WIDTH = visualBounds.getHeight()-30;
    public static final double HEIGHT = visualBounds.getHeight()-30;
    private static final Logger LOGGER = Logger.getGlobal();
    public static int indice =0;
    public static boolean carryOn=false;
    
    /**
     * Sauvegarde les informations suivante dans un config.txt à la racine :
     * level, gameModeTIME, gameModeMove, continue, toutes les key.
     */
    public static void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File("config.txt")))) {
            //writer.write("date "+"\n");
            if (!(Levels.instance()==null))
                writer.write("level "+Levels.instance().getIndice()+"\n");
            if (GameMode.isActive(Game.TIMER))
                writer.write("gameModeTIME true\n");
            if (GameMode.isActive(Game.PLAYERMOVE))
                writer.write("gameModeMOVE true\n");
            writer.write("continue "+Levels.getInstance().getContinue()+"\n");
            writer.write("KeyUP "+Key.getInstance().getKeyUP()+"\n");
            writer.write("KeyDOWN "+Key.getInstance().getKeyDOWN()+"\n");
            writer.write("KeyLEFT "+Key.getInstance().getKeyLEFT()+"\n");
            writer.write("KeyRIGHT "+Key.getInstance().getKeyRIGHT()+"\n");
            writer.write("KeyR "+Key.getInstance().getKeyR()+"\n");     
            writer.close();
        } catch (IOException ex){
            LOGGER.log(Level.SEVERE,"Coud not create file : config.txt",ex);
        }
    }
    
    /**
     * Lis le fichier config.txt.
     */
    private void config() {
        try (BufferedReader br = new BufferedReader(new FileReader("config.txt"))) {
            String nextLine;
            while ((nextLine = br.readLine()) != null) {
                String[] parts = nextLine.split(" ");
                switch (parts[0]) {
                    case "level":
                        JavaBobyIsYou.indice=Integer.parseInt(parts[1]);
                        break;
                    case "gameModeMOVE":
                        GameMode.setActivity(Game.PLAYERMOVE, Boolean.parseBoolean(parts[1]));
                        break;
                    case "gameModeTIME":
                        GameMode.setActivity(Game.TIMER, Boolean.parseBoolean(parts[1]));
                        break;
                    case "continue":
                        JavaBobyIsYou.carryOn=Boolean.parseBoolean(parts[1]);
                        break;
                    case "KeyUP":
                        Key.getInstance().setKeyUP(getKeyCode(parts[1]));
                        break;
                    case "KeyDOWN":
                        Key.getInstance().setKeyDOWN(getKeyCode(parts[1]));
                        break;
                    case "KeyLEFT":
                        Key.getInstance().setKeyLEFT(getKeyCode(parts[1]));
                        break;
                    case "KeyRIGHT":
                        Key.getInstance().setKeyRIGHT(getKeyCode(parts[1]));
                        break;
                    case "KeyR":
                        Key.getInstance().setKeyR(getKeyCode(parts[1]));
                        break;
                    default:
                        break;
                }
            }
            br.close();
            } catch (IOException | NumberFormatException e) {
                //rien faire
                //c'est que le fichier n'existe pas
            }
    }

    @Override
    /**
     * 
     */
    public void start(Stage primaryStage) {   
        Key.getInstance();
        config();
        MenuInit d = MenuInit.getInstance();
        d.setStage(primaryStage);
        primaryStage.setTitle("BobyIsYou");
        primaryStage.setScene(d.scene);
        primaryStage.show();
    }
    
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            //Looger
            FileHandler hdl = new FileHandler("BIS.log");
            hdl.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(hdl);
            
            Thread.setDefaultUncaughtExceptionHandler((Thread t, Throwable e) -> {
                LOGGER.log(Level.SEVERE,"Exeption Uncaught : ",e);
            });       
            launch(args);
        } catch (IOException | SecurityException ex) {
            Logger.getLogger(JavaBobyIsYou.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}