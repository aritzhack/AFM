package afm.gui.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import afm.data.GUIData;
import afm.data.Properties;
import afm.gui.container.ContainerChestTest;
import afm.tileEntity.TETestChest;

public class GUIChestTest extends AFMGUI {
	
	TETestChest tileEntity;

	public GUIChestTest(TETestChest tile_entity,
			InventoryPlayer player_inventory) {
		super(new ContainerChestTest(tile_entity, player_inventory),
				GUIData.BG_TESTCHEST);
		this.tileEntity = tile_entity;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int i, int j) {

		fontRenderer.drawString(StatCollector.translateToLocal(tileEntity.getInvName()), 6, 6, 0x404040);
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