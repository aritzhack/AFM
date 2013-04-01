package afm.client.render.sbrh;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import afm.data.RenderingData;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

/**
 * SimpleBlockRenderer
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class SimpleBlockRenderer implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		if (modelId == RenderingData.RENDER_ID_BETTER_TORCH) {
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

	private boolean renderBetterTorch(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {

		int meta = world.getBlockMetadata(x, y, z);
		if (meta != 0) return renderer.renderBlockTorch(block, x, y, z);
		double par2 = x, par4 = y, par6 = z;

		Tessellator tessellator = Tessellator.instance;
		Icon icon = block.getBlockTextureFromSideAndMetadata(0, 0);

		icon = icon != null ? icon : Minecraft.getMinecraft().renderEngine.getMissingIcon(0);

		double texMinX = icon.getMinU();
		double texMinY = icon.getMinV();
		double texMaxX = icon.getMaxU();
		double texMaxY = icon.getMaxV();
		double d9 = icon.getInterpolatedU(7.0D);
		double d10 = icon.getInterpolatedV(6.0D);
		double d11 = icon.getInterpolatedU(9.0D);
		double d12 = icon.getInterpolatedV(8.0D);
		double d13 = icon.getInterpolatedU(7.0D);
		double d14 = icon.getInterpolatedV(13.0D);
		double d15 = icon.getInterpolatedU(9.0D);
		double d16 = icon.getInterpolatedV(15.0D);
		par2 += 0.5D;
		par6 += 0.5D;
		double d17 = par2 - 0.5D;
		double d18 = par2 + 0.5D;
		double d19 = par6 - 0.5D;
		double d20 = par6 + 0.5D;
		double d21 = 0.0625D;

		// Top (fire)
		tessellator.addVertexWithUV(par2 + d21, par4 + 0.375D, par6 - d21, d11, d10);
		tessellator.addVertexWithUV(par2 + d21, par4 + 0.375D, par6 + d21, d11, d12);
		tessellator.addVertexWithUV(par2 - d21, par4 + 0.375D, par6 + d21, d9, d12);
		tessellator.addVertexWithUV(par2 - d21, par4 + 0.375D, par6 - d21, d9, d10);

		// Bottom
		tessellator.addVertexWithUV(par2 - d21, par4 + 1D, par6 - d21, d13, d14);
		tessellator.addVertexWithUV(par2 - d21, par4 + 1D, par6 + d21, d13, d16);
		tessellator.addVertexWithUV(par2 + d21, par4 + 1D, par6 + d21, d15, d16);
		tessellator.addVertexWithUV(par2 + d21, par4 + 1D, par6 - d21, d15, d14);

		tessellator.addVertexWithUV(par2 - d21, par4 + 1.0D, d19, texMaxX, texMaxY);
		tessellator.addVertexWithUV(par2 - d21, par4, d19, texMaxX, texMinY);
		tessellator.addVertexWithUV(par2 - d21, par4, d20, texMinX, texMinY);
		tessellator.addVertexWithUV(par2 - d21, par4 + 1.0D, d20, texMinX, texMaxY);

		tessellator.addVertexWithUV(par2 + d21, par4 + 1.0D, d20, texMaxX, texMaxY);
		tessellator.addVertexWithUV(par2 + d21, par4, d20, texMaxX, texMinY);
		tessellator.addVertexWithUV(par2 + d21, par4, d19, texMinX, texMinY);
		tessellator.addVertexWithUV(par2 + d21, par4 + 1.0D, d19, texMinX, texMaxY);

		tessellator.addVertexWithUV(d17, par4 + 1.0D, par6 + d21, texMaxX, texMaxY);
		tessellator.addVertexWithUV(d17, par4, par6 + d21, texMaxX, texMinY);
		tessellator.addVertexWithUV(d18, par4, par6 + d21, texMinX, texMinY);
		tessellator.addVertexWithUV(d18, par4 + 1.0D, par6 + d21, texMinX, texMaxY);

		tessellator.addVertexWithUV(d18, par4 + 1.0D, par6 - d21, texMaxX, texMaxY);
		tessellator.addVertexWithUV(d18, par4, par6 - d21, texMaxX, texMinY);
		tessellator.addVertexWithUV(d17, par4, par6 - d21, texMinX, texMinY);
		tessellator.addVertexWithUV(d17, par4 + 1.0D, par6 - d21, texMinX, texMaxY);
		return true;
	}

}
