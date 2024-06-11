package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    public int x,y;
    public int speed;

    //public boolean alive;
    public BufferedImage imageSprite;

    public Rectangle getBounds(){
        return new Rectangle(x,y,imageSprite.getWidth(),imageSprite.getHeight());
    }
}
