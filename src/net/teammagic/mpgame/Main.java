package net.teammagic.mpgame;

import net.philippr99.customnetworking.client.ClientPacketHandler;
import net.philippr99.customnetworking.packets.HeartbeatPacket;
import net.philippr99.customnetworking.packets.LocationPacket;
import net.philippr99.networklib.CustomClientSocket;
import net.philippr99.networklib.handler.PacketCreatorHandler;
import net.philippr99.networklib.handler.PacketOutputHandler;
import net.philippr99.networklib.handler.PacketSizePrintHandler;
import net.philippr99.networklib.handler.PacketSplitterHandler;
import net.philippr99.networklib.packet.PacketManager;
import net.philippr99.networklib.packets.IntegerPacket;
import net.philippr99.networklib.packets.StringPacket;
import net.philippr99.networklib.pipe.Pipe;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        PlayGround pg = new PlayGround();

        pg.setSize(500, 500);
        pg.setLocationRelativeTo(null);
        pg.setVisible(true);

        PacketManager.getInstance().addPacket(0x00, HeartbeatPacket.class);
        PacketManager.getInstance().addPacket(0x01, IntegerPacket.class);
        PacketManager.getInstance().addPacket(0x02, StringPacket.class);
        PacketManager.getInstance().addPacket(0x03, LocationPacket.class);
        try {
            CustomClientSocket client = new CustomClientSocket("localhost",
                    5088,
                    new Pipe().
                            addHandler("Splitter", new PacketSplitterHandler()).
                            addHandler("PacketCreator", new PacketCreatorHandler()).
                            addHandler("PacketHandler", new ClientPacketHandler()),
                    new Pipe().
                            addHandler("PacketOutputHandler", new PacketOutputHandler()).
                            addHandler("PacketSizePrintHandler", new PacketSizePrintHandler()));
            //Little test
            Thread.sleep(100);
            client.sendPacket(new StringPacket("Client Connected", "Now ...."));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}