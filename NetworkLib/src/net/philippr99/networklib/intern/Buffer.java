package net.philippr99.networklib.intern;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;

/**
 * Created by chome on 4/14/17.
 */
public class Buffer {

    private int MAX = 65535;
    private byte[] buffer = new byte[MAX];
    private int pointer = 0;

    public Buffer()
    {
    }

    public int readIn(BufferedInputStream reader) throws IOException {
        if(!((pointer+reader.available()) >= MAX))
        {
            int avaliable = reader.available(); // because we want the length
            reader.read(buffer,pointer,avaliable);
            pointer+= avaliable;
            System.out.println("av:"+avaliable);
            return 0; //works
        }else
        {
            return -1; //error
        }
    }

    public void writeOut(BufferedOutputStream outputStream)
    {
        try {
            outputStream.write(buffer,0,4); //to only write the amount of bytes
            outputStream.flush();
            pointer = 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeInt(int i)
    {
        buffer[pointer+0] = (byte)( (i >> 24) & 0xff);
        buffer[pointer+1] = (byte)( (i >> 16) & 0xff);
        buffer[pointer+2] = (byte)( (i >> 8) & 0xff);
        buffer[pointer+3] = (byte)( (i >> 0) & 0xff);

        System.out.println( buffer[pointer+3]+":"+buffer[pointer+2]+":"+buffer[pointer+1]+":"+buffer[pointer]);


        pointer+=4;
    }

    public int readInt()
    {
        if(pointer >= 4)
        {
            System.out.println(buffer[3]+":"+buffer[2]+":"+buffer[1]+":"+buffer[0]);
            int number = ((( ((int)buffer[3]) << 0)) & 0x000000ff) | //important cos of the signed bytes in java
                    ((( ((int)buffer[2]) << 8)) &0x0000ff00) |
                    ((( ((int)buffer[1]) << 16)) & 0x00ff0000) |
                    ((( ((int)buffer[0]) << 24)) & 0xff000000);

            pointer-=4; //setting back the pointer
            backShiftArray(4);
            return number;
        }

        return -1;
    }

    public void backShiftArray(int b) //calling with the ints you used for int f.e 4
    {
        for(int i = b; i <= pointer+b; i++) buffer[i-b] = buffer[i];
    }

}
