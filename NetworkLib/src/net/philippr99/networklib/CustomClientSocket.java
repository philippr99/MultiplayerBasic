package net.philippr99.networklib;

import net.philippr99.networklib.intern.BufferSerializer;
import net.philippr99.networklib.packet.Packet;
import net.philippr99.networklib.pipe.Pipe;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by chome on 4/14/17.
 */
public class CustomClientSocket {

    public Socket socket;
    protected BufferedInputStream input;
    protected BufferedOutputStream outputStream;

    protected Pipe inputPipe, outputPipe;

    protected volatile BufferSerializer buffer;

    public CustomClientSocket(Socket socket, Pipe inputPipe, Pipe outputPipe) {
        this.inputPipe = inputPipe;
        this.outputPipe = outputPipe;
        this.socket = socket;

        new Thread(new Runnable() {
            @Override
            public void run() {
                init();
            }
        }).start();
    }

    public CustomClientSocket(String address, int port, Pipe inputPipe, Pipe outputPipe) throws IOException {
        this(new Socket(address, port), inputPipe, outputPipe);
    }

    /**
     * Sending a packet to the other end of the pipe
     *
     * @param p
     */
    public void sendPacket(Packet p) {
        BufferSerializer result = (BufferSerializer) outputPipe.handle(this, p);
        result.writeOut(outputStream);
    }

    public void connected() {
    }//called if connected

    public void disconnected() {
        System.err.println("Killed or closed! Maybe Bufferoverflow, too long strings or packet size!!");
        try {
            input.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        try {

            input = new BufferedInputStream(socket.getInputStream());
            outputStream = new BufferedOutputStream(socket.getOutputStream());

            buffer = new BufferSerializer(); //readinBuffer

            connected();

            int result = 0;
            do {
                synchronized (buffer) {
                    if (input.available() > 0) {
                        result = buffer.readIn(input);
                        Object obj = null;
                        do {
                            obj = inputPipe.handle(this, buffer);
                        } while (obj != null); //while loop is because, if there are more buffered packets!
                    } else {
                        if (socket.isConnected() == false) break; //handle over keep alive packets!
                    }
                }
            } while (result != -1); //also killed by overflowing buffer;
            //TODO: DETECT BROKEN CONNECTION THROUGHT HEARTH BEAT PACKETS AND THROUGH EXTERN INTERRUP ON THE disconnect() FUNKION
            disconnected();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
