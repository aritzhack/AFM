package afm.i18n;

import afm.core.Properties;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Localization {
	
	public static final String[] locales = {
		"es_ES",
		//"en_US",
	};
	
	public static void loadLocales(){
		for(String locale : locales){
			LanguageRegistry.instance().loadLocalization(Properties.LANG_DIR + locale + ".lang", locale, true);
		}
	}

}
