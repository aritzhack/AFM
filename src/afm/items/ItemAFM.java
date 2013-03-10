package afm.items;

import afm.core.AFM;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * ItemAFM
 *
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
class ItemAFM extends Item {

	ItemAFM(int id, String itemName, CreativeTabs tab) {
		super(id);
		this.setCreativeTab(tab);
		this.setUnlocalizedName(itemName);
	}
	
	ItemAFM(int id, String itemName) {
		this(id, itemName, AFM.tabAFM);
	}
	
    @Override
    @SideOnly(Side.CLIENT)
    public void func_94581_a(IconRegister iconRegister)
    {
        this.iconIndex = iconRegister.func_94245_a("afm:" + this.getUnlocalizedName());
    }

}
