package MyFirstGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class EnemyMissiles extends JPanel {
    public static final int HEIGHT = 30;
    public static final int WIDTH = 30;
    public static final int SPEED = 2;

    private int x;
    private int y;
    private int height;
    private int width;
    private int speed;
    private BufferedImage missileImage;

    public EnemyMissiles(int x, int y){
        this.x = x;
        this.y = y;
        this.width = WIDTH;
        this.height = HEIGHT;
        this.speed = SPEED;

        try {
            missileImage = ImageIO.read(getClass().getResource("/PAGE7.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void moveDone(){
        this.y += this.speed;
    }

    public void draw(Graphics g) {
        if (missileImage != null) {
            g.drawImage(missileImage, x, y, width, height, null);
        } else {
            g.setColor(Color.RED);
            g.fillRect(x, y, width, height);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, this.width, this.height);
    }

    public int getY() {
        return this.y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }
}

