package MyFirstGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BossMissile extends JPanel {

    private int x;
    private int y;
    private int dx;// כמה לזוז ציר ציר X
    private int dy;// כמה לזוז ציר Y
    private int width = 70;
    private int height = 90;
    private BufferedImage image;

    public BossMissile(int x, int y, int dx, int dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;

        try {
            image = ImageIO.read(getClass().getResource("/PAGE8.png"));
        } catch (IOException e) {
            System.err.println("Boss missile image not found!");
        }
    }

    public void move() {
        this.x += dx;
        this.y += dy;
    }

    public void draw(Graphics g) {
        if (image != null) {
            g.drawImage(image, x, y, width, height, null);
        } else {
            g.setColor(Color.RED);
            g.fillRect(x, y, width, height);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}

