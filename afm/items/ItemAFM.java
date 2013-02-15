package afm.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import afm.data.ItemData;

public class ItemAFM extends Item {

	public ItemAFM(int id, String itemName, int indexInTexture, CreativeTabs tab) {
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
