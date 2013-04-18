package aritzh.afm.i18n;

import aritzh.afm.data.Config;
import cpw.mods.fml.common.registry.LanguageRegistry;

/**
 * Localization
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Localization {

	private static final String[] locales = { "es_ES", "en_US", };

	public static void loadLocales() {
		for (String locale : Localization.locales) {
			LanguageRegistry.instance().loadLocalization(Config.LANG_DIR + locale + ".lang", locale, false);
		}
	}

}
