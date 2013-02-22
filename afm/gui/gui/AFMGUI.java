package afm.gui.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import org.lwjgl.opengl.GL11;

public abstract class AFMGUI extends GuiContainer {

    String backgroundTexture = null;

    protected AFMGUI(Container par1Container, String background) {
        super(par1Container);
        this.backgroundTexture = background;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2,
                                                   int var3) {
        int picture = mc.renderEngine.getTexture(backgroundTexture);

        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

        this.mc.renderEngine.bindTexture(picture);

        int x = (width - xSize) / 2;

        int y = (height - ySize) / 2;

        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }

}
