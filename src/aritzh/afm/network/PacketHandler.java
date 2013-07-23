package aritzh.afm.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import aritzh.afm.core.AFMLogger;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

/**
 * PacketHandler
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class PacketHandler implements IPacketHandler {

    Map<Byte, AFMPacket> packets = new Hashtable<Byte, AFMPacket>();

    RandomNumberPacket rnPacket = new RandomNumberPacket();

    public PacketHandler() {
        this.packets.put(this.rnPacket.getPacketID(), this.rnPacket);

    }

    @Override
    public void onPacketData(final INetworkManager manager, final Packet250CustomPayload packet, final Player player) {

        final DataInputStream packetData = new DataInputStream(new ByteArrayInputStream(packet.data));

        try {
            this.packets.get(packetData.readByte()).handle(packetData, player);

        } catch (final IOException e) {
            AFMLogger.log("Error handling packets: ", e);
        }
    }

}
