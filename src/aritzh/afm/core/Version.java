package aritzh.afm.core;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.ForgeVersion;
import aritzh.afm.core.util.UtilAFM;
import aritzh.afm.data.Config;
import aritzh.afm.data.Strings;
import cpw.mods.fml.common.Loader;

/**
 * Version
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Version implements Runnable {

	public enum State {
		UPTODATE, OUTDATED, ERRORED, UNINITIALIZED
	}

	// The player. This is set before the thread starts
	private EntityPlayer player;

	// Versions the mod was compiled with
	public static final String MOD_VERSION = "%VERSION%";
	public static final String C_MC_VERSION = "%MC_VERSION%";
	public static final String C_FORGE_VERSION = "%FORGE_VERSION%";

	// Versions the mod is running with
	public String mcVersion;
	public String FORGE_VERSION;

	public String recommendedAfmVersion;
	public String recommendedAfmVersionURL;

	public State modState = State.UNINITIALIZED;

	private final String REMOTE_VERSION_FILE = "https://raw.github.com/aritzhack/AFM/master/version.properties";

	public String checkVersion() {
		// Version.checkForgeVersion();

		return this.checkModVersionState();
	}

	@SuppressWarnings("unused")
	private void checkForgeVersion() {
		int builtWithBuildVersion = this.getBuildNumber(Version.C_FORGE_VERSION);
		this.FORGE_VERSION = ForgeVersion.getVersion();

		if (builtWithBuildVersion == -1) {
			AFMLogger.localize(Strings.ERROR_FORGE);
			return;
		}
		if (ForgeVersion.buildVersion < builtWithBuildVersion) {
			AFMLogger.localize(Strings.OUTDATED_FORGE, Version.C_FORGE_VERSION, this.FORGE_VERSION);
		} else {
		}
	}

	private String checkModVersionState() {
		this.mcVersion = Loader.instance().getMCVersionString().split(" ")[1];

		this.getRecommendedVersion();

		int compBuild = this.getBuildNumber(Version.MOD_VERSION);
		int recBuild = this.getBuildNumber(this.recommendedAfmVersion);
		if (compBuild == -1 || recBuild == -1) {
			this.modState = State.ERRORED;
		}
		if (this.modState == State.ERRORED) {
			AFMLogger.log(Level.WARNING, UtilAFM.localize(Strings.ERROR_MOD, this.mcVersion));
		} else if (compBuild <= recBuild) {
			this.modState = State.OUTDATED;
			String s = UtilAFM.localize(Strings.OUTDATED_MOD, Version.MOD_VERSION, this.recommendedAfmVersion, this.mcVersion, this.recommendedAfmVersionURL);
			AFMLogger.log(s);
			return "\u00a72" + s;
		} else {
			this.modState = State.UPTODATE;
			AFMLogger.localize(Strings.UPTODATE_MOD);
		}
		return "";
	}

	private void getRecommendedVersion() {
		try {
			Properties p = new Properties();
			p.load(new URL(this.REMOTE_VERSION_FILE).openStream());
			if (p.containsKey(this.mcVersion)) {
				String[] split = p.getProperty(this.mcVersion).split(":");
				this.recommendedAfmVersion = split[0];
				this.recommendedAfmVersionURL = split[1];
				return; // Just in case...
			} else {
				this.modState = State.ERRORED;
			}
		} catch (IOException ioe) {
			this.modState = State.ERRORED;
		}
	}

	private int getBuildNumber(String verString) {
		int lastIndex = verString.lastIndexOf(".");
		if (lastIndex == -1) return -1;
		return Integer.valueOf(verString.substring(lastIndex + 1));
	}

	public Version(EntityPlayer player) {
		this.player = player;
	}

	public static void sendChatToPlayer(EntityPlayer player) {
		new Thread(new Version(player)).start();
	}

	@Override
	public void run() {
		String s = this.checkVersion();
		if (Config.displayVersionMessageInChat && !s.equals("")) {
			this.player.sendChatToPlayer(s);
		}
	}

}
