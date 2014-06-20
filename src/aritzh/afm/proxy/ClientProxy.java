package aritzh.afm.proxy;

import aritzh.afm.client.render.TESR.TESRWireless;
import aritzh.afm.tileEntity.TEWireless;
import net.minecraft.client.Minecraft;
import aritzh.afm.client.render.TESR.TESRTestModel;
import aritzh.afm.core.AFMLogger;
import aritzh.afm.data.RenderingData;
import aritzh.afm.tileEntity.TETestModel;
import cpw.mods.fml.client.registry.ClientRegistry;

/**
 * ClientProxy
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void writeChatMessageToPlayer(final String s) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(s);
    }

    /**
     * Registers the textures for blocks and items
     */
    @Override
    public void registerTexuresAndRenderers() {

        AFMLogger.debug("Registering renderers");
        ClientRegistry.bindTileEntitySpecialRenderer(TETestModel.class, new TESRTestModel());
        ClientRegistry.bindTileEntitySpecialRenderer(TEWireless.class, new TESRWireless());
        RenderingData.init();
    }

}
