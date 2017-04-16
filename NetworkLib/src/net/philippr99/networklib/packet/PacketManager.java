package net.philippr99.networklib.packet;

import java.util.HashMap;

/**
 * Created by chome on 4/15/17.
 */
public class PacketManager {
    private static PacketManager instance;
    private HashMap<Integer, Class<?>> packets = new HashMap<Integer, Class<?>>();

    private PacketManager() {
        instance = this;
    }

    public static PacketManager getInstance() {
        if (instance == null) new PacketManager();
        return instance;
    }

    /**
     * Returns Packet ID or -1 for not finding this packet
     *
     * @param p
     * @return
     */
    public int getIDForPacket(Packet p) {
        for (int key : packets.keySet()) {
            if (packets.get(key).getName().equalsIgnoreCase(p.getClass().getName())) return key;
        }

        return -1;
    }

    /**
     * Returns packet or null
     *
     * @param id
     * @return
     */
    public Packet getPacketForID(int id) {
        if (packets.containsKey(id)) {
            Class<?> packetclass = packets.get(id);
            try {
                Packet p = (Packet) packetclass.newInstance();
                return p;
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public void addPacket(int id, Class<?> clazz) {
        packets.put(id, clazz);
    }
}
