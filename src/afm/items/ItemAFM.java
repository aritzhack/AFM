package afm.items;

import afm.data.ItemData;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * ItemAFM
 *
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
class ItemAFM extends Item {

	ItemAFM(int id, String itemName, int indexInTexture, CreativeTabs tab) {
		super(id);
		this.setCreativeTab(tab);
		this.setIconIndex(indexInTexture);
		this.setItemName(itemName);
	}

	public ItemAFM(int id, int indexInTexture, CreativeTabs tab) {
		super(id);
		this.setCreativeTab(tab);
		this.setIconIndex(indexInTexture);
	}

	@Override
	public String getTextureFile() {
		return ItemData.TEXTURE;
	}

}
