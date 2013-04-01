package afm.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.Icon;
import afm.data.BlockData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * ItemTestModel
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemTestModel extends ItemBlock {

	public ItemTestModel(int par1) {
		super(par1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateIcons(IconRegister iconRegister) {
		this.iconIndex = iconRegister.registerIcon("afm:" + BlockData.NAME_TESTMODEL + "_item");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int par1) {
		return this.iconIndex;
	}

}
