package afm.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import afm.core.AFMLogger;
import afm.core.util.UtilAFM;

/**
 * CommandKillAll
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CommandKillAll {

	public static void handle(ICommandSender sender, String[] args) {
		String entityType = null;
		if (args.length >= 2) {
			AFMLogger.debug("Input: " + args[1]);
			entityType = args[1];
		}
		if (sender.getCommandSenderName().equalsIgnoreCase("Rcon")) {
			sender.sendChatToPlayer("Cannot be used from server console");
		}
		EntityPlayerMP player = UtilAFM.getPlayer(sender.getCommandSenderName());
		if (player == null) return;
		World w = player.worldObj;
		if (w == null) return;
		List<Entity> unload = new ArrayList<Entity>();
		try {
			for (int x = 0; x < w.loadedEntityList.size(); x++) {
				Entity curr = (Entity) w.loadedEntityList.get(x);
				if (entityType == null || EntityList.getEntityString(curr).equals(entityType)) {
					unload.add(curr);
				}
			}
			w.unloadEntities(unload);
		} catch (Exception e) {
		}

	}

	public static String getUsage() {
		return "/afm killAll (entityType)";
	}

	public static List<String> getTabCompletion(ICommandSender sender, String[] args) {
		return Arrays.asList("pig", "cow");
	}

}