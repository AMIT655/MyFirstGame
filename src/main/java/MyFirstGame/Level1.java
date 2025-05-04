package MyFirstGame;

import javax.swing.*;

public class Level1 extends JPanel {
    private Object2[] enemies = new Object2[12];



    public Level1(){
        int x = 150;
        int y = 100;
        for (int i = 0; i < this.enemies.length; i++) {
            this.enemies[i] = new Object2(x, y);
            x += 150; // ריווח אופקי בין אויבים

            if ((i + 1) % 4 == 0) { // אחרי כל 4 אויבים, יורדים שורה
                x = 200;
                y += 100; // ריווח אנכי בין שורות
            }
        }
    }


    public Object2[] getEnemies(){
        return this.enemies;
    }


}
