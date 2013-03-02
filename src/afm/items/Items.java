package afm.items;

import afm.core.AFMLogger;

public class Items {

	public static ItemQuartz quartz;

	public static void init() {

		AFMLogger.log("Initializing items");

		Items.quartz = new ItemQuartz();
	}
}
