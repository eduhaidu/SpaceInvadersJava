package main;

import javax.swing.JPanel;
import java.awt.*;

import enemy.EnemyGrid;
import entity.Player;

public class GamePanel extends JPanel implements Runnable{

    final int originalSpriteSize = 16; // Sprite de 16x16
    final int scale = 3;

    public final int spriteSize = originalSpriteSize*scale; // Sprite-urile for fi afisate pe ecran la marimea de 48x48
    final int maxScreenCol = 16;
    final int maxScreenRow = 16;
    final int screenWidth = spriteSize*maxScreenCol; // 768 pixeli
    final int screenHeight = spriteSize*maxScreenRow; // 768 pixeli

    //FPS
    int fps = 30;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this,keyHandler);
    EnemyGrid enemyGrid = new EnemyGrid(this);
    //Enemy enemy = new Enemy(this);

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // Performanta mai buna la randare
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while(gameThread!=null){

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if(delta>=1){
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update(){
        player.update();
        enemyGrid.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        player.draw(g2);
        enemyGrid.draw(g2);
        //enemy.draw(g2);
        g2.dispose();
    }
}
