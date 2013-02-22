package afm.blocks;

import afm.core.AFM;
import afm.core.AFMLogger;
import afm.data.BlockData;
import afm.tileEntity.TEPortableChest;
import net.minecraft.block.BlockChest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.Random;

public class BlockPortableChest extends BlockChest {
    final Random random = new Random();


    @Override
    public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
        TEPortableChest te = (TEPortableChest) world.getBlockTileEntity(x, y, z);
        ItemStack ret = te.crateISFromContent();

        float dX = this.random.nextFloat() * 0.8F + 0.1F;
        float dY = this.random.nextFloat() * 0.8F + 0.1F;

        EntityItem eItem = new EntityItem(world, x + dX, y + dY, z, ret);
        eItem.getEntityItem().setTagCompound(ret.getTagCompound());
        float var15 = 0.05F;
        AFMLogger.log("Spawned chest drop?");
        eItem.motionX = (float) this.random.nextGaussian() * var15;
        eItem.motionY = (float) this.random.nextGaussian() * var15 + 0.2F;
        eItem.motionZ = (float) this.random.nextGaussian() * var15;
        world.spawnEntityInWorld(eItem);
    }

    public BlockPortableChest() {
        super(BlockData.ID_PORTABLE_CHEST);
        this.setCreativeTab(AFM.tabAFM);
    }

    @Override
    public TileEntity createNewTileEntity(World var1) {
        return new TEPortableChest();
    }

}
