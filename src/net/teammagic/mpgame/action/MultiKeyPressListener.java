package net.teammagic.mpgame.action;

import net.teammagic.mpgame.PlayGround;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

public class MultiKeyPressListener extends KeyAdapter {
    private final Set<Integer> pressed = new HashSet<>();

    public synchronized void keyPressed(KeyEvent e) {
        pressed.add(e.getKeyCode());

        for (Integer c : pressed) {
            switch (c) {
                case KeyEvent.VK_UP:
                    PlayGround.player.y -= PlayGround.player.speed;
                    break;
                case KeyEvent.VK_DOWN:
                    PlayGround.player.y += PlayGround.player.speed;
                    break;
                case KeyEvent.VK_RIGHT:
                    PlayGround.player.x += PlayGround.player.speed;
                    break;
                case KeyEvent.VK_LEFT:
                    PlayGround.player.x -= PlayGround.player.speed;
                    break;
            }
        }
    }

    public synchronized void keyReleased(KeyEvent e) {
        pressed.remove(e.getKeyCode());
    }
}
