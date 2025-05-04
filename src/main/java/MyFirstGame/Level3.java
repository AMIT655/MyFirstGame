package MyFirstGame;

import javax.swing.*;

public class Level3 extends JPanel {

    private Boss boss;

    public Level3() {
        boss = new Boss(50, 100); // מיקום התחלתי
    }

    public Boss getBoss() {
        return boss;
    }
}


