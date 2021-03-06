package aritzh.afm.client.render.model;

import aritzh.afm.data.Config;
import aritzh.afm.data.RenderingData;
import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.model.ModelBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

/**
 * ModelTestModel
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ModelTestModel extends ModelBase {

    private final IModelCustom model;

    final float scale = 1F / 16F;

    public ModelTestModel() {
        this.model = AdvancedModelLoader.loadModel(RenderingData.MODEL_TESTMODEL);
        this.textureWidth = 64;
        this.textureHeight = 64;
    }

    public void render(final double x, final double y, final double z) {

        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);

        GL11.glTranslated(x + .5, y - .5, z + .5);
        FMLClientHandler.instance().getClient().renderEngine.func_110577_a(new ResourceLocation(Config.MOD_ID.toLowerCase(), RenderingData.TEXTURE_TESTMODEL));

        this.model.renderAll();

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }

}
