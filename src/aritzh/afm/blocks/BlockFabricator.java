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
import aritzh.afm.tileEntity.TEFabricator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * BlockFabricator
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockFabricator extends BlockContainerAFM {

    public BlockFabricator() {
        super(BlockData.ID_FABRICATOR, BlockData.NAME_FABRICATOR, Material.wood);
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as
     * appropriate
     */
    @Override
    public void breakBlock(final World world, final int x, final int y, final int z, final int par5, final int par6) {
        final TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
        if (tileEntity == null || !(tileEntity instanceof TEFabricator)) return;
        ((TEFabricator) tileEntity).dropItems();
        super.breakBlock(world, x, y, z, par5, par6); // To change body of overridden
                                                      // methods use
                                                      // File | Settings | File Templates.
    }

    @Override
    public boolean onBlockActivated(final World world, final int x, final int y, final int z, final EntityPlayer player, final int i, final float f, final float g, final float t) {

        final TileEntity te = world.getBlockTileEntity(x, y, z);

        if (te == null || !(te instanceof TEFabricator) || player.isSneaking()) return false;
        if (world.isRemote) return true;

        player.openGui(AFM.afm, GUIData.ID_FABRICATOR, world, x, y, z);
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(final World var1) {
        return new TEFabricator();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(final int side, final int metadata) {
        return Block.workbench.getIcon(side, metadata);
    }

    @Override
    @SideOnly(Side.CLIENT)
    // Do nothing, no need to register any icon
    public void registerIcons(final IconRegister par1IconRegister) {
    }

}
