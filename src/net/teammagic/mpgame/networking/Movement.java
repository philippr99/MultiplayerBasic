package net.teammagic.mpgame.networking;

import net.teammagic.mpgame.PlayGround;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static java.lang.Math.*;

public class Movement implements Runnable{
    private static final int speed = 5;

    private int mouseX, mouseY;
    private int actX, actY;

    @Override public void run() {
        calcNextPosition();
        PlayGround.currentShape = new Rectangle2D.Double(actX, actY, 10, 10);
        PlayGround.figure.repaint();
    }

    private void calcNextPosition(){
        double angle = angleFrom(actX, actY, mouseX, mouseY);
        actX += (int)round(speed * sin(angle));
        actY -= (int)round(speed * cos(angle));
    }


    private double angleFrom(int APositionX, int APositionY, int BPositionX, int BPositionY)
    {
        int dx = BPositionX - APositionX;
        int dy = BPositionY - APositionY;

        return atan2(dy, dx) + 90;
    }

    public void setActPos(int x, int y){
        actX = x;
        actY = y;
    }

    public void setActPos(Point p){
        actX = p.x;
        actY = p.y;
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
