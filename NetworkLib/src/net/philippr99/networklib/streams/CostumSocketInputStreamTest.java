package net.philippr99.networklib.streams;

import net.philippr99.networklib.intern.BufferSerializer;

/**
 * Created by chome on 4/14/17.
 */
public class CostumSocketInputStreamTest implements Runnable{
    private volatile BufferSerializer buffer;

    public CostumSocketInputStreamTest(BufferSerializer buffer)
    {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while(true)
        {
            if(buffer != null)
            {
                synchronized (buffer)
                {
                    String input = buffer.readString();
                    int in = buffer.readInt();
                    if(input != null && in != -1)
                        System.out.println(input+";"+in);
                }
            }
        }
    }
}
