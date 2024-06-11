package enemy;

import entity.Entity;;

import java.awt.image.BufferedImage;

public class Enemy extends Entity {

    public int direction;
    public boolean isAlive;

    public Enemy(int x, int y, int speed, BufferedImage sprite) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.imageSprite = sprite;
        this.direction = 1;
        this.isAlive = true;
    }

    public void update() {
        if(direction==1){
            x+=speed;
        }
        else if(direction==-1){
            x-=speed;
        }
    }


}
