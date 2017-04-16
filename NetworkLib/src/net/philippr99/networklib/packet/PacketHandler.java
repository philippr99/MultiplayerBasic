package net.philippr99.networklib.packet;

import net.philippr99.networklib.CustomClientSocket;
import net.philippr99.networklib.packet.Packet;
import net.philippr99.networklib.packets.IntegerPacket;
import net.philippr99.networklib.packets.StringPacket;
import net.philippr99.networklib.pipe.Handler;

/**
 * Created by chome on 4/15/17.
 */
public abstract class PacketHandler implements Handler<Packet,Object>{

    public Object handle(CustomClientSocket socket, Packet p)
    {
        handlePacket(socket, p);
        return new Object(); //Return Object because return null means the pipe is broken!
    }

    public abstract void handlePacket(CustomClientSocket socket, Packet p);
}
