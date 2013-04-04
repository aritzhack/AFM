package afm.core;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import afm.AFM;

/**
 * EventHandler
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EventHandler {

	@ForgeSubscribe
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		if (!event.entityPlayer.worldObj.isRemote) {
			AFM.proxy.writeChatMessageToPlayer(event.action.name());
		}
	}
}
