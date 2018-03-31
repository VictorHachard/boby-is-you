package view;

import exeptions.TypeElementNotFoundException;
import java.io.IOException;
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

/*
 *
 * @author Glaskani
 */
public class JavaBobyIsYou extends Application {
    
    static Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();   
    Stage primaryStage;
    Scene scene;
    static double WIDTH = visualBounds.getWidth()-40;
    static double HEIGHT = visualBounds.getHeight()-40;
    private static final Logger LOGGER = Logger.getGlobal();

    @Override
    /**
     * 
     */
    public void start(Stage primaryStage) throws IOException, TypeElementNotFoundException {     
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
        /*Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
        @Override
        /*public void uncaughtException(Thread t, Throwable e) {
            String str = "";
            for (StackTraceElement el : e.getStackTrace()){
                str += el.getClassName() + " " + el.getFileName() + " " + el.getMethodName()+ " " + el.getLineNumber()+"--";
            }
                
            LOGGER.log(Level.SEVERE,e.toString() + " " + str);
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
});*/
        
        
        
        launch(args);        
    }
}