package afm.proxy;

//import afm.client.render.TESR.TESRTestModel;
import net.minecraft.client.Minecraft;
import afm.core.AFMLogger;

/**
 * ClientProxy
 *
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class ClientProxy extends CommonProxy {

	@Override
	public void writeChatMessageToPlayer(String s) {
		Minecraft.getMinecraft().thePlayer.addChatMessage(s);
	}

	/**
	 * Registers the textures for blocks and items
	 */
	@Override
	public void registerTexuresAndRenderers() {

		AFMLogger.log("Registering renderers");
		//ClientRegistry.bindTileEntitySpecialRenderer(TETestModel.class, new TESRTestModel());
	}

}
