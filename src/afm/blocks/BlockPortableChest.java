package afm.blocks;

import java.util.Random;

import net.minecraft.block.BlockChest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import afm.core.AFM;
import afm.data.BlockData;
import afm.tileEntity.TEPortableChest;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * BlockPortableChest
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockPortableChest extends BlockChest {
	final Random random = new Random();
	Icon icon;

	public BlockPortableChest() {
		super(BlockData.ID_PORTABLE_CHEST, 0); // 0 means not redstone when open AFAIK
		this.setUnlocalizedName(BlockData.NAME_PORTABLE_CHEST);
		this.setCreativeTab(AFM.tabAFM);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTextureFromSideAndMetadata(int side, int meta) {
		return this.icon;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
		TEPortableChest te = (TEPortableChest) world.getBlockTileEntity(x, y, z);
		ItemStack ret = te.crateISFromContent();

		float dX = this.random.nextFloat() * 0.8F + 0.1F;
		float dY = this.random.nextFloat() * 0.8F + 0.1F;

		EntityItem eItem = new EntityItem(world, x + dX, y + dY, z, ret);
		eItem.getEntityItem().setTagCompound(ret.getTagCompound());
		float var15 = 0.05F;

		eItem.motionX = (float) this.random.nextGaussian() * var15;
		eItem.motionY = (float) this.random.nextGaussian() * var15 + 0.2F;
		eItem.motionZ = (float) this.random.nextGaussian() * var15;
		world.spawnEntityInWorld(eItem);
	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TEPortableChest();
	}

}
