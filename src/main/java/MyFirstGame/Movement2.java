package MyFirstGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Movement2 implements KeyListener {
    private Player2 player2;
    private MainPanel panel;


    public Movement2(Player2 player2, MainPanel panel) {
        this.player2 = player2;
        this.panel = panel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP){
            this.player2.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            this.player2.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.player2.moveLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.player2.moveRight();
        }
        else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            int missileWidth = Missile.WIDTH; // אותו כמו במחלקת Missile
            int missileHeight = Missile.HEIGHT; // אותו כמו במחלקת Missile

            int missileX = player2.getX() + player2.getWidth() / 2 - missileWidth / 2;
            int missileY = player2.getY() - missileHeight;

            panel.addMissile(new Missile(missileX, missileY));
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
