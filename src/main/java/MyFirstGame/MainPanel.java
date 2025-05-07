package MyFirstGame;

import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static MyFirstGame.Main.WINDOW_HEIGHT2;
import static MyFirstGame.Main.WINDOW_WIDTH2;

public class MainPanel extends JPanel {
    private BufferedImage backgroundImage;
    private boolean gameOver;

    private Player2 player2;
    private Missile[] missiles = new Missile[15];
    private int countMissiles;
    private boolean reloading;
    private int playerLives;

    private Object2[] enemies;
    private EnemyMissiles[] enemyMissiles = new EnemyMissiles[15];
    private boolean level2Started;

    private Boss boss;
    private boolean level3Start;
    private boolean bossDirectionRight = true;

    private int fireCounter = 0;
    private int points;

    public MainPanel(int x, int y, int width, int height) {

        this.countMissiles = 10;
        this.reloading = false;
        this.playerLives = 10;

        Level1 level1 = new Level1();
        this.enemies = level1.getEnemies();

        this.player2 = new Player2(WINDOW_WIDTH2 / 2, WINDOW_HEIGHT2 - 200);

        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(new Movement2(player2, this));


        try {
            backgroundImage = ImageIO.read(getClass().getResource("/PAGE2.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        mainLoopGame();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // ציור רקע
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }

        // ציור אויבים
        for (int i = 0; i < enemies.length; i++) {
            if (enemies[i] != null) {
                enemies[i].paint(g);
            }
        }

        // ציור שחקן
        player2.paint(g);

        // ציור הבוס ומד חיים
        if (boss != null && !boss.isDead()) {
            boss.paint(g);
            g.setColor(Color.RED);
            g.fillRect(20, 90, boss.getHealth() * 4, 20);
            g.setColor(Color.WHITE);
            g.drawRect(20, 90, boss.getHealth() * 4, 20);
            g.drawString("Boss Health", 20, 85);
        }

        // ציור טילים של השחקן
        for (int i = 0; i < missiles.length; i++) {
            if (missiles[i] != null) {
                missiles[i].paint(g);
            }
        }

        // ציור טילים של אויבים
        for (int i = 0; i < enemyMissiles.length; i++) {
            if (enemyMissiles[i] != null) {
                enemyMissiles[i].draw(g);
            }
        }

        // טקסט חיי שחקן וטילים
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Missiles: " + countMissiles + (reloading ? " (reloading)" : ""), 20, 30);
        g.drawString("Lives: " + playerLives, 20, 60);
        g.drawString("Points: " + points ,1120,30);

        // הודעת סיום
        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 80));
            g.drawString("GAME OVER", getWidth() / 2 - 250, getHeight() / 2);
        }
    }

    public void reloadMissiles() {
        reloading = true;
        repaint();
        new Thread(() -> {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countMissiles = 10;
            reloading = false;
        }).start();
    }

    public void addMissile(Missile missile) {
        if (this.countMissiles > 0 && !this.reloading) {
            for (int i = 0; i < this.missiles.length; i++) {
                if (this.missiles[i] == null) {
                    this.missiles[i] = missile;
                    this.countMissiles--;
                    if (this.countMissiles == 0) {
                        reloadMissiles();
                    }
                    break;
                }
            }
        }
    }

    private void restartGame() {
        SwingUtilities.invokeLater(() -> {
            JFrame newWindow = new JFrame("New Game");
            newWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            newWindow.setSize(WINDOW_WIDTH2, WINDOW_HEIGHT2);
            newWindow.setLocationRelativeTo(null);
            newWindow.setResizable(false);

            MainPanel newGame = new MainPanel(0, 0, WINDOW_WIDTH2, WINDOW_HEIGHT2);
            newWindow.add(newGame);
            newWindow.setVisible(true);
        });
    }

    private void showGameOverWindow() {
        SwingUtilities.getWindowAncestor(this).dispose();

        JFrame gameOverFrame = new JFrame("Game Over");
        gameOverFrame.setSize(WINDOW_WIDTH2, WINDOW_HEIGHT2);
        gameOverFrame.setLocationRelativeTo(null);
        gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameOverFrame.setResizable(false);

        JLabel backgroundLabel = new JLabel();
        try {
            ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/5532919.jpg"));
            backgroundLabel.setIcon(backgroundIcon);
        } catch (Exception e) {
            System.err.println("Background image not found");
            backgroundLabel.setOpaque(true);
            backgroundLabel.setBackground(Color.BLACK);
        }

        backgroundLabel.setLayout(new BoxLayout(backgroundLabel, BoxLayout.Y_AXIS));


        JLabel titleLabel = new JLabel("You lost!!!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 60));
        titleLabel.setForeground(Color.MAGENTA);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


        JLabel scoreLabel = new JLabel("Total Points: " + this.points, SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 30));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


        JButton restartButton = new JButton("Restart");
        restartButton.setFont(new Font("Arial", Font.PLAIN, 20));
        restartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        restartButton.addActionListener(e -> {
            gameOverFrame.dispose();
            restartGame();
        });

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 20));
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.addActionListener(e -> System.exit(0));


        backgroundLabel.add(Box.createVerticalStrut(100));
        backgroundLabel.add(titleLabel);
        backgroundLabel.add(Box.createVerticalStrut(40));
        backgroundLabel.add(scoreLabel);
        backgroundLabel.add(Box.createVerticalStrut(40));
        backgroundLabel.add(restartButton);
        backgroundLabel.add(Box.createVerticalStrut(20));
        backgroundLabel.add(exitButton);

        gameOverFrame.setContentPane(backgroundLabel);
        gameOverFrame.setVisible(true);
    }


    private void showYouWonWindow (){
        SwingUtilities.getWindowAncestor(this).dispose();

        JFrame youWonFrame = new JFrame("You Won!");
        youWonFrame.setSize(WINDOW_WIDTH2, WINDOW_HEIGHT2);
        youWonFrame.setLocationRelativeTo(null);
        youWonFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        youWonFrame.setResizable(false);

        JLabel backgroundLabel = new JLabel();
        try {
            ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/5532919.jpg"));
            backgroundLabel.setIcon(backgroundIcon);
        } catch (Exception e) {
            System.err.println("Background image not found");
        }

        backgroundLabel.setLayout(new BoxLayout(backgroundLabel, BoxLayout.Y_AXIS));
        backgroundLabel.add(Box.createVerticalStrut(100));

        // טקסט YOU WON
        JLabel youWonLabel = new JLabel("YOU WON!!");
        youWonLabel.setFont(new Font("Arial", Font.BOLD, 50));
        youWonLabel.setForeground(Color.MAGENTA);
        youWonLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        backgroundLabel.add(youWonLabel);

        // ניקוד
        JLabel scoreLabel = new JLabel("Total Points: " + this.points);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        backgroundLabel.add(Box.createVerticalStrut(10));
        backgroundLabel.add(scoreLabel);

        backgroundLabel.add(Box.createVerticalStrut(40));

        // כפתור ריסטארט
        JButton restartButton = new JButton("RESTART");
        restartButton.setFont(new Font("Arial", Font.PLAIN, 20));
        restartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        restartButton.addActionListener(e -> {
            youWonFrame.dispose();
            restartGame();
        });
        backgroundLabel.add(restartButton);

        backgroundLabel.add(Box.createVerticalStrut(20));

        // כפתור יציאה
        JButton exitButton = new JButton("EXIT");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 20));
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.addActionListener(e -> System.exit(0));
        backgroundLabel.add(exitButton);

        youWonFrame.setContentPane(backgroundLabel);
        youWonFrame.setVisible(true);
    }

    private boolean allEnemiesDead() {
        for (int i = 0; i < enemies.length; i++) {
            if (enemies[i] != null) {
                return false;
            }
        }
        return true;
    }


    // מחזור המשחק הראשי (main game loop) שמטפל בכל תזוזה, ירי, ופגיעות במשחק
    public void mainLoopGame() {
        new Thread(() -> {
            while (true) {
                // תזוזת אויבים
                for (int i = 0; i < enemies.length; i++) {
                    if (enemies[i] != null) {
                        enemies[i].directional();
                        if (enemies[i].getX() < 0 || enemies[i].getX() > WINDOW_WIDTH2 - 200) {
                            enemies[i].flip();
                        }
                    }
                }

                fireCounter++;

                // ירי אויבים כל 60 צעדים
                if (fireCounter % 60 == 0 && !gameOver) {
                    for (int i = 0; i < enemies.length; i++) {
                        if (enemies[i] != null) {
                            for (int j = 0; j < enemyMissiles.length; j++) {
                                if (enemyMissiles[j] == null) {
                                    int bulletX = enemies[i].getX() + enemies[i].getWidth() / 2 - 5;
                                    int bulletY = enemies[i].getY() + enemies[i].getHeight();
                                    enemyMissiles[j] = new EnemyMissiles(bulletX, bulletY);
                                    break;
                                }
                            }
                        }
                    }
                }

                // תזוזת וירי של הבוס
                if (boss != null && !boss.isDead() && !gameOver) {
                    if (fireCounter % 5 == 0) {
                        if (bossDirectionRight) {
                            boss.moveRight();
                            if (boss.getX() + boss.getWidth() >= WINDOW_WIDTH2 - 5) {
                                bossDirectionRight = false;
                            }
                        } else {
                            boss.moveLeft();
                            if (boss.getX() <= 5) {
                                bossDirectionRight = true;
                            }
                        }
                    }

                    if (fireCounter % 100 == 0) {
                        boss.shoot();
                    }
                    boss.updateMissiles();

                    // פגיעות טילים של הבוס בשחקן
                    BossMissile[] bossMissiles = boss.getMissiles();
                    for (int i = 0; i < bossMissiles.length; i++) {
                        if (bossMissiles[i] != null) {
                            Rectangle bossMissileRect = bossMissiles[i].getBounds();

                            Rectangle playerRect = new Rectangle(
                                    player2.getX() + 40,
                                    player2.getY() + 40,
                                    player2.getWidth() - 80,
                                    player2.getHeight() - 80
                            );

                            if (bossMissileRect.intersects(playerRect)) {
                                bossMissiles[i] = null;
                                this.points-=20;
                                playerLives-=2;

                                if (playerLives <= 0 && !gameOver) {
                                    gameOver = true;
                                    SwingUtilities.invokeLater(() -> showGameOverWindow());
                                }
                            }
                        }
                    }
                }

                // תזוזת טילים של השחקן + פגיעות
                for (int i = 0; i < missiles.length; i++) {
                    if (missiles[i] != null) {
                        missiles[i].moveUp();

                        Rectangle missileRect = new Rectangle(
                                missiles[i].getX(),
                                missiles[i].getY(),
                                missiles[i].getWIDTH(),
                                missiles[i].getHEIGHT()
                        );

                        if (missiles[i].getY() < 0) {
                            missiles[i] = null;
                            continue;
                        }

                        // פגיעות בטילים של הבוס
                        if (boss != null) {
                            BossMissile[] bossMissilesArray = boss.getMissiles();
                            for (int j = 0; j < bossMissilesArray.length; j++) {
                                if (bossMissilesArray[j] != null) {
                                    Rectangle bossMissileRect = bossMissilesArray[j].getBounds();
                                    if (missileRect.intersects(bossMissileRect)) {
                                        bossMissilesArray[j] = null;
                                        missiles[i] = null;
                                        break;
                                    }
                                }
                            }
                        }

                        // פגיעות באויבים
                        for (int j = 0; j < enemies.length; j++) {
                            if (enemies[j] != null) {
                                Rectangle enemyRect = new Rectangle(
                                        enemies[j].getX(),
                                        enemies[j].getY(),
                                        enemies[j].getWidth(),
                                        enemies[j].getHeight()
                                );
                                if (missileRect.intersects(enemyRect)) {
                                    enemies[j] = null;
                                    missiles[i] = null;
                                    this.points+=10;
                                    break;
                                }
                            }
                        }

                        // פגיעות בבוס
                        if (boss != null && !boss.isDead()) {
                            Rectangle bossRect = boss.getBounds();
                            if (missileRect.intersects(bossRect)) {
                                boss.takeHit();
                                this.points+=10;
                                missiles[i] = null;
                            }
                        }
                    }
                }

                // תזוזת טילים של אויבים + פגיעות בשחקן וטילים של השחקן
                for (int i = 0; i < enemyMissiles.length; i++) {
                    if (enemyMissiles[i] != null) {
                        enemyMissiles[i].moveDone();
                        Rectangle bulletRect = enemyMissiles[i].getBounds();

                        Rectangle playerRect = new Rectangle(
                                player2.getX() + 40,
                                player2.getY() + 40,
                                player2.getWidth() - 80,
                                player2.getHeight() - 80
                        );

                        if (bulletRect.intersects(playerRect)) {
                            enemyMissiles[i] = null;
                            this.points-=10;
                            playerLives--;

                            if (playerLives <= 0 && !gameOver) {
                                gameOver = true;
                                SwingUtilities.invokeLater(() -> showGameOverWindow());
                            }
                        } else {
                            for (int j = 0; j < missiles.length; j++) {
                                if (missiles[j] != null) {
                                    Rectangle missileRect = new Rectangle(
                                            missiles[j].getX(),
                                            missiles[j].getY(),
                                            missiles[j].getWIDTH(),
                                            missiles[j].getHEIGHT()
                                    );
                                    if (bulletRect.intersects(missileRect)) {
                                        this.points+=1;
                                        enemyMissiles[i] = null;
                                        missiles[j] = null;
                                        break;
                                    }
                                }
                            }

                            if (enemyMissiles[i] != null && enemyMissiles[i].getY() > getHeight()) {
                                enemyMissiles[i] = null;
                            }
                        }
                    }
                }

                // מעבר שלבים
                if (!level2Started && allEnemiesDead()) {
                    level2Started = true;
                    enemies = new Level2().getEnemies();
                }

                if (level2Started && allEnemiesDead() && !level3Start) {
                    level3Start = true;
                    boss = new Level3().getBoss();
                }
                if (level3Start && boss.isDead()) {
                    this.points+=100;
                    showYouWonWindow();
                    break;
                }




                repaint();

                try {
                    Thread.sleep(15); // מהירות קבועה של המשחק
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}













