package afm.core;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class EventHandler {

	@ForgeSubscribe
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		AFM.proxy.writeChatMessageToPlayer(event.action.name());
	}
}
