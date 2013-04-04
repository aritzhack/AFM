package afm.wip.blocks;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import afm.blocks.BlockContainerAFM;
import afm.data.BlockData;
import afm.data.RenderingData;
import afm.wip.tileEntity.TEWire;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockWire extends BlockContainerAFM {

	BlockWire() {
		super(BlockData.ID_WIRE, BlockData.NAME_WIRE);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TEWire();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {

	}

	@Override
	public int getRenderType() {
		return RenderingData.RENDER_ID_WIRE;
	}

}
