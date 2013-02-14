package afm.blocks;

import afm.core.AFMLogger;
import afm.items.ColouredGlassItem;
import afm.items.TintedGlassItem;
import afm.wip.blocks.BlockFabricator;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Blocks {

	public static ColouredGlass colouredGlass;
	public static TintedGlass tintedGlass;
	public static OreAFM oreBlock;
	public static DayDetector dayDetector;
	public static BlockSharedCraftingTable sharedCrafting;
	public static BlockTestModel blockTestModel;
	public static BlockFabricator blockFabricator;
	public static BlockLaser blockLaser;

	public static void init() {
		
		AFMLogger.log("Registering and initializing blocks");

		colouredGlass = new ColouredGlass();
		tintedGlass = new TintedGlass();
		oreBlock = new OreAFM();
		dayDetector = new DayDetector();
		sharedCrafting = new BlockSharedCraftingTable();
		blockTestModel = new BlockTestModel();
		blockFabricator = new BlockFabricator();
		blockLaser = new BlockLaser();
		
		LanguageRegistry.addName(oreBlock, "AFM ore");
		LanguageRegistry.addName(dayDetector, "Day Detector");
		LanguageRegistry.addName(sharedCrafting, "Shared Crafting Table");
		LanguageRegistry.addName(blockTestModel, "Test Model");
		LanguageRegistry.addName(blockFabricator, "fabricator");
		LanguageRegistry.addName(blockLaser, "Laser");
		// LanguageRegistry.addName(new ItemStack(), "");

		GameRegistry.registerBlock(colouredGlass, ColouredGlassItem.class,
				"colouredGlass");
		GameRegistry.registerBlock(tintedGlass, TintedGlassItem.class,
				"tintedGlass");
		GameRegistry.registerBlock(oreBlock, "tempOre");
		GameRegistry.registerBlock(dayDetector, "dayDetector");
		GameRegistry.registerBlock(sharedCrafting, "sharedCrafting");
		GameRegistry.registerBlock(blockTestModel, "testModel");
		GameRegistry.registerBlock(blockFabricator, "blockFabricator");
		GameRegistry.registerBlock(blockLaser, "blockLaser");




		colouredGlass.init();
		oreBlock.init();

	}

}