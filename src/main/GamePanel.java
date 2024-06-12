package main;

import javax.swing.JPanel;
import java.awt.*;

import enemy.EnemyGrid;
import entity.Laser;
import entity.Player;

public class GamePanel extends JPanel implements Runnable {

    public boolean gameOver = false;
    public int score = 0;
    public int enemySpeed = 1;

    final int originalSpriteSize = 16; // Sprite de 16x16
    final int scale = 3;

    public final int spriteSize = originalSpriteSize * scale; // Sprite-urile for fi afisate pe ecran la marimea de 48x48
    final int maxScreenCol = 16;
    final int maxScreenRow = 16;
    final int screenWidth = spriteSize * maxScreenCol; // 768 pixeli
    final int screenHeight = spriteSize * maxScreenRow; // 768 pixeli

    //FPS
    int fps = 60;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyHandler);
    public EnemyGrid enemyGrid = new EnemyGrid(this, enemySpeed);
    //Enemy enemy = new Enemy(this);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // Performanta mai buna la randare
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (!gameOver) {
            double drawInterval = 1000000000 / fps;
            double delta = 0;
            long lastTime = System.nanoTime();
            long currentTime;
            while (gameThread != null) {

                currentTime = System.nanoTime();

                delta += (currentTime - lastTime) / drawInterval;

                lastTime = currentTime;

                if (delta >= 1) {
                    update();
                    repaint();
                    delta--;
                }
            }
            if (gameOver) {
                break;
            }
        }
    }

    public void update() {
        if (!gameOver) {
            player.update();
            enemyGrid.update();

            if (enemyGrid.areAllDead()) {
                advanceGame();
            }
        }
        if (gameOver && keyHandler.shootKeyPressed) {
            resetGame();
        }
    }

    public void resetGame() {
        enemyGrid = new EnemyGrid(this, enemySpeed);
        player.setDefaultValues();
        score = 0;
        gameOver = false;
    }

    public void advanceGame() {
        enemySpeed += 2;
        enemyGrid = new EnemyGrid(this, enemySpeed);
        gameOver = false;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        player.draw(g2);
        enemyGrid.draw(g2);
        hitDetection();

        // Desenare scor
        g2.setColor(Color.white);
        g2.setFont(new Font("Arial", Font.BOLD, 20));
        g2.drawString("Score: " + score, 10, 20);

        if (gameOver) {
            g2.setColor(Color.white);
            g2.setFont(new Font("Arial", Font.BOLD, 50));
            g2.drawString("GAME OVER", screenWidth / 2 - 150, screenHeight / 2);
        }
        //enemy.draw(g2);
        g2.dispose();
    }

    public void hitDetection() {
        // Verificare coliziune
        for (int i = 0; i < enemyGrid.rows; i++) {
            for (int j = 0; j < enemyGrid.cols; j++) {
                if (enemyGrid.enemies[i][j].isAlive) {
                    if (!player.lasers.isEmpty()) {
                        for (int k = 0; k < player.lasers.size(); k++) {
                            Laser laser = player.lasers.get(k);
                            if (laser.x > enemyGrid.enemies[i][j].x && laser.x < enemyGrid.enemies[i][j].x + spriteSize &&
                                    laser.y > enemyGrid.enemies[i][j].y && laser.y < enemyGrid.enemies[i][j].y + spriteSize) {
                                enemyGrid.enemies[i][j].isAlive = false;
                                player.lasers.remove(k);
                                if (enemyGrid.enemies[i][j].type == 1) {
                                    score += 20;
                                } else if (enemyGrid.enemies[i][j].type == 2) {
                                    score += 30;
                                } else if (enemyGrid.enemies[i][j].type == 3) {
                                    score += 10;
                                }
                            }
                        }
                    }

                }
            }
        }

        for (int i = 0; i < enemyGrid.rows; i++) {
            for (int j = 0; j < enemyGrid.cols; j++) {
                if (enemyGrid.enemies[i][j].isAlive) {
                    for (Laser laser : enemyGrid.enemies[i][j].lasers) {
                        if (laser.x > player.x && laser.x < player.x + spriteSize &&
                                laser.y > player.y && laser.y < player.y + spriteSize) {
                            player.isAlive = false;
                            gameOver = true;
                        }
                    }
                }
            }
        }
    }
}
