package afm.wip.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import afm.blocks.BlockContainerAFM;
import afm.data.BlockData;
import afm.wip.tileEntity.TEAFMTank;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTank extends BlockContainerAFM {
	
	

	@Override
	public boolean onBlockActivated(World world, int x, int y,
			int z, EntityPlayer player, int par6, float bx,
			float by, float bz) {
		TileEntity te = world.getBlockTileEntity(x, y, z);
		if (te == null || !(te instanceof TEAFMTank) || player.isSneaking() || !LiquidContainerRegistry.isContainer(player.inventory.getCurrentItem()))
			return false;
		TEAFMTank tank = (TEAFMTank) te;
		tank.clicked(player);
		return true;
	}

	public BlockTank() {
		super(BlockData.ID_TANK, BlockData.NAME_TANK, Material.glass);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		iconRegister.registerIcon(String.format("afm:%s", BlockData.NAME_TANK));
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TEAFMTank();
	}

}
