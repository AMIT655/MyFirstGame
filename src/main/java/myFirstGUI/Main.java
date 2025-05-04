package myFirstGUI;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

public static final int WINDOW_WIDTH = 900;
public static final int WINDOW_HEIGHT = 700;

    public static void main(String[] args) {

        JFrame window = new JFrame("WorldBox");
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        MenuPanel menuPanel = new MenuPanel(0, 0, WINDOW_WIDTH / 4, WINDOW_HEIGHT);
        window.add(menuPanel);

        ContentPanel contentPanel = new ContentPanel(WINDOW_WIDTH / 4, 0, WINDOW_WIDTH - menuPanel.getWidth(), WINDOW_HEIGHT);
        window.add(contentPanel);

        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setLayout(null);
        window.setResizable(false);
        window.setVisible(true);





    }
}