package afm.command;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import afm.core.UtilAFM;

public class CommandKillAll {

	public static void handle(ICommandSender sender, String[] args) {
		String entityType = null;
		if (args.length >= 2) {
			entityType = args[1];
		}
		if (sender.getCommandSenderName().equalsIgnoreCase("Rcon")) {
			sender.sendChatToPlayer("Cannot be used from server console");
		}
		EntityPlayerMP player = UtilAFM.getPlayer(sender.getCommandSenderName());
		if (player == null)
			return;
		World w = player.worldObj;
		if (w == null)
			return;
		Iterator<Entity> iter = w.loadedEntityList.iterator();

		while (iter.hasNext()) {
			Entity next = iter.next();
			if (entityType == null) {
				if (!(next instanceof EntityPlayer)) {
					player.onKillEntity(null);
					// TODO NO MATA!!
				}
			} else if (next.getEntityName() == entityType) {
				next.onKillEntity(null);
			}
		}

	}

	public static String getUsage() {
		return "/afm killAll (entityType)";
	}

	public static List getTabCompletion(ICommandSender sender, String[] args) {
		return Arrays.asList(new String[] { "pig", "cow" });
	}

}