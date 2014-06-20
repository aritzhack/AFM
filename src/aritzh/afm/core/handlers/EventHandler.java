package aritzh.afm.core.handlers;

import aritzh.afm.core.Version;
import cpw.mods.fml.common.network.IConnectionHandler;
import cpw.mods.fml.common.network.Player;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetLoginHandler;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatMessageComponent;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

/**
 * EventHandler
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EventHandler implements IConnectionHandler {

    @ForgeSubscribe
    public void onPlayerInteractEvent(final PlayerInteractEvent event) {
        if (!event.entityPlayer.worldObj.isRemote) {
            event.entityPlayer.sendChatToPlayer(new ChatMessageComponent().func_111079_a(event.action.name()));
        }
    }

    @Override
    public void playerLoggedIn(final Player player, final NetHandler netHandler, final INetworkManager manager) {
        if (player instanceof EntityPlayer) {
            Version.sendChatToPlayer((EntityPlayer) player);
        }
    }

    @Override
    public String connectionReceived(final NetLoginHandler netHandler, final INetworkManager manager) {
        return null;
    }

    @Override
    public void connectionOpened(final NetHandler netClientHandler, final String server, final int port, final INetworkManager manager) {
    }

    @Override
    public void connectionOpened(final NetHandler netClientHandler, final MinecraftServer server, final INetworkManager manager) {
    }

    @Override
    public void connectionClosed(final INetworkManager manager) {
    }

    @Override
    public void clientLoggedIn(final NetHandler clientHandler, final INetworkManager manager, final Packet1Login login) {
    }
}
