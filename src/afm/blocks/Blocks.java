package afm.blocks;

import afm.core.AFMLogger;
import afm.data.BlockData;
import afm.items.ItemGlassColoured;
import afm.items.ItemGlassTinted;
import afm.items.ItemPortableChest;
import afm.items.ItemTestModel;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Blocks
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Blocks {

	public static BlockGlassColoured colouredGlass;
	public static BlockGlassTinted tintedGlass;
	public static BlockOreAFM oreAFM;
	public static BlockTestChest dayDetector;
	public static BlockSharedWorkbench sharedWorkbench;
	public static BlockTestModel testModel;
	public static BlockLaser laser;
	public static BlockPortableChest portableChest;
	public static BlockFabricator fabricator;
	public static BlockTank tank;

	public static BlockBetterTorch betterTorch;

	public static void init() {

		AFMLogger.debug("Initializing blocks");

		Blocks.colouredGlass = new BlockGlassColoured();
		Blocks.tintedGlass = new BlockGlassTinted();
		Blocks.oreAFM = new BlockOreAFM();
		Blocks.dayDetector = new BlockTestChest();
		Blocks.sharedWorkbench = new BlockSharedWorkbench();
		Blocks.testModel = new BlockTestModel();
		Blocks.fabricator = new BlockFabricator();
		Blocks.laser = new BlockLaser();
		Blocks.portableChest = new BlockPortableChest();
		Blocks.tank = new BlockTank();

		Blocks.betterTorch = new BlockBetterTorch();

	}

	public static void registerBlocks() {
		AFMLogger.debug("Registering blocks");

		GameRegistry.registerBlock(Blocks.colouredGlass, ItemGlassColoured.class, BlockData.NAME_COLOUREDGLASS);
		GameRegistry.registerBlock(Blocks.tintedGlass, ItemGlassTinted.class, BlockData.NAME_TINTEDGLASS);
		GameRegistry.registerBlock(Blocks.oreAFM, BlockData.NAME_ORE_AFM);
		GameRegistry.registerBlock(Blocks.dayDetector, BlockData.NAME_TESTCHEST);
		GameRegistry.registerBlock(Blocks.sharedWorkbench, BlockData.NAME_SHARED_WORKBENCH);
		GameRegistry.registerBlock(Blocks.testModel, ItemTestModel.class, BlockData.NAME_TESTMODEL);
		GameRegistry.registerBlock(Blocks.laser, BlockData.NAME_LASER);
		GameRegistry.registerBlock(Blocks.portableChest, ItemPortableChest.class, BlockData.NAME_PORTABLE_CHEST);
		GameRegistry.registerBlock(Blocks.fabricator, BlockData.NAME_FABRICATOR);
		GameRegistry.registerBlock(Blocks.tank, BlockData.NAME_TANK);

		GameRegistry.registerBlock(Blocks.betterTorch, BlockData.NAME_BETTER_TORCH);
	}

	public static void addRecipes() {
		Blocks.colouredGlass.initRecipes();
		Blocks.oreAFM.initRecipes();
	}

}