package net.teammagic.mpgame.entities;

import java.awt.*;

import static java.lang.Math.sqrt;

public class Player extends Entity implements Runnable {
    public boolean useKeyboard = true;
    private int mouseX, mouseY;

    public Player(double x, double y, double w, double h) {
        super(x, y, w, h);
    }

    public Player(double x, double y) {
        super(x, y);
    }

    @Override
    public void run() {
        if (useKeyboard) return;

        double dX = mouseX - x;
        double dY = mouseY - y;

        double dirLen = sqrt(dX * dX + dY * dY);

        if (dirLen > speed) {
            x += dX / dirLen * speed;
            y += dY / dirLen * speed;
        } else {
            x += dX / 2;
            y += dY / 2;
        }

        //System.out.println("Position:  x: " + x + ", y: " + y);
        //System.out.println("Mouse:  x: " + mouseX + ", y: " + mouseY);
    }

    public void setMousePos(int x, int y) {
        mouseX = x;
        mouseY = y;
    }

    public void setMousePos(Point p) {
        mouseX = p.x;
        mouseY = p.y;
    }
}
