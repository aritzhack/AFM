package afm.client.render.sbrh;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import afm.core.AFMLogger;
import afm.data.RenderingData;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class SimpleBlockRenderer implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		if(modelId == RenderingData.RENDER_ID_BETTER_TORCH){
			this.renderBetterTorch(world, x, y, z, block, modelId, renderer);
		}
		
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return false;
	}

	@Override
	public int getRenderId() {
		return 0;
	}
	
	private boolean renderBetterTorch(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer){
		AFMLogger.debug("Rendering using my renderer!");
		
		int meta = world.getBlockMetadata(x, y, z);
		if(meta!=0)	return renderer.renderBlockTorch(block, x, y, z);
		else {
			renderer.renderTorchAtAngle(block, (double)x + .5D, (double)y + .5D, (double)z + .5D, .5D, 0.0D, 0);
			return true;
		}
	}

}
