package afm.gui.gui;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import afm.core.Properties;
import afm.gui.container.ContainerSharedCrafting;
import afm.tileEntity.TESharedCrafting;

public class GUISharedCrafting extends AFMGUI {

	public GUISharedCrafting(TESharedCrafting tileEntity,
			InventoryPlayer invPlayer, World world) {
		super(new ContainerSharedCrafting(tileEntity, invPlayer, world),
				Properties.GUI.BG_SHAREDCRAFTING);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int i, int j) {

		fontRenderer.drawString("Crafting", 6, 6, 0x404040);
		fontRenderer.drawString(
				StatCollector.translateToLocal("container.inventory"), 6, 75,
				0x404040);
	}

}
