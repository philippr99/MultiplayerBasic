package net.teammagic.mpgame;

import net.philippr99.networklib.CostumClientSocket;

public class Main {
    public static void main(String[] args) {
        PlayGround pg = new PlayGround();

        pg.setSize(500, 500);
        pg.setLocationRelativeTo(null);
        pg.setVisible(true);

        CostumClientSocket client = new CostumClientSocket("localhost", 5088);
    }
}