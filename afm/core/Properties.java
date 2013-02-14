package afm.core;

import java.io.File;

import net.minecraftforge.common.Configuration;

public final class Properties {

	private static final String RES_DIR = "/resources/";
	public static final String LANG_DIR = RES_DIR + "lang/";
	private static Configuration config;

	public static class Network {
		public static final byte PCKT_ID_RANDOM_NUMBERS = 0;
		public static final String CHANNEL = "AFMChannel";
	}

	public static class Item {
		public static final String TEXTURE = RES_DIR + "items.png";
		public static final int TEXTUREINDEX_QUARTZ = 0;
		private static final int ID_DEFAULT_QUARTZ = 600;

		public static int ID_QUARTZ = ID_DEFAULT_QUARTZ;
	}

	public static class Mod {
		public static final String ID = "AFM";
		public static final String VERSION = "0.2";
		public static final String NAME = "AFM v" + VERSION;
		public static final String MC_VERSION = "1.4.7";
		public static final String FORGE_VERSION = "6.6.1.524";
	}

	public static class Block {
		public static final String TEXTURE = RES_DIR + "blocks.png";

		public static final String TEXTURE_TESTMODEL = RES_DIR
				+ "testmodel.png";

		public static final int TEXTUREINDEX_ORE = 0;
		public static final int TEXTUREINDEX_DAYDETECTOR = 1;
		public static final int TEXTUREINDEX_LASER = 2;

		public static final int TEXTUREROW_COLOURED_GLASS = 1;
		public static final int TEXTUREROW_TINTED_GLASS = 2;

		private static final int ID_DEFAULT_COLOURED_GLASS = 500;
		private static final int ID_DEFAULT_ORE = 501;
		public static final int ID_DEFAULT_DAYDETECTOR = 502;
		private static final int ID_DEFAULT_SHAREDCRAFTING = 503;
		private static final int ID_DEFAULT_TESTMODEL = 504;
		private static final int ID_DEFAULT_FABRICATOR = 505;
		private static final int ID_DEFAULT_LASER = 506;
		private static final int ID_DEFAULT_TINTED_GLASS = 507;

		public static int ID_COLOURED_GLASS = ID_DEFAULT_COLOURED_GLASS;
		public static int ID_ORE = ID_DEFAULT_ORE;
		public static int ID_DAYDETECTOR = ID_DEFAULT_DAYDETECTOR;
		public static int ID_SHAREDCRAFTING = ID_DEFAULT_SHAREDCRAFTING;
		public static int ID_TESTMODEL = ID_DEFAULT_TESTMODEL;
		public static int ID_FABRICATOR = ID_DEFAULT_FABRICATOR;
		public static int ID_LASER = ID_DEFAULT_LASER;
		public static int ID_TINTED_GLASS = ID_DEFAULT_TINTED_GLASS;

	}

	public static class GUI {
		private static final String DIR = RES_DIR + "gui/";

		public static final String BG_TESTCHEST = DIR + "testchest.png";
		public static final String BG_SHAREDCRAFTING = DIR + "crafting.png";
		public static final String BG_FABRICATOR = DIR + "fabricator.png";

		// GUI IDs
		public static final int ID_TESTCHEST = 0;
		public static final int ID_SHAREDCRAFTING = 1;
		public static final int ID_FABRICATOR = 2;
	}

	public static void init(File configFile) {
		config = new Configuration(configFile);
		config.load();

		if (!config.hasCategory("AFM")) {
			initConfig(config);
		}

		loadConfig(config);

		config.save();
	}

	private static void loadConfig(Configuration config) {
		Block.ID_COLOURED_GLASS = config.getBlock("Blocks", "colouredGlass",
				Block.ID_DEFAULT_COLOURED_GLASS).getInt();
		Block.ID_ORE = config.getBlock("Blocks", "oreID", Block.ID_DEFAULT_ORE)
				.getInt();
		Block.ID_DAYDETECTOR = config.getBlock("Blocks", "dayDetector",
				Block.ID_DEFAULT_DAYDETECTOR).getInt();
		Block.ID_SHAREDCRAFTING = config.getBlock("Blocks", "sharedCrafting",
				Block.ID_DEFAULT_SHAREDCRAFTING).getInt();
		Block.ID_TESTMODEL = config.getBlock("Blocks", "testModel",
				Block.ID_DEFAULT_TESTMODEL).getInt();
		Block.ID_LASER = config.getBlock("Blocks", "laser",
				Block.ID_DEFAULT_LASER).getInt();
		Block.ID_TINTED_GLASS = config.getBlock("Blocks", "tintedGlass",
				Block.ID_DEFAULT_TINTED_GLASS).getInt();
		
		Item.ID_QUARTZ = config.getItem("Items", "quartzID",
				Item.ID_DEFAULT_QUARTZ).getInt();
	}

	private static void initConfig(Configuration config) {
		AFMLogger.log("Initializing config");
		config.addCustomCategoryComment("AFM",
				"Main category. Here are all the main configs");
		config.addCustomCategoryComment("Blocks",
				"All block configs (ID's, worldgen,...)");
		config.addCustomCategoryComment("Items", "All item configs (ID's,...)");
	}
}
