package afm.core;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;

import net.minecraftforge.common.ForgeVersion;
import afm.data.Strings;
import cpw.mods.fml.common.Loader;

/**
 * Version
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Version {

	private enum State {
		UPTODATE, OUTDATED, ERRORED, UNINITIALIZED
	}

	// Versions the mod was compiled with
	public static final String MOD_VERSION = "%VERSION%";
	public static final String C_MC_VERSION = "%MC_VERSION%";
	public static final String C_FORGE_VERSION = "%FORGE_VERSION%";

	// Versions the mod is running with
	public static String mcVersion;
	public static String FORGE_VERSION;

	public static String recommendedAfmVersion;

	public static State modState = State.UNINITIALIZED;

	private static final String REMOTE_VERSION_FILE = "https://raw.github.com/aritzhack/AFM/master/version.properties";

	public static void checkVersion() {
		// Version.checkForgeVersion();

		Version.checkVersionState();
	}

	private static void checkForgeVersion() {
		int builtWithBuildVersion = Version.getBuildNumber(Version.C_FORGE_VERSION);
		Version.FORGE_VERSION = ForgeVersion.getVersion();

		if (builtWithBuildVersion == -1) {
			AFMLogger.localize(Strings.ERROR_FORGE);
			return;
		}
		if (ForgeVersion.buildVersion < builtWithBuildVersion) {
			AFMLogger.localize(Strings.OUTDATED_FORGE, Version.C_FORGE_VERSION, Version.FORGE_VERSION);
		} else {
		}
	}

	private static void checkVersionState() {
		Version.mcVersion = Loader.instance().getMCVersionString().split(" ")[1];

		Version.getRecommendedVersion();

		int compBuild = Version.getBuildNumber(Version.MOD_VERSION);
		int recBuild = Version.getBuildNumber(Version.recommendedAfmVersion);

		if (Version.modState == State.ERRORED || compBuild == -1 || recBuild == -1) {
			AFMLogger.localize(Level.WARNING, Strings.ERROR_MOD, Version.mcVersion);
		} else if (compBuild < recBuild) {
			Version.modState = State.OUTDATED;
			AFMLogger.localize(Strings.OUTDATED_MOD, Version.recommendedAfmVersion, Version.mcVersion);
		} else {
			Version.modState = State.UPTODATE;
			AFMLogger.localize(Strings.UPTODATE_MOD);
		}
	}

	private static void getRecommendedVersion() {
		try {
			Properties p = new Properties();
			p.load(new URL(Version.REMOTE_VERSION_FILE).openStream());
			if (p.containsKey(Version.mcVersion)) {
				Version.recommendedAfmVersion = p.getProperty(Version.mcVersion);
				return; // Just in case...
			} else {
				Version.modState = State.ERRORED;
			}
		} catch (IOException ioe) {
			Version.modState = State.ERRORED;
		}
	}

	private static int getBuildNumber(String verString) {
		int lastIndex = verString.lastIndexOf(".");
		if (lastIndex == -1) return -1;
		return Integer.valueOf(verString.substring(lastIndex + 1));
	}

}
