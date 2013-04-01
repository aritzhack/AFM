package afm.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import afm.data.BlockData;
import afm.tileEntity.TEPortableChest;

/**
 * ItemPortableChest
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemPortableChest extends ItemBlock {

	public ItemPortableChest(int id) {
		super(id);
		this.setUnlocalizedName(BlockData.NAME_PORTABLE_CHEST);
	}

	@Override
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
		boolean ret = super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);
		if (!ret) return false;
		TileEntity te = world.getBlockTileEntity(x, y, z);
		if (!(te instanceof TEPortableChest)) {
			world.setBlockTileEntity(x, y, z, new TEPortableChest());
			te = world.getBlockTileEntity(x, y, z);
		}
		TEPortableChest tepc = (TEPortableChest) te;
		tepc.setContentFromIS(stack);

		return ret;
	}

}
