package net.philippr99.networklib;

import net.philippr99.networklib.intern.Buffer;
import net.philippr99.networklib.streams.CostumSocketInputStreamTest;

import java.io.*;
import java.net.Socket;

/**
 * Created by chome on 4/14/17.
 */
public class CostumClientSocket {

    private String address;
    private int port;

    private Socket socket;
    private BufferedInputStream input;
    private BufferedOutputStream outputStream;

    private volatile Buffer buffer;

    public CostumClientSocket(String address, int port)
    {
        this.address = address;
        this.port = port;

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


            buffer = new Buffer(); //call after initializing buffer
            new Thread(new CostumSocketInputStreamTest(buffer)).start(); //IntReadingTestHandler

            int result = 0;
            do
            {
                synchronized (buffer)
                {
                    if(input.available() > 0)
                        result = buffer.readIn(input);
                }
            }while(result != -1); //also killed by overflowing buffer;

            System.out.print("Killed or closed!");

            input.close();
            outputStream.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
