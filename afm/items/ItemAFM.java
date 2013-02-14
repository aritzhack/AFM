package afm.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import afm.core.Properties;

public class ItemAFM extends Item {

	public ItemAFM(int id, int indexInTexture, CreativeTabs tab, String name) {
		super(id);
		this.setCreativeTab(tab);
		this.setIconIndex(indexInTexture);
	}

	@Override
	public String getTextureFile() {
		return Properties.Item.TEXTURE;
	}

}
