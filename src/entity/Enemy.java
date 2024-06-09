package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Enemy extends Entity{

    public int direction;
    GamePanel gamePanel;

    public Enemy(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        setDefaultValues();
        getEnemyImage();
    }

    public void setDefaultValues(){
        x=100;
        y=100;
        speed=1;
        alive=true;
        direction=1;
    }

    public void getEnemyImage(){
        try{
            imageSprite = ImageIO.read(getClass().getResourceAsStream("/enemy/enemySprite.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        if(x+speed>gamePanel.getWidth()-gamePanel.spriteSize){
            direction = -1;
            y+=gamePanel.spriteSize;
        }
        if(x-speed<0){
            direction = 1;
            y+=gamePanel.spriteSize;
        }
        x+=speed*direction;
    }

    public void draw(Graphics2D g){
        g.drawImage(imageSprite,x,y,gamePanel.spriteSize,gamePanel.spriteSize,null);
    }


}
