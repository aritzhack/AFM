package afm.core;

import java.util.logging.Level;
import java.util.logging.Logger;

import afm.core.util.UtilAFM;
import afm.data.Config;
import cpw.mods.fml.common.FMLLog;

/**
 * AFMLogger
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class AFMLogger {

	private static final Logger logger = Logger.getLogger("AFM");
	private static final Level defaultLevel = Level.INFO;

	public static void init() {
		AFMLogger.logger.setParent(FMLLog.getLogger());
	}

	public static void log(Level level, String message) {
		AFMLogger.logger.log(level, message);
	}

	public static void log(Level l, String message, Throwable thrown) {
		AFMLogger.logger.log(AFMLogger.defaultLevel, message, thrown);
	}

	public static void log(Level level, String message, Object... params) {
		AFMLogger.log(level, String.format(message, params));
	}

	public static void log(String message) {
		AFMLogger.log(AFMLogger.defaultLevel, message);
	}

	public static void log(String message, Object... params) {
		AFMLogger.log(AFMLogger.defaultLevel, String.format(message, params));
	}

	public static void log(String message, Throwable thrown) {
		AFMLogger.log(AFMLogger.defaultLevel, message, thrown);
	}

	public static void log(Object o) {
		AFMLogger.log(String.valueOf(o));
	}

	public static void debug(String s) {
		if (Config.debug) {
			AFMLogger.log(s);
		}
	}

	public static void localize(String s) {
		AFMLogger.localize(AFMLogger.defaultLevel, s);
	}

	public static void localize(Level l, String s) {
		AFMLogger.log(l, UtilAFM.localize(s));
	}

	public static void localize(String s, Object... args) {
		AFMLogger.localize(AFMLogger.defaultLevel, s, args);
	}

	public static void localize(Level l, String s, Object... args) {
		AFMLogger.log(l, UtilAFM.localize(s, args));
	}

}
