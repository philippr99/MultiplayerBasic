package net.philippr99.networklib.handler;

import net.philippr99.networklib.CustomClientSocket;
import net.philippr99.networklib.intern.BufferSerializer;
import net.philippr99.networklib.pipe.Handler;

import java.nio.Buffer;

/**
 * Created by chome on 4/15/17.
 */
public class PacketSizePrintHandler implements Handler<BufferSerializer, BufferSerializer> {
    @Override
    public BufferSerializer handle(CustomClientSocket socket, BufferSerializer in) {
        BufferSerializer outPut = new BufferSerializer();
        outPut.writeInt(in.pointer); //writing size before packet
        outPut.copyBuffer(in); // writing packet
        return outPut;
    }
}
