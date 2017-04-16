package net.philippr99.networklib.packets;

import net.philippr99.networklib.intern.BufferSerializer;
import net.philippr99.networklib.packet.Packet;

/**
 * Created by chome on 4/15/17.
 */
public class IntegerPacket implements Packet {
    public int i;

    public IntegerPacket() {

    }

    public IntegerPacket(int i) {
        this.i = i;
    }

    @Override
    public void write(BufferSerializer serializer) {
        serializer.writeInt(i);
    }

    @Override
    public void read(BufferSerializer deserializer) {
        this.i = deserializer.readInt();
    }
}
