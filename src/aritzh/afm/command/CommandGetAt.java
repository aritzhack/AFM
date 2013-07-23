package aritzh.afm.command;

import java.util.List;

import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.world.World;

/**
 * CommandGetAt
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CommandGetAt {

    public static void handle(final EntityPlayer player, final String[] args) {
        if (args.length != 4) throw new WrongUsageException(CommandGetAt.getUsage());

        if (player == null) return;
        final World w = player.worldObj;
        if (w == null) return;
        final int x = Integer.valueOf(args[1]);
        final int y = Integer.valueOf(args[2]);
        final int z = Integer.valueOf(args[3]);
        if (!w.blockExists(x, y, z)) {
            player.sendChatToPlayer(new ChatMessageComponent().func_111079_a("Block: Air"));
        }
        final int id = w.getBlockId(x, y, z);
        final int meta = w.getBlockMetadata(x, y, z);
        player.sendChatToPlayer(new ChatMessageComponent().func_111079_a("Block: ID=" + id + ", meta=" + meta));
    }

    public static String getUsage() {
        return "/afm getAt x y z";
    }

    public static List<String> getTabCompletion(final ICommandSender sender, final String[] args) {
        return null;
    }

}
