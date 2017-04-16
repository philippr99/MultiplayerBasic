package net.philippr99.networklib.packets;

import net.philippr99.networklib.intern.BufferSerializer;
import net.philippr99.networklib.packet.Packet;

/**
 * Created by chome on 4/15/17.
 */
public class StringPacket implements Packet {
    public String str;
    public String str2;

    public StringPacket() {

    }

    public StringPacket(String str, String str2) {
        this.str = str;
        this.str2 = str2;
    }

    @Override
    public void write(BufferSerializer serializer) {
        serializer.writeString(str);
        serializer.writeString(str2);
    }

    @Override
    public void read(BufferSerializer deserializer) {
        this.str = deserializer.readString();
        this.str2 = deserializer.readString();
    }
}
