package net.teammagic.mpgame.entities;

import java.awt.geom.Rectangle2D;

public class Entity extends Rectangle2D.Double {
    public double speed = 5;

    public Entity(double x, double y, double w, double h) {
        super(x, y, w, h);
    }

    public Entity(double x, double y) {
        super(x, y, 10, 10);
    }
}
