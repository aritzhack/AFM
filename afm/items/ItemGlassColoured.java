package afm.items;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import afm.core.util.UtilAFM;
import afm.data.BlockData;

public class ItemGlassColoured extends ItemBlock {

	public ItemGlassColoured(int par1) {
		super(par1);
		this.setHasSubtypes(true);
		this.setItemName(BlockData.NAME_COLOUREDGLASS);
	}

	@Override
	public String getItemNameIS(ItemStack itemStack) {
		return this.getItemName() + "." + UtilAFM.colorNames[itemStack.getItemDamage()].toLowerCase().replace(" ", "");
	}

	@Override
	public int getMetadata(int damageValue) {
		return damageValue;
	}

}
