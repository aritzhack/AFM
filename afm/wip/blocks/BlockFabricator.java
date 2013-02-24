package afm.wip.blocks;

import afm.blocks.BlockContainerAFM;
import afm.core.AFM;
import afm.data.BlockData;
import afm.data.GUIData;
import afm.wip.tileEntity.TEFabricator;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockFabricator extends BlockContainerAFM {

    public BlockFabricator() {
        super(BlockData.ID_FABRICATOR, BlockData.NAME_FABRICATOR, Material.wood);
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    @Override
    public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
        if (tileEntity == null || !(tileEntity instanceof TEFabricator)) return;
        ((TEFabricator) tileEntity).dropItems();
        super.breakBlock(world, x, y, z, par5, par6);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float f, float g, float t) {

        TileEntity te = world.getBlockTileEntity(x, y, z);

        if (te == null || !(te instanceof TEFabricator) || player.isSneaking())
            return false;
        if (world.isRemote) return true;

        player.openGui(AFM.afm, GUIData.ID_FABRICATOR, world, x, y, z);
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World var1) {
        return new TEFabricator();
    }

    @Override
    public int getBlockTextureFromSide(int side) {
        return Block.workbench.getBlockTextureFromSide(side);
    }

    @Override
    public String getTextureFile() {
        return Block.workbench.getTextureFile();
    }

}
