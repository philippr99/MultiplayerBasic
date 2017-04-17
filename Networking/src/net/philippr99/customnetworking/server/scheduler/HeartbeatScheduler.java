package net.philippr99.customnetworking.server.scheduler;

import net.philippr99.customnetworking.packets.HeartbeatPacket;
import net.philippr99.networklib.CustomClientSocket;

/**
 * Created by chome on 4/16/17.
 */
public class HeartbeatScheduler implements Runnable {

    private int timeout;
    private CustomClientSocket socket;
    private boolean receivedAlivePacket = false;

    public HeartbeatScheduler(int timeout, CustomClientSocket socket, Callback<Object, Boolean> callback) {
        this.timeout = timeout;
        this.socket = socket;
    }

    public void start() {
        new Thread(this).start(); //forking
    }

    @Override
    public void run() {
        socket.sendPacket(new HeartbeatPacket());
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true) {
            try {

                Thread.sleep(timeout);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
