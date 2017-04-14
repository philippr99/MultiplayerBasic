package net.philippr99.networklib;

import net.philippr99.networklib.intern.Buffer;
import net.philippr99.networklib.server.ConnectedClient;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by chome on 4/14/17.
 */
public class CostumServerSocket {
    private int port;

    private ServerSocket socket;


    public CostumServerSocket(int port)
    {
        this.port = port;

        init();
    }

    private void init()
    {
        try {
            ServerSocket socket = new ServerSocket(port);

            while(true) {
                Socket clientSocket = socket.accept();
                new Thread(new ConnectedClient(clientSocket)).start();
            }
        } catch (IOException e) {

        }
    }


}
