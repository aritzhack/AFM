package afm.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import afm.core.AFM;
import afm.core.Properties;
import afm.core.UtilAFM;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ItemQuartz extends ItemAFM {

	public ItemQuartz() {
		super(Properties.Item.ID_QUARTZ, Properties.Item.TEXTUREINDEX_QUARTZ,
				AFM.tabAFM, "quartz");
		setItemName("quartz");
		addRecipe();
	}

	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer player, World world,
			int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		AFM.proxy.writeChatMessageToPlayer(String.format(
				"Block hit at: X:%f, Y:%f, Z:%f", new Object[] { hitX, hitY,
						hitZ }));
		return false;
	}
	
	private void addRecipe(){
		ItemStack out = new ItemStack(this);
		
		UtilAFM.addDescriptionToStack(out, "Funciona!!");
		
		GameRegistry.addShapelessRecipe(out, new ItemStack(Item.diamond), new ItemStack(Item.diamond), new ItemStack(Item.diamond), new ItemStack(Item.diamond));
	}

}
