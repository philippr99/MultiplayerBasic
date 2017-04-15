package net.philippr99.networklib.handler;

import net.philippr99.networklib.intern.BufferSerializer;
import net.philippr99.networklib.packet.Packet;
import net.philippr99.networklib.packet.PacketManager;
import net.philippr99.networklib.pipe.Handler;

/**
 * Created by chome on 4/15/17.
 */
public class PacketCreatorHandler implements Handler<BufferSerializer, Packet> {
    @Override
    public Packet handle(BufferSerializer in) {
        int id = in.readInt();
        Packet packet = PacketManager.getInstance().getPacketForID(id);
        if(packet == null)return null;

        packet.read(in);
        return packet;
    }
}
