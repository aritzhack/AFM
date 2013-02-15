package afm.core;

import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
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

	public static void addDescriptionToStack(ItemStack is, String description) {
		if (!is.hasTagCompound()) {
			is.stackTagCompound = new NBTTagCompound();
		}

		if (!is.stackTagCompound.hasKey("display")) {
			is.stackTagCompound.setCompoundTag("display", new NBTTagCompound());
		}

		if (!is.stackTagCompound.hasKey("display")) {
			is.stackTagCompound.setCompoundTag("display", new NBTTagCompound());
		}
		NBTTagCompound display = is.stackTagCompound.getCompoundTag("display");
		if (!display.hasKey("Lore")) {
			display.setTag("Lore", new NBTTagList());
		}
		display.getTagList("Lore").appendTag(new NBTTagString("AFMDisplayTag", "\u00a7r" + description));
	}

	public static final String[] colorNames = { "White", "Orange", "Magenta", "Light Blue", "Yellow", "Light Green", "Pink", "Dark Grey", "Light Grey", "Cyan",
		"Purple", "Blue", "Brown", "Green", "Red", "Black" };

}
