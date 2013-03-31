package afm.wip.blocks;

import java.util.Random;

import net.minecraft.block.BlockTorch;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import afm.core.AFM;
import afm.data.BlockData;
import afm.data.RenderingData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBetterTorch extends BlockTorch {
	
	Icon icon;

	public BlockBetterTorch() {
		super(BlockData.ID_BETTER_TORCH);
		this.setUnlocalizedName(BlockData.NAME_BETTER_TORCH);
		this.setCreativeTab(AFM.tabAFM);
		this.setLightValue(0.9375F);
	}

	@Override
	public void updateTick(World world, int x, int y, int z,
			Random par5Random) {
		if(world.getBlockMetadata(x, y, z) == 0){
			world.setBlockMetadataWithNotify(x, y, z, 6, 2);
		}
		super.updateTick(world, x, y, z, par5Random);
		this.dropTorchIfCantStay(world, x, y, z);
		if(world.getBlockMetadata(x, y, z) == 6){
			world.setBlockMetadataWithNotify(x, y, z, 0, 2);
		}
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world,
			int x, int y, int z) {
		if(world.getBlockMetadata(x, y, z) == 0){
            float f = 0.1F;
            this.setBlockBounds(0.5F - f, 0.4F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
		}
	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z,
			int side, float hitX, float hitY, float hitZ, int meta) {
		return super.onBlockPlaced(world, x, y, z, side, hitX, hitY, hitZ,
				meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y,
			int z, Random rand) {
		int meta = world.getBlockMetadata(x, y, z);
		if(meta!=0){
			super.randomDisplayTick(world, x, y, z, rand);
			return;
		}
		
        double px = ((double)x + 0.5D);
        double py = ((double)y + 0.4D);
        double pz = ((double)z + 0.5D);
        
        world.spawnParticle("smoke", px, py, pz, 0.0D, 0.0D, 0.0D);
        world.spawnParticle("flame", px, py, pz, 0.0D, 0.0D, 0.0D);
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
				world.isBlockSolidOnSide(x, y+1, z, ForgeDirection.DOWN, true);
	}

	@Override
	public int getRenderType() {
		return RenderingData.RENDER_ID_BETTER_TORCH;
	}
	
	

}
