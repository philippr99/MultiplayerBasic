package net.philippr99.networklib.packet;

import net.philippr99.networklib.intern.BufferSerializer;

/**
 * Created by chome on 4/15/17.
 */
public interface Packet {

    public void write(BufferSerializer serializer);

    public void read(BufferSerializer deserializer);
}
