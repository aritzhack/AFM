package aritzh.afm.data;

import aritzh.afm.client.render.sbrh.SimpleBlockRenderer;
import cpw.mods.fml.client.registry.RenderingRegistry;

/**
 * RenderingData
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class RenderingData {

    public static final String MODEL_DIR = "/assets/afm/models";
    public static final String MODEL_TEX_DIR = "/assets/afm/textures/models";

    public static final String TEXTURE_TESTMODEL = RenderingData.MODEL_TEX_DIR + "/testmodel.png";
    public static final String TEXTURE_WIRELESS = RenderingData.MODEL_TEX_DIR + "/wireless.png";

    public static int RENDER_ID_BETTER_TORCH = 0;

    public static String MODEL_TESTMODEL = RenderingData.MODEL_DIR + "/testModel.obj";
    public static String MODEL_WIRELESS = RenderingData.MODEL_DIR + "/wireless.obj";

    public static void init() {
        RenderingData.RENDER_ID_BETTER_TORCH = RenderingRegistry.getNextAvailableRenderId();

        final SimpleBlockRenderer renderer = new SimpleBlockRenderer();

        RenderingRegistry.registerBlockHandler(RenderingData.RENDER_ID_BETTER_TORCH, renderer);
    }

}
