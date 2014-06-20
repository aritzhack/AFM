package aritzh.afm.data;

import net.minecraftforge.common.Configuration;

/**
 * ItemData
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemData {

    private static final int ID_DEFAULT_QUARTZ = 600;
    private static final int ID_DEFAULT_SCREWDRIVER = 601;

    public static int ID_QUARTZ = ItemData.ID_DEFAULT_QUARTZ;
    public static int ID_SCREWDRIVER = ItemData.ID_DEFAULT_SCREWDRIVER;

    public static final String NAME_QUARTZ = "AFMQuartz";
    public static final String NAME_SCREWDRIVER = "AFMScrewdriver";

    public static void loadConfig(final Configuration config) {
        ItemData.ID_QUARTZ = config.getItem("Items", "quartzID", ItemData.ID_DEFAULT_QUARTZ).getInt();
        ItemData.ID_SCREWDRIVER = config.getItem("Items", "screwdriverID", ItemData.ID_DEFAULT_SCREWDRIVER).getInt();
    }
}
