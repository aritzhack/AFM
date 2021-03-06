package aritzh.afm.client.render.TESR;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import aritzh.afm.client.render.model.ModelTestModel;

/**
 * TESRTestModel
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TESRTestModel extends TileEntitySpecialRenderer {

    ModelTestModel model;

    public TESRTestModel() {
        this.model = new ModelTestModel();
    }

    @Override
    public void renderTileEntityAt(final TileEntity tileEntity, final double x, final double y, final double z, final float tick) {
        this.model.render(x, y, z);
    }
}
