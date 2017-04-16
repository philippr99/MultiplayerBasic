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

public class BufferSerializer {

    private int MAX = 65535;
    public byte[] buffer = new byte[MAX];
    public int pointer = 0;

    public BufferSerializer()
    {

    }

    /**
     * A function to read the bytes into the intern buffer
     * @param reader
     * @return int successfull or not
     * @throws IOException
     */
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

    /**
     * A function to send the intern buffer to the outputstream
     * @param out
     */
    public void writeOut(BufferedOutputStream out)
    {
        try {
            out.write(buffer,0,pointer); //to only write the amount of bytes
            out.flush();
            pointer = 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writing an int to the intern buffer
     * @param i
     */
    public void writeInt(int i)
    {
        buffer[pointer+0] = (byte)( (i >> 24) & 0xff);
        buffer[pointer+1] = (byte)( (i >> 16) & 0xff);
        buffer[pointer+2] = (byte)( (i >> 8) & 0xff);
        buffer[pointer+3] = (byte)( (i >> 0) & 0xff);

        pointer+=4;
    }

    /**
     * Reading an int from the intern buffer, and resetting the buffer
     * @return result is positive or -1 for error
     */
    public int readInt()
    {
        int result = readInternInt();
        if(result != -1)readInternIntSuccessfully();
        return result;
    }

    /**
     * Reading an int from the intern buffer without resetting the buffer
     * @return
     */
    public int readInternInt()
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

    /**
     * Resetting the intern buffer after reading an int
     */
    public void readInternIntSuccessfully()
    {
        backShiftArray(4);
    }

    public void writeString(String string)
    {
        byte b[] = string.getBytes(Charset.forName("UTF-8"));
        int size = b.length;
        writeInt(size);
        for(int i = 0; i < size; i++)
        {
            buffer[pointer] = b[i];
            pointer++;
        }
    }

    /**
     * Reading an String from the intern buffer
     * @return
     */
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
            backShiftArray(size);

            return new String(str,Charset.forName("UTF-8"));
        }
        return null;
    }

    /**
     * Writing a byte to the buffer
     * @param b
     */
    public void writeByte(Byte b)
    {
        buffer[pointer] = b;
        pointer++;
    }

    /**
     * Reading a byte from the buffer
     * @return
     */
    public byte readByte()
    {
        byte b = buffer[0];
        backShiftArray(1); //TODO: don't forget
        return b;
    }

    /**
     * A little util to reset the intern buffer
     * @param b
     */
    private void backShiftArray(int b) //calling with the ints you used for int f.e 4
    {
        pointer-=b;
        for(int i = b; i <= pointer+b; i++) buffer[i-b] = buffer[i];
    }

    /**
     * Copying the old buffer into the new one
     * @param old
     */
    public void copyBuffer(BufferSerializer old)
    {
        int size = old.pointer;
        for(int i = 0; i < size; i++)
        {
            buffer[pointer] = old.buffer[i];
            old.pointer--;
            pointer++;
        }
    }

}
