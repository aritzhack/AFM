package afm.data;

import net.minecraftforge.common.Configuration;

/**
 * BlockData
 *
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class BlockData {
	public static final String TEXTURE = Properties.RES_DIR + "blocks.png";

	public static final String TEXTURE_TESTMODEL = Properties.RES_DIR + "testmodel.png";

	public static final int TEXTUREINDEX_ORE = 0;
	public static final int TEXTUREINDEX_TESTCHEST = 1;
	public static final int TEXTUREINDEX_LASER = 2;

	public static final int TEXTUREROW_COLOURED_GLASS = 1;
	public static final int TEXTUREROW_TINTED_GLASS = 2;

	private static final int ID_DEFAULT_COLOURED_GLASS = 500;
	private static final int ID_DEFAULT_ORE = 501;
	private static final int ID_DEFAULT_TESTCHEST = 502;
	private static final int ID_DEFAULT_SHAREDCRAFTING = 503;
	private static final int ID_DEFAULT_TESTMODEL = 504;
	private static final int ID_DEFAULT_FABRICATOR = 505;
	private static final int ID_DEFAULT_LASER = 506;
	private static final int ID_DEFAULT_TINTED_GLASS = 507;
	private static final int ID_DEFAULT_PORTABLE_CHEST = 508;

	public static int ID_COLOURED_GLASS = BlockData.ID_DEFAULT_COLOURED_GLASS;
	public static int ID_ORE = BlockData.ID_DEFAULT_ORE;
	public static int ID_TESTCHEST = BlockData.ID_DEFAULT_TESTCHEST;
	public static int ID_SHAREDWORKBENCH = BlockData.ID_DEFAULT_SHAREDCRAFTING;
	public static int ID_TESTMODEL = BlockData.ID_DEFAULT_TESTMODEL;
	public static final int ID_FABRICATOR = BlockData.ID_DEFAULT_FABRICATOR;
	public static int ID_LASER = BlockData.ID_DEFAULT_LASER;
	public static int ID_TINTED_GLASS = BlockData.ID_DEFAULT_TINTED_GLASS;
	public static int ID_PORTABLE_CHEST = BlockData.ID_DEFAULT_PORTABLE_CHEST;

	public static final String NAME_LASER = "AFMLaser";
	public static final String NAME_SHARED_WORKBENCH = "AFMSharedWorkbench";
	public static final String NAME_TESTMODEL = "AFMTestModel";
	public static final String NAME_TESTCHEST = "AFMTestChest";
	public static final String NAME_ORE_AFM = "AFMOre";
	public static final String NAME_FABRICATOR = "AFMFabricator";
	public static final String NAME_PORTABLE_CHEST = "AFMPortableChest";

	public static final String NAME_TINTEDGLASS = "AFMTintedGlass";
	public static final String NAME_COLOUREDGLASS = "AFMColouredGlass";

	// public static final String NAME_ = "";

	public static void loadConfig(Configuration config) {
		BlockData.ID_COLOURED_GLASS = config.getBlock("Blocks", "colouredGlass", BlockData.ID_DEFAULT_COLOURED_GLASS).getInt();
		BlockData.ID_ORE = config.getBlock("Blocks", "oreID", BlockData.ID_DEFAULT_ORE).getInt();
		BlockData.ID_TESTCHEST = config.getBlock("Blocks", "testChest", BlockData.ID_DEFAULT_TESTCHEST).getInt();
		BlockData.ID_SHAREDWORKBENCH = config.getBlock("Blocks", "sharedCrafting", BlockData.ID_DEFAULT_SHAREDCRAFTING).getInt();
		BlockData.ID_TESTMODEL = config.getBlock("Blocks", "testModel", BlockData.ID_DEFAULT_TESTMODEL).getInt();
		BlockData.ID_LASER = config.getBlock("Blocks", "laser", BlockData.ID_DEFAULT_LASER).getInt();
		BlockData.ID_TINTED_GLASS = config.getBlock("Blocks", "tintedGlass", BlockData.ID_DEFAULT_TINTED_GLASS).getInt();
		BlockData.ID_TINTED_GLASS = config.getBlock("Blocks", "tintedGlass", BlockData.ID_DEFAULT_TINTED_GLASS).getInt();
		BlockData.ID_PORTABLE_CHEST = config.getBlock("Blocks", "portableCrafting", BlockData.ID_DEFAULT_PORTABLE_CHEST).getInt();
	}
}
