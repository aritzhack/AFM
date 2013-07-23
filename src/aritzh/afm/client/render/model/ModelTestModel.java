package aritzh.afm.client.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import aritzh.afm.data.BlockData;
import aritzh.afm.data.Config;
import cpw.mods.fml.client.FMLClientHandler;

/**
 * ModelTestModel
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ModelTestModel extends ModelBase {
//	final ModelRenderer TopCube;
//	final ModelRenderer MidTube;
//	final ModelRenderer BottomCube;
	
	private IModelCustom model;

	final float scale = 1F / 16F;

	public ModelTestModel() {
		model = AdvancedModelLoader.loadModel(Config.MODEL_DIR + "/model.obj");
		this.textureWidth = 64;
		this.textureHeight = 64;
	}

	public void render(double x, double y, double z) {

		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);

		GL11.glTranslated(x + .5, y - .5, z + .5);
		FMLClientHandler.instance().getClient().renderEngine.func_110577_a(new ResourceLocation(Config.MOD_ID.toLowerCase(), BlockData.TEXTURE_TESTMODEL));

		model.renderAll();
		
		//this.TopCube.render(this.scale);
		//this.MidTube.render(this.scale);
		//this.BottomCube.render(this.scale);

		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}
//
//	private void setRotation(ModelRenderer model) {
//		model.rotateAngleX = 0F;
//		model.rotateAngleY = 0F;
//		model.rotateAngleZ = 0F;
//	}

}
