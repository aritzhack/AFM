package afm.network;

import afm.core.AFMLogger;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

public class PacketHandler implements IPacketHandler {

    Map<Byte, AFMPacket> packets = new Hashtable<Byte, AFMPacket>();

    RandomNumberPacket rnPacket = new RandomNumberPacket();

    public PacketHandler() {
        this.packets.put(this.rnPacket.getPacketID(), this.rnPacket);

    }

    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {

        DataInputStream packetData = new DataInputStream(new ByteArrayInputStream(packet.data));

        try {
            this.packets.get(packetData.readByte()).handle(packetData, player);

        } catch (IOException e) {
            AFMLogger.log("Error handling packets: ", e);
        }
    }

}
