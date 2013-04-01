package afm.proxy;

import afm.tileEntity.TEAFMTank;
import afm.tileEntity.TEFabricator;
import afm.tileEntity.TEPortableChest;
import afm.tileEntity.TESharedWorkbench;
import afm.tileEntity.TETestChest;
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
		GameRegistry.registerTileEntity(TETestChest.class, "tileEntity.afm.testChest");
		GameRegistry.registerTileEntity(TESharedWorkbench.class, "tileEntity.afm.sharedCrafting");
		GameRegistry.registerTileEntity(TEFabricator.class, "tileEntity.afm.fabricator");
		GameRegistry.registerTileEntity(TEPortableChest.class, "tileEntity.afm.portableChest");
		GameRegistry.registerTileEntity(TEAFMTank.class, "tileEntity.afm.tank");
	}

}
