package afm.proxy;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.MinecraftForgeClient;
import afm.client.render.TESR.TESRTestModel;
import afm.core.AFMLogger;
import afm.core.Properties;
import afm.tileEntity.TETestModel;
import cpw.mods.fml.client.registry.ClientRegistry;

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
		MinecraftForgeClient.preloadTexture(Properties.Block.TEXTURE);
		MinecraftForgeClient.preloadTexture(Properties.Item.TEXTURE);

		AFMLogger.log("Registering renderers");
		ClientRegistry.bindTileEntitySpecialRenderer(TETestModel.class,
				new TESRTestModel());
	}

}
