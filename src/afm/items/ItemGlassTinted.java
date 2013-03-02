package afm.items;

import afm.core.util.UtilAFM;
import afm.data.BlockData;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * ItemGlassTinted
 *
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class ItemGlassTinted extends ItemBlock {

	public ItemGlassTinted(int par1) {
		super(par1);
		this.setHasSubtypes(true);
		this.setItemName(BlockData.NAME_TINTEDGLASS);
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
