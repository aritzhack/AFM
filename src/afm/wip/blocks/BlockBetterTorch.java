package afm.wip.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockTorch;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import afm.core.AFM;
import afm.core.AFMLogger;
import afm.data.BlockData;
import afm.data.RenderingData;

public class BlockBetterTorch extends BlockTorch {
	
	Icon icon;

	public BlockBetterTorch() {
		super(BlockData.ID_BETTER_TORCH);
		this.setUnlocalizedName(BlockData.NAME_BETTER_TORCH);
		this.setCreativeTab(AFM.tabAFM);
		this.setLightValue(0.9375F);
	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z,
			int side, float hitX, float hitY, float hitZ, int meta) {
		AFMLogger.debug("Side: " + side);
		return super.onBlockPlaced(world, x, y, z, side, hitX, hitY, hitZ,
				meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTextureFromSideAndMetadata(int par1, int par2) {
		return icon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register) {
		icon = register.registerIcon(String.format("afm:%s", BlockData.NAME_BETTER_TORCH));
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return super.canPlaceBlockAt(world, x, y, z) ||
				world.isBlockSolidOnSide(x, y+1, z, ForgeDirection.UP, true);
	}

	@Override
	public int getRenderType() {
		return RenderingData.RENDER_ID_BETTER_TORCH;
	}
	
	

}
