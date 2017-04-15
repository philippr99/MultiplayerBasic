package net.philippr99.networklib.packets;

import net.philippr99.networklib.intern.BufferSerializer;
import net.philippr99.networklib.packet.Packet;

/**
 * Created by chome on 4/15/17.
 */
public class StringPacket implements Packet{
    public String str;

    public StringPacket()
    {

    }

    public StringPacket(String str)
    {
        this.str = str;
    }

    @Override
    public void write(BufferSerializer serializer) {
        serializer.writeString(str);
    }

    @Override
    public void read(BufferSerializer deserializer) {
        this.str = deserializer.readString();
    }
}
