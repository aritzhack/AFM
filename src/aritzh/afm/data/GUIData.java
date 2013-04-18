package aritzh.afm.data;

import net.minecraftforge.common.Configuration;

/**
 * GUIData
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GUIData {
	private static final String GUI_DIR = Config.TEX_DIR + "gui/";

	public static final String BG_TESTCHEST = GUIData.GUI_DIR + "testchest.png";
	public static final String BG_SHAREDCRAFTING = GUIData.GUI_DIR + "crafting.png";
	public static final String BG_FABRICATOR = GUIData.GUI_DIR + "fabricator.png";

	// GUI IDs
	public static final int ID_TESTCHEST = 0;
	public static final int ID_SHAREDWORKBENCH = 1;
	public static final int ID_FABRICATOR = 2;

	public static void loadConfig(Configuration config) {

	}
}
