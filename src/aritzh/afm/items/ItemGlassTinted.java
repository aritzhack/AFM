package aritzh.afm.items;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import aritzh.afm.core.util.UtilAFM;
import aritzh.afm.data.BlockData;

/**
 * ItemGlassTinted
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemGlassTinted extends ItemBlock {

    public ItemGlassTinted(final int par1) {
        super(par1);
        this.setHasSubtypes(true);
        this.setUnlocalizedName(BlockData.NAME_TINTEDGLASS);
    }

    @Override
    public String getUnlocalizedName(final ItemStack itemStack) {
        return this.getUnlocalizedName() + "." + UtilAFM.colorNames[itemStack.getItemDamage()].toLowerCase().replace(" ", "");
    }

    @Override
    public int getMetadata(final int damageValue) {
        return damageValue;
    }

}
