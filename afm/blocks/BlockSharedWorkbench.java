package afm.blocks;

import afm.core.AFM;
import afm.data.BlockData;
import afm.data.GUIData;
import afm.tileEntity.TESharedWorkbench;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSharedWorkbench extends BlockContainerAFM {

	public BlockSharedWorkbench() {
		super(BlockData.ID_SHAREDWORKBENCH, BlockData.NAME_SHARED_WORKBENCH, Block.workbench.blockIndexInTexture, Material.wood);
	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TESharedWorkbench();
	}

	@Override
	public String getTextureFile() {
		return Block.workbench.getTextureFile();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float f, float g, float t) {

		TileEntity te = world.getBlockTileEntity(x, y, z);

		if (te == null || player.isSneaking())
			return false;

		player.openGui(AFM.afm, GUIData.ID_SHAREDWORKBENCH, world, x, y, z);
		return true;
	}

	@Override
	public int getBlockTextureFromSide(int side) {
		return Block.workbench.getBlockTextureFromSide(side);
	}

}
