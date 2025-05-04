package MyFirstGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Missile extends JPanel {

    public static final int HEIGHT = 60;
    public static final int WIDTH = 110;
    public static final int SPEED = 10;

    private int x;
    private int y;
    private int height;
    private int width;
    private int speed;
    private BufferedImage missileImage;

    public Missile(int x, int y){
        this.x = x;
        this.y = y;
        this.height = HEIGHT;
        this.width =WIDTH;
        this.speed = SPEED;

        try {
            missileImage = ImageIO.read(getClass().getResource("/PAGE4-removebg-preview.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        if (missileImage != null) {
            g.drawImage(missileImage, x, y, width, height, null);
        } else {
            g.setColor(Color.RED);
            g.fillRect(x, y, width, height);
        }
    }


    public void moveUp(){
        this.y -= speed;
    }


    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getSPEED() {
        return SPEED;
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
