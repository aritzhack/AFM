package aritzh.afm.items;

import aritzh.afm.core.AFMLogger;

/**
 * Items
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Items {

    public static ItemQuartz quartz;

    public static void init() {

        AFMLogger.debug("Initializing items");

        Items.quartz = new ItemQuartz();
    }
}
