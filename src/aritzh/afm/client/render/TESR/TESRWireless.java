package aritzh.afm.client.render.TESR;

import aritzh.afm.client.render.model.ModelWireless;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

/**
 * @author Aritz Lopez
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TESRWireless extends TileEntitySpecialRenderer {

    ModelWireless model;

    public TESRWireless(){
        model = new ModelWireless();
    }

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float f) {

    }
}
