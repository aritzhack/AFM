package afm.command;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

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
	public String getCommandUsage(ICommandSender par1iCommandSender) {
		return "/afm [setAt | getAt | killAll]";
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender commandSender) {
		// Can just be used by people with ban-permission (OPs).
		// This could be improved letting people handle permission per
		// sub-command,
		// Not having to make different commands for different permissions
		return commandSender.canCommandSenderUseCommand(3, "ban");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> addTabCompletionOptions(ICommandSender par1iCommandSender, String[] args) {

		if (args.length > 0) {
			if (args[0].equalsIgnoreCase("setAt"))
				return CommandSetAt.getTabCompletion(par1iCommandSender, args);
			else if (args[0].equalsIgnoreCase("getAt"))
				return CommandGetAt.getTabCompletion(par1iCommandSender, args);
			else if (args[0].equalsIgnoreCase("killAll")) return CommandGetAt.getTabCompletion(par1iCommandSender, args);
		}
		return CommandBase.getListOfStringsMatchingLastWord(args, "setAt", "getAt", "killAll");
	}

	@Override
	public void processCommand(ICommandSender commandSender, String[] args) {

		if (args.length < 1) {
			commandSender.sendChatToPlayer("Usage: " + this.getCommandUsage(commandSender));
			return;
		}
		String command = args[0];
		if (command.equalsIgnoreCase("setAt")) {
			CommandSetAt.handle(commandSender, args);
		} else if (command.equalsIgnoreCase("getAt")) {
			CommandGetAt.handle(commandSender, args);
		} else if (command.equalsIgnoreCase("killAll")) {
			CommandKillAll.handle(commandSender, args);
		} else {
			commandSender.sendChatToPlayer("Usage: " + this.getCommandUsage(commandSender));
		}
	}

}
