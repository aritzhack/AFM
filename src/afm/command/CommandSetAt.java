package afm.command;

import afm.core.util.UtilAFM;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

import java.util.List;

/**
 * CommandSetAt
 *
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class CommandSetAt {

	public static void handle(ICommandSender sender, String[] args) {
		if (args.length < 5)
			throw new WrongUsageException(CommandSetAt.getUsage());
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
		int meta = 0;
		
		w.setBlock(x, y, z, id);

		if (args.length == 6) {
			meta = Integer.valueOf(args[5]);
			w.setBlockMetadataWithNotify(x, y, z, meta, 3); // 3 updates client+server, but just with same ID
		} else if (args.length > 6) throw new WrongUsageException(CommandSetAt.getUsage());


	}

	public static String getUsage() {
		return "/afm setAt x y z id (metadata)";
	}

	public static List<String> getTabCompletion(ICommandSender sender, String[] args) {
		return null;
	}

}
