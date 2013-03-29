package afm.gui;

import afm.core.AFMLogger;
import afm.data.GUIData;
import afm.inventory.ContainerChestTest;
import afm.inventory.ContainerFabricator;
import afm.inventory.ContainerSharedWorkbench;
import afm.tileEntity.TEFabricator;
import afm.tileEntity.TEPortableChest;
import afm.tileEntity.TESharedWorkbench;
import afm.tileEntity.TETestChest;
import afm.wip.tileEntity.TEAFMTank;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.logging.Level;

/**
 * GUIHandler
 *
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class GUIHandler implements IGuiHandler {

	public GUIHandler() {
		GameRegistry.registerTileEntity(TETestChest.class, "tileEntity.afm.testChest");
		GameRegistry.registerTileEntity(TESharedWorkbench.class, "tileEntity.afm.sharedCrafting");
		GameRegistry.registerTileEntity(TEFabricator.class, "tileEntity.afm.fabricator");
		GameRegistry.registerTileEntity(TEPortableChest.class, "tileEntity.afm.portableChest");
		GameRegistry.registerTileEntity(TEAFMTank.class, "tileEntity.afm.tank");
	}

	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {

		TileEntity tile_entity = world.getBlockTileEntity(x, y, z);

		switch (id) {
			case GUIData.ID_TESTCHEST:
				if (tile_entity instanceof TETestChest)
					return new ContainerChestTest((TETestChest) tile_entity, player.inventory);
				break;
			case GUIData.ID_SHAREDWORKBENCH:
				if (tile_entity instanceof TESharedWorkbench)
					return new ContainerSharedWorkbench((TESharedWorkbench) tile_entity, player.inventory, world);
				break;
			case GUIData.ID_FABRICATOR:
				if (tile_entity instanceof TEFabricator)
					return new ContainerFabricator((TEFabricator) tile_entity, player.inventory, world);
				break;
			default:
				AFMLogger.log(Level.SEVERE, "GUI with ID " + id + " could not be found");
				break;
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {

		TileEntity tile_entity = world.getBlockTileEntity(x, y, z);

		switch (id) {
			case GUIData.ID_TESTCHEST:
				if (tile_entity instanceof TETestChest)
					return new GUIChestTest((TETestChest) tile_entity, player.inventory);
				break;
			case GUIData.ID_SHAREDWORKBENCH:
				if (tile_entity instanceof TESharedWorkbench)
					return new GUISharedWorkbench((TESharedWorkbench) tile_entity, player.inventory, world);
				break;
			case GUIData.ID_FABRICATOR:
				if (tile_entity instanceof TEFabricator)
					return new GUIFabricator((TEFabricator) tile_entity, player.inventory, world);
				break;
			default:
				AFMLogger.log(Level.SEVERE, "GUI with ID " + id + " could not be found");
				break;
		}

		return null;
	}
}