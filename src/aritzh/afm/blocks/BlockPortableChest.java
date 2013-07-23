package aritzh.afm.blocks;

import java.util.Random;

import net.minecraft.block.BlockChest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import aritzh.afm.AFM;
import aritzh.afm.data.BlockData;
import aritzh.afm.tileEntity.TEPortableChest;
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

    public BlockPortableChest() {
        super(BlockData.ID_PORTABLE_CHEST, 0); // 0 means not redstone when open AFAIK
        this.setUnlocalizedName(BlockData.NAME_PORTABLE_CHEST);
        this.setCreativeTab(AFM.tabAFM);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getBlockTexture(final IBlockAccess blockAccess, final int x, final int y, final int z, final int side) {
        return super.getBlockTexture(blockAccess, x, y, z, side);
    }

    @Override
    public void breakBlock(final World world, final int x, final int y, final int z, final int par5, final int par6) {
        final TEPortableChest te = (TEPortableChest) world.getBlockTileEntity(x, y, z);
        final ItemStack ret = te.crateISFromContent();

        final float dX = this.random.nextFloat() * 0.8F + 0.1F;
        final float dY = this.random.nextFloat() * 0.8F + 0.1F;

        final EntityItem eItem = new EntityItem(world, x + dX, y + dY, z, ret);
        eItem.getEntityItem().setTagCompound(ret.getTagCompound());
        final float var15 = 0.05F;

        eItem.motionX = (float) this.random.nextGaussian() * var15;
        eItem.motionY = (float) this.random.nextGaussian() * var15 + 0.2F;
        eItem.motionZ = (float) this.random.nextGaussian() * var15;
        world.spawnEntityInWorld(eItem);
    }

    @Override
    public TileEntity createNewTileEntity(final World var1) {
        return new TEPortableChest();
    }

}
