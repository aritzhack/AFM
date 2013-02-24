package afm.wip.gui.gui;

import afm.data.GUIData;
import afm.gui.gui.AFMGUI;
import afm.wip.gui.container.ContainerFabricator;
import afm.wip.tileEntity.TEFabricator;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class GUIFabricator extends AFMGUI {

    TEFabricator tileEntity;

    public GUIFabricator(TEFabricator tileEntity, InventoryPlayer inventory,
                         World world) {
        super(new ContainerFabricator(tileEntity, inventory, world),
                GUIData.BG_FABRICATOR);
        this.tileEntity = tileEntity;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int i, int j) {

        this.fontRenderer.drawString(StatCollector.translateToLocal(this.tileEntity.getInvName()), 6, 6, 0x404040);
        this.fontRenderer.drawString(
                StatCollector.translateToLocal("container.inventory"), 7, 73,
                0x404040);
    }

}
