package aritzh.afm.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import aritzh.afm.core.AFMLogger;

/**
 * CommandKillAll
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CommandKillAll {

    public static void handle(final EntityPlayer player, final String[] args) {
        String entityType = null;
        if (args.length >= 2) {
            AFMLogger.debug("Input: " + args[1]);
            entityType = args[1];
        }

        final World w = player.worldObj;
        if (w == null) return;
        final List<Entity> unload = new ArrayList<Entity>();
        try {
            for (int x = 0; x < w.loadedEntityList.size(); x++) {
                final Entity curr = (Entity) w.loadedEntityList.get(x);
                if (entityType == null || EntityList.getEntityString(curr).equals(entityType)) {
                    unload.add(curr);
                }
            }
            w.unloadEntities(unload);
        } catch (final Exception e) {
        }

    }

    public static String getUsage() {
        return "/afm killAll (entityType)";
    }

    public static List<String> getTabCompletion(final ICommandSender sender, final String[] args) {
        return Arrays.asList("pig", "cow");
    }

}