package afm.gui.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import afm.core.Properties;
import afm.gui.container.ContainerChestTest;
import afm.tileEntity.TETestChest;

public class GUIChestTest extends AFMGUI {

	public GUIChestTest(TETestChest tile_entity,
			InventoryPlayer player_inventory) {
		super(new ContainerChestTest(tile_entity, player_inventory),
				Properties.GUI.BG_TESTCHEST);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int i, int j) {

		fontRenderer.drawString("Test-Chest!", 6, 6, 0x404040);
		fontRenderer.drawString(
				StatCollector.translateToLocal("container.inventory"), 6, 75,
				0x404040);
	}

	@Override
	public void initGui() {
		super.initGui();
		// Add buttons, this way:
		// controlList.add(new GuiButton(1, 0, 0, 10, 10, "+"));
	}

}