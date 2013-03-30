package afm.data;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class RenderingData {
	
	public static int RENDER_ID_BETTER_TORCH = 0;
	
	public static void init(){
		RenderingData.RENDER_ID_BETTER_TORCH = RenderingRegistry.getNextAvailableRenderId();
	}

}
