package enemy;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class EnemyGrid {

    GamePanel gp;
    Enemy[] enemies;
    int mapEnemyNum[][];

    public EnemyGrid(GamePanel gp){
        this.gp=gp;
        enemies = new Enemy[5];
        mapEnemyNum = new int[11][5];
        getEnemyImage();
        loadMap();
    }

    public void getEnemyImage(){
        try{
            enemies[0]=new Enemy();
            enemies[0].imageSprite= ImageIO.read(getClass().getResourceAsStream("/enemy/enemySprite.png"));

            enemies[1]=new Enemy();
            enemies[1].imageSprite= ImageIO.read(getClass().getResourceAsStream("/enemy/enemySprite1.png"));

            enemies[2]=new Enemy();
            enemies[2].imageSprite= ImageIO.read(getClass().getResourceAsStream("/enemy/enemySprite2.png"));

        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap() {
        try {
            InputStream in = getClass().getResourceAsStream("/maps/gridMap.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            int col = 0;
            int row = 0;

            while (col < 11 && row < 5) {
                String Line = br.readLine();
                while (col < 11) {
                    String numbers[] = Line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapEnemyNum[col][row] = num;
                    col++;
                }
                if(col==11){
                    col=0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D g2){
        int col=0;
        int row=0;
        int x=100;
        int y=100;
        while(col<11&&row<5){
            int tileNum = mapEnemyNum[col][row];
            g2.drawImage(enemies[tileNum].imageSprite,x,y,gp.spriteSize,gp.spriteSize,null);
            col++;
            x+=gp.spriteSize;
            if(col==11){
                col=0;
                x=100;
                row++;
                y+=gp.spriteSize;
            }
        }
    }
}
