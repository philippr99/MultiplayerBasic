package net.philippr99.networking.server;

import com.sun.corba.se.spi.activation.Server;
import net.philippr99.networklib.CustomClientSocket;
import net.philippr99.networklib.observing.Observable;
import net.philippr99.networklib.observing.ObserverManager;
import net.philippr99.networklib.packet.Packet;
import net.philippr99.networklib.packet.PacketHandler;
import net.philippr99.networklib.packets.DoublePacket;
import net.philippr99.networklib.packets.IntegerPacket;
import net.philippr99.networklib.packets.StringPacket;

/**
 * Created by chome on 4/16/17.
 */
public class ServerPacketHandler extends PacketHandler implements Observable {
    public ServerPacketHandler()
    {
        ObserverManager.getInstance().registerObservableInstance(this);
    }
    @Override
    public void handlePacket(CustomClientSocket socket, Packet p) {
        ObserverManager.getInstance().announceObserver(this,p,socket); //announcing the Observer
        // System.out.println("Received Packet");
        if (p instanceof IntegerPacket) {
            System.err.println("Received IntegerPacket" + ((IntegerPacket) p).i);
        } else if (p instanceof StringPacket) {
            System.err.println("String: " + ((StringPacket) p).str + ": " + ((StringPacket) p).str2);
            System.err.println("Received by: " + socket.socket.getRemoteSocketAddress().toString());
        }else if(p instanceof DoublePacket)
        {
            System.err.println("Received DoublePacket: " + ((DoublePacket) p).i);
        }
    }
}
