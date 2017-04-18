package net.philippr99.networking.server.scheduler;

import net.philippr99.networking.packets.HeartbeatPacket;
import net.philippr99.networking.server.ServerPacketHandler;
import net.philippr99.networklib.CustomClientSocket;
import net.philippr99.networklib.observing.Observable;
import net.philippr99.networklib.observing.Observer;
import net.philippr99.networklib.observing.ObserverManager;
import net.philippr99.networklib.packet.Packet;
import net.philippr99.networklib.packet.PacketManager;

/**
 * Created by chome on 4/16/17.
 */
//Umschreiben, dass er die Packets an alle Clients sendet!
public class HeartbeatScheduler implements Runnable,Observer {

    private int timeout;
    private CustomClientSocket socket;
    private boolean receivedAlivePacket = false;

    private HeartbeatPacket heartbeatPacket;

    public HeartbeatScheduler(int timeout, CustomClientSocket socket, Callback<Object, Boolean> callback, ServerPacketHandler serverPacketHandler) {
        this.timeout = timeout;
        this.socket = socket;

        ObserverManager.getInstance().addObserver(serverPacketHandler,this);
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
                //for threadsafety
                synchronized (heartbeatPacket) {
                    Thread.sleep(timeout);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //Packet incomming
    @Override
    public void action(Observable observable, Object... objects) {
        if(objects[0] instanceof Packet)
        {
            Packet p = (Packet)objects[0];
            CustomClientSocket customClientSocket = (CustomClientSocket) objects[1];
            synchronized (heartbeatPacket) {
                if (p instanceof HeartbeatPacket) heartbeatPacket = (HeartbeatPacket) p;
            }
        }
    }
}
