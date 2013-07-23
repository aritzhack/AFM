package aritzh.afm.network;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import aritzh.afm.data.Config;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

/**
 * AFMPacket
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class AFMPacket {

    protected abstract byte getPacketID();

    Packet newPacket() {

        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        final DataOutputStream packetData = new DataOutputStream(bos);
        try {
            packetData.writeByte(this.getPacketID());
            this.writePacket(packetData);
        } catch (final Exception ex) {
            ex.printStackTrace();
        }

        final Packet250CustomPayload packet = new Packet250CustomPayload();
        packet.channel = Config.Network.CHANNEL;
        packet.data = bos.toByteArray();
        packet.length = bos.size();

        return packet;
    }

    protected abstract void writePacket(DataOutputStream packetData) throws IOException;

    public static void sendPacketToServer(final AFMPacket packet) {
        packet.sendPacketToServer();
    }

    void sendPacketToServer() {
        PacketDispatcher.sendPacketToServer(this.newPacket());
    }

    void sendPacketToPlayer(final Player player) {
        PacketDispatcher.sendPacketToPlayer(this.newPacket(), player);
    }

    public static void sendPacketToPlayer(final AFMPacket packet, final Player player) {
        packet.sendPacketToPlayer(player);
    }

    public abstract void handle(DataInputStream packetData, Player player) throws IOException;

}
