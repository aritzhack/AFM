package aritzh.afm.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import aritzh.afm.AFM;
import aritzh.afm.data.BlockData;
import aritzh.afm.data.GUIData;
import aritzh.afm.tileEntity.TESharedWorkbench;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * BlockSharedWorkbench
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockSharedWorkbench extends BlockContainerAFM {

    @Override
    @SideOnly(Side.CLIENT)
    // Do nothing because we don't need to register any icon
    public void registerIcons(final IconRegister par1IconRegister) {
    }

    public BlockSharedWorkbench() {
        super(BlockData.ID_SHAREDWORKBENCH, BlockData.NAME_SHARED_WORKBENCH, Material.wood);
    }

    @Override
    public TileEntity createNewTileEntity(final World var1) {
        return new TESharedWorkbench();
    }

    @Override
    public boolean onBlockActivated(final World world, final int x, final int y, final int z, final EntityPlayer player, final int i, final float f, final float g, final float t) {

        final TileEntity te = world.getBlockTileEntity(x, y, z);

        if (te == null || player.isSneaking()) return false;

        player.openGui(AFM.afm, GUIData.ID_SHAREDWORKBENCH, world, x, y, z);
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(final int side, final int metadata) {
        return Block.workbench.getIcon(side, metadata);
    }

}
