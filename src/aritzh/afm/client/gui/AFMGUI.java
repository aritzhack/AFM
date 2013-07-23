package aritzh.afm.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

/**
 * AFMGUI
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class AFMGUI extends GuiContainer {

    ResourceLocation backgroundTexture = null;

    protected AFMGUI(final Container par1Container, final ResourceLocation background) {
        super(par1Container);
        this.backgroundTexture = background;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(final float var1, final int var2, final int var3) {

        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.renderEngine.func_110577_a(this.backgroundTexture);
        final int x = (this.width - this.xSize) / 2;
        final int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
    }

}
