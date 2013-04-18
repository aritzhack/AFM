package aritzh.afm.data;

import java.io.File;

import aritzh.afm.core.Version;

import net.minecraftforge.common.Configuration;

/**
 * Properties
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public final class Config {

	public static final String RES_DIR = "/mods/afm/";
	public static final String TEX_DIR = Config.RES_DIR + "textures/";
	public static final String LANG_DIR = Config.RES_DIR + "lang/";
	private static Configuration config;

	public static class Network {
		public static final byte PCKT_ID_RANDOM_NUMBERS = 0;
		public static final String CHANNEL = "AFMChannel";
	}

	public static boolean debug = false;
	public static boolean displayVersionMessageInChat = true;

	public static final String MOD_ID = "AFM";
	public static final String MOD_NAME = Config.MOD_ID + " v" + Version.MOD_VERSION;

	public static void init(File configFile) {
		Config.config = new Configuration(configFile);
		Config.config.load();

		Config.loadConfig(Config.config);

		Config.config.save();
	}

	private static void loadConfig(Configuration config) {

		config.addCustomCategoryComment("AFM", "Main category. Here are all the main configs");
		config.addCustomCategoryComment("Blocks", "All block configs (ID's, worldgen,...)");
		config.addCustomCategoryComment("Items", "All item configs (ID's,...)");

		Config.debug = config.get("AFM", "debug", false, "Enables  debug mode (outputs more data)").getBoolean(false);
		Config.displayVersionMessageInChat = config.get("AFM", "displayVersion", true, "Whether or not the player should receive a chat message telling if there's a new version of the mod").getBoolean(true);

		BlockData.loadConfig(config);
		ItemData.loadConfig(config);
		GUIData.loadConfig(config);

	}
}
