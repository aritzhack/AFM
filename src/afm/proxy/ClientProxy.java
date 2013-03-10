package afm.proxy;

//import afm.client.render.TESR.TESRTestModel;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.MinecraftForgeClient;
import afm.core.AFMLogger;
import afm.data.BlockData;
import afm.data.ItemData;

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
		AFMLogger.log("Registering textures");
		MinecraftForgeClient.preloadTexture(BlockData.TEXTURE);
		MinecraftForgeClient.preloadTexture(ItemData.TEXTURE);

		AFMLogger.log("Registering renderers");
		//ClientRegistry.bindTileEntitySpecialRenderer(TETestModel.class, new TESRTestModel());
	}

}
