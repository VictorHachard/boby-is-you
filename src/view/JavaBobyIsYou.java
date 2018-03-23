package view;

import exeptions.TypeElementNotFoundException;
import java.awt.Label;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import model.TypeElement;

/*
 *
 * @author Glaskani
 */
public class JavaBobyIsYou extends Application {
    
    Stage primaryStage;
    Scene scene;
    private static final Logger LOGGER = Logger.getGlobal();

    @Override
    /**
     * 
     */
    public void start(Stage primaryStage) throws IOException, TypeElementNotFoundException {     
        
        //recupere la taille de l'ecran
        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();       
        
        MenuInit d = new MenuInit(visualBounds.getWidth(), visualBounds.getHeight());
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
        FileHandler hdl = new FileHandler("BIS.log");
        hdl.setFormatter(new SimpleFormatter());
        LOGGER.addHandler(hdl);
        LOGGER.log( Level.INFO, "coucoumain" );
        /*Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Calendar cal = Calendar.getInstance();
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

    }
});*/
        
        
        
        launch(args);        
    }
}