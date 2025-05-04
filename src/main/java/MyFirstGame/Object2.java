package MyFirstGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Object2 extends JPanel {

    public static final int OBJECT2_WIDTH = 65;
    public static final int OBJECT2_HEIGHT = 65;

    private int x;
    private int y;
    private int width;
    private int height;
    private int direction; // כיוון
    private BufferedImage object;

    public Object2(int x, int y){
        this.x = x;
        this.y = y;
        this.width = OBJECT2_WIDTH;
        this.height = OBJECT2_HEIGHT;
        this.direction = 1;
        try {
            object = ImageIO.read(getClass().getResource("/PAGE3-removebg-preview.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
// פונקציה שמקטינה את האוביקט
    public void reduce(){
        this.width--;
        this.height--;
    }

    public void directional(){
        if (this.direction == 1){
            moveLeft();
        }
        else {
            moveRight();
        }
    }

    public void flip(){
        if (this.direction == 1){
            this.direction = 0;
        }
        else {
            this.direction = 1;
        }

    }

    public void moveUp(){
        this.y -= 5;
    }

    public void moveDown(){
        this.y += 5;
    }

    public void moveLeft(){
        this.x -= 5;
    }

    public void moveRight(){
        this.x += 5;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void paint(Graphics g) {
        if (object != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(object, x, y, width, height, null);
        } else {
            g.setColor(Color.MAGENTA);
            g.fillRect(this.x, this.y, this.width, this.height);
        }
    }
}
