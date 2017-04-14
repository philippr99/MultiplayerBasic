package net.teammagic.mpgame;

import net.teammagic.mpgame.networking.Movement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PlayGround extends JFrame{
    private Movement mvmnt = new Movement();
    public static JComponent figure;
    public static Shape currentShape;

    public PlayGround() throws HeadlessException {
        CreateGraphics();
        this.add(figure);

        mvmnt.setActPos(100, 100);
        currentShape = new Rectangle2D.Double(100, 100, 10, 10);

        new Thread(mvmnt).start();

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(mvmnt, 0, 50, TimeUnit.MILLISECONDS);
    }

    public void CreateGraphics(){
        figure = new JComponent() {
            //private java.util.List<Shape> shapes = new ArrayList<Shape>();

            {
                MouseAdapter mouseAdapter = new MouseAdapter() {
                    public void mousePressed(MouseEvent e) {
                        mvmnt.setMousePos(e.getPoint());
                        repaint();
                    }

                    public void mouseDragged(MouseEvent e) {
                        mvmnt.setMousePos(e.getPoint());
                        repaint();
                    }

                    public void mouseMoved(MouseEvent e) {
                        mvmnt.setMousePos(e.getPoint());
                        repaint();
                    }

                    public void mouseReleased(MouseEvent e) {
                        //currentShape = null;
                        //repaint();
                    }
                };
                addMouseListener(mouseAdapter);
                addMouseMotionListener(mouseAdapter);
            }

            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(Color.BLACK);
                g2d.fill(currentShape);
                //for (Shape shape : shapes)
                //    g2d.draw(shape);
            }
        };
    }
}
