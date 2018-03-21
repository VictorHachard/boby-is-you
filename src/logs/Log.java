/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logs;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import view.JavaBobyIsYou;

/**
 *
 * @author Windows
 */
public class Log {
    
    public static Logger log;
    
    public void log() throws IOException {
        log = Logger.getLogger(JavaBobyIsYou.class.getName());
        
        log.setLevel(Level.ALL); //pour envoyer les messages de tous les niveaux
        
        try {
            FileHandler fh = new FileHandler("myLog.txt");
            fh.setFormatter(new SimpleFormatter());
            log.addHandler(fh);
        }
        catch (SecurityException | IOException e) {}
        
        FileHandler fh = new FileHandler();
    }
    
}
