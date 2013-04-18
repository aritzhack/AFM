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

	public static int RENDER_ID_BETTER_TORCH = 0;

	public static void init() {
		RenderingData.RENDER_ID_BETTER_TORCH = RenderingRegistry.getNextAvailableRenderId();

		SimpleBlockRenderer renderer = new SimpleBlockRenderer();

		RenderingRegistry.registerBlockHandler(RenderingData.RENDER_ID_BETTER_TORCH, renderer);
	}

}
