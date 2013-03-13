package afm.data;

import net.minecraftforge.common.Configuration;

/**
 * GUIData
 *
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class GUIData {
	private static final String DIR = Properties.RES_DIR + "/mods/afm/textures/gui/";

	public static final String BG_TESTCHEST = GUIData.DIR + "testchest.png";
	public static final String BG_SHAREDCRAFTING = GUIData.DIR + "crafting.png";
	public static final String BG_FABRICATOR = GUIData.DIR + "fabricator.png";

	// GUI IDs
	public static final int ID_TESTCHEST = 0;
	public static final int ID_SHAREDWORKBENCH = 1;
	public static final int ID_FABRICATOR = 2;

	public static void loadConfig(Configuration config) {

	}
}
