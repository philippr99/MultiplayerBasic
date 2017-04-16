package net.philippr99.networklib.predefiened.streams;

import net.philippr99.networklib.intern.BufferSerializer;

import java.io.BufferedOutputStream;
import java.util.Random;

/**
 * Created by chome on 4/14/17.
 */
public class CostumSocketOutputStreamTest implements Runnable {

    private BufferSerializer buffer;
    private BufferedOutputStream out;

    public CostumSocketOutputStreamTest(BufferSerializer buffer, BufferedOutputStream out) {
        this.buffer = buffer;
        this.out = out;
    }

    @Override
    public void run() {
        while (true) {
            int ran = new Random().nextInt(20000);
            System.out.println("Sent: " + ran);
            buffer.writeString("Gesendet: " + ran + " ->");
            buffer.writeInt(ran);
            buffer.writeOut(out);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
