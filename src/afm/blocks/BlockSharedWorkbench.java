package afm.blocks;

import afm.core.AFM;
import afm.data.BlockData;
import afm.data.GUIData;
import afm.tileEntity.TESharedWorkbench;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

/**
 * BlockSharedWorkbench
 *
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class BlockSharedWorkbench extends BlockContainerAFM {

	public BlockSharedWorkbench() {
		super(BlockData.ID_SHAREDWORKBENCH, BlockData.NAME_SHARED_WORKBENCH, Material.wood, TextureType.NONE);
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
	public Icon getBlockTextureFromSideAndMetadata(int side, int meta) {
		return Block.workbench.getBlockTextureFromSideAndMetadata(side, meta);
	}

}
