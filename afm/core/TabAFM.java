package afm.core;

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
		return new ItemStack(Blocks.oreBlock);
	}

}
