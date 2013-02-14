package afm.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import afm.blocks.Blocks;
import afm.command.CommandAFM;
import afm.gui.GUIHandler;
import afm.i18n.Localization;
import afm.items.Items;
import afm.network.PacketHandler;
import afm.proxy.CommonProxy;
import afm.world.WorldGenerator;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(name = Properties.Mod.NAME, modid = Properties.Mod.ID, version = Properties.Mod.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = true, channels = { Properties.Network.CHANNEL }, packetHandler = PacketHandler.class)
public class AFM {

	Configuration config;

	WorldGenerator worldGen = new WorldGenerator();

	@Instance(Properties.Mod.ID)
	public static AFM afm = new AFM();

	@SidedProxy(clientSide = "afm.proxy.ClientProxy", serverSide = "afm.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static CreativeTabs tabAFM = new TabAFM(CreativeTabs.getNextID(),
			"tabAFM");

	public static GUIHandler guiHandler = new GUIHandler();
	
	@ServerStarting
	public void serverStarting(FMLServerStartingEvent e){
		e.registerServerCommand(new CommandAFM());
	}

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {

		AFMLogger.init();

		Properties.init(event.getSuggestedConfigurationFile());
		
		Localization.loadLocales();

		MinecraftForge.EVENT_BUS.register(new EventHandler());

		NetworkRegistry.instance().registerGuiHandler(afm, guiHandler);
	}

	@Init
	public void init(FMLInitializationEvent event) {

		this.showDebugGroup(true, event.getSide().toString());

		proxy.registerTexuresAndRenderers();

		Items.init();
		Blocks.init();

		GameRegistry.registerWorldGenerator(this.worldGen);

		this.showDebugGroup(false, event.getSide().toString());
	}

	private void showDebugGroup(boolean opening, String side) {

		String s = String.format(
				"%s Initialization for Minecraft %s and Forge %s %s in %s",
				Properties.Mod.NAME, Properties.Mod.MC_VERSION,
				Properties.Mod.FORGE_VERSION, opening ? "started" : "finished",
				side);

		// Just aesthetics...
		String b1 = "", b2 = "";
		for (int i = 0; i < s.length(); i++) {
			b1 = b1 + (opening ? "=" : "-");
			b2 = b2 + (opening ? "-" : "=");
		}

		AFMLogger.log(b1);
		AFMLogger.log(s);
		AFMLogger.log(b2);
	}
}
