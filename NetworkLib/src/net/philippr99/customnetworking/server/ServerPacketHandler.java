package net.philippr99.customnetworking.server;

import net.philippr99.networklib.CustomClientSocket;
import net.philippr99.networklib.packet.Packet;
import net.philippr99.networklib.packet.PacketHandler;
import net.philippr99.networklib.packets.IntegerPacket;
import net.philippr99.networklib.packets.StringPacket;

/**
 * Created by chome on 4/16/17.
 */
public class ServerPacketHandler extends PacketHandler{
    @Override
    public void handlePacket(CustomClientSocket socket, Packet p) {
        // System.out.println("Received Packet");
        if(p instanceof IntegerPacket)
        {
            System.err.println("Received IntegerPacket"+ ((IntegerPacket)p).i);
        }else if(p instanceof StringPacket)
        {
            System.err.println("String: "+((StringPacket)p).str+": "+((StringPacket)p).str2);
            System.err.println("Received by: "+socket.socket.getRemoteSocketAddress().toString());
        }
    }
}
