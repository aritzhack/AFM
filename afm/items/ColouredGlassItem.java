package afm.items;

import afm.core.Properties;
import afm.core.UtilAFM;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ColouredGlassItem extends ItemBlock {

	public ColouredGlassItem(int par1) {
		super(par1);
		this.setHasSubtypes(true);
		this.setItemName("colouredGlass");
	}

	@Override
	public String getItemNameIS(ItemStack itemStack) {
		return this.getItemName() + "." + UtilAFM.colorNames[itemStack.getItemDamage()].toLowerCase();
	}

	@Override
	public int getMetadata(int damageValue) {
		return damageValue;
	}

}
