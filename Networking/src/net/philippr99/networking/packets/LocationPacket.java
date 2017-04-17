package net.philippr99.networking.packets;

import net.philippr99.networklib.intern.BufferSerializer;
import net.philippr99.networklib.packet.Packet;

/**
 * Created by chome on 4/16/17.
 */
public class LocationPacket implements Packet {
    private int x, y;

    public LocationPacket() {

    }

    public LocationPacket(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void write(BufferSerializer serializer) {
        serializer.writeInt(x);
        serializer.writeInt(y);
    }

    @Override
    public void read(BufferSerializer deserializer) {
        this.x = deserializer.readInt();
        this.y = deserializer.readInt();
    }
}
