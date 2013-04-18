package aritzh.afm.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import aritzh.afm.core.AFMLogger;
import aritzh.afm.data.GUIData;
import aritzh.afm.inventory.ContainerFabricator;
import aritzh.afm.tileEntity.TEFabricator;

/**
 * GUIFabricator
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GUIFabricator extends AFMGUI {

	TEFabricator tileEntity;
	GuiButton clearButton;

	public GUIFabricator(TEFabricator tileEntity, InventoryPlayer inventory, World world) {
		super(new ContainerFabricator(tileEntity, inventory, world), GUIData.BG_FABRICATOR);
		this.tileEntity = tileEntity;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int i, int j) {
		this.fontRenderer.drawString(StatCollector.translateToLocal(this.tileEntity.getInvName()), 6, 6, 0x404040);
		this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 7, 73, 0x404040);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initGui() {
		super.initGui();
		this.buttonList.add(this.clearButton = new GuiButton(1, this.guiLeft + this.xSize / 2 - 20, this.guiTop + this.ySize / 2 - 25, 40, 20, "Clear"));
	}

	@Override
	protected void mouseClicked(int x, int y, int par3) {
		super.mouseClicked(x, y, par3);
		AFMLogger.debug("Pos: (" + x + ", " + y + ")");
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		if (button == this.clearButton) {
			this.tileEntity.clearMatrix();
			AFMLogger.debug("Width: " + this.width + ", Height: " + this.height);
			AFMLogger.debug("XSize: " + this.xSize + ", YSize: " + this.ySize);
			AFMLogger.debug("GuiLeft: " + this.guiLeft + ", GUITop: " + this.guiTop);
		}
		super.actionPerformed(button);
	}

}
