package afm.wip.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import afm.blocks.BlockContainerAFM;
import afm.core.AFM;
import afm.data.BlockData;
import afm.data.GUIData;
import afm.data.Properties;
import afm.wip.tileEntity.TEFabricator;

public class BlockFabricator extends BlockContainerAFM {

	public BlockFabricator() {
		super(BlockData.ID_FABRICATOR, BlockData.NAME_FABRICATOR, Material.wood);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int i, float f, float g, float t) {

		TileEntity te = world.getBlockTileEntity(x, y, z);

		if (te == null || player.isSneaking())
			return false;

		player.openGui(AFM.afm, GUIData.ID_FABRICATOR, world, x, y, z);
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TEFabricator();
	}

	@Override
	public int getBlockTextureFromSide(int side) {
		return workbench.getBlockTextureFromSide(side);
	}

	@Override
	public String getTextureFile() {
		return workbench.getTextureFile();
	}

}
