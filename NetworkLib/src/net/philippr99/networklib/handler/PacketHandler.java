package net.philippr99.networklib.handler;

import net.philippr99.networklib.packet.Packet;
import net.philippr99.networklib.packets.IntegerPacket;

/**
 * Created by chome on 4/15/17.
 */
public class PacketHandler {

    public void handle(Packet p)
    {
       // System.out.println("Received Packet");
        if(p instanceof IntegerPacket)
        {
            System.err.println("Received IntegerPacket"+ ((IntegerPacket)p).i);
        }

    }
}
