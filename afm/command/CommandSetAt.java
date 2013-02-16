package afm.command;

import java.util.List;

import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import afm.core.util.UtilAFM;

public class CommandSetAt {

	public static void handle(ICommandSender sender, String[] args) {
		if (args.length < 5)
			throw new WrongUsageException(CommandSetAt.getUsage(), new Object[0]);
		if (sender.getCommandSenderName().equalsIgnoreCase("Rcon")) {
			sender.sendChatToPlayer("Cannot be used from server console");
		}
		EntityPlayerMP player = UtilAFM.getPlayer(sender.getCommandSenderName());
		if (player == null)
			return;
		World w = player.worldObj;
		if (w == null)
			return;
		int x = Integer.valueOf(args[1]);
		int y = Integer.valueOf(args[2]);
		int z = Integer.valueOf(args[3]);
		int id = Integer.valueOf(args[4]);

		if (args.length == 5 || args.length == 6) {
			w.setBlockWithNotify(x, y, z, id);
		}
		if (args.length == 6) {
			int meta = Integer.valueOf(args[5]);
			w.setBlockMetadata(x, y, z, meta);
		} else
			throw new WrongUsageException(CommandSetAt.getUsage(), new Object[0]);

	}

	public static String getUsage() {
		return "/afm setAt x y z id (metadata)";
	}

	public static List getTabCompletion(ICommandSender sender, String[] args) {
		return null;
	}

}
