package entity;

import java.awt.image.BufferedImage;

public class Laser extends Entity {
    public int direction;

    public Laser(int x, int y, int speed,int direction, BufferedImage sprite) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.direction = direction;
        this.imageSprite=sprite;
    }

    public void move(){
        this.y += speed*direction;
    }

    public boolean isOutOfBounds(int screenHeight){
        return this.y<0||this.y>screenHeight;
    }
}
