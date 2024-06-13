package enemy;

import entity.Entity;
import entity.Laser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Enemy extends Entity {

    public int direction;
    public boolean isAlive;
    public int type;

    public ArrayList<Laser> lasers;

    public Enemy(int x, int y, int speed, BufferedImage sprite) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.imageSprite = sprite;
        this.direction = 1;
        this.isAlive = true;
        this.lasers = new ArrayList<>();
    }

    public void update() {
        if(direction==1){
            x+=speed;
        }
        else if(direction==-1){
            x-=speed;
        }
    }

    public void shoot(){
        BufferedImage laserSprite = null;
        try {
            laserSprite = ImageIO.read(getClass().getResourceAsStream("/laser/laser.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(Math.random()<0.001&&this.type==2){
            lasers.add(new Laser(x+imageSprite.getWidth()/2,y+imageSprite.getHeight(),5,1,laserSprite));
        }
    }

    public void updateLasers(){
        for(Laser laser : lasers){
            laser.move();
        }
    }


}
