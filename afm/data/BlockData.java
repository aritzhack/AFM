package afm.data;

import net.minecraftforge.common.Configuration;

public class BlockData {
	public static final String TEXTURE = Properties.RES_DIR + "blocks.png";

	public static final String TEXTURE_TESTMODEL = Properties.RES_DIR + "testmodel.png";

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

	public static int ID_COLOURED_GLASS = BlockData.ID_DEFAULT_COLOURED_GLASS;
	public static int ID_ORE = BlockData.ID_DEFAULT_ORE;
	public static int ID_DAYDETECTOR = BlockData.ID_DEFAULT_DAYDETECTOR;
	public static int ID_SHAREDWORKBENCH = BlockData.ID_DEFAULT_SHAREDCRAFTING;
	public static int ID_TESTMODEL = BlockData.ID_DEFAULT_TESTMODEL;
	public static int ID_FABRICATOR = BlockData.ID_DEFAULT_FABRICATOR;
	public static int ID_LASER = BlockData.ID_DEFAULT_LASER;
	public static int ID_TINTED_GLASS = BlockData.ID_DEFAULT_TINTED_GLASS;

	public static final String NAME_LASER = "AFMLaser";
	public static final String NAME_SHARED_WORKBENCH = "AFMSharedWorkbench";
	public static final String NAME_TESTMODEL = "AFMTestModel";
	public static final String NAME_DAY_DETECTOR = "AFMDayDetector";
	public static final String NAME_ORE_AFM = "AFMOre";
	public static final String NAME_FABRICATOR = "AFMFabricator";

	public static final String NAME_TINTEDGLASS = "AFMTintedGlass";
	public static final String NAME_COLOUREDGLASS = "AFMColouredGlass";

	// public static final String NAME_ = "";

	public static void loadConfig(Configuration config) {
		BlockData.ID_COLOURED_GLASS = config.getBlock("Blocks", "colouredGlass", BlockData.ID_DEFAULT_COLOURED_GLASS).getInt();
		BlockData.ID_ORE = config.getBlock("Blocks", "oreID", BlockData.ID_DEFAULT_ORE).getInt();
		BlockData.ID_DAYDETECTOR = config.getBlock("Blocks", "dayDetector", BlockData.ID_DEFAULT_DAYDETECTOR).getInt();
		BlockData.ID_SHAREDWORKBENCH = config.getBlock("Blocks", "sharedCrafting", BlockData.ID_DEFAULT_SHAREDCRAFTING).getInt();
		BlockData.ID_TESTMODEL = config.getBlock("Blocks", "testModel", BlockData.ID_DEFAULT_TESTMODEL).getInt();
		BlockData.ID_LASER = config.getBlock("Blocks", "laser", BlockData.ID_DEFAULT_LASER).getInt();
		BlockData.ID_TINTED_GLASS = config.getBlock("Blocks", "tintedGlass", BlockData.ID_DEFAULT_TINTED_GLASS).getInt();
	}
}
