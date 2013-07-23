package aritzh.afm.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import aritzh.afm.data.BlockData;
import aritzh.afm.tileEntity.TEPortableChest;

/**
 * ItemPortableChest
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemPortableChest extends ItemBlock {

    public ItemPortableChest(final int id) {
        super(id);
        this.setUnlocalizedName(BlockData.NAME_PORTABLE_CHEST);
    }

    @Override
    public boolean placeBlockAt(final ItemStack stack, final EntityPlayer player, final World world, final int x, final int y, final int z, final int side, final float hitX, final float hitY, final float hitZ, final int metadata) {
        final boolean ret = super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);
        if (!ret) return false;
        TileEntity te = world.getBlockTileEntity(x, y, z);
        if (!(te instanceof TEPortableChest)) {
            world.setBlockTileEntity(x, y, z, new TEPortableChest());
            te = world.getBlockTileEntity(x, y, z);
        }
        final TEPortableChest tepc = (TEPortableChest) te;
        tepc.setContentFromIS(stack);

        return ret;
    }

}
