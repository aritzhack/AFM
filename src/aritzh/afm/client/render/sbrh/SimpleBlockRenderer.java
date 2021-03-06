package aritzh.afm.client.render.sbrh;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import aritzh.afm.data.RenderingData;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

/**
 * SimpleBlockRenderer
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class SimpleBlockRenderer implements ISimpleBlockRenderingHandler {

    @Override
    public void renderInventoryBlock(final Block block, final int metadata, final int modelID, final RenderBlocks renderer) {
    }

    @Override
    public boolean renderWorldBlock(final IBlockAccess world, final int x, final int y, final int z, final Block block, final int modelId, final RenderBlocks renderer) {
        if (modelId == RenderingData.RENDER_ID_BETTER_TORCH) {
            return this.renderBetterTorch(world, x, y, z, block, renderer);
        }

        return false;
    }

    @Override
    public boolean shouldRender3DInInventory() {
        return false;
        // Shouldn't this depend on the modelID?
    }

    @Override
    public int getRenderId() {
        return 0;
    }

    private boolean renderBetterTorch(final IBlockAccess world, final int x, final int y, final int z, final Block block, final RenderBlocks renderer) {

        final int meta = world.getBlockMetadata(x, y, z);
        if (meta != 0) return renderer.renderBlockTorch(block, x, y, z);
        double par2 = x;
        double par6 = z;

        final Tessellator tessellator = Tessellator.instance;
        final Icon icon = block.getBlockTexture(world, x, y, z, 0);

        // icon = icon != null ? icon :
        // Minecraft.getMinecraft().renderEngine.func_110581_b(par1ResourceLocation)

        final double texMinX = icon.getMinU();
        final double texMinY = icon.getMinV();
        final double texMaxX = icon.getMaxU();
        final double texMaxY = icon.getMaxV();
        final double d9 = icon.getInterpolatedU(7.0D);
        final double d10 = icon.getInterpolatedV(6.0D);
        final double d11 = icon.getInterpolatedU(9.0D);
        final double d12 = icon.getInterpolatedV(8.0D);
        final double d13 = icon.getInterpolatedU(7.0D);
        final double d14 = icon.getInterpolatedV(13.0D);
        final double d15 = icon.getInterpolatedU(9.0D);
        final double d16 = icon.getInterpolatedV(15.0D);
        par2 += 0.5D;
        par6 += 0.5D;
        final double d17 = par2 - 0.5D;
        final double d18 = par2 + 0.5D;
        final double d19 = par6 - 0.5D;
        final double d20 = par6 + 0.5D;
        final double d21 = 0.0625D;

        // Top (fire)
        tessellator.addVertexWithUV(par2 + d21, y + 0.375D, par6 - d21, d11, d10);
        tessellator.addVertexWithUV(par2 + d21, y + 0.375D, par6 + d21, d11, d12);
        tessellator.addVertexWithUV(par2 - d21, y + 0.375D, par6 + d21, d9, d12);
        tessellator.addVertexWithUV(par2 - d21, y + 0.375D, par6 - d21, d9, d10);

        // Bottom
        tessellator.addVertexWithUV(par2 - d21, y + 1D, par6 - d21, d13, d14);
        tessellator.addVertexWithUV(par2 - d21, y + 1D, par6 + d21, d13, d16);
        tessellator.addVertexWithUV(par2 + d21, y + 1D, par6 + d21, d15, d16);
        tessellator.addVertexWithUV(par2 + d21, y + 1D, par6 - d21, d15, d14);

        tessellator.addVertexWithUV(par2 - d21, y + 1.0D, d19, texMaxX, texMaxY);
        tessellator.addVertexWithUV(par2 - d21, y, d19, texMaxX, texMinY);
        tessellator.addVertexWithUV(par2 - d21, y, d20, texMinX, texMinY);
        tessellator.addVertexWithUV(par2 - d21, y + 1.0D, d20, texMinX, texMaxY);

        tessellator.addVertexWithUV(par2 + d21, y + 1.0D, d20, texMaxX, texMaxY);
        tessellator.addVertexWithUV(par2 + d21, y, d20, texMaxX, texMinY);
        tessellator.addVertexWithUV(par2 + d21, y, d19, texMinX, texMinY);
        tessellator.addVertexWithUV(par2 + d21, y + 1.0D, d19, texMinX, texMaxY);

        tessellator.addVertexWithUV(d17, y + 1.0D, par6 + d21, texMaxX, texMaxY);
        tessellator.addVertexWithUV(d17, y, par6 + d21, texMaxX, texMinY);
        tessellator.addVertexWithUV(d18, y, par6 + d21, texMinX, texMinY);
        tessellator.addVertexWithUV(d18, y + 1.0D, par6 + d21, texMinX, texMaxY);

        tessellator.addVertexWithUV(d18, y + 1.0D, par6 - d21, texMaxX, texMaxY);
        tessellator.addVertexWithUV(d18, y, par6 - d21, texMaxX, texMinY);
        tessellator.addVertexWithUV(d17, y, par6 - d21, texMinX, texMinY);
        tessellator.addVertexWithUV(d17, y + 1.0D, par6 - d21, texMinX, texMaxY);
        return true;
    }
}


















