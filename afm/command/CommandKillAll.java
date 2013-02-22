package afm.command;

import afm.core.util.UtilAFM;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;

public class CommandKillAll {

    public static void handle(ICommandSender sender, String[] args) {
        String entityType = null;
        if (args.length >= 2) {
            entityType = args[1];
        }
        if (sender.getCommandSenderName().equalsIgnoreCase("Rcon")) {
            sender.sendChatToPlayer("Cannot be used from server console");
        }
        EntityPlayerMP player = UtilAFM.getPlayer(sender.getCommandSenderName());
        if (player == null)
            return;
        World w = player.worldObj;
        if (w == null)
            return;

        for (Object next : w.loadedEntityList) {
            if (next == null || !(next instanceof Entity)) continue;
            Entity entity = (Entity) next;
            if (entityType == null) {
                if (!(entity instanceof EntityPlayer)) {
                    player.onKillEntity(null);
                    // TODO Doesn't
                }
            } else if (entity.getEntityName().equals(entityType)) {
                entity.onKillEntity(null);
            }
        }

    }

    public static String getUsage() {
        return "/afm killAll (entityType)";
    }

    public static List getTabCompletion(ICommandSender sender, String[] args) {
        return Arrays.asList("pig", "cow");
    }

}