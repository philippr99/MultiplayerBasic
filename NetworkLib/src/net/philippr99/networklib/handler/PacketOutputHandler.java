package net.philippr99.networklib.handler;

import net.philippr99.networklib.intern.BufferSerializer;
import net.philippr99.networklib.packet.Packet;
import net.philippr99.networklib.packet.PacketManager;
import net.philippr99.networklib.pipe.Handler;

/**
 * Created by chome on 4/15/17.
 */
public class PacketOutputHandler implements Handler<Packet, BufferSerializer> {
    @Override
    public BufferSerializer handle(Packet in) {
        BufferSerializer serializer = new BufferSerializer();
        int packetID = PacketManager.getInstance().getIDForPacket(in);
        if(packetID == -1)
        {
            try {
                throw new Exception("Packet is not registered Exception!");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        serializer.writeInt(packetID);
        in.write(serializer);
        return serializer;
    }
}
