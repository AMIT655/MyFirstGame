package MyFirstGame;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static final Dimension FULLSCREEN = Toolkit.getDefaultToolkit().getScreenSize();
    public static int WINDOW_WIDTH2 = FULLSCREEN.width;
    public static int WINDOW_HEIGHT2 = FULLSCREEN.height;

    public static void main(String[] args) {
        // יצירת חלון ראשי
        JFrame window = new JFrame("Chicken invaders");
        window.setResizable(false);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(WINDOW_WIDTH2, WINDOW_HEIGHT2);
        window.setLocationRelativeTo(null);

        // מוסיפים את תפריט הפתיחה
        StartMenu startMenu = new StartMenu(window);
        window.add(startMenu);

        window.setVisible(true);
    }
}



