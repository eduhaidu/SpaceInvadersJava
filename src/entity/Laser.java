package entity;

import java.awt.image.BufferedImage;

public class Laser extends Entity {
    public int life;

    public Laser(int x, int y, int speed, BufferedImage sprite, int life) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.life = life;
        this.imageSprite=sprite;
    }

    public void move(){
        this.y -= speed;
    }

    public boolean isOutOfBounds(int screenHeight){
        return this.y<0||this.y>screenHeight;
    }
}
