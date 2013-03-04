package afm.gui.gui;

import org.lwjgl.opengl.GL11;

import afm.data.GUIData;
import afm.gui.container.ContainerSharedWorkbench;
import afm.tileEntity.TESharedWorkbench;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

/**
 * GUISharedWorkbench
 *
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class GUISharedWorkbench extends AFMGUI {

	TESharedWorkbench tileEntity;

	public GUISharedWorkbench(TESharedWorkbench tileEntity,
							  InventoryPlayer invPlayer, World world) {
		super(new ContainerSharedWorkbench(tileEntity, invPlayer, world),
				GUIData.BG_SHAREDCRAFTING);
		this.tileEntity = tileEntity;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int i, int j) {

		fontRenderer.drawString(StatCollector.translateToLocal("container.crafting"), 6, 6, 0x404040);
		fontRenderer.drawString(
				StatCollector.translateToLocal("container.inventory"), 6, 75,
				0x404040);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2,
			int var3) {
        int var4 = this.mc.renderEngine.getTexture("/gui/crafting.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(var4);
        int var5 = (this.width - this.xSize) / 2;
        int var6 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
	}
	

}
