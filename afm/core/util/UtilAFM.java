package afm.core.util;

import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.common.FMLCommonHandler;

public final class UtilAFM {

	public static EntityPlayerMP getPlayer(String username) {
		MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
		if (server == null)
			return null;
		EntityPlayerMP ret = server.getConfigurationManager().getPlayerForUsername(username);
		if (ret == null)
			throw new PlayerNotFoundException();
		return ret;
	}

	public static final String[] colorNames = { "White", "Orange", "Magenta", "Light Blue", "Yellow", "Light Green", "Pink", "Dark Grey", "Light Grey", "Cyan",
		"Purple", "Blue", "Brown", "Green", "Red", "Black" };

}
