package net.philippr99.networklib.intern;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * Created by chome on 4/14/17.
 */

//Not Threadsafe!
public class Buffer {

    private int MAX = 65535;
    private byte[] buffer = new byte[MAX];
    private int pointer = 0;

    private BufferedOutputStream out;

    public Buffer(BufferedOutputStream out)
    {
        this.out = out;
    }

    public int readIn(BufferedInputStream reader) throws IOException {
        if(!((pointer+reader.available()) >= MAX))
        {
            int avaliable = reader.available(); // because we want the length
            System.out.println("av:"+avaliable);
            reader.read(buffer,pointer,avaliable);
            pointer+= avaliable;
            return 0; //works
        }else
        {
            return -1; //error
        }
    }

    private void writeOut(int size)
    {
        try {
            out.write(buffer,pointer-size,size); //to only write the amount of bytes
            out.flush();
            pointer = 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Write Integer
    public void writeInt(int i)
    {
        buffer[pointer+0] = (byte)( (i >> 24) & 0xff);
        buffer[pointer+1] = (byte)( (i >> 16) & 0xff);
        buffer[pointer+2] = (byte)( (i >> 8) & 0xff);
        buffer[pointer+3] = (byte)( (i >> 0) & 0xff);

        pointer+=4;
        writeOut(4);
    }

    //Read Integer
    public int readInt()
    {
        int result = readInternInt();
        if(result != -1)readInternIntSuccessfully();
        return result;
    }

    private int readInternInt()
    {
        if(pointer >= 4)
        {
            int number = ((( ((int)buffer[3]) << 0)) & 0x000000ff) | //important cos of the signed bytes in java
                    ((( ((int)buffer[2]) << 8)) &0x0000ff00) |
                    ((( ((int)buffer[1]) << 16)) & 0x00ff0000) |
                    ((( ((int)buffer[0]) << 24)) & 0xff000000);
            return number;
        }

        return -1;
    }

    private void readInternIntSuccessfully()
    {
        pointer-=4; //setting back the pointer
        backShiftArray(4);
    }

    public void writeString(String string)
    {
        int size = string.length();
        byte b[] = string.getBytes(Charset.forName("UTF-8"));
        writeInt(size);
        for(int i = 0; i < size; i++)
        {
            buffer[pointer] = b[i];
            pointer++;
        }

        writeOut(size); //string length
    }

    public String readString()
    {
        int size = readInternInt(); //like getting a local copy of int
        if(size == -1)return null;

        if(pointer >= size){
            readInternIntSuccessfully(); //renmove Int also from array
            byte str[] = new byte[size];
            for(int i = 0; i < size; i++)
            {
                str[i] = buffer[i];
            }
            pointer-=size;
            backShiftArray(size);

            return new String(str,Charset.forName("UTF-8"));
        }else
        {

        }

        return null;
    }

    public void backShiftArray(int b) //calling with the ints you used for int f.e 4
    {
        for(int i = b; i <= pointer+b; i++) buffer[i-b] = buffer[i];
    }

}
