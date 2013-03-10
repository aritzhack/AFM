package afm.gui.gui;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import afm.data.GUIData;
import afm.gui.container.ContainerSharedWorkbench;
import afm.tileEntity.TESharedWorkbench;

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
	

}
