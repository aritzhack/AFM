package afm.i18n;

import afm.data.Properties;
import cpw.mods.fml.common.registry.LanguageRegistry;

/**
 * Localization
 *
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class Localization {

	private static final String[] locales = {"es_ES", "en_US",};

	public static void loadLocales() {
		for (String locale : Localization.locales) {
			LanguageRegistry.instance().loadLocalization(Properties.LANG_DIR + locale + ".lang", locale, false);
		}
	}

}