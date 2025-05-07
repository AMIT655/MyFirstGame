package MyFirstGame;

import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Player2 extends JPanel {

    public static final int PLAYER_WIDTH = 100;
    public static final int PLAYER_HEIGHT = 100;

    private int x;
    private int y;
    private int width;
    private int height;
    private BufferedImage playerImage;

    public Player2(int x, int y){
        this.x = x;
        this.y = y;
        this.width = PLAYER_WIDTH;
        this.height = PLAYER_HEIGHT;
        try {
            playerImage = ImageIO.read(getClass().getResource("/PAGE6.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void moveUp(){
        if (this.y > 200 ) {
            this.y -= 50;
        }
    }

    public void moveDown(){
        if (y + height < Main.WINDOW_HEIGHT2) {
            this.y += 50;
        }
    }

    public void moveLeft(){
        if (x > 0) {
            this.x -= 50;
        }
    }

    public void moveRight(){
        if (x + width < Main.WINDOW_WIDTH2) {
            this.x += 50;
        }
    }

    public void paint(Graphics g){
        if (playerImage != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(playerImage, x, y, width, height, null);
        } else {
            g.setColor(Color.GREEN); // fallback
            g.fillOval(x, y, width, height);
        }

    }

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
