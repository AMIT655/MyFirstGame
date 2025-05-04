package myFirstGUI;

import javax.swing.*;
import java.awt.*;

public class ContentPanel extends JPanel {
    private int x;
    private int y;

    public ContentPanel(int x, int y, int width, int height){
        this.setBounds(x, y, width, height);
        this.setBackground(Color.BLUE);
        this.x = 100;
        this.y = 100;
        this.goRight();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillOval(this.x,this.y,100,100);
        g.setColor(Color.PINK);
        g.fillOval(250,100,100,100);

    }

    public void goRight(){
        new Thread(() -> {
            while (true){
                this.x++;
                this.y++;
                try {
                    Thread.sleep(40);
                    repaint();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

}
