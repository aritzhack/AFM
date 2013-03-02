package afm.core.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;

/**
 * UtilNBT
 *
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class UtilNBT {

	public static void addDescriptionToStack(ItemStack is, String description) {
		NBTTagCompound stackTag = UtilNBT.getISTagCompound(is);

		if (!stackTag.hasKey("display")) {
			stackTag.setCompoundTag("display", new NBTTagCompound());
		}

		NBTTagCompound display = stackTag.getCompoundTag("display");
		if (!display.hasKey("Lore")) {
			display.setTag("Lore", new NBTTagList());
		}
		display.getTagList("Lore").appendTag(new NBTTagString("AFMDisplayTag", "\u00a7r" + description));
	}

	public static NBTTagCompound getISTagCompound(ItemStack is) {
		if (!is.hasTagCompound()) {
			is.stackTagCompound = new NBTTagCompound();
		}
		return is.getTagCompound();
	}

}
