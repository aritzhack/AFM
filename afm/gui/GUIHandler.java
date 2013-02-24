package afm.gui;

import afm.core.AFMLogger;
import afm.data.GUIData;
import afm.gui.container.ContainerChestTest;
import afm.gui.container.ContainerSharedWorkbench;
import afm.gui.gui.GUIChestTest;
import afm.gui.gui.GUISharedWorkbench;
import afm.tileEntity.TEPortableChest;
import afm.tileEntity.TESharedWorkbench;
import afm.tileEntity.TETestChest;
import afm.wip.gui.container.ContainerFabricator;
import afm.wip.gui.gui.GUIFabricator;
import afm.wip.tileEntity.TEFabricator;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.logging.Level;

public class GUIHandler implements IGuiHandler {

    public GUIHandler() {
        GameRegistry.registerTileEntity(TETestChest.class, "tileEntity.afm.testChest");
        GameRegistry.registerTileEntity(TESharedWorkbench.class, "tileEntity.afm.sharedCrafting");
        GameRegistry.registerTileEntity(TEFabricator.class, "tileEntity.afm.fabricator");
        GameRegistry.registerTileEntity(TEPortableChest.class, "tileEntity.afm.portableChest");
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