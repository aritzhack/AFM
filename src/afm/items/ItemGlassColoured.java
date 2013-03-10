package afm.items;

import afm.core.util.UtilAFM;
import afm.data.BlockData;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * ItemGlassColoured
 *
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class ItemGlassColoured extends ItemBlock {

	public ItemGlassColoured(int par1) {
		super(par1);
		this.setHasSubtypes(true);
		this.setUnlocalizedName(BlockData.NAME_COLOUREDGLASS);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		return this.getUnlocalizedName() + "." + UtilAFM.colorNames[itemStack.getItemDamage()].toLowerCase().replace(" ", "");
	}

	@Override
	public int getMetadata(int damageValue) {
		return damageValue;
	}

}
