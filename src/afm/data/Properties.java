package afm.data;

import net.minecraftforge.common.Configuration;

import java.io.File;

/**
 * Properties
 *
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public final class Properties {

	public static final String RES_DIR = "/afm/res/";
	public static final String LANG_DIR = Properties.RES_DIR + "lang/";
	private static Configuration config;

	public static class Network {
		public static final byte PCKT_ID_RANDOM_NUMBERS = 0;
		public static final String CHANNEL = "AFMChannel";
	}

	public static final String MOD_ID = "AFM";
	public static final String VERSION = "%VERSION%";
	public static final String MOD_NAME = MOD_ID + " v" + Properties.VERSION;
	public static final String MC_VERSION = "%MC_VERSION%";
	public static final String FORGE_VERSION = "%FORGE_VERSION%";

	public static void init(File configFile) {
		Properties.config = new Configuration(configFile);
		Properties.config.load();

		Properties.loadConfig(Properties.config);

		Properties.config.save();
	}

	private static void loadConfig(Configuration config) {

		config.addCustomCategoryComment("AFM", "Main category. Here are all the main configs");
		config.addCustomCategoryComment("Blocks", "All block configs (ID's, worldgen,...)");
		config.addCustomCategoryComment("Items", "All item configs (ID's,...)");

		BlockData.loadConfig(config);
		ItemData.loadConfig(config);
		GUIData.loadConfig(config);

	}
}
