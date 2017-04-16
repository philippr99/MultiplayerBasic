package net.philippr99.networklib.server;

import net.philippr99.networklib.CustomClientSocket;
import net.philippr99.networklib.packets.IntegerPacket;
import net.philippr99.networklib.packets.StringPacket;
import net.philippr99.networklib.pipe.Pipe;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by chome on 4/16/17.
 */
public class CustomConnectedClient extends CustomClientSocket{

    public CustomConnectedClient(Socket socket, Pipe inputPipe, Pipe outputPipe) {
        super(socket, inputPipe, outputPipe);
    }

    public CustomConnectedClient(String address, int port, Pipe inputPipe, Pipe outputPipe) throws IOException {
        super(address, port, inputPipe, outputPipe);
    }

    @Override
    public void connected() {
        System.out.println("Connected -> "+socket.getInetAddress());
        sendPacket(new IntegerPacket(8)); //sending IntegerPacket
        sendPacket(new StringPacket("Test,Test","Second Test"));
        sendPacket(new IntegerPacket(1234)); //sending IntegerPacket
        sendPacket(new IntegerPacket(9976)); //sending IntegerPacket
    }

    @Override
    public void disconnected() {
        System.out.println("Disconnected -> "+socket.getInetAddress());
    }
}
