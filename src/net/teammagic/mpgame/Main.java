package net.teammagic.mpgame;

import net.philippr99.networklib.CostumClientSocket;

/**
 * Created by chome on 4/14/17.
 */
public class Main {

    public static void main(String[] args) {
        CostumClientSocket client = new CostumClientSocket("localhost",5088);
    }
}