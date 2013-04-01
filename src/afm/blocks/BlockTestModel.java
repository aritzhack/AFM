package afm.blocks;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import afm.data.BlockData;
import afm.tileEntity.TETestModel;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * BlockTestModel
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockTestModel extends BlockContainerAFM {

	public BlockTestModel() {
		super(BlockData.ID_TESTMODEL, BlockData.NAME_TESTMODEL);
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TETestModel();
	}

	@Override
	@SideOnly(Side.CLIENT)
	// No need to register any icon
	public void registerIcons(IconRegister par1IconRegister) {
	}
}
