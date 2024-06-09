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
        this.alive = true;
    }

    public void move(){
        this.y -= speed;
    }

    //Make the enemy die to the laser
    public boolean isColliding(Entity e){
        return this.x<e.x+e.imageSprite.getWidth()&&
                this.x+this.imageSprite.getWidth()>e.x&&
                this.y<e.y+e.imageSprite.getHeight()&&
                this.y+this.imageSprite.getHeight()>e.y;
    }

    public boolean isOutOfBounds(int screenHeight){
        return this.y<0||this.y>screenHeight;
    }
}
