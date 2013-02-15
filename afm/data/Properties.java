package afm.data;

import java.io.File;

import net.minecraftforge.common.Configuration;

public final class Properties {

	public static final String RES_DIR = "/resources/";
	public static final String LANG_DIR = Properties.RES_DIR + "lang/";
	private static Configuration config;

	public static class Network {
		public static final byte PCKT_ID_RANDOM_NUMBERS = 0;
		public static final String CHANNEL = "AFMChannel";
	}

	public static final String MOD_ID = "AFM";
	public static final String VERSION = "0.2";
	public static final String MOD_NAME = "AFM v" + Properties.VERSION;
	public static final String MC_VERSION = "1.4.7";
	public static final String FORGE_VERSION = "6.6.1.524";

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
