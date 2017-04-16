package net.philippr99.server;

import net.philippr99.customnetworking.packets.HearthbeatPacket;
import net.philippr99.networklib.CustomServerSocket;
import net.philippr99.networklib.handler.PacketCreatorHandler;
import net.philippr99.networklib.handler.PacketOutputHandler;
import net.philippr99.networklib.handler.PacketSizePrintHandler;
import net.philippr99.networklib.handler.PacketSplitterHandler;
import net.philippr99.networklib.packet.PacketManager;
import net.philippr99.networklib.packets.IntegerPacket;
import net.philippr99.networklib.packets.StringPacket;
import net.philippr99.networklib.pipe.Pipe;
import net.philippr99.customnetworking.server.ServerPacketHandler;

/**
 * Created by chome on 4/14/17.
 */
public class Main {

    public static void main(String[] args)
    {
        PacketManager.getInstance().addPacket(0x00, HearthbeatPacket.class);
        PacketManager.getInstance().addPacket(0x01, IntegerPacket.class);
        PacketManager.getInstance().addPacket(0x02, StringPacket.class);
        CustomServerSocket server = new CustomServerSocket(5088,
                new Pipe().
                        addHandler("Splitter", new PacketSplitterHandler()).
                        addHandler("PacketCreator",new PacketCreatorHandler()).
                        addHandler("PacketHandler", new ServerPacketHandler()),
                new Pipe().addHandler("PacketOutputHandler",new PacketOutputHandler()).addHandler("PacketSizePrintHandler",new PacketSizePrintHandler()));
    }
}
