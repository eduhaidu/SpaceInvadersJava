package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;


public class Player extends Entity{

    GamePanel gp;
    KeyHandler kh;
    ArrayList<Laser> lasers;
    private long lastShotTime;
    private final long shotCooldown = 1000;

    public Player(GamePanel gp, KeyHandler kh){
        this.gp = gp;
        this.kh = kh;
        this.lasers = new ArrayList<>();

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        x = 100;
        y = 600;
        speed = 3;
        alive = true;
        lastShotTime = System.currentTimeMillis();
    }

    public void getPlayerImage(){
        try{
            imageSprite= ImageIO.read(getClass().getResourceAsStream("/player/playerSprite.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        if(kh.leftPressed){
            if(x-speed>0) {
                x -= speed;
            }
        }
        if(kh.rightPressed){
            if(x+speed<gp.getWidth()-gp.spriteSize){
                x += speed;
            }
        }
        if(kh.shootKeyPressed){
            shootLasers();
        }
        updateLasers();
    }

    public void shootLasers(){
        long currentTime = System.currentTimeMillis();
        if(currentTime - lastShotTime > shotCooldown){
            BufferedImage laserSprite = null;
            try{
                laserSprite = ImageIO.read(getClass().getResourceAsStream("/laser/laser.png"));
            }catch(IOException e){
                e.printStackTrace();
            }
            Laser laser = new Laser(x,y,5,laserSprite,10);
            lasers.add(laser);
            lastShotTime = currentTime;
        }
    }

    public void updateLasers(){
        for(Laser laser : lasers){
            laser.move();
        }
        lasers.removeIf(laser->laser.isOutOfBounds(gp.getHeight()));
    }
    public void draw(Graphics2D g2){

        g2.drawImage(imageSprite,x,y,gp.spriteSize,gp.spriteSize,null);

        for (Laser laser : lasers){
            g2.drawImage(laser.imageSprite,laser.x,laser.y-gp.spriteSize/2,gp.spriteSize,gp.spriteSize,null);
        }

    }
}
