package aritzh.afm.blocks;

import java.util.Random;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import aritzh.afm.AFM;
import aritzh.afm.data.BlockData;
import aritzh.afm.data.GUIData;
import aritzh.afm.tileEntity.TETestChest;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * BlockTestChest
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockTestChest extends BlockContainerAFM {

    public BlockTestChest() {
        super(BlockData.ID_TESTCHEST, BlockData.NAME_TESTCHEST);
        this.setCreativeTab(AFM.tabAFM);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(final IconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon(String.format("afm:%s", BlockData.NAME_TESTCHEST));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getBlockTexture(final IBlockAccess blockAccess, final int x, final int y, final int z, final int side) {
        return this.blockIcon;
    }

    @Override
    public boolean onBlockActivated(final World world, final int x, final int y, final int z, final EntityPlayer player, final int i, final float f, final float g, final float t) {
        final TileEntity tile_entity = world.getBlockTileEntity(x, y, z);

        if (tile_entity == null || player.isSneaking()) return false;

        player.openGui(AFM.afm, GUIData.ID_TESTCHEST, world, x, y, z);
        return true;
    }

    @Override
    public void breakBlock(final World world, final int x, final int y, final int z, final int i, final int j) {
        this.dropItems(world, x, y, z);
        super.breakBlock(world, x, y, z, i, j);
    }

    private void dropItems(final World world, final int x, final int y, final int z) {
        final Random rand = new Random();

        final TileEntity tile_entity = world.getBlockTileEntity(x, y, z);

        if (!(tile_entity instanceof IInventory)) return;

        final IInventory inventory = (IInventory) tile_entity;

        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            final ItemStack item = inventory.getStackInSlot(i);

            if (item != null && item.stackSize > 0) {
                final float rx = rand.nextFloat() * 0.6F + 0.1F;
                final float ry = rand.nextFloat() * 0.6F + 0.1F;
                final float rz = rand.nextFloat() * 0.6F + 0.1F;

                final EntityItem entity_item = new EntityItem(world, x + rx, y + ry, z + rz, new ItemStack(item.itemID, item.stackSize, item.getItemDamage()));

                final float factor = 0.5F;

                entity_item.motionX = rand.nextGaussian() * factor;
                entity_item.motionY = rand.nextGaussian() * factor + 0.2F;
                entity_item.motionZ = rand.nextGaussian() * factor;
                world.spawnEntityInWorld(entity_item);
                item.stackSize = 0;
            }
        }
    }

    @Override
    public TileEntity createNewTileEntity(final World var1) {
        return new TETestChest();
    }

}
