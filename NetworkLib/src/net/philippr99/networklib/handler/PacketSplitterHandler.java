package net.philippr99.networklib.handler;

import net.philippr99.networklib.intern.BufferSerializer;
import net.philippr99.networklib.pipe.Handler;

/**
 * Created by chome on 4/15/17.
 */
public class PacketSplitterHandler implements Handler<BufferSerializer, BufferSerializer>{

    //TODO: IMPORTANT TO DONT CREATE EVERYTIME A NEW BUFFER JUST WAIT WITH THE OTHER SIZE
    @Override
    public BufferSerializer handle(BufferSerializer in) {
        if(in.readInternInt() != -1)
        {
            int size = in.readInt();
            BufferSerializer output = new BufferSerializer();
            for(int i = 0; i < size; i++)
            {
                output.writeByte(in.readByte());
            }
            return output;
        }

        return null;
    }
}
