package net.philippr99.customnetworking.packets;

import net.philippr99.networklib.intern.BufferSerializer;
import net.philippr99.networklib.packet.Packet;

import java.util.Random;

/**
 * Created by chome on 4/16/17.
 */
public class HearthbeatPacket implements Packet{

    public HearthbeatPacket()
    {

    }

    @Override
    public void write(BufferSerializer serializer) {
    }

    @Override
    public void read(BufferSerializer deserializer) {

    }
}
