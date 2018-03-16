/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author ninjaphillipe
 */
public class javaFxMethodForGames 
{
    /**
     * This method move one image by 64 pixels
     * @param image 
     * @param x the number of case that you move this image 
     * @param y the 
     */
    public static void moveImageByCase(ImageView image,int x,int y)
    {
        x = x*64;
        y = y*64;
        image.setTranslateX(x);
        image.setTranslateY(y);
    }
    
}
