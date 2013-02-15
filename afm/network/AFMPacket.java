package afm.network;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import afm.data.Properties;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public abstract class AFMPacket {

	protected abstract byte getPacketID();

	public Packet newPacket() {

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream packetData = new DataOutputStream(bos);
		try {
			packetData.writeByte(this.getPacketID());
			this.writePacket(packetData);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = Properties.Network.CHANNEL;
		packet.data = bos.toByteArray();
		packet.length = bos.size();

		return packet;
	}

	protected abstract void writePacket(DataOutputStream packetData) throws IOException;

	public static void sendPacketToServer(AFMPacket packet) {
		PacketDispatcher.sendPacketToServer(packet.newPacket());
	}

	public static void sendPacketToPlayer(AFMPacket packet, Player player) {
		PacketDispatcher.sendPacketToPlayer(packet.newPacket(), player);
	}

	public abstract void handle(DataInputStream packetData, Player player) throws IOException;

}
