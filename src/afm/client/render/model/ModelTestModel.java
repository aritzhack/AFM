package afm.client.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

import org.lwjgl.opengl.GL11;

import afm.data.BlockData;
import cpw.mods.fml.client.FMLClientHandler;

/**
 * ModelTestModel
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ModelTestModel extends ModelBase {
	final ModelRenderer TopCube;
	final ModelRenderer MidTube;
	final ModelRenderer BottomCube;

	final float scale = 1F / 16F;

	public ModelTestModel() {
		this.textureWidth = 64;
		this.textureHeight = 64;

		this.TopCube = new ModelRenderer(this, 0, 0);
		this.TopCube.addBox(0F, 0F, 0F, 16, 1, 16);
		this.TopCube.setRotationPoint(-8F, 8F, -8F);
		this.TopCube.setTextureSize(64, 64);
		this.TopCube.mirror = true;
		this.setRotation(this.TopCube);
		this.MidTube = new ModelRenderer(this, 0, 34);
		this.MidTube.addBox(0F, 0F, 0F, 2, 14, 2);
		this.MidTube.setRotationPoint(-1F, 9F, -1F);
		this.MidTube.setTextureSize(64, 64);
		this.MidTube.mirror = true;
		this.setRotation(this.MidTube);
		this.BottomCube = new ModelRenderer(this, 0, 17);
		this.BottomCube.addBox(0F, 0F, 0F, 16, 1, 16);
		this.BottomCube.setRotationPoint(-8F, 23F, -8F);
		this.BottomCube.setTextureSize(64, 64);
		this.BottomCube.mirror = true;
		this.setRotation(this.BottomCube);
	}

	public void render(double x, double y, double z) {

		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);

		GL11.glTranslated(x + .5, y - .5, z + .5);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(BlockData.TEXTURE_TESTMODEL);

		this.TopCube.render(this.scale);
		this.MidTube.render(this.scale);
		this.BottomCube.render(this.scale);

		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}

	private void setRotation(ModelRenderer model) {
		model.rotateAngleX = 0F;
		model.rotateAngleY = 0F;
		model.rotateAngleZ = 0F;
	}

}
