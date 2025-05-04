package MyFirstGame;

import javax.swing.*;

public class Level2 extends JPanel {

    private Object2[] enemies = new Object2[10];

    public Level2() {
        int x = 200;
        int y = 50;

        for (int i = 0; i < this.enemies.length; i++) {
            this.enemies[i] = new Object2(x, y);
            x += 150; // ריווח אופקי

            // אחרי כל 4 אויבים, מעבר לשורה הבאה
            if ((i + 1) % 4 == 0) {
                x = 250;
                y += 80; // ריווח אנכי
            }
        }
    }

    public Object2[] getEnemies() {
        return this.enemies;
    }
}



