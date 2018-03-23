package model;

import java.util.Random;

/**
 *
 * @author Windows
 */
public class RandomMap {
    
    private int x;
    private int y;
    
    public RandomMap() {
        
    }
    
    private int randomNumber(int min,int max) {
        if (min >= max)
            throw new IllegalArgumentException("max must be greater than min");
       Random r = new Random();
       return r.nextInt((max-min)+1)+min;
    }
    
}
