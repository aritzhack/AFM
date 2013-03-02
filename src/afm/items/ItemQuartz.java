package afm.items;

import afm.core.AFM;
import afm.core.util.UtilNBT;
import afm.data.ItemData;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemQuartz extends ItemAFM {

	public ItemQuartz() {
		super(ItemData.ID_QUARTZ, ItemData.NAME_QUARTZ, ItemData.TEXTUREINDEX_QUARTZ, AFM.tabAFM);
		this.addRecipe();
	}

	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		AFM.proxy.writeChatMessageToPlayer(String.format("Block hit at: X:%f, Y:%f, Z:%f", hitX, hitY, hitZ));
		return false;
	}

	private void addRecipe() {
		ItemStack out = new ItemStack(this);

		UtilNBT.addDescriptionToStack(out, "Funciona!!");

		GameRegistry
				.addShapelessRecipe(out, new ItemStack(Item.diamond), new ItemStack(Item.diamond), new ItemStack(Item.diamond), new ItemStack(Item.diamond));
	}

}
