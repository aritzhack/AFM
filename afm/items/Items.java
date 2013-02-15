package afm.items;

import net.minecraft.item.ItemReed;
import afm.core.AFMLogger;

public class Items {

	public static ItemQuartz quartz;
	public static ItemReed diamBlockSpawner;

	public static void init() {
		AFMLogger.log("Registering and initializing items");

		Items.quartz = new ItemQuartz();

	}
}
