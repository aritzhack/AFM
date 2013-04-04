package afm.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import afm.core.AFMLogger;
import afm.data.Config;
import cpw.mods.fml.common.network.Player;

/**
 * RandomNumberPacket
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class RandomNumberPacket extends AFMPacket {

	int ran1, ran2;

	public RandomNumberPacket(int ran1, int ran2) {
		this.ran1 = ran1;
		this.ran2 = ran2;
	}

	public RandomNumberPacket() {
	}

	@Override
	protected byte getPacketID() {
		return Config.Network.PCKT_ID_RANDOM_NUMBERS;
	}

	@Override
	protected void writePacket(DataOutputStream packetData) throws IOException {
		packetData.writeInt(this.ran1);
		packetData.writeInt(this.ran2);
	}

	@Override
	public void handle(DataInputStream packetData, Player player) throws IOException {
		this.ran1 = packetData.readInt();
		this.ran2 = packetData.readInt();
		AFMLogger.log(String.format("Random numbers: %d, %d", this.ran1, this.ran2));
	}

}
