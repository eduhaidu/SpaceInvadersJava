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
    Enemy[][] enemies;
    int rows=5;
    int cols=11;
    int gridX=100;
    int gridY=100;

    public EnemyGrid(GamePanel gp){
        this.gp = gp;
        enemies = new Enemy[rows][cols];
        for (int i=0;i<rows;i++)
            for (int j=0;j<cols;j++)
                enemies[i][j] = null;
        loadEnemies();
    }

    public void loadEnemies(){
        try{
            InputStream in = getClass().getResourceAsStream("/maps/gridMap.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            int y = gridY;
            for(int i=0; i<rows; i++){
                int x = gridX;
                line = br.readLine();
                for(int j=0; j<cols; j++){
                    if(line.charAt(j)=='0'){
                        enemies[i][j]=new Enemy(x,y,1,ImageIO.read(getClass().getResourceAsStream("/enemy/enemySprite.png")));
                    }
                    else if(line.charAt(j)=='1'){
                        enemies[i][j] = new Enemy(x,y,1,ImageIO.read(getClass().getResourceAsStream("/enemy/enemySprite1.png")));
                    }
                    else if(line.charAt(j)=='2'){
                        enemies[i][j] = new Enemy(x,y,1,ImageIO.read(getClass().getResourceAsStream("/enemy/enemySprite2.png")));
                    }
                    else{
                        System.out.println("Numar invalid in fisierul de configurare al inamicilor la linia "+(i+1)+" si coloana "+(j+1));
                    }
                    x+=gp.spriteSize;
                }
                y+=gp.spriteSize;
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                if(enemies[i][j]!=null){
                    enemies[i][j].update();
                }
            }
        }
    }

    public void draw(Graphics g){
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                if(enemies[i][j]!=null){
                    g.drawImage(enemies[i][j].imageSprite,enemies[i][j].x,enemies[i][j].y,gp.spriteSize,gp.spriteSize,null);
                }
            }
        }
    }

}