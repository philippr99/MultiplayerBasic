package net.teammagic.mpgame;

import java.awt.*;

import static java.lang.Math.*;

public class OwnPlayer extends Player implements Runnable {
    private int mouseX, mouseY;

    public OwnPlayer(double x, double y, double w, double h) {
        super(x, y, w, h);
    }

    public OwnPlayer(double x, double y) {
        super(x, y);
    }

    @Override public void run() {
        double angle = angleFrom((int) x, (int) y, mouseX, mouseY);
        x += (int) round(speed * sin(angle));
        y -= (int) round(speed * cos(angle));
    }

    private double angleFrom(int APositionX, int APositionY, int BPositionX, int BPositionY) {
        int dx = BPositionX - APositionX;
        int dy = BPositionY - APositionY;

        return atan2(dy, dx) + 90;
    }

    public void setMousePos(int x, int y){
        mouseX = x;
        mouseY = y;
    }

    public void setMousePos(Point p){
        mouseX = p.x;
        mouseY = p.y;
    }
}
