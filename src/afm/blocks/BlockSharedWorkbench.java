package afm.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import afm.AFM;
import afm.data.BlockData;
import afm.data.GUIData;
import afm.tileEntity.TESharedWorkbench;
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
	public void registerIcons(IconRegister par1IconRegister) {
	}

	public BlockSharedWorkbench() {
		super(BlockData.ID_SHAREDWORKBENCH, BlockData.NAME_SHARED_WORKBENCH, Material.wood);
	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TESharedWorkbench();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float f, float g, float t) {

		TileEntity te = world.getBlockTileEntity(x, y, z);

		if (te == null || player.isSneaking()) return false;

		player.openGui(AFM.afm, GUIData.ID_SHAREDWORKBENCH, world, x, y, z);
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int metadata) {
		return Block.workbench.getIcon(side, metadata);
	}

}
