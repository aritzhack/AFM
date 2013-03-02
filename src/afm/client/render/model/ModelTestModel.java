package afm.client.render.model;

import afm.data.BlockData;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.client.ForgeHooksClient;
import org.lwjgl.opengl.GL11;

public class ModelTestModel extends ModelBase {
	final ModelRenderer TopCube;
	final ModelRenderer MidTube;
	final ModelRenderer BottomCube;

	final float scale = 1F / 16F;

	public ModelTestModel() {
		textureWidth = 64;
		textureHeight = 64;

		TopCube = new ModelRenderer(this, 0, 0);
		TopCube.addBox(0F, 0F, 0F, 16, 1, 16);
		TopCube.setRotationPoint(-8F, 8F, -8F);
		TopCube.setTextureSize(64, 64);
		TopCube.mirror = true;
		setRotation(TopCube);
		MidTube = new ModelRenderer(this, 0, 34);
		MidTube.addBox(0F, 0F, 0F, 2, 14, 2);
		MidTube.setRotationPoint(-1F, 9F, -1F);
		MidTube.setTextureSize(64, 64);
		MidTube.mirror = true;
		setRotation(MidTube);
		BottomCube = new ModelRenderer(this, 0, 17);
		BottomCube.addBox(0F, 0F, 0F, 16, 1, 16);
		BottomCube.setRotationPoint(-8F, 23F, -8F);
		BottomCube.setTextureSize(64, 64);
		BottomCube.mirror = true;
		setRotation(BottomCube);
	}

	public void render(double x, double y, double z) {

		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);

		GL11.glTranslated(x + .5, y - .5, z + .5);
		ForgeHooksClient.bindTexture(BlockData.TEXTURE_TESTMODEL, 0);

		TopCube.render(scale);
		MidTube.render(scale);
		BottomCube.render(scale);

		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();

	}

	private void setRotation(ModelRenderer model) {
		model.rotateAngleX = 0F;
		model.rotateAngleY = 0F;
		model.rotateAngleZ = 0F;
	}

}
