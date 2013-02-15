package afm.gui.gui;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import afm.data.GUIData;
import afm.data.Properties;
import afm.gui.container.ContainerSharedWorkbench;
import afm.tileEntity.TESharedWorkbench;

public class GUISharedWorkbench extends AFMGUI {

	public GUISharedWorkbench(TESharedWorkbench tileEntity,
			InventoryPlayer invPlayer, World world) {
		super(new ContainerSharedWorkbench(tileEntity, invPlayer, world),
				GUIData.BG_SHAREDCRAFTING);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int i, int j) {

		fontRenderer.drawString("Crafting", 6, 6, 0x404040);
		fontRenderer.drawString(
				StatCollector.translateToLocal("container.inventory"), 6, 75,
				0x404040);
	}

}
