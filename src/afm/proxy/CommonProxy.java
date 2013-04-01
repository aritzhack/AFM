package afm.proxy;

import afm.tileEntity.TETestModel;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * CommonProxy
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CommonProxy {

	public void registerTexuresAndRenderers() {
	}

	public void writeChatMessageToPlayer(String s) {
	}

	public void registerTileEntities() {
		GameRegistry.registerTileEntity(TETestModel.class, "AFMTETestModel");
	}

}
