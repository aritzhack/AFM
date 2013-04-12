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
import afm.tileEntity.TEFabricator;
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
	 * ejects contained items into the world, and notifies neighbours of an update, as appropriate
	 */
	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if (tileEntity == null || !(tileEntity instanceof TEFabricator)) return;
		((TEFabricator) tileEntity).dropItems();
		super.breakBlock(world, x, y, z, par5, par6); // To change body of overridden methods use
														// File | Settings | File Templates.
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float f, float g, float t) {

		TileEntity te = world.getBlockTileEntity(x, y, z);

		if (te == null || !(te instanceof TEFabricator) || player.isSneaking()) return false;
		if (world.isRemote) return true;

		player.openGui(AFM.afm, GUIData.ID_FABRICATOR, world, x, y, z);
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TEFabricator();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int metadata) {
		return Block.workbench.getIcon(side, metadata);
	}

	@Override
	@SideOnly(Side.CLIENT)
	// Do nothing, no need to register any icon
	public void registerIcons(IconRegister par1IconRegister) {
	}

}
