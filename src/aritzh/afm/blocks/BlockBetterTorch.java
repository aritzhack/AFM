package aritzh.afm.blocks;

import java.util.Random;

import net.minecraft.block.BlockTorch;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import aritzh.afm.AFM;
import aritzh.afm.data.BlockData;
import aritzh.afm.data.RenderingData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * BlockBetterTorch
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockBetterTorch extends BlockTorch {

    public BlockBetterTorch() {
        super(BlockData.ID_BETTER_TORCH);
        this.setUnlocalizedName(BlockData.NAME_BETTER_TORCH);
        this.setCreativeTab(AFM.tabAFM);
        this.setLightValue(0.9375F);
    }

    @Override
    public void updateTick(final World world, final int x, final int y, final int z, final Random par5Random) {
        if (world.getBlockMetadata(x, y, z) == 0) {
            world.setBlockMetadataWithNotify(x, y, z, 6, 2);
        }
        super.updateTick(world, x, y, z, par5Random);
        this.dropTorchIfCantStay(world, x, y, z);
        if (world.getBlockMetadata(x, y, z) == 6) {
            world.setBlockMetadataWithNotify(x, y, z, 0, 2);
        }
    }

    @Override
    public void setBlockBoundsBasedOnState(final IBlockAccess world, final int x, final int y, final int z) {
        if (world.getBlockMetadata(x, y, z) == 0) {
            final float f = 0.1F;
            this.setBlockBounds(0.5F - f, 0.4F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
        }
    }

    @Override
    public int onBlockPlaced(final World world, final int x, final int y, final int z, final int side, final float hitX, final float hitY, final float hitZ, final int meta) {
        return super.onBlockPlaced(world, x, y, z, side, hitX, hitY, hitZ, meta);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(final World world, final int x, final int y, final int z, final Random rand) {
        final int meta = world.getBlockMetadata(x, y, z);
        if (meta != 0) {
            super.randomDisplayTick(world, x, y, z, rand);
            return;
        }

        final double px = x + 0.5D;
        final double py = y + 0.4D;
        final double pz = z + 0.5D;

        world.spawnParticle("smoke", px, py, pz, 0.0D, 0.0D, 0.0D);
        world.spawnParticle("flame", px, py, pz, 0.0D, 0.0D, 0.0D);
    }

    @Override
    protected boolean dropTorchIfCantStay(final World world, final int x, final int y, final int z) {
        if (world.getBlockMetadata(x, y, z) != 0) return super.dropTorchIfCantStay(world, x, y, z);
        if (!this.canPlaceBlockAt(world, x, y, x)) {
            if (!world.isBlockSolidOnSide(x, y + 1, z, ForgeDirection.DOWN)) {
                this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
                world.setBlockToAir(x, y, z);
            }

            return false;
        } else return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getBlockTexture(final IBlockAccess par1IBlockAccess, final int par2, final int par3, final int par4, final int par5) {
        return this.blockIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(final IconRegister register) {
        this.blockIcon = register.registerIcon(String.format("afm:%s", BlockData.NAME_BETTER_TORCH));
    }

    @Override
    public boolean canPlaceBlockAt(final World world, final int x, final int y, final int z) {
        return super.canPlaceBlockAt(world, x, y, z) || world.isBlockSolidOnSide(x, y + 1, z, ForgeDirection.DOWN, true);
    }

    @Override
    public int getRenderType() {
        return RenderingData.RENDER_ID_BETTER_TORCH;
    }

}
