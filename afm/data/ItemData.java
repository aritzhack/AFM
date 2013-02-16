package afm.data;

import net.minecraftforge.common.Configuration;

public class ItemData {

	public static final String TEXTURE = Properties.RES_DIR + "items.png";
	public static final int TEXTUREINDEX_QUARTZ = 0;

	private static final int ID_DEFAULT_QUARTZ = 600;

	public static int ID_QUARTZ = ItemData.ID_DEFAULT_QUARTZ;

	public static String NAME_QUARTZ = "quartz";

	public static void loadConfig(Configuration config) {
		ItemData.ID_QUARTZ = config.getItem("Items", "quartzID", ItemData.ID_DEFAULT_QUARTZ).getInt();
	}
}
