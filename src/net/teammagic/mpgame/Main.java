package net.teammagic.mpgame;
import net.philippr99.networklib.CustomClientSocket;
import net.philippr99.networklib.handler.PacketCreatorHandler;
import net.philippr99.networklib.handler.PacketHandler;
import net.philippr99.networklib.handler.PacketSplitterHandler;
import net.philippr99.networklib.packet.PacketManager;
import net.philippr99.networklib.packets.IntegerPacket;
import net.philippr99.networklib.packets.StringPacket;
import net.philippr99.networklib.pipe.Pipe;


public class Main {

    public static void main(String[] args) {
        PlayGround pg = new PlayGround();

        pg.setSize(500, 500);
        pg.setLocationRelativeTo(null);
        pg.setVisible(true);

        PacketManager.getInstance().addPacket(1, IntegerPacket.class);
        PacketManager.getInstance().addPacket(3, StringPacket.class);
        CustomClientSocket client = new CustomClientSocket("localhost",
                5088, new Pipe().addHandler("Splitter",
                new PacketSplitterHandler()).addHandler("PacketCreator",new PacketCreatorHandler()),
                null,
                new PacketHandler());
    }
}