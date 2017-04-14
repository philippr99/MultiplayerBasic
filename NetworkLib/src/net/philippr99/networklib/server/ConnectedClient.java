package net.philippr99.networklib.server;

import net.philippr99.networklib.intern.Buffer;
import net.philippr99.networklib.streams.CostumSocketOutputStreamTest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by chome on 4/14/17.
 */
public class ConnectedClient implements Runnable{


    private BufferedInputStream input;
    private BufferedOutputStream outputStream;
    private Socket clientSocket;
    private Buffer outputBuffer;

    public ConnectedClient(Socket socket)
    {
        this.clientSocket =socket;
        this.outputBuffer = new Buffer();
    }

    @Override
    public void run() {
        try {
            input = new BufferedInputStream(clientSocket.getInputStream());
            outputStream = new BufferedOutputStream(clientSocket.getOutputStream());

            new Thread(new CostumSocketOutputStreamTest(outputBuffer,outputStream)).start(); //Outputstream test

            System.out.println("Connected -> "+clientSocket.getInetAddress());
            Buffer inputBuffer = new Buffer();
            while (input.read() != -1) {
                inputBuffer.readIn(input);
            }
            System.out.println("Disconnected -> "+clientSocket.getInetAddress());

            input.close();
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
