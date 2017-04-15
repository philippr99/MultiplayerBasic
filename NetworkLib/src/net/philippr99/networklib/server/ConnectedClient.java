package net.philippr99.networklib.server;

import net.philippr99.networklib.intern.BufferSerializer;
import net.philippr99.networklib.packet.Packet;
import net.philippr99.networklib.packets.IntegerPacket;
import net.philippr99.networklib.packets.StringPacket;
import net.philippr99.networklib.pipe.Pipe;
import net.philippr99.networklib.streams.CostumSocketOutputStreamTest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * Created by chome on 4/14/17.
 */
public class ConnectedClient implements Runnable{


    private BufferedInputStream input;
    private BufferedOutputStream outputStream;
    private Socket clientSocket;
    private Pipe inputPipe;
    private Pipe outputPipe;

    public ConnectedClient(Socket socket, Pipe inputPipe, Pipe outputPipe)
    {
        this.clientSocket =socket;
        this.inputPipe = inputPipe;
        this.outputPipe = outputPipe;
    }

    /**
     * Sending a packet to this Client
     * @param p
     */
    public void sendPacket(Packet p)
    {
        BufferSerializer result = (BufferSerializer) outputPipe.handle(p);
        result.writeOut(outputStream);
    }

    @Override
    public void run() {
        try {
            input = new BufferedInputStream(clientSocket.getInputStream());
            outputStream = new BufferedOutputStream(clientSocket.getOutputStream());

           // new Thread(new CostumSocketOutputStreamTest(new BufferSerializer(),outputStream)).start(); //TODO: reset through send Packet
            sendPacket(new IntegerPacket(8)); //sending IntegerPacket
            sendPacket(new StringPacket("Halt dein Maul geht es? :)"));
            sendPacket(new IntegerPacket(1234)); //sending IntegerPacket
            sendPacket(new IntegerPacket(9976)); //sending IntegerPacket

            System.out.println("Connected -> "+clientSocket.getInetAddress());
            BufferSerializer inputBuffer = new BufferSerializer();
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
