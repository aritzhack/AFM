package afm.items;

import afm.core.AFMLogger;

/**
 * Items
 *
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class Items {

	public static ItemQuartz quartz;

	public static void init() {

		AFMLogger.log("Initializing items");

		Items.quartz = new ItemQuartz();
	}
}
