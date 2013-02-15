package afm.blocks;

import afm.core.AFMLogger;
import afm.data.BlockData;
import afm.items.ItemGlassColoured;
import afm.items.ItemGlassTinted;
import afm.wip.blocks.BlockFabricator;
import cpw.mods.fml.common.registry.GameRegistry;

public class Blocks {

	public static BlockGlassColoured colouredGlass;
	public static BlockGlassTinted tintedGlass;
	public static BlockOreAFM oreAFM;
	public static BlockDayDetector dayDetector;
	public static BlockSharedWorkbench sharedWorkbench;
	public static BlockTestModel testModel;
	public static BlockFabricator fabricator;
	public static BlockLaser laser;

	public static void init() {

		AFMLogger.log("Registering and initializing blocks");

		Blocks.colouredGlass = new BlockGlassColoured();
		Blocks.tintedGlass = new BlockGlassTinted();
		Blocks.oreAFM = new BlockOreAFM();
		Blocks.dayDetector = new BlockDayDetector();
		Blocks.sharedWorkbench = new BlockSharedWorkbench();
		Blocks.testModel = new BlockTestModel();
		Blocks.fabricator = new BlockFabricator();
		Blocks.laser = new BlockLaser();

		GameRegistry.registerBlock(Blocks.colouredGlass, ItemGlassColoured.class, BlockData.NAME_COLOUREDGLASS);
		GameRegistry.registerBlock(Blocks.tintedGlass, ItemGlassTinted.class, BlockData.NAME_TINTEDGLASS);
		GameRegistry.registerBlock(Blocks.oreAFM, BlockData.NAME_ORE_AFM);
		GameRegistry.registerBlock(Blocks.dayDetector, BlockData.NAME_DAY_DETECTOR);
		GameRegistry.registerBlock(Blocks.sharedWorkbench, BlockData.NAME_SHARED_WORKBENCH);
		GameRegistry.registerBlock(Blocks.testModel, BlockData.NAME_TESTMODEL);
		GameRegistry.registerBlock(Blocks.fabricator, BlockData.NAME_FABRICATOR);
		GameRegistry.registerBlock(Blocks.laser, BlockData.NAME_LASER);

		Blocks.colouredGlass.init();
		Blocks.oreAFM.init();

	}

}