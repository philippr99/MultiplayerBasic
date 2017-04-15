package net.philippr99.networklib;

import net.philippr99.networklib.handler.PacketHandler;
import net.philippr99.networklib.intern.BufferSerializer;
import net.philippr99.networklib.packet.Packet;
import net.philippr99.networklib.pipe.Pipe;
import net.philippr99.networklib.streams.CostumSocketInputStreamTest;

import java.io.*;
import java.net.Socket;

/**
 * Created by chome on 4/14/17.
 */
public class CustomClientSocket {

    private String address;
    private int port;

    private Socket socket;
    private BufferedInputStream input;
    private BufferedOutputStream outputStream;

    private Pipe inputPipe, outputPipe;
    private PacketHandler handler;

    private volatile BufferSerializer buffer;

    public CustomClientSocket(String address, int port, Pipe inputPipe, Pipe outputPipe, PacketHandler handler)
    {
        this.address = address;
        this.port = port;
        this.inputPipe = inputPipe;
        this.outputPipe = outputPipe;
        this.handler = handler;


        new Thread(new Runnable() {
            @Override
            public void run() {
                init();
            }
        }).start();
    }

    private void init()
    {
        try {
            Socket socket = new Socket(address, port);
            input = new BufferedInputStream(socket.getInputStream());
            outputStream = new BufferedOutputStream(socket.getOutputStream());


            buffer = new BufferSerializer(); //readinBuffer
           // new Thread(new CostumSocketInputStreamTest(buffer)).start(); //IntReadingTestHandler

            int result = 0;
            do
            {
                synchronized (buffer)
                {
                    if(input.available() > 0) {
                        result = buffer.readIn(input);
                        Packet p = null;
                        do {
                            p = (Packet) inputPipe.handle(buffer);
                            if (p != null) handler.handle(p);
                        }while(p != null); //while loop is because, if there are more buffered packets!
                    }
                }
            }while(result != -1); //also killed by overflowing buffer;

            System.out.print("Killed or closed! Maybe Bufferoverflow, too long strings or packet size!!");

            input.close();
            outputStream.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
