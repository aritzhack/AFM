package afm.wip.gui.gui;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import afm.data.GUIData;
import afm.data.Properties;
import afm.gui.gui.AFMGUI;
import afm.wip.gui.container.ContainerFabricator;
import afm.wip.tileEntity.TEFabricator;

public class GUIFabricator extends AFMGUI {

	public GUIFabricator(TEFabricator tileEntity, InventoryPlayer inventory,
							World world) {
		super(new ContainerFabricator(tileEntity, inventory, world),
				GUIData.BG_FABRICATOR);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int i, int j) {

		this.fontRenderer.drawString("Fabricator", 62, 6, 0x404040);
		this.fontRenderer.drawString(
				StatCollector.translateToLocal("container.inventory"), 7, 73,
				0x404040);
	}

}
