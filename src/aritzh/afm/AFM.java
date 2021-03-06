package aritzh.afm;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import aritzh.afm.blocks.Blocks;
import aritzh.afm.client.gui.GUIHandler;
import aritzh.afm.command.CommandAFM;
import aritzh.afm.core.AFMLogger;
import aritzh.afm.core.TabAFM;
import aritzh.afm.core.Version;
import aritzh.afm.core.handlers.EventHandler;
import aritzh.afm.core.util.UtilAFM;
import aritzh.afm.data.Config;
import aritzh.afm.i18n.Localization;
import aritzh.afm.items.Items;
import aritzh.afm.network.PacketHandler;
import aritzh.afm.proxy.CommonProxy;
import aritzh.afm.world.WorldGenerator;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * AFM
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@Mod(name = Config.MOD_NAME, modid = Config.MOD_ID, version = Version.MOD_VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = true, channels = { Config.Network.CHANNEL }, packetHandler = PacketHandler.class)
public class AFM {

    Configuration config;

    final WorldGenerator worldGen = new WorldGenerator();

    @Instance(Config.MOD_ID)
    public static AFM afm = new AFM();

    @SidedProxy(clientSide = "aritzh.afm.proxy.ClientProxy", serverSide = "aritzh.afm.proxy.CommonProxy")
    public static CommonProxy proxy;

    public static final CreativeTabs tabAFM = new TabAFM(CreativeTabs.getNextID());

    public static final GUIHandler guiHandler = new GUIHandler();

    @cpw.mods.fml.common.Mod.EventHandler
    public void serverStarting(final FMLServerStartingEvent e) {
        e.registerServerCommand(new CommandAFM());
    }

    @cpw.mods.fml.common.Mod.EventHandler
    public void preInit(final FMLPreInitializationEvent event) {

        AFMLogger.init();

        Localization.loadLocales();

        Config.init(event.getSuggestedConfigurationFile());

        MinecraftForge.EVENT_BUS.register(new EventHandler());
        NetworkRegistry.instance().registerConnectionHandler(new EventHandler());

        NetworkRegistry.instance().registerGuiHandler(AFM.afm, AFM.guiHandler);
    }

    @cpw.mods.fml.common.Mod.EventHandler
    public void init(final FMLInitializationEvent event) {

        this.showDebugGroup(true, event.getSide().toString());

        AFM.proxy.registerTexuresAndRenderers();
        AFM.proxy.registerTileEntities();

        Items.init();
        Blocks.init();

        Blocks.registerBlocks();
        Blocks.addRecipes();

        UtilAFM.initOreDict();

        GameRegistry.registerWorldGenerator(this.worldGen);

        this.showDebugGroup(false, event.getSide().toString());
    }

    private void showDebugGroup(final boolean opening, final String side) {

        final String s = String.format("%s Initialization for Minecraft %s and Forge %s %s in %s", Config.MOD_NAME, Version.C_MC_VERSION, Version.C_FORGE_VERSION, opening ? "started" : "finished", side);

        // Just aesthetics...
        String b1 = "", b2 = "";
        for (int i = 0; i < s.length(); i++) {
            b1 = b1 + (opening ? "=" : "-");
            b2 = b2 + (opening ? "-" : "=");
        }

        AFMLogger.debug(b1);
        AFMLogger.debug(s);
        AFMLogger.debug(b2);
    }
}
