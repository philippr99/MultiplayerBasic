package net.philippr99.networklib;

import net.philippr99.networklib.packet.Packet;
import net.philippr99.networklib.pipe.Pipe;
import net.philippr99.networklib.server.CustomConnectedClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by chome on 4/14/17.
 */
public class CustomServerSocket {
    private int port;
    private ServerSocket socket;
    private Pipe inputPipe, outputPipe;

    public ArrayList<CustomClientSocket> sockets = new ArrayList<CustomClientSocket>(); //list of sockets

    public CustomServerSocket(int port, Pipe inputPipe, Pipe outputPipe)
    {
        this.port = port;
        this.inputPipe = inputPipe;
        this.outputPipe = outputPipe;

        init();
    }

    /**
     * Sending a Packet to all Clients
     * @param p
     */
    public void broadcastPacket(Packet p)
    {
        for(CustomClientSocket socket : sockets)socket.sendPacket(p);
    }

    private void init()
    {
        try {
            ServerSocket socket = new ServerSocket(port);

            while(true) {
                Socket clientSocket = socket.accept();
                //TODO: Maybe there are thread collisions so we have to clone the pipes for each thread
                sockets.add(new CustomConnectedClient(clientSocket,inputPipe, outputPipe)); //löst sich von selbst zu einem exteren Thread ab!
            }
        } catch (IOException e) {

        }
    }
}
