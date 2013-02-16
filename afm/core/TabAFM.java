package afm.core;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import afm.blocks.Blocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TabAFM extends CreativeTabs {

	public TabAFM(int par1, String par2Str) {
		super(par1, par2Str);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTranslatedTabLabel() {
		return "Tab AFM";
	}

	@Override
	public ItemStack getIconItemStack() {
		return new ItemStack(Blocks.oreAFM);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void displayAllReleventItems(List list) {
		super.displayAllReleventItems(list);
		// Here are added the items that have some special properties

		// ItemStack i1 = new ItemStack(Blocks.tintedGlass, 1, 0);
		// UtilAFM.addDescriptionToStack(i1, i1.getItemName());
		// list.add(i1);
	}

}
