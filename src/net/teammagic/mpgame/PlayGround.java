package net.teammagic.mpgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PlayGround extends JFrame{
    LinkedList<Player> players = new LinkedList<>();
    OwnPlayer ownPlayer;

    public PlayGround() {
        this.add(initializeCanvas());

        ownPlayer = new OwnPlayer(50, 50);
        new Thread(ownPlayer).start();

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        executor.scheduleAtFixedRate(ownPlayer, 0, 50, TimeUnit.MILLISECONDS);
        executor.scheduleAtFixedRate(() -> repaint(), 0, 50, TimeUnit.MILLISECONDS);
    }

    public JComponent initializeCanvas() {
        return new JComponent() {

            MouseAdapter mouseAdapter = initializeMouse();

            {
                addMouseListener(mouseAdapter);
                addMouseMotionListener(mouseAdapter);
            }

            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(Color.GREEN);
                g2d.fill(ownPlayer);

                g2d.setPaint(Color.BLACK);
                for (Shape player : players)
                    g2d.draw(player);
            }
        };
    }

    private MouseAdapter initializeMouse() {
        return new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                ownPlayer.setMousePos(e.getPoint());
            }

            public void mouseDragged(MouseEvent e) {
                ownPlayer.setMousePos(e.getPoint());
            }

            public void mouseMoved(MouseEvent e) {
                ownPlayer.setMousePos(e.getPoint());
            }

            public void mouseReleased(MouseEvent e) {

            }
        };
    }
}
