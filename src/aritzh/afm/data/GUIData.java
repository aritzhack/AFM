package aritzh.afm.data;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Configuration;

/**
 * GUIData
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GUIData {
	private static final String GUI_DIR = "textures/gui/";

	public static final ResourceLocation BG_TESTCHEST = new ResourceLocation(Config.MOD_ID.toLowerCase(), GUI_DIR + "testchest.png");
	public static final ResourceLocation BG_SHAREDCRAFTING = new ResourceLocation(Config.MOD_ID.toLowerCase(), GUIData.GUI_DIR + "crafting.png");
	public static final ResourceLocation BG_FABRICATOR = new ResourceLocation(Config.MOD_ID.toLowerCase(), GUIData.GUI_DIR + "fabricator.png");

	// GUI IDs
	public static final int ID_TESTCHEST = 0;
	public static final int ID_SHAREDWORKBENCH = 1;
	public static final int ID_FABRICATOR = 2;

	public static void loadConfig(Configuration config) {

	}
}
