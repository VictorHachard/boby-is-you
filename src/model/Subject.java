package model;

import java.util.List;

/**
 *
 * @author Glaskani
 */
public class Subject {
    protected List<Observer> obs;

    public void registerObserver(Observer obs){
        if(!this.obs.contains(obs))
            this.obs.add(obs);
    }

    public void removeObserver(Observer obs){
        this.obs.remove(obs);
    }

    public void notifyObservers(){
        for(Observer o: obs)
            o.update(this);
    }
}
