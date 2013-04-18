package aritzh.afm.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;

import org.lwjgl.opengl.GL11;

/**
 * AFMGUI
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class AFMGUI extends GuiContainer {

	String backgroundTexture = null;

	protected AFMGUI(Container par1Container, String background) {
		super(par1Container);
		this.backgroundTexture = background;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {

		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.renderEngine.bindTexture(this.backgroundTexture);
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
	}

}
