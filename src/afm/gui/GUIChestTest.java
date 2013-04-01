package afm.gui;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import afm.data.GUIData;
import afm.inventory.ContainerChestTest;
import afm.tileEntity.TETestChest;

/**
 * GUIChestTest
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GUIChestTest extends AFMGUI {

	TETestChest tileEntity;

	public GUIChestTest(TETestChest tile_entity, InventoryPlayer player_inventory) {
		super(new ContainerChestTest(tile_entity, player_inventory), GUIData.BG_TESTCHEST);
		this.tileEntity = tile_entity;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int i, int j) {

		this.fontRenderer.drawString(StatCollector.translateToLocal(this.tileEntity.getInvName()), 6, 6, 0x404040);
		this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 6, 75, 0x404040);
	}

	@Override
	public void initGui() {
		super.initGui();
		// Add buttons, this way:
		// controlList.add(new GuiButton(1, 0, 0, 10, 10, "+"));
	}

}