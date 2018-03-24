package model;

import java.util.ArrayList;

/**
 *
 * @author Glaskani
 */
public class Subject {
    
    /*private static Subject INSTANCE = null;
    
    public static Subject getInstance() {           
        if (INSTANCE == null)
            INSTANCE = new Subject();
        return INSTANCE;
    }

    Subject() {
        obs = new ArrayList<>();
    }*/
    
    protected ArrayList<Observer> obs = new ArrayList<>();

    void registerObserver(Observer obs){
        if(!this.obs.contains(obs))
            this.obs.add(obs);
    }

    void removeObserver(Observer obs){
        this.obs.remove(obs);
    }

    void notifyObservers(TypeTypeElement te1,TypeTypeElement te2){
        for(Observer o: obs)
            o.update(this,te1,te2);
    }
}
