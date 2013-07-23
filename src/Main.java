import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.minecraft.launchwrapper.Launch;

public class Main {

    public static void main(final String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        String username = args[0];
        final String password = args[1];
        final Method m = Start.class.getDeclaredMethod("getSession", String.class, String.class);
        ;
        m.setAccessible(true);
        final String[] ret = (String[]) m.invoke(null, username, password);
        if (ret != null) {
            String session;
            System.out.println("Username: " + (username = ret[0]));
            System.out.println("Session: " + (session = ret[1]));
            Launch.main(new String[] { "--version 1.6", "--tweakClass", "cpw.mods.fml.common.launcher.FMLTweaker", "--username", username, "--session", session });
        } else {
            Launch.main(new String[] { "--version 1.6", "--tweakClass", "cpw.mods.fml.common.launcher.FMLTweaker", "--username", username });
        }
    }
}
