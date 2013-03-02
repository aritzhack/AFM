package afm.client.render.TESR;

import afm.client.render.model.ModelTestModel;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

/**
 * TESRTestModel
 *
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class TESRTestModel extends TileEntitySpecialRenderer {

	ModelTestModel model;

	public TESRTestModel() {
		model = new ModelTestModel();
	}

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y,
								   double z, float tick) {
		model.render(x, y, z);
	}
}
