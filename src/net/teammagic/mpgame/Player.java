package net.teammagic.mpgame;

import java.awt.geom.Rectangle2D;

public class Player extends Rectangle2D.Double {
    int speed = 5;

    public Player(double x, double y, double w, double h) {
        super(x, y, w, h);
    }

    public Player(double x, double y) {
        super(x, y, 10, 10);
    }
}
