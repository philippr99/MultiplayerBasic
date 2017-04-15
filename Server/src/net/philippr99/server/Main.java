package net.philippr99.server;

import net.philippr99.networklib.CustomServerSocket;
import net.philippr99.networklib.handler.PacketOutputHandler;
import net.philippr99.networklib.handler.PacketSizePrintHandler;
import net.philippr99.networklib.packet.PacketManager;
import net.philippr99.networklib.packets.IntegerPacket;
import net.philippr99.networklib.packets.StringPacket;
import net.philippr99.networklib.pipe.Pipe;

/**
 * Created by chome on 4/14/17.
 */
public class Main {

    public static void main(String[] args)
    {
        PacketManager.getInstance().addPacket(1, IntegerPacket.class);
        PacketManager.getInstance().addPacket(3, StringPacket.class);
        CustomServerSocket server = new CustomServerSocket(5088,
                null,
                new Pipe().addHandler("PacketOutputHandler",new PacketOutputHandler()).addHandler("PacketSizePrintHandler",new PacketSizePrintHandler()));
    }
}
