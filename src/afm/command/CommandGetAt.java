package afm.command;

import afm.core.util.UtilAFM;
import net.minecraft.block.Block;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

import java.util.List;

/**
 * CommandGetAt
 *
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class CommandGetAt {

	public static void handle(ICommandSender sender, String[] args) {
		if (args.length != 4)
			throw new WrongUsageException(CommandGetAt.getUsage());
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
		if (!w.blockExists(x, y, z)) {
			sender.sendChatToPlayer("Block: Air");
		}
		int id = w.getBlockId(x, y, z);
		int meta = w.getBlockMetadata(x, y, z);
		Block b = Block.blocksList[id];
		sender.sendChatToPlayer("Block: ID=" + id + ", meta=" + meta + ", name=" + b.getUnlocalizedName());
	}

	public static String getUsage() {
		return "/afm getAt x y z";
	}

	public static List<String> getTabCompletion(ICommandSender sender, String[] args) {
		return null;
	}

}
