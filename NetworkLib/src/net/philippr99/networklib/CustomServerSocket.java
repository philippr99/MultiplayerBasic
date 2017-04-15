package net.philippr99.networklib;

import net.philippr99.networklib.pipe.Pipe;
import net.philippr99.networklib.server.ConnectedClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by chome on 4/14/17.
 */
public class CustomServerSocket {
    private int port;

    private ServerSocket socket;
    private Pipe inputPipe, outputPipe;

    public CustomServerSocket(int port, Pipe inputPipe, Pipe outputPipe)
    {
        this.port = port;
        this.inputPipe = inputPipe;
        this.outputPipe = outputPipe;

        init();
    }

    private void init()
    {
        try {
            ServerSocket socket = new ServerSocket(port);

            while(true) {
                Socket clientSocket = socket.accept();
                new Thread(new ConnectedClient(clientSocket,inputPipe, outputPipe)).start();
            }
        } catch (IOException e) {

        }
    }


}
