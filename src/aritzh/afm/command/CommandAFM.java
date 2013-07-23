package aritzh.afm.command;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatMessageComponent;
import aritzh.afm.core.util.UtilAFM;

/**
 * CommandAFM
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CommandAFM extends CommandBase {

    @Override
    public String getCommandName() {
        return "afm";
    }

    @Override
    public String getCommandUsage(final ICommandSender par1iCommandSender) {
        return "/afm [setAt | getAt | killAll]";
    }

    @Override
    public boolean canCommandSenderUseCommand(final ICommandSender commandSender) {
        return UtilAFM.isOp(commandSender.getCommandSenderName());
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<String> addTabCompletionOptions(final ICommandSender commandSender, final String[] args) {
        if (!(commandSender instanceof EntityPlayer)) {
            commandSender.sendChatToPlayer(new ChatMessageComponent().func_111079_a("Must be a player to issue this command!"));
        }

        if (args.length > 0) {
            final EntityPlayer player = (EntityPlayer) commandSender;
            if (args[0].equalsIgnoreCase("setAt")) return CommandSetAt.getTabCompletion(player, args);
            else if (args[0].equalsIgnoreCase("getAt")) return CommandGetAt.getTabCompletion(player, args);
            else if (args[0].equalsIgnoreCase("killAll")) return CommandKillAll.getTabCompletion(player, args);
        }
        return CommandBase.getListOfStringsMatchingLastWord(args, "setAt", "getAt", "killAll");
    }

    @Override
    public void processCommand(final ICommandSender commandSender, final String[] args) {

        if (!(commandSender instanceof EntityPlayer)) {
            commandSender.sendChatToPlayer(new ChatMessageComponent().func_111079_a("Must be a player to issue this command!"));
        }

        if (args.length < 1) {
            commandSender.sendChatToPlayer(new ChatMessageComponent().func_111079_a("Usage: " + this.getCommandUsage(commandSender)));
            return;
        }
        final String command = args[0];
        final EntityPlayer player = (EntityPlayer) commandSender;
        if (command.equalsIgnoreCase("setAt")) {
            CommandSetAt.handle(player, args);
        } else if (command.equalsIgnoreCase("getAt")) {
            CommandGetAt.handle(player, args);
        } else if (command.equalsIgnoreCase("killAll")) {
            CommandKillAll.handle(player, args);
        } else {
            player.sendChatToPlayer(new ChatMessageComponent().func_111079_a("Usage: " + this.getCommandUsage(commandSender)));
        }
    }

}
