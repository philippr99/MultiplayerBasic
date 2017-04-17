package net.teammagic.mpgame;

import net.teammagic.mpgame.action.MultiKeyPressListener;
import net.teammagic.mpgame.entities.Entity;
import net.teammagic.mpgame.entities.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PlayGround extends JFrame {
    public static Player player;
    LinkedList<Entity> players = new LinkedList<>();

    public PlayGround() {
        this.add(initializeCanvas());

        player = new Player(50, 50);

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        executor.scheduleAtFixedRate(player, 0, 20, TimeUnit.MILLISECONDS);
        executor.scheduleAtFixedRate(this::repaint, 0, 20, TimeUnit.MILLISECONDS);
    }

    public JComponent initializeCanvas() {
        JComponent comp = new JComponent() {
            KeyAdapter keyAdapter = initializeKeys();
            MouseAdapter mouseAdapter = initializeMouse();

            {
                addKeyListener(keyAdapter);
            }

            {
                addMouseListener(mouseAdapter);
                addMouseMotionListener(mouseAdapter);
            }

            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(Color.GREEN);
                g2d.fill(player);

                g2d.setPaint(Color.BLACK);
                players.forEach(g2d::fill);
            }
        };

        comp.setFocusable(true);
        return comp;
    }

    private MouseAdapter initializeMouse() {
        return new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                player.useKeyboard = false;
                player.setMousePos(e.getPoint());
            }

            public void mouseDragged(MouseEvent e) {
                player.setMousePos(e.getPoint());
            }

            public void mouseReleased(MouseEvent e) {
                player.useKeyboard = true;
            }
        };
    }

    private KeyAdapter initializeKeys() {
        return new MultiKeyPressListener();
    }
}
