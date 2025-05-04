package MyFirstGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Boss extends JPanel {

    private int x;
    private int y;
    private int width;
    private int height;
    private int health;
    private BufferedImage image;
    private BossMissile[] bossMissiles = new BossMissile[20];


    public Boss(int x, int y){
        this.x = x;
        this.y = y;
        this.width = 200;
        this.height = 200;
        this.health = 50;

        try {
            image = ImageIO.read(getClass().getResource("/PAGE5.png"));
        } catch (IOException e) {
            System.err.println("Boss image not found!");
        }
    }

    public void updateMissiles() {
        for (int i = 0; i < bossMissiles.length; i++) {
            if (bossMissiles[i] != null) {
                bossMissiles[i].move();

                if (bossMissiles[i].getY() > Main.WINDOW_HEIGHT2) {
                    bossMissiles[i] = null;
                }
            }
        }
    }


    public void shoot() {
        for (int i = 0; i < bossMissiles.length; i++) {
            if (bossMissiles[i] == null) {
                bossMissiles[i] = new BossMissile(x + width / 2, y + height, 0, 7); // למטה
                break;
            }
        }
        for (int i = 0; i < bossMissiles.length; i++) {
            if (bossMissiles[i] == null) {
                bossMissiles[i] = new BossMissile(x + width / 2, y + height, -5, 7); // שמאלה למטה
                break;
            }
        }
        for (int i = 0; i < bossMissiles.length; i++) {
            if (bossMissiles[i] == null) {
                bossMissiles[i] = new BossMissile(x + width / 2, y + height, 5, 7); // ימינה למטה
                break;
            }
        }
    }



    public void paint(Graphics g) {
        if (image != null) {
            g.drawImage(image, x, y, width, height, null);
        } else {
            g.setColor(Color.RED);
            g.fillRect(x, y, width, height);
        }
        for (int i = 0; i < bossMissiles.length; i++) {
            if (bossMissiles[i] != null) {
                bossMissiles[i].draw(g);
            }
        }

    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void takeHit() {
        health--;
    }

    public boolean isDead() {
        return health <= 0;
    }

    public int getHealth() {
        return health;
    }

    public void moveUp(){
        if (this.y > 0 ) {
            this.y -= 18;
        }
    }

    public void moveDown(){
        if (y + this.height < Main.WINDOW_HEIGHT2) {
            this.y += 18;
        }
    }

    public void moveLeft(){
        if (x > 0) {
            this.x -= 18;
        }
    }

    public void moveRight(){
        if (x + this.width < Main.WINDOW_WIDTH2) {
            this.x += 18;
        }
    }

    public BossMissile[] getMissiles() {
        return bossMissiles;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getWidth() {
        return width;
    }
}
