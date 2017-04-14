package net.philippr99.networklib.streams;

import net.philippr99.networklib.CostumClientSocket;
import net.philippr99.networklib.intern.Buffer;

/**
 * Created by chome on 4/14/17.
 */
public class CostumSocketInputStreamTest implements Runnable{
    private volatile Buffer buffer;

    public CostumSocketInputStreamTest(Buffer buffer)
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
                    int input = buffer.readInt();
                    if(input != -1)
                        System.out.println(input);
                }
            }
        }
    }
}
