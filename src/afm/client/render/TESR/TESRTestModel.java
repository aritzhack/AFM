package afm.client.render.TESR;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import afm.client.render.model.ModelTestModel;

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
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick) {
		this.model.render(x, y, z);
	}
}
